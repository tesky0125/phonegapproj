site.main.ctrller = site.main.ctrller || {};
site.main.ctrller.AboutUs = (function(context,$){
	var _this = this,
		_context = context,
		_container = {};

	var initUI = function($container,callback){
		site.util.TemplLoader.read_template("templ/AboutUs.html",function(htmlDom){
			$container.append($(htmlDom));
			callback();
		});
	};
	var initJQMap = function($container){
	
	};
	var initModule = function($container){
		initUI($container,function(){
			initJQMap($container);
		});
	};

	return {
		initModule:initModule
	};
}(window,jQuery));