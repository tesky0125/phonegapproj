site.main.ctrller = site.main.ctrller || {};
site.main.ctrller.DynamicMesg = (function(context,$){
	var _this = this,
		_context = context,
		_container = {};

	var initUI = function($container,callback){
		site.util.TemplLoader.read_template("templ/DynamicMesg.html",function(htmlDom){
			$container.append($(htmlDom));
			callback();
		});
	};
	var initJQMap = function($container){
		_container={
			$dynamicMesgList:$container.find("#announcediv")
		};
	};
	var initData = function(){
		site.main.model.DynamicMesg.list(function(data){
			var dynamicMesgList = JSON.parse(data);

			for(var i=0;i<5;i++){
				var dynamicMesg = dynamicMesgList[i];
				var item=''+
					'<a href="javascript:void(0);" title="" target="" style="font-size: small">'+dynamicMesg.content+'</a><br>';
				_container.$dynamicMesgList.append(item);
			}

		});
	};
	var initModule = function($container){
		initUI($container,function(){
			initJQMap($container);
			initData();
            site.common.scroll('announcediv',24,20,3000);
		});
	};

	return {
		initModule:initModule
	};
}(window,jQuery));