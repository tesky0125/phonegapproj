var fs = require('file-system');
var path = require('path');
var HtmlDom = require('htmldom');
var replace = require('../../vendor/replace');
var uglifyUnderscore = require('../../vendor/uglify-underscore');

module.exports = function(grunt, config, webappPath) {
  var isDebug = grunt.option('debug');

  // @RenderPage 语法替换
  function renderPage(content, filepath) {
    var reg = /@RenderPage\("([^"]+)"\)/g;

    function replace($1, $2, filepath) {
      filepath = path.join(path.dirname(filepath), $2);
      var body = fs.readFileSync(
        filepath,
        { encoding: 'utf8' }
      );

      var combinedTemplate = grunt.config('combinedTemplate')
      combinedTemplate.push(filepath);
      grunt.config('combinedTemplate', combinedTemplate);

      body = body.replace(/@using\s*System\.Configuration;?/, '');

      if (body.match(reg)) {
        body = body.replace(reg, function($1, $2) {
          return replace($1, $2, filepath);
        });
      }

      return body.trim();
    }

    return content.replace(reg, function($1, $2) {
      return replace($1, $2, filepath);
    });
  }

  function uglifyCshtml(contents, filepath) {
    console.log('Running htmlmin ' + filepath);
    contents = renderPage(contents, filepath);
    contents = contents.replace(
      /@using\s*System\.Configuration[^;]/,
      '@using System\.Configuration;'
    );
    // 删除.net注释 @* *@ 
    contents = contents.replace(/@\*[\s\S]*?\*@/g, '');

    var htmldom = new HtmlDom(contents, [
      /<%([\s\S]+?)%>/g, 
      /@\{[^\}]+\}/g,
      /@\w+(\[[^\]]*\]|\([^\)]*\)|[\.\w])*/g
    ]);
    // 修复lizard config js压缩bug
    var $ = htmldom.$;
    var lizarConfig = $('script[type=text/lizard-config]');
    lizarConfig.html('var _lizardConfig=' + lizarConfig.html());

    // 模块版本号
    var pdConfig = $('script[pdconfig]');
    var versionConfig = 'version:' + grunt.config('version');

    if (pdConfig.attr('lizardconfig')) {
      versionConfig = pdConfig.attr('lizardconfig') + ',' + versionConfig;
    }
    pdConfig.attr('lizardconfig', versionConfig);

    // 静态文件加版本号
    $('link[href]').attr('href', function(index, oldValue) {
      if (oldValue.indexOf('h5/common/main.css') !== -1 || !/\.css$/.test(oldValue)) {
        return oldValue;
      }
      return oldValue + '?v=' + grunt.config('version');
    });

    // 图片引用加版本号
    $('img[src]').attr('src', function(index, oldValue) {
      if (/\.(png|jpg|gif)$/.test(oldValue)) {
        return oldValue + '?v=' + grunt.config('version'); 
      } else {
        return oldValue;
      }
    });

    // weinre调试
    if (isDebug || grunt.option('weinre')) {
      $('head').append('<script src="http://svn.ui.sh.ctripcorp.com:8899/target/target-script-min.js#'+ config.host.split(':')[0] +'"></script>');
    }

    // 压缩模板
    if (isDebug) {
      contents = htmldom.html();
    } else {
      contents = htmldom.stringify({
        booleanAttributes: true,
        templateType: ['text/template', 'text/lizard-template', 'text/Lizard-template'],
        jsCodeType: ['text/lizard-config'],
        onServerCode: uglifyUnderscore
      });
    }

    // // 取出underscore模板之间的空格
    // contents = contents.replace(/\s*<%([\s\S]+?)%>\s*/g, function(match) {
    //   return match.trim();
    // });

    // 压缩lizard-config js bug修复
    contents = contents.replace('var _lizardConfig=', '');

    return contents;
  }

   // 框架替换
  config.replace.web = config.replace.web.concat([
    {
      filter: ['views/**/*.{cshtml,html}'],
      replace: [
        {
          // 路径dest替换
          match: new RegExp('\/' + config.channel + '\/' + config.webresourceSrc, 'ig'),
          replacement: function(match) {
            var src = config.webresourceSrc;
            return match.replace(src, 'dest/' + src);
          }
        }
      ]
    },
    {
      filter: ['views/_viewstart.cshtml'],
      replace: [
        {
          // viewstart
          match: /(layout[\s\S]*?)views\//i,
          replacement: '$1dest/views/'
        }
      ]
    }
  ]);

  config.replace.web.forEach(function(item) {
    item.filterFn = fs.fileMatch(item.filter, true)
  });

  // 复制并压缩模板 
  fs.copySync(
    path.join(webappPath, 'views'),
    path.join(webappPath, 'dest/views'), {
      process: function(contents, filepath, relative) {
        if (/web\.config$/i.test(filepath)) return contents;
        if (/\.(cshtml|html)$/.test(filepath)) {
          contents = uglifyCshtml(contents, filepath);
        }

        contents = replace(contents, path.join('views', relative), config.replace.web);
        // 乱码
        contents = '\uFEFF' + contents;

        return contents;
      }
    }
  );
}