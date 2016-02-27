import IScroll from './iscroll';
import $ from 'jquery';
import _ from 'underscore';
// import React from 'react';
// import IScroll from './iscroll';

// export default class ISelecter extends React.Component {
//     constructor() {
//         super();
//         this.state = {};
//     }
//     render() {
//         return (
// 			<div>Hello World</div>
// 		);
//     }
// }


(function(root) {
	var tmpl = '<div class="wrapper">' +
					'<div class="scroller">' +
						'<ul class="list">' +
							'<%for (var i = 0; i < data.length; i++) {%>' +
							'<li data-index="<%=i%>" ><%=data[i]%></li>' +
							'<%}%>' +
						'</ul>' +
					'</div>' +
					'<div class="mask"></div>' +
				'</div>';

	var ISelecter = function(container, options) {
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

		this.init();
	}

	ISelecter.prototype = {
		constructor: ISelecter,
		init: function() {
			this.initData();
			this.render();
			this.initSize();
			this.initScroll();
			this.initIndex();
			this.resetCss();
		},
		initData: function(){
			this.innerData = _.extend([], this.data);
			//fake items
			this.fakeNum = this.displayNum - 1;
			this.fakeOffsetNum = this.fakeNum / 2;
			this.index = this.index + this.fakeOffsetNum;
			for (var i = 0; i < this.fakeOffsetNum; i++) {
				this.innerData.unshift('');
				this.innerData.push('');
			}
		},
		render: function(){
			//template
			var html = _.template(tmpl)({
				data: this.innerData
			});
			this.container.html(html);
			//initial dom
			this.wrapper = this.container.find('.wrapper');
			this.scroller = this.wrapper.find('.scroller');
			this.list = this.scroller.find('ul');
			this.items = this.list.find('li');
		},
		initSize: function() {
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
		},
		initScroll: function() {
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

		},
		initIndex: function(){
			var index=this.index;
			this.index=0;//index always is 0 when init
			this.setIndex(index);
		},
		getIndex: function() {
			var pos = this.scroll.x - this.scrollOffset;
			var index = Math.abs(pos) / this.itemWidth;
			return Math.round(index);
		},
		setIndex: function(index) {
			if (index < this.fakeOffsetNum + 0 || index >= this.fakeOffsetNum + this.itemNum) return;

			var isChange = this.index != index;
			this.index = index;

			this.adjustPosition(true);
			if (isChange) {
				this.resetCss();
				//trigger event
				this.onselectchange && this.onselectchange(this.index - this.fakeOffsetNum, this.data);
			}
		},
		adjustPosition: function(bAnimate) {
			var index = this.index, left, time = 0;
			left = (this.itemWidth * index) * (-1) + this.scrollOffset;
			if (bAnimate) time = this.animatTime;
			this.scroll.scrollTo(left, 0, time);//will trigger scroll
		},
		resetCss: function() {
			this.items.removeClass('current');
			this.list.find('li[data-index="' + this.index + '"]').addClass('current');
		},
	};

	if ( typeof module != 'undefined' && module.exports ) {
		module.exports = ISelecter;
	} else {
		root.ISelecter = ISelecter;
	}
}(window));