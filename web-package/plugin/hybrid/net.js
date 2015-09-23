var fs = require('file-system');
var path = require('path');
var HtmlDom = require('htmldom');
var util = require('utils-extend');
var base64Img = require('base64-img');
var replace = require('../../vendor/replace');
// 根据base64生成图片名字
var base64Name = (function() {
  var i = 0;

  return function() {
    i++;
    return 'base64-' + i;
  }
})();

// 通过htmldom分析xml文件
function getConfigGen(dirpath) {
  var ConfigProfile = fs.readFileSync(path.join(dirpath, 'ConfigProfile.xml'));
  var htmldom = new HtmlDom(ConfigProfile);

  return htmldom.$;
}

module.exports = function(grunt, config, webappPath) {
  var $configGen = getConfigGen(webappPath);
  var localRoute = {};
  var hybridPath = path.join(webappPath, 'dest', config.hybridChannel);
  var webresourcePath = path.join(webappPath, 'dest', config.hybridChannel, 'webresource');
  var layoutName = 'Views/Shared/_Layout.cshtml';
  var hybridRoot = getHost() + '/webapp/' + config.channel + '/dest/' + config.hybridChannel + '/';
  var h5Root = getHost() + '/webapp/' + config.channel + '/dest/' + config.webresourceSrc + '/';
  var isDebug = grunt.option('debug');
  var inctrip = grunt.option('inctrip');

  function getHost() {
    return 'http://' + config.host;
  }

  function removeServerCode(s) {
    var couples, pre = '', i, j, post;

    do {
       i = s.indexOf('@{');
       if (i < 0) break;

       couples = 1;
       pre += s.substr(0, i);
       s = s.substr(i + 2);

       while (s && couples) {

           i = s.indexOf('{');
           j = s.indexOf('}');

           if (i < 0) i = Infinity;
           if (j < 0) j = Infinity;

           couples += (i < j) ? 1 : -1;
           s = s.substr(Math.min(i, j) + 1);
       }
    } while (1);

    return pre + s;
  }

  function addLocalRoute(contents, filepath) {
    var reg = /"?url_schema"?\s*:\s*('[^']*'|"[^"]*"|\[[^\]]*\])/;
    var match = contents.match(reg);
    var urlSchema = '';

    if (match) {
      urlSchema = match[1];
    }
    if (!urlSchema) {
      return grunt.log.error(filepath + '的url_schema为空，请配置');
    }

    eval('urlSchema=' + urlSchema);

    if (!Array.isArray(urlSchema)) {
      urlSchema = [urlSchema];
    }

    filepath = filepath.replace(/\.[\w-]+$/, '');

    var relative = util.path.unixifyPath(path.relative(
      webappPath,
      filepath
    ));

    urlSchema.forEach(function(item) {
      var src = relative.replace('dest\/', '');

      if (isDebug || inctrip) {
        src = hybridRoot + src + '.js';
      }

      localRoute[item] = src;
    });
  }

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

  // hybrid替换任务
  config.replace.hybrid = config.replace.hybrid.concat([
    {
      filter: ['views/**/*'],
      replace: [
        {
          // 替换confGen
          match: /@ConfigurationManager\.AppSettings\["([\w-]+)"\]/g,
          replacement: function(match, $1) {
            var value = $configGen(config.buEnv + ' ' + $1).html();

            if (!value) {
              throw new Error('configGen没有获取到' + config.buEnv + '环境' + $1 + '值，请尝试更新htmldom, 或者检查configGen配置, 错误文件位置: ' + this.filepath);
            }

            return value;
          }
        },
        {
          //72.png?v=1.0?v=1.0
          // 去掉版本号, css不去掉版本号
          match: /(\?v=[\w-\.]+)+/g,
          replacement: ''
        },
        {
          // 删除服务器配置
          match: /@using\s+system\.configuration;?/ig,
          replacement: ''
        },
        {
          // 替换base64
          match: /data:image\/\w+;base64[^'")]+/g,
          replacement: function(match) {
            if (isDebug) return match;
            var filepath = this.filepath;
            var newpath = path.join(hybridPath, 'pic');
            var imagepath = base64Img.imgSync(match, newpath, base64Name());

            return 'pic/' + path.basename(imagepath);
          }
        },
        {
          match: new RegExp('\/webapp\/' + config.channel + '\/dest\/' + config.webresourceSrc, 'g'),
          replacement: function(match) {
            if (isDebug || inctrip) {
              return getHost() + '/webapp/' + config.channel + '/dest/' + config.webresourceSrc;
            } else {
              return 'webresource';
            }
          }
        }
      ]
    }
  ]);

  config.replace.hybrid.forEach(function(item) {
    item.filterFn = fs.fileMatch(item.filter, true)
  });

  function processCsthml(contents, filepath, relative) {
    // 删除服务器代码
    contents = removeServerCode(contents);
    contents = replace(contents, path.join('views', relative), config.replace.hybrid);
    contents = contents.replace(/@\w+(\[[^\]]*\]|\([^\)]*\)|[\.\w])*/g, '');
    var htmldom = new HtmlDom(contents, [
      /<%([\s\S]+?)%>/g
    ]);
    var $ = htmldom.$;

    if (/_layout.cshtml/i.test(filepath)) {
      // 清空html属性
      $('html')[0].attributes = {};
      // 设置title
      $('title').html(config.channel);
      $('[name="appBaseUrl"]').attr('content', '/webapp/' + config.channel + '/');
      // 隐藏h5头部
      $('#headerview').css('display', 'none');
      var buConfigSrc = config.buConfig;
      if (isDebug) {
        buConfigSrc = h5Root + buConfigSrc;
      } else {
        buConfigSrc = 'webresource/' + buConfigSrc;
      }
      var localroute = './lizardlocalroute.js';
      if (isDebug || inctrip) {
        localroute =  hybridRoot + 'lizardlocalroute.js';
      }
      
      // 替换框架, pdconfig路径
      $('script[pdconfig]').attr('src', function(index, oldValue) {
        return oldValue.replace(/(http:)?\/\/webresource.c-ctrip.com\/code/, '../lizard/webresource/code')
      }).attr('pdconfig', buConfigSrc);

      $('link[href]').attr('href', function(index, oldValue) {
        return oldValue.replace(/(http:)?\/\/webresource.c-ctrip.com\/styles/, '../lizard/webresource/styles');
      });

      // 增加localroute
      $('head').append('<script src="' + localroute + '"></script>');
    }

    if (isDebug) {
      contents = htmldom.html();
    } else {
      contents = htmldom.stringify({
        removeAttributeQuotes: true,
        templateType: ['text/template', 'text/lizard-template', 'text/Lizard-template']
      });
    }

    if (/_layout.cshtml/i.test(filepath)) {
      filepath = path.join(hybridPath, 'index.html');
    } else {
      addLocalRoute(contents, filepath);
      // 变成cmd
      contents = 'define(function(){return' + JSON.stringify(contents) + '});';
      filepath = path.join(path.join(hybridPath, 'views'), relative).replace(/\.[\w-]+$/, '.js');
    }

    return {
      contents: contents,
      filepath: filepath
    };
  }

  grunt.log.writeln('Running 模板复制');
  fs.copySync(
    path.join(webappPath, 'dest/views'),
    path.join(hybridPath, 'views'), {
      process: processCsthml,
      filter: (function() {
        var defaults = [
          '**/*',
          '!**/.\\w+',
          '!_ViewStart.cshtml',
          '!**/*Error.cshtml',
          '!Web.config'
        ];

        config.viewsExclude.forEach(function(item) {
          defaults.push('!' + item);
        });

        return defaults;
      })()
  });

  grunt.log.writeln('Running 写入lizardlocalroute.js');

  if (config.defaultView) {
    localRoute.defaultView = config.defaultView;
  } else if (localRoute['index']) {
    localRoute.defaultView = 'index';
  } else if (localRoute['/index']) {
    localRoute.defaultView = '/index';
  }
  fs.writeFileSync(
    path.join(hybridPath, 'lizardlocalroute.js'),
    'window.LizardLocalroute=' + JSON.stringify(localRoute)
  );
  // 删除shared文件夹
  if (fs.existsSync(path.join(hybridPath, 'views/shared'))) {
    fs.rmdirSync(path.join(hybridPath, 'views/shared'));
  }
}