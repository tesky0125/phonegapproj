site={};
site.main = (function(context,$){
	var _this = this,
		_context = context,
		_container = {};

	var initJQMap = function($container){
		_container={
			$container:$container,
			$weather:$container.find(".util_weather"),
			$site_util:$container.find(".site_util"),
			$login_entry:$container.find(".main_login"),
			$main_logo:$container.find(".main_logo"),
			$main_menu:$container.find(".main_menu"),
			$dynamic_mesg:$container.find(".main_banner_notice"),
			$global_search:$container.find(".main_banner_search"),
			$scroll_news:$container.find(".main_content_top_scroll_news"),
			$recruit_info:$container.find(".main_content_top_recruit_info"),
			$employ_guide:$container.find(".main_content_top_employ_guide"),
			$online_train:$container.find(".main_content_bottom_online_train"),
			$life_service:$container.find(".main_content_bottom_life_service"),
			$advertise:$container.find(".main_content_bottom_advertise"),
			$outer_link:$container.find(".main_link"),
			$about_us:$container.find(".main_about_us")
		};
	};
	var initModule = function($container){
		initJQMap($container);

		site.main.ctrller.Weather.initModule(_container.$weather);
		site.main.ctrller.SiteUtil.initModule(_container.$site_util);
		site.main.ctrller.LoginEntry.initModule(_container.$login_entry);
		site.main.ctrller.LogoArea.initModule(_container.$main_logo);
		site.main.ctrller.MainMenu.initModule(_container.$main_menu);
		site.main.ctrller.DynamicMesg.initModule(_container.$dynamic_mesg);
		site.main.ctrller.GlobalSearch.initModule(_container.$global_search);
		site.main.ctrller.ScrollShow.initModule(_container.$scroll_news);
		site.main.ctrller.RecruitInfo.initModule(_container.$recruit_info);
		site.main.ctrller.EmployGuide.initModule(_container.$employ_guide);
		site.main.ctrller.OnlineTrain.initModule(_container.$online_train);
		site.main.ctrller.LifeService.initModule(_container.$life_service);
		site.main.ctrller.Advertise.initModule(_container.$advertise);
		site.main.ctrller.OuterLink.initModule(_container.$outer_link);
		site.main.ctrller.AboutUs.initModule(_container.$about_us);
		site.main.ctrller.FloatAd.initModule(_container.$container);
	};

	return {
		initModule:initModule
	};

}(window,jQuery));