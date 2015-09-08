site.util = site.util || {};
site.util.TemplLoader = (function(context,$){
	var read_template = function(templ_path,callback){
		var xhttp;
		if (window.XMLHttpRequest){
			xhttp=new XMLHttpRequest();
		}else {
			xhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xhttp.onreadystatechange=function(){
			if(xhttp.readyState===4 && xhttp.status===200){
				callback(xhttp.response);
			}
		};
		xhttp.open("GET",templ_path,true);
		xhttp.send(null);
	};

	var html_to_dom = function(html){
		var parser,xmlDoc;
		if (window.DOMParser){
			parser=new DOMParser();
			xmlDoc=parser.parseFromString(html,"text/xml");
		}else{
			xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
			xmlDoc.async=false;
			xmlDoc.loadXML(html); 
		} 
		return xmlDoc;
	};

	return {
		read_template:read_template,
		html_to_dom:html_to_dom
	};

}(window,jQuery));