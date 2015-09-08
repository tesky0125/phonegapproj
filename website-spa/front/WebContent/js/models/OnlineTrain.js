site.main.model = site.main.model || {};
site.main.model.OnlineTrain = (function(){
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
            url: site.config.backend_url+"online_train",
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
            date:"2015-05-10 23:59:59"
        };
        var jsonData = JSON.stringify(data);
        $.ajax({
            type: "POST",
            url: site.config.backend_url+"online_train",
            data: "type=get&data="+jsonData,
            success: function(response){
                callback(response);
            }
        });
    };
    var insert = function(id,title,cotent,callback){
        var data={
            id:id,
            title:title,
            content:cotent,
            date:new Date().format("yyyy-MM-dd HH:mm:ss")
        };
        var jsonData = JSON.stringify(data);
        $.ajax({
            type: "POST",
            url: site.config.backend_url+"online_train",
            data: "type=insert&data="+jsonData,
            success: function(response){
                callback(response);
            }
        });
    };
    var update = function(id,title,cotent,callback){
        var data={
            id:id,
            title:title,
            content:cotent,
            date:new Date().format("yyyy-MM-dd HH:mm:ss")
        };
        var jsonData = JSON.stringify(data);
        $.ajax({
            type: "POST",
            url: site.config.backend_url+"online_train",
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
            date:"2015-05-10 23:59:59"
        };
        var jsonData = JSON.stringify(data);
        $.ajax({
            type: "POST",
            url: site.config.backend_url+"online_train",
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