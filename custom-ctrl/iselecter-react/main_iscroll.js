// import React from 'react';
// import ReactDom from 'react-dom';

import IScroll from './iscroll';
import $ from 'jquery';

// ReactDom.render(
// 	<ISelecter />,
// 	document.getElementById('example')
// );

$(function() {
    var myScroll = new IScroll('.wrapper', {
        scrollX: true,
        scrollY: false,
        mouseWheel: true
    });

    $(document).click(function(e) {
        myScroll.scrollBy(-100, 0, 1000);
    });
});
document.addEventListener('touchmove', function(e) {
    e.preventDefault();
}, false);