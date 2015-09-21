site.main.ctrller = site.main.ctrller || {};
site.main.ctrller.RecruitInfo = (function(context,$){
	var _this = this,
		_context = context,
		_container = {};

	var initUI = function($container,callback){
		site.util.TemplLoader.read_template("templ/RecruitInfo.html",function(htmlDom){
			$container.append($(htmlDom));
			callback();
		});
	};
	var initJQMap = function($container){
	    _container={
            $recruitInfoTitle:$container.find("#recruitInfoTitle span"),
            $recruitHeadLine:$container.find("#recruitHeadLine span"),
            $recruitInfoList:$container.find(".article_content")
        };
	};
    var initData = function(){
        site.main.model.RecruitInfo.list(function(data){
            var recruitInfoList = JSON.parse(data);
            var recruitInfoHeadline = recruitInfoList[0];
            _container.$recruitHeadLine.text(recruitInfoHeadline.content);

            for(var i=1;i<9;i++){
                var recruitInfo = recruitInfoList[i];
                var item=''+
                    '<li>'+
                        '<div><a href="javascript:void(0);" title="" target="_self"><span>'+recruitInfo.content+'</span></a>'+
                        '<span>'+recruitInfo.date_time+'</span></div>'+
                    '</li>';
                _container.$recruitInfoList.append(item);
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