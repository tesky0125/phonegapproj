site.main.ctrller = site.main.ctrller || {};
site.main.ctrller.EmployGuide = (function(context,$){
	var _this = this,
		_context = context,
		_container = {};

	var initUI = function($container,callback){
		site.util.TemplLoader.read_template("templ/EmployGuide.html",function(htmlDom){
			$container.append($(htmlDom));
			callback();
		});
	};
	var initJQMap = function($container){
        _container={
            $employGuideTitle:$container.find("#employGuideTitle span"),
            $employGuideList:$container.find(".article_content")
        };
	};
    var initData = function(){
        site.main.model.EmployGuide.list(function(data){
            var employGuideList = JSON.parse(data);

            for(var i=0;i<9;i++){
                var employGuide = employGuideList[i];
                var item=''+
                    '<li>'+
                    '<div><a href="javascript:void(0);" title="" target="_self"><span>'+employGuide.content+'</span></a>'+
                    '<span>'+employGuide.date_time+'</span></div>'+
                    '</li>';
                _container.$employGuideList.append(item);
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