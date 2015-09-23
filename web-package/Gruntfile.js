var path = require('path');
var util = require('utils-extend');
var fs = require('file-system');
var requirejs = require('requirejs');
var CssDom = require('cssdom');
var request = require('ajax-request');
var base64Img = require('base64-img');
var uglifyHtml = require('./vendor/html');//htmlmin ?
var replace = require('./vendor/replace');//replace custom define

var config, webappPath, hybridPath, isDebug, inctrip;

//获取walletConfig中配置的非ctrl的module paths
function getModulePath(filepath) {
  var buConfig = fs.readFileSync(filepath, { encoding: 'utf8' });
  // 自动分析模块路径 
  buConfig.replace(/paths\s*:\s*({[\s\S]*?})/, function(match, $1) {//paths:{...}
    $1 = 'return ' + $1.replace(/baseUrl\s*\+\s*/g, '').trim();//去除baseUrl+
    $1 = new Function($1)();//eval json

    util.extend(config.paths, $1);
  });
}

//根据gruntCfg中配置的isSimple(ctrl)和walletConfig的配置(非ctrl)自动构造所有modules
function getSimpleModules() {
  var modules = [];
  var mainJs = config.buConfig.replace(/\.js$/, '');//walletConfig
  var paths = config.paths;//modules
  if (!util.isArray(config.isSimple)) {//simple新模式下必然成立，请查看221行
    throw new Error('请把isSimple配置为controller文件夹，比如: "controller",或者["ctr1", "ctrl2"]');
  }

  //获取walletConfig中配置的modules，可去除html
  function getPath(includeHtml) {//get modules path
    var ret = [];
    var extname;

    for (var i in paths) {
      extname = path.extname(paths[i]);
      if (extname == '.html') {
        if (includeHtml) {
          i = 'text!' + i; 
        } else {
          continue;
        }
      }
      ret.push(i);
    }

    return ret;
  }

  //递归获取ctrl目录下的modules
  function recurse(ctr, name) {
    fs.recurseSync(ctr, '**/*.js', function(filepath, filename, relative) {
      if (!filename) return;
      relative = path.join(name, path.relative(ctr, filepath)).replace(/.js$/, '');

      modules.push({
        name: util.path.unixifyPath(relative),
        exclude: [mainJs]
      });
    });
  }

  //插入{"name": "walletConfig","include": [...]}
  modules.push({
    name: mainJs,
    include: getPath(false)
  });

  config.isSimple.forEach(function(item) {
    var ctrlSrc = path.join(webappPath, config.webresourceSrc, item);
    recurse(ctrlSrc, item);
  });

  // 自定义模块 除ctrl以外的模块
  config.modules.forEach(function(item) {
    if (item.create) {
      modules.push(item);
    }
  });

  // 排除只压缩不合并的模块
  modules[0].include = modules[0].include.filter(function(item) {
    return config.buConfigExclude.indexOf(item) === -1;
  });
  
  // 排除controller
  modules = modules.filter(function(item) {
    return config.controllerExclude.indexOf(item.name) === -1;
  });

  return modules;
}

// 静态资源路径替换 配置webresource路径
function destRes(html, isHybrid) {
  var resource = '/webapp/' + config.channel + '/dest/' + config.webresourceSrc;

  html = html.replace(
    new RegExp('\/webapp\/' + config.channel + '\/' + config.webresourceSrc, 'ig'),
    function() {
      if (isHybrid) {
        return 'webresource';//hybrid指wallet.zip
      } else {
        if (isDebug) {
          return 'http://' + config.host + resource;
        } else {
          return resource;//???
        }
      }
    }
  );
  return html;
}

// 根据base64生成图片名字，用于替换css中的base64图片
var base64Name = (function() {
  var i = 0;

  return function() {
    i++;
    return 'base64-' + i;
  }
})();

function isExcludeImage(url) {
  var isExclude = false;
  var resourceExclude = config.resourceExclude;
  var length = resourceExclude.length;

  if (url.indexOf('http') !== 0) {
    url = 'http:' + url;
  }

  while(length--) {
    if (url.indexOf(resourceExclude[length]) == 0) {
      return true;
    }
  }

  return isExclude;
}

