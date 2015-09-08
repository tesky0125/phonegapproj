site = site || {};
site.common = (function(context,$){
    var scroll = function(marqueebox,lh,speed,delay) {
        var pause=false;
        var timer;
        var obj=document.getElementById(marqueebox);
        obj.innerHTML+=obj.innerHTML;
        obj.style.marginTop=0;
        obj.onmouseover=function(){pause=true;}
        obj.onmouseout=function(){pause=false;}

        function start(){
            timer=setInterval(scrolling,speed);
            if(!pause) obj.style.marginTop=parseInt(obj.style.marginTop)-1+"px";
        }

        function scrolling(){
            if(parseInt(obj.style.marginTop)%lh!=0){
                obj.style.marginTop=parseInt(obj.style.marginTop)-1+"px";
                if(Math.abs(parseInt(obj.style.marginTop))>=obj.scrollHeight) obj.style.marginTop=0;
            }else{
                clearInterval(timer);
                setTimeout(start,delay);
            }
        }

        setTimeout(start,delay);
    };

    var tab_switch = function(tabname, pagename, id) {
        var i = 1;
        while (true) {
            var obj_h1 = document.getElementById(tabname + "_" + i);
            i++;
            if (obj_h1) {
                if (obj_h1.className == "sel") {
                    obj_h1.className = "";
                }
            } else {
                i = 1;
                break;
            }
        }
        while (true) {
            var obj_h2 = document.getElementById(pagename + "_" + i);
            i++;
            if (obj_h2) {
                if (obj_h2.style.display == "") {
                    obj_h2.style.display = "none";
                }
            } else {
                break;
            }
        }
        var obj = document.getElementById(tabname + "_" + id);
        if (obj) {
            obj.className = "sel";
        }
        var obj2 = document.getElementById(pagename + "_" + id);
        obj2.style.display = "";
    };

    var pic_show = (function () {
        var img = [];
        var url = [];
        var alt = [];
        var counts;
        var timer;

        var set_data = function(pic_arr) {
            var i;
            counts = pic_arr.length;

            for (i = 0; i < counts; i++) {
                alt[i+1] = pic_arr[i][0];
                img[i+1] = pic_arr[i][1];
                url[i+1] = pic_arr[i][2]
            }
        }

        var show_img = function(cur){
            clearInterval(timer);

            document.getElementById("url").href=url[cur];
            document.getElementById("pic").src=img[cur];
            document.getElementById("pic").alt=alt[cur];
            document.getElementById("title").innerHTML=alt[cur];
            document.getElementById("title").href=url[cur];

            //thumbnail
            for ( var i = 1; i <= counts; i++) {
                document.getElementById("thumbnail" + i).className = 'axx';
            }
            document.getElementById("thumbnail" + cur).className = 'bxx';

            cur++;
            if (cur > counts) {
                cur = 1;
            }

            timer = setTimeout(function () {
                show_img(cur);
            }, 3000);
        }


        return {
            set_data:set_data,
            show_img:show_img
        };
    }());


    return {
        scroll:scroll,
        tab_switch:tab_switch,
        pic_show:pic_show
    };
}(window,jQuery));
