site.main.ctrller = site.main.ctrller || {};
site.main.ctrller.Weather = (function(context,$){
	var _this = this,
		_context = context,
		_container = {};

	var initUI = function($container,callback){
		site.util.TemplLoader.read_template("templ/Weather.html",function(htmlDom){
			$container.append($(htmlDom));
			callback();
		});
	};
	var initJQMap = function($container){
	    _container={
            $info_text:$container.find(".info_text a")
        };
	};
    var initData = function () {
        _container.$info_text.text(new Date().format("今天是：yyyy年MM月dd日 星期dddd"));
    }
	var initModule = function($container){
		initUI($container,function(){
			initJQMap($container);
            initData();
		});
	};

	return {
		initModule:initModule
	};
}(window,jQuery));