module.exports = function(grunt) {
  grunt.config('version', grunt.template.today('yyyymmdd_H_MM'));
  // 初始化参数配置 walletConfig/gruntCfg，构造paths/modules
  grunt.registerTask('init2.0', function() {
    var pkg = grunt.file.readJSON('package.json');
    webappPath = grunt.option('path');
    isDebug = grunt.option('debug');
    inctrip = grunt.option('inctrip');//?
    config = require(path.join(webappPath, 'gruntCfg.js'));
    // Default options extend
    config = util.extend({
      buConfigExclude: [],
      controllerExclude: [],
      paths: {},//walletConfig-paths
      skipModuleInsertion: true,
      hybridIsSimple: true,//
      frameworkExclude: [],
      hybridExclude: [],//NO USE
      viewsExclude: [],//hybrid views
      jsExclude: [],//hybrid webres
      resourceExclude: [],//css http://**
      base64: [],
      modules: [],//
      replace: {
        web: [],
        hybrid: []
      },
      // http抓取到的图片
      _httpImages: []
    }, config);

    if (config.channels) {
      config.channels.forEach(function(item) {
        getModulePath(path.join(webappPath, config.webresourceSrc, item.name, config.buConfig));
      });
    } else {
      //获取walletConfig中配置的modules
      getModulePath(path.join(webappPath, config.webresourceSrc, config.buConfig));
    }
    
    // 排除框架的包(模块) 定义在lizard package.json中
    pkg.frameworkInclude.forEach(function(item) {
      if (!config.paths[item]) {
        config.paths[item] = 'empty:';
      }
    });

    // 排除自定义框架包(模块)
    config.frameworkExclude.forEach(function(item) {
      config.paths[item] = 'empty:';
    });

    // 处理require.text.js 添加require.text包
    config.paths.text = path.join(__dirname, 'text');

    // html模板预编译 //TODO
    if (config.htmlPrecompile) {
      config.htmlPrecompileFilter = fs.fileMatch(config.htmlPrecompile);
    } else {
      config.htmlPrecompileFilter = fs.fileMatch('');
    }

    if (!config.hybridChannel) {
      config.hybridChannel = config.channel;
    }

    // hybrid包直连情况，使用prdhost
    if (inctrip) {
      config.host = config.prdhost;
    }

    hybridPath = path.join(webappPath, 'dest', config.hybridChannel);//wallet/dest/wallet

    // 新simple模式，配置controller
    if (util.isString(config.isSimple)) {
      config.isSimple = [config.isSimple];
    }

    // 新simple模式自动构造modules,gruntCfg中无须配置
    config.modules = getSimpleModules();

    // 删除被合并的模板
    grunt.config('combinedTemplate', []);
  });

  // 清空dest或者多余的hybrid静态文件
  grunt.registerTask('clear2.0', function() {
    grunt.log.writeln('Running 删除dest目录');
    var dest = path.join(webappPath, 'dest');
    if (fs.existsSync(dest)) {
      fs.chmodSync(dest, 511);
      fs.rmdirSync(dest);
    }
  });

  // imagemin任务
  grunt.registerTask('imagemin2.0', function(env) {
    if (isDebug || grunt.option('imagemin') == false) return;
    if (!grunt.task.exists('imagemin')) {
      grunt.loadNpmTasks('grunt-contrib-imagemin');
    }
    var files;
    if (env === 'web') {
      files = [{
        expand: true,
        cwd: path.join(webappPath, 'dest', config.webresourceSrc),
        src: ['**/*.{png,jpg,gif}'],
        dest: path.join(webappPath, 'dest', config.webresourceSrc)  
      }];
    } else {
      files = [{
        expand: true,
        cwd: path.join(webappPath, 'dest', config.hybridChannel),
        src: ['**/*.{png,jpg,gif}'],
        dest: path.join(webappPath, 'dest', config.hybridChannel)
      }];
    }

    grunt.config('imagemin', {
      dynamic: {
        options: {
          optimizationLevel: 6
        },
        files: files
      }
    });
    grunt.task.run(['imagemin']);
  });

  // r.js
  grunt.registerTask('requirejs2.0', function() {
    grunt.log.writeln('Running requirejs打包任务');
    var done = this.async();
    var compressOpts = {
      drop_console: true
    };
    var dir = path.join(webappPath, 'dest', config.webresourceSrc);//webapp/wallet/dest/res
    var rjsOptions = {
      baseUrl: path.join(webappPath, config.webresourceSrc),//webapp/wallet/res
      dir: dir,
      optimize: isDebug ? 'none' : 'uglify2',
      paths: config.paths,//define in require
      uglify2: {
        mangle: {
          except: ['$super']//TODO
        },
        compress: compressOpts
      },
      removeCombined: true,
      // 不是amd模块，只压缩
      skipModuleInsertion: false,
      onBuildWrite: function (moduleName, filepath, contents) {
        var reg = new RegExp('[\"\']' + moduleName + '[\"\']\,');

        //把打包的模块变成匿名模块
        config.modules.forEach(function(item) {
          if (moduleName === item.name) {
            contents = contents.replace(reg, '');
          }
        });

        // text模块已存在框架里面
        if (moduleName == 'text') {
          return '';
        }

        // 压缩html模板
        if (moduleName.indexOf('text!') == 0) {
          filepath = path.join(
            rjsOptions.baseUrl,
            rjsOptions.paths[moduleName.replace(/^text!/, '')]
          );//webapp/wallet/res/tpl/index.html
          var html = fs.readFileSync(filepath, { encoding: 'utf8' });
          var relative = path.relative(path.join(webappPath, config.webresourceSrc), filepath);//tpl/index.html

          if (config.htmlPrecompileFilter(relative)) {//TODO
            html = uglifyHtml.precompile(html, filepath);
          } else {
            html = uglifyHtml.uglify(html, filepath);
            html = JSON.stringify(html);
          }

          return 'define("' + 
            moduleName + 
            '", [], function(){return' + html + '});';
        }
        return contents;
      },
      modules: util.extend([], config.modules),
      optimizeCss: false
    };

    requirejs.optimize(rjsOptions, function(buildResponse) {
      done();
    }, function(err) {
      grunt.fatal(err);
    });
  });
  
  // 默认是.net模板引擎，可以自定义
  grunt.registerTask('template2.0', function(env) {
    var plugin = config.plugin || 'net';
    var src = path.join('plugin', env, plugin);

    require('./' + src)(grunt, config, webappPath);
  });

  // 删除被合并的文件
  grunt.registerTask('removeCombined', function() {
    if (isDebug) return;
    var combinedTemplate = grunt.config('combinedTemplate');

    combinedTemplate = util.unique(combinedTemplate);

    for (var i = 0; i < combinedTemplate.length; i++) {
      var relative = path.relative(webappPath, combinedTemplate[i]);
      var filepath = path.join(webappPath, 'dest', relative);

      fs.unlinkSync(filepath);
    }
  });

  // web静态资源处理
  grunt.registerTask('webres', function() {
    var src = path.join(webappPath, 'dest', config.webresourceSrc);//webapp/wallet/dest/res

    fs.copySync(src, src, {
      noProcess: '**/*.{jpg,png,gif,ttf}',
      process: function(contents, filepath, relative) {
        var extname = path.extname(relative);
        // 自定义替换
       contents = replace(contents, path.join(config.webresourceSrc, relative), config.replace.web);
       // css文件处理
        if (extname === '.css') {
          var css = new CssDom(contents, filepath);

          contents = css.stringify();
        }

        // 全局js路径处理 //TODO
        if (relative === config.buConfig) {//walletConfig.js
          var resource = '/webapp/' + config.channel + '/dest/' + config.webresourceSrc;

          contents = contents.replace(
              /Lizard.appBaseUrl\s*\+\s*['"]([\w-]+)\/['"]/,
              function(match, $1) {
                if (isDebug || inctrip) {
                  return '"http://' + config.host + resource + '/"';
                } else {
                  return '"' + resource + '/"';
                }
            });
        }

       return contents;
      }
    });
  });


  // hybrid静态资源处理
  grunt.registerTask('hybridres', function(env) {
    if (isDebug || inctrip) return;
    var src = path.join(webappPath, 'dest', config.webresourceSrc);//webapp/wallet/dest/res
    var dest = path.join(hybridPath, 'webresource');//webapp/wallet/dest/wallet/webresource
    // 重复的base64不打
    var baseArr = [];

    function csscode(contents, filepath, relative) {
      var css = new CssDom(contents, filepath);
      var newpath = path.join(hybridPath, 'webresource', relative);

      function change(value) {
        value = value.replace(/url\(('[^']+'|"[^"]+"|[^)]+)\)/, function(match, url) {
          url = url.replace(/^['"]|['"]$/g, '');
          // http图片分析
          if (/^(https?:)?\/\//.test(url) && !isExcludeImage(url)) {
            config._httpImages.push(url);
            url = path.join(path.relative(relative, 'pic'), url.replace(/^(https?:)?\/\/[^\/]+\//, ''));
            url = util.path.unixifyPath(url);
          }
          // base64强制转化
          if (/^data:image/.test(url)) {
            var name, exist = false;

            for (var i = 0; i < baseArr.length; i++) {
              if (baseArr[i].data === url) {
                exist = true;
                name = baseArr[i].format;
                break;
              }
            }

            if (!exist) {
              name = base64Name();
              var item = {
                data: url,
                path: path.dirname(newpath),
                name: name,
                format: name + '.' + url.match(/^data:image\/(\w+)/)[1]
              };
              baseArr.push(item);
              name = item.format;
            }

            url = name;
          }

          return 'url(' + url + ')';
        });

        return value;
      }
      var ps = ['background', 'background-image', 'border-image', '-webkit-border-image'];
      ps.forEach(function(item) {
        var obj = {};
        obj[item] = change;
        css.property(item, obj);
      });

      return css.stringify();
    }

    function filter() {
      var defaults = [
        '**/*',
        '!**/.\\w+',
        '!**/*.html',
        '!text.js',
        '!build.txt'
      ];
      config.jsExclude.forEach(function(item) {
        defaults.push('!' + item);
      });
      return defaults;
    }

    fs.copySync(src, dest, {
      noProcess: '**/*.{jpg,png,gif,ttf}',
      filter: filter(),
      process: function(contents, filepath, relative) {
        var extname = path.extname(relative);

        // 自定义替换
          contents = replace(contents,path.join(config.webresourceSrc, relative), config.replace.hybrid);

        // css文件处理
        if (extname === '.css') {
          contents = csscode.apply(null, arguments);
        }

        // 全局js路径处理 //TODO
        if (relative === config.buConfig) {//walletConfig.js
          contents = contents.replace(/paths\s*:\s*\{([^\}]+)\}/,
            function(match, $1) {
              $1 = $1.replace(/:\s*\w+\s*\+\s*(?:'([^']+)'|"([^"]+)")/g, function(match, $1, $2) {
                $1 = $1 || $2;
                return ":'webresource/" + $1 + "'";
              });
              return 'paths:{' + $1+ '}'
            }
          );
        }

        // 路径替换
        contents = destRes(contents, true);
        return contents;
      }
    });

    for (var i = 0; i < baseArr.length; i++) {
      var item = baseArr[i];
      base64Img.imgSync(item.data, item.path, item.name);
    }
  });

  // 下载三方图片资源
  grunt.registerTask('image_download', function() {
    if (!config._httpImages.length || isDebug) return;
    var done = this.async();
    var count = 0;
    var rootPath;
    
    rootPath = path.join(hybridPath, 'pic');

    config._httpImages.forEach(function(picUrl) {
      if (picUrl.indexOf('http') != 0) {
        picUrl = 'http:' + picUrl;
      }
      grunt.log.writeln('Running fetch ' + picUrl);
      request.download({
        url: picUrl,
        rootPath: rootPath,
        ignore: true
      }, function(err, res, body, filepath) {
        count++;
        if (err) return grunt.log.error('Fetch pic ' + picUrl + err);

        if (count == config._httpImages.length) {
          done();
        }
      });
    });
  });

  // 生成zip包
  grunt.registerTask('zip2.0', function() {
    if (isDebug) return;
    var filePackage = require('file-package');
    var done = this.async();
    var zip = config.zip;
    var filter = inctrip ? 'index.html' : null;

    if (util.path.isAbsolute(zip)) {
       zip = path.join(zip, config.hybridChannel + '.zip');
    } else {
       zip = path.join(webappPath, config.zip, config.hybridChannel + '.zip');
    }

    filePackage(
      path.join(webappPath, 'dest', config.hybridChannel), 
      zip, {
      level: 9,
      filter: filter,
      packageRoot: config.hybridChannel,
      done: function(size) {
        grunt.log.writeln('Created ' + zip + ' (' + size + ' bytes)')
        done();
      }
    });
  });

  /**
   * @description
   * web包
   * grunt web2.0 --path=c:\\path
   * grunt web2.0 --path=c:\\path --debug --imagemin=false
   */
  grunt.registerTask('web2.0', ['init2.0', 'clear2.0', 'template2.0:web', 'removeCombined', 'requirejs2.0:web', 'webres','imagemin2.0:web']);

  /**
   * @description
   * web包
   * grunt web2.0 --path=c:\\path
   * grunt package2.0 --path=c:\\path --debug
   * grunt package2.0 --path=c:\\path --weinre
   */
   grunt.registerTask('package2.0', ['web2.0', 'template2.0:hybrid', 'hybridres', 'image_download', 'imagemin2.0:hybrid', 'zip2.0']);

   grunt.registerTask('test2.0', ['init2.0', 'template2.0:hybrid', 'hybridres']);
}