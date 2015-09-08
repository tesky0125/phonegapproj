site.main.ctrller = site.main.ctrller || {};
site.main.ctrller.SiteUtil = (function(context,$){
	var _this = this,
		_context = context,
		_container = {};

	var initUI = function($container,callback){
		site.util.TemplLoader.read_template("templ/SiteUtil.html",function(htmlDom){
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
	
	var events={};
	events.OnLinkClick = function(e){};

	return {
		initModule:initModule,
		OnLinkClick:events.OnLinkClick
	};
}(window,jQuery));
