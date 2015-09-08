site.main.ctrller = site.main.ctrller || {};
site.main.ctrller.LoginEntry = (function(context,$){
	var _this = this,
		_context = context,
		_container = {};

	var initUI = function($container,callback){
		site.util.TemplLoader.read_template("templ/LoginEntry.html",function(htmlDom){
			$container.append($(htmlDom));
			callback();
		});
	};
	var initJQMap = function($container){
	    _container={
            $userLogin:$container.find("#userLogin span"),
            $userRegister:$container.find("#userRegister span")
        };
	};
    var initEvents = function () {
        _container.$userLogin.off("click").click(function (e) {
            userLoginClick();
        });
        _container.$userRegister.off("click").click(function (e) {
            userRegisterClick();
        });
    };
	var initModule = function($container){
		initUI($container,function(){
			initJQMap($container);
            initEvents();
		});
	};

    var userLoginClick = function(){
        console.log('login click');
    };
    var userRegisterClick = function(){
        console.log('register click');
    };

	return {
		initModule:initModule
	};
}(window,jQuery));