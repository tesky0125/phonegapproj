site.main.model = site.main.model || {};
site.main.model.ScrollShow = (function(){
	var list = function(count,callback){
		var cnt=count,cb=callback;
		if(arguments.length===1){
			cnt=null;
			cb=arguments[0];
		}
		var data={};
		var jsonData = JSON.stringify(data);
		$.ajax({
			type: "POST",
			url: site.config.backend_url+"scroll_show",
			data: "type=list&count="+cnt+"&data="+jsonData,
			success: function(response){
				cb(response);
			}
		});
	};
	var get = function(id,callback){
		var data={
			id:id,
			title:"",
			content:"",
			image:"",
			date:"2015-05-10 23:59:59"
		};
		var jsonData = JSON.stringify(data);
		$.ajax({
			type: "POST",
			url: site.config.backend_url+"scroll_show",
			data: "type=get&data="+jsonData,
			success: function(response){
				callback(response);
			}
		});
	};
	var insert = function(id,title,cotent,image,callback){
		var data={
			id:id,
			title:title,
			content:cotent,
			image:image,
			date:new Date().format("yyyy-MM-dd HH:mm:ss")
		};
		var jsonData = JSON.stringify(data);
		$.ajax({
			type: "POST",
			url: site.config.backend_url+"scroll_show",
			data: "type=insert&data="+jsonData,
			success: function(response){
				callback(response);
			}
		});
	};
	var update = function(id,title,cotent,image,callback){
		var data={
			id:id,
			title:title,
			content:cotent,
			image:image,
			date:new Date().format("yyyy-MM-dd HH:mm:ss")
		};
		var jsonData = JSON.stringify(data);
		$.ajax({
			type: "POST",
			url: site.config.backend_url+"scroll_show",
			data: "type=update&data="+jsonData,
			success: function(response){
				callback(response);
			}
		});
	};
	var _delete = function(id,callback){
		var data={
			id:id,
			title:"",
			content:"",
			image:"",
			date:"2015-05-10 23:59:59"
		};
		var jsonData = JSON.stringify(data);
		$.ajax({
			type: "POST",
			url: site.config.backend_url+"scroll_show",
			data: "type=delete&data="+jsonData,
			success: function(response){
				callback(response);
			}
		});
	};

	return {
		list:list,
		get:get,
		insert:insert,
		update:update,
		delete:_delete
	};
}());