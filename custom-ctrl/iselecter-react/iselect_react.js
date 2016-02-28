import React from 'react';
import IScroll from './iscroll';
import $ from 'jquery';
import _ from 'underscore';

export default class ISelecter extends React.Component {
    constructor() {
        super();
        this.state = {};
    }
    componentWillMount(){
    	var container = this.props.container;
    	var options = this.props.options;
    	this.onselectchange = this.props.onselectchange;
    	this.container = $(container);
		//default options
		_.extend(this, {
			width: 300, //unit:px
			height: 50,
			displayNum: 3,
			animatTime: 100,
			bounceTime: 100,
			itemFontSize: 15,
			highlightFontSize:30,
			data: [],
			index: 0
		}, options);
		this.initData();
    }
	initData (){
		this.innerData = _.extend([], this.data);
		//fake items
		this.fakeNum = this.displayNum - 1;
		this.fakeOffsetNum = this.fakeNum / 2;
		this.index = this.index + this.fakeOffsetNum;
		for (var i = 0; i < this.fakeOffsetNum; i++) {
			this.innerData.unshift('');
			this.innerData.push('');
		}
	}
    render() {
    	var list = this.innerData.map(function(item, index) {
    	    return ( 
        		<li key={index} data-index={index}>{item}</li>
    	    );
    	});
        return (
				<div className="wrapper">
					<div className="scroller">
						<ul className="list">
							{list}
						</ul>
					</div>
					<div className="mask"></div>
				</div>
		);
    }
    componentDidMount () {
    	this.initDom();
		this.initSize();
		this.initScroll();
		this.initIndex();
		this.resetCss();
    }
    initDom () {
    	//initial dom
		this.wrapper = this.container.find('.wrapper');
		this.scroller = this.wrapper.find('.scroller');
		this.list = this.scroller.find('ul');
		this.items = this.list.find('li');
    }
	initSize () {
		this.itemWidth = this.width / this.displayNum;
		this.itemNum = this.data.length;
		//override width and height
		this.wrapper.width(this.width);
		this.wrapper.height(this.height);
		this.scroller.width(this.itemWidth * (this.itemNum + this.fakeNum));
		this.list.height(this.height);
		this.items.height(this.height);
		this.items.width(this.itemWidth);
		this.items.css({
			'line-height': this.height + 'px'
		});
		//offset to middle
		this.scrollOffset = ((this.displayNum - 1) / 2) * (this.itemWidth);
	}
	initScroll () {
		//prevent default event
		this.container.on('touchmove', function(e) {
			e.preventDefault();
		});

		//initial iscroll
		this.scroll = new IScroll('.wrapper', {
			scrollX: true,
			scrollY: false,
			bounceTime: this.bounceTime,
			mouseWheel: false
		});
		this.scroll.on('scrollStart', $.proxy(function() {
			console.log('[IScroll:scrollStart]');
		}, this));
		this.scroll.on('scrollEnd', $.proxy(function() {
			console.log('[IScroll:scrollEnd]');
			var index = this.getIndex();
			this.setIndex(index);
		}, this));

		this.scroll.on('scrollCancel', $.proxy(function() {
			console.log('[IScroll:scrollCancel]');
		}, this));

	}
	initIndex (){
		var index=this.index;
		this.index=0;//index always is 0 when init
		this.setIndex(index);
	}
	getIndex () {
		var pos = this.scroll.x - this.scrollOffset;
		var index = Math.abs(pos) / this.itemWidth;
		return Math.round(index);
	}
	setIndex (index) {
		if (index < this.fakeOffsetNum + 0 || index >= this.fakeOffsetNum + this.itemNum) return;

		var isChange = this.index != index;
		this.index = index;

		this.adjustPosition(true);
		if (isChange) {
			this.resetCss();
			//trigger event
			this.onselectchange && this.onselectchange(this.index - this.fakeOffsetNum, this.data);
		}
	}
	adjustPosition (bAnimate) {
		var index = this.index, left, time = 0;
		left = (this.itemWidth * index) * (-1) + this.scrollOffset;
		if (bAnimate) time = this.animatTime;
		this.scroll.scrollTo(left, 0, time);//will trigger scroll
	}
	resetCss () {
		this.items.removeClass('current');
		this.list.find('li[data-index="' + this.index + '"]').addClass('current');
	}
}

