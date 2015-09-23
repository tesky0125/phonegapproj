var HtmlDom = require('htmldom');
var uglifyUnderscore = require('./uglify-underscore');
var _ = require('underscore');

function uglify(html, filepath) {
  if (filepath) {
    console.log('Running htmlmin ' + filepath);
  }
  var htmldom = new HtmlDom(html, [/<%([\s\S]+?)%>/g]);
  var code = htmldom.stringify({
    booleanAttributes: true,
    removeAttributeQuotes: true,
    templateType: ['text/template', 'text/lizard-template'],
    onServerCode: uglifyUnderscore
  });

  code = code.replace(/\s*<%([\s\S]+?)%>\s*/g, function(match) {
    return match.trim();
  });
  
  return code;
}


function precompile(html, filepath) {
  console.log('Running precompile html ' + filepath);
  html = uglify(html);

  var result = '';

  try {
    result = _.template(html).source;
  } catch (e) {
    throw new Error(filepath + ' 预编译underscore模板出错:\n' + e);
  }

  return ' ' + result;
}

exports.uglify = uglify;

exports.precompile = precompile;