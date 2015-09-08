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
spa.shell=(function($){
	var configMap={
			anchor_schema_map:{
				chat:{opened:true,closed:true}
			},
			main_html:String()+
				'<div class="spa-shell-head">'+
				'	<div class="spa-shell-head-logo"></div>'+
				' 	<div class="spa-shell-head-acct"></div>'+
				' 	<div class="spa-shell-head-search"></div>'+
				'</div>'+
				'<div class="spa-shell-main">'+
				' 	<div class="spa-shell-main-nav"></div>'+
				' 	<div class="spa-shell-main-content"></div>'+
				'</div>'+
				'<div class="spa-shell-foot"></div>'+
				'<div class="spa-shell-chat"></div>'+
				'<div class="spa-shell-modal"></div>',
			chat_extend_time:250,
			chat_retract_time:300,
			chat_extend_height:450,
			chat_retract_height:15,
			chat_extended_title:"Click to retract",
			chat_retracted_title:"Click to extend",
		},
		stateMap={
			$container:null,
			anchor_map:{},
			is_chat_retracted:true
		},
		jqueryMap={},
		copyAnchorMap,
		changeAnchorPart,
		onHashChange,
		setJqueryMap,
		toggleChat,
		onClickChat,
		initModule;
	
	copyAnchorMap = function(){
		return $.extend(true,{},stateMap.anchor_map);
	};

	changeAnchorPart = function(arg_map){
		var anchor_map_revise = copyAnchorMap(),
			bool_return=false,
			key_name,
			key_name_dep;

		KEYVAL:
		for(key_name in arg_map){
			if(arg_map.hasOwnProperty(key_name)){
				if(key_name.indexOf("_") === 0){
					continue KEYVAL;
				}
				anchor_map_revise[key_name]=arg_map[key_name];
				key_name_dep="_"+key_name;
				if(arg_map[key_name_dep]){
					anchor_map_revise[key_name_dep]=arg_map[key_name_dep];
				}else{
					delete anchor_map_revise[key_name_dep];
					delete anchor_map_revise["_s"+key_name_dep];
				}
			}
		}

		try{
			$.uriAnchor.setAnchor(anchor_map_revise);
		}catch(error){
			$.uriAnchor.setAnchor(stateMap.anchor_map,null,true);
			bool_return=false;
		}

		return bool_return;
	};

	onHashChange = function(){
		var anchor_map_previous = copyAnchorMap(),
			anchor_map_proposed,
			_s_chat_previous,
			_s_chat_proposed,
			s_chat_proposed;

		try{
			anchor_map_proposed=$.uriAnchor.makeAnchorMap();
		}catch(error){
			$.uriAnchor.setAnchor(anchor_map_previous,null,true);	
		}
		stateMap.anchor_map  = anchor_map_proposed;

		_s_chat_previous = anchor_map_previous._s_chat;
		_s_chat_proposed = anchor_map_proposed._s_chat;

		if(!anchor_map_previous || _s_chat_previous !== _s_chat_proposed){
			s_chat_proposed = anchor_map_proposed.chat;
			switch(s_chat_proposed){
				case "opened":
					toggleChat(true);
					break;
				case "closed":
					toggleChat(false);
					break;
				default:
					toggleChat(false);
					delete anchor_map_proposed.chat;
					$.uriAnchor.setAnchor(anchor_map_proposed,null,true);
			}
		}
		return false;
	};

	setJqueryMap = function(){
		var $container = stateMap.$container;
		jqueryMap={
			$container:$container,
			$chat:$container.find(".spa-shell-chat")
		};
	};

	toggleChat = function(do_extend,callback){
		var px_chat_height = jqueryMap.$chat.height(),
			is_opened = px_chat_height === configMap.chat_extend_height,
			is_closed = px_chat_height === configMap.chat_retract_height,
			is_sliding = !is_opened && !is_closed;

		if(is_sliding) return false;
		if(do_extend){
			jqueryMap.$chat.animate({height:configMap.chat_extend_height},
				configMap.chat_extend_time,
				function(){
					jqueryMap.$chat.attr("title",configMap.chat_extended_title);
					stateMap.is_chat_retracted = false;
					if(callback){
						callback(jqueryMap.$chat);
					}
				});
			return true;
		}else{
			jqueryMap.$chat.animate({height:configMap.chat_retract_height},
				configMap.chat_retract_time,
				function(){
					jqueryMap.$chat.attr("title",configMap.chat_retracted_title);
					stateMap.is_chat_retracted = true;
					if(callback){
						callback(jqueryMap.$chat);
					}
				});
			return true;
		}
		return false;
	};

	onClickChat = function(e){
		changeAnchorPart({
			chat:(stateMap.is_chat_retracted?"opened":"closed")
		});
		return false;
	}

	initModule = function($container){
		stateMap.$container = $container;
		$container.html(configMap.main_html);
		setJqueryMap();

		stateMap.is_chat_retracted=true;
		jqueryMap.$chat.attr("title",configMap.chat_extended_title).click(onClickChat);

		$.uriAnchor.configModule({
			schema_map:configMap.anchor_schema_map
		});

		spa.chat.configModule({});
		spa.chat.initModule(jqueryMap.$chat);

		$(window).bind("hashchange",onHashChange).trigger("hashchange");
	};

	return {
		initModule:initModule
	};
}(jQuery));