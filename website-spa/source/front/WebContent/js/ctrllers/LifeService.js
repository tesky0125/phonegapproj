site.main.ctrller = site.main.ctrller || {};
site.main.ctrller.LifeService = (function(context,$){
	var _this = this,
		_context = context,
		_container = {};

	var initUI = function($container,callback){
		site.util.TemplLoader.read_template("templ/LifeService.html",function(htmlDom){
			$container.append($(htmlDom));
			callback();
		});
	};
	var initJQMap = function($container){
		_container={
			$lifeServiceTitle:$container.find("#lifeServiceTitle span"),
			$lifeServiceList:$container.find(".article_content")
		};
	};
	var initData = function(){
		site.main.model.LifeService.list(function(data){
			var lifeServiceList = JSON.parse(data);

			for(var i=0;i<9;i++){
				var lifeService = lifeServiceList[i];
				var item=''+
					'<li>'+
					'<div><a href="javascript:void(0);" title="" target="_self"><span>'+lifeService.content+'</span></a>'+
					'<span>'+lifeService.date_time+'</span></div>'+
					'</li>';
				_container.$lifeServiceList.append(item);
			}

		});
	};
	var initEvents = function(){

	};
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