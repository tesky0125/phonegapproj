site.main.ctrller = site.main.ctrller || {};
site.main.ctrller.ScrollShow = (function(context,$){
	var _this = this,
		_context = context,
		_container = {};

	var initUI = function($container,callback){
		site.util.TemplLoader.read_template("templ/ScrollShow.html",function(htmlDom){
			$container.append($(htmlDom));
			callback();
		});
	};
	var initJQMap = function($container){
        _container = {
            $bigImage:$container.find(".imgbig"),
            $indicator1:$container.find(".picshow_change a:eq(0) img"),
            $indicator2:$container.find(".picshow_change a:eq(1) img"),
            $indicator3:$container.find(".picshow_change a:eq(2) img"),
            $indicator4:$container.find(".picshow_change a:eq(3) img"),
            $indicator5:$container.find(".picshow_change a:eq(4) img")
        };
	};
    var initData= function () {
        site.main.model.ScrollShow.list(function(data) {
            var scrollShowList = JSON.parse(data);
            var pic_arr = [];
            for(var i=0;i<5;i++){
                pic_arr[i]=[scrollShowList[i].title,site.config.media_url+scrollShowList[i].image,"javascript:void(0);"];
            }
            site.common.pic_show.set_data(pic_arr);
            site.common.pic_show.show_img(1);
        });
    };
    var initEvents= function () {
        _container.$indicator1.off('click').click(onIndicatorClick);
        _container.$indicator2.off('click').click(onIndicatorClick);
        _container.$indicator3.off('click').click(onIndicatorClick);
        _container.$indicator4.off('click').click(onIndicatorClick);
        _container.$indicator5.off('click').click(onIndicatorClick);
    }
	var initModule = function($container){
		initUI($container,function(){
			initJQMap($container);
            initData();
            initEvents();

		});
	};

    var onIndicatorClick = function(e){
        var targetId = e.currentTarget.parentNode.id;
        var index = parseInt(targetId.replace("thumbnail",""));

        site.common.pic_show.show_img(index);
    }

	return {
		initModule:initModule
	};
}(window,jQuery));
