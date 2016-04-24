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
	//继承属性(属性借用)
	Parent.call(this,'male',name,age)
}
//继承方法(原型继承)
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
//设置父类
Man.__super__ = Parent.prototype;


//原型继承函数实现
__extends = function(child, parent) { 
	//继承属性(属性借用)
	for (var key in parent) {
		if (__hasProp.call(parent, key)) child[key] = parent[key]; 
	} 

	//继承方法
	function ctor() { 
		//还原constructor
		this.constructor = child; 
	} 
	ctor.prototype = parent.prototype; 
	child.prototype = new ctor(); 
	//设置父类
	child.__super__ = parent.prototype; 
	return child; 
};
