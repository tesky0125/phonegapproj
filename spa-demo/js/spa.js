/*
jslint	browser:true, continue:true,
devel:true, indent:2, maxerr:50,
newcap:true, momen:true, plusplus:true,
regexp:true, sloppy:true, vars:false,
white:true
*/
/*
global $,spa
*/
var spa = (function($) {
	var configMap={},
		initModule;

	initModule = function($container){
		spa.shell.initModule($container);
		return true;
	};

	return {
		initModule:initModule
	};
}(jQuery));