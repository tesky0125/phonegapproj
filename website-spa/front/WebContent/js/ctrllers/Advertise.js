site.main.ctrller = site.main.ctrller || {};
site.main.ctrller.Advertise = (function(context,$){
	var _this = this,
		_context = context,
		_container = {};

	var initUI = function($container,callback){
		site.util.TemplLoader.read_template("templ/Advertise.html",function(htmlDom){
			$container.append($(htmlDom));
			callback();
		});
	};
	var initJQMap = function($container){
		_container={
			$advertiseTitle:$container.find("#advertiseTitle span"),
			$advertiseList:$container.find(".article_content")
		};
	};
	var initData = function(){
		site.main.model.Advertise.list(function(data){
			var advertiseList = JSON.parse(data);

			for(var i=0;i<9;i++){
				var advertise = advertiseList[i];
				var item=''+
					'<li>'+
					'<div><a href="javascript:void(0);" title="" target="_self"><span>'+advertise.content+'</span></a>'+
					'<span>'+advertise.date_time+'</span></div>'+
					'</li>';
				_container.$advertiseList.append(item);
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