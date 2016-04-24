var loader = (function() {
  var loader = {};
  var modules = {};
  var cache = {};

  var dummy = function() {return function() {};};
  var initModule = function(name, fn) {
    var module = {id: name, exports: {}};
    fn(module.exports, dummy(), module);
    var exports = cache[name] = module.exports;
    return exports;
  };

  loader.require = function(path) {
    if (cache.hasOwnProperty(path)) return cache[path];
    if (modules.hasOwnProperty(path)) return initModule(path, modules[path]);
    throw new Error('Cannot find module "' + path + '"');
  };

  loader.define = function(path, fn) {
    modules[path] = fn;
  };
  return loader;
})();

//example
loader.define('index',function(exports, require, module){
  var Index = function(){

  }
  module.exports = Index;
});

var Index = loader.require('index');