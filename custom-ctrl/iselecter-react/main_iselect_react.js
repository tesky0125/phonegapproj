import React from 'react';
import ReactDom from 'react-dom';
import ISelecter from './iselect_react';
import _ from 'underscore';

var data = ['100元', '200元', '300元', '400元', '500元', '600元'];
var options = {
	width:500/*width*/,//unit:px
	height:100,
	displayNum:5,
	data:data,
	index:2
};
var onselectchange = function(index, data){
	console.log('index:'+index);
	console.log('data:'+data);
}

ReactDom.render(
	<ISelecter container="#selecter" options={options} onselectchange={onselectchange}/>,
	document.getElementById("selecter")
);
