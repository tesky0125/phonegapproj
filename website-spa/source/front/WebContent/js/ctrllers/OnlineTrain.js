site.main.ctrller = site.main.ctrller || {};
site.main.ctrller.OnlineTrain = (function(context,$){
	var _this = this,
		_context = context,
		_container = {};

	var initUI = function($container,callback){
		site.util.TemplLoader.read_template("templ/OnlineTrain.html",function(htmlDom){
			$container.append($(htmlDom));
			callback();
		});
	};
	var initJQMap = function($container){
		_container={
			$onlineTrainTitle:$container.find("#onlineTrainTitle span"),
			$onlineTrainList:$container.find(".article_content")
		};
	};
	var initData = function(){
		site.main.model.OnlineTrain.list(function(data){
			var onlineTrainList = JSON.parse(data);

			for(var i=0;i<9;i++){
				var onlineTrain = onlineTrainList[i];
				var item=''+
					'<li>'+
					'<div><a href="javascript:void(0);" title="" target="_self"><span>'+onlineTrain.content+'</span></a>'+
					'<span>'+onlineTrain.date_time+'</span></div>'+
					'</li>';
				_container.$onlineTrainList.append(item);
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