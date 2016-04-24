//原型继承原理分析
function Parent(sex, name, age) {
    //成员变量
    this.sex;
    this.name;
    this.age;
}
//成员方法
Parent.prototype.speak = function() {
        console.log('this is', name, sex, age)
    }
    //静态成员与方法
Parent.staticMember;
Parent.staticMethod = function() {}


function Child(name, age) {
    //继承成员变量(属性借用)
    Parent.call(this, 'male', name, age)
}
//继承静态成员与方法
_.extend(Child, Parent);

//继承成员方法(原型继承)
//1.Child.prototype = Parent.prototype;
//缺点：修改Child.prototype,会影响Parent.prototype
//2.Child.prototype = new Parent();
//缺点：无效会执行Parent的构造函数
//3.
function ctor() {}
ctor.prototype = Parent.prototype;
Child.prototype = new ctor();
//还原constructor
Child.prototype.constructor = Child;
//设置父类
Child.__super__ = Parent.prototype;



//原型继承函数实现
var inherits = function(parent, protoProps, staticProps) {
    var child;

    //继承属性(属性借用)
    child = function() {
        parent.apply(this, arguments);
    };

    //继承静态成员与方法
    _.extend(child, parent);

    //继承方法
    function ctor() {}
    ctor.prototype = parent.prototype;
    child.prototype = new ctor();

    //扩展成员属性
    if (protoProps)
        _.extend(child.prototype, protoProps);

    //扩展静态属性
    if (staticProps)
        _.extend(child, staticProps);

    //还原constructor
    child.prototype.constructor = child;

    //设置父类
    //子类.__super__.constructor.call(this);
    child.__super__ = parent.prototype;

    return child;
};
