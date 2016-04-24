//原型继承原理分析
function Parent(sex,name,age){
	this.sex;
	this.name;
	this.age;
}
Parent.prototype.speak=function(){
	console.log('this is',name,sex,age)
}

function Man(name,age){
	//属性继承
	Parent.call(this,'male',name,age)
}
//方法继承(原型继承)
//1.Man.prototype = Parent.prototype;
//缺点：修改Man.prototype,会影响Parent.prototype
//2.Man.prototype = new Parent();
//缺点：无效会执行Parent的构造函数
//3.
function ctor(){}
ctor.prototype = Parent.prototype;
Man.prototype = new ctor();
//还原constructor
Man.prototype.constructor = Man;
Man.__super__ = Parent.prototype;


//原型继承函数实现
__extends = function(child, parent) { 
	for (var key in parent) {
		if (__hasProp.call(parent, key)) child[key] = parent[key]; 
	} 

	function ctor() { 
		this.constructor = child; 
	} 
	ctor.prototype = parent.prototype; 
	child.prototype = new ctor(); 
	child.__super__ = parent.prototype; 
	return child; 
};
