var output = /<%([-=])([\s\S]+?)%>/;
var evaluate = /<%([\s\S]+?)%>/;
var strip = require('strip-comment');
var uglifyJs = require('uglify-js');

module.exports = function(code) {
  var matched = code.match(output);
  var start = '<%';
  var end = '%>';

  if (matched) {
    start += matched[1];
    return start + matched[2].trim() + end;
  } else if (matched = code.match(evaluate)) {
    matched = matched[1];
  } else {
    return code;
  }

  try {
    matched = uglifyJs.minify(matched, {
      fromString: true,
      mangle: false,
      compress: true,
      output: {
        semicolons: true
      }
    });
    return start + matched.code + end;
  } catch (e) {} 

  // 散乱的js代码
  matched = strip.js(matched);
  matched = matched
           .replace(/if\s+\(/g, 'if(')
           .replace(/\s*\{\s*/g, '{')
           .replace(/\s*\}\s*/g, '}')
           .replace(/\s*([!=]?[>=<]=?)\s*/g, '$1')
           .replace(/\s*(&&)\s*/g, '$1');
  return start + matched.trim() + end;
}