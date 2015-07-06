site.main.ctrller = site.main.ctrller || {};
site.main.ctrller.MainMenu = (function(context,$){
	var _this = this,
		_context = context,
		_container = {};

	var initUI = function($container,callback){
		site.util.TemplLoader.read_template("templ/MainMenu.html",function(htmlDom){
			$container.append($(htmlDom));
			callback();
		});
	};
	var initJQMap = function($container){
        _container = {
            $menu1:$container.find("#mainMenuList a:eq(0) span"),
            $menu2:$container.find("#mainMenuList a:eq(1) span"),
            $menu3:$container.find("#mainMenuList a:eq(2) span"),
            $menu4:$container.find("#mainMenuList a:eq(3) span"),
            $menu5:$container.find("#mainMenuList a:eq(4) span")
        };
	};
    var initEvents = function(){
        _container.$menu1.off("click").click(function (e) {
            menu1Click();
        });
        _container.$menu2.off("click").click(function (e) {
            menu2Click();
        });
        _container.$menu3.off("click").click(function (e) {
            menu3Click();
        });
        _container.$menu4.off("click").click(function (e) {
            menu4Click();
        });
        _container.$menu5.off("click").click(function (e) {
            menu5Click();
        });
    };
	var initModule = function($container){
		initUI($container,function(){
			initJQMap($container);
            initEvents();
		});
	};
    var menu1Click = function () {
        console.log('menu1 click');
    };
    var menu2Click = function () {
        console.log('menu2 click');
    };
    var menu3Click = function () {
        console.log('menu3 click');
    };
    var menu4Click = function () {
        console.log('menu4 click');
    };
    var menu5Click = function () {
        console.log('menu5 click');
    };

	return {
		initModule:initModule
	};
}(window,jQuery));