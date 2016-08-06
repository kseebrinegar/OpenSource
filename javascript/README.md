#javascript怪癖语法

##1. 在预解析下var 和 函数,优先提函数名到最前(因为函数名是函数对象,相当于初始化,所以会overwrite掉空的var)

##2. js负负得正：- -1记住，要空格 = 1,因为如果挨着，就不合语法了。

##3. 设置数组长度,会把数组元素有影响
 ```
    var myArr = ['foo', 'bar', 'baz'];
     myArr.length = 0;
 ```
 如这里,就会把数组给清空了。
 
```
     var myArr = b = ['foo', 'bar', 'baz'];
     myArr.length = 2;
```
这样,数组就只剩两个了。


##4. 用构造函数创建对象时,如果构造函数有返回对象,那么会盖掉构造函数创建的对象
```
    var x = 0;
    function foo() {
        x++;
        this.x = x;
        return foo;
    }
    var bar =  new foo;
    console.log(bar.x);

```

所以 foo对象没有x所以,是undefined

以下可以解决:

```
    var x = 0;
    function foo() {
        x++;
        this.x = x;
        return this;
    }
    var bar =  new foo;
    console.log(bar.x);

```

以下用于实现无论是new还是直接调用构造函数,都能返回构造函数创建的对象:

```
    function foo() {        
        this.x = 1;
        if (this instanceof foo) {
            return this;
        } else {
            return new foo();
        }

    }
    
    var bar = foo();
    console.log(bar.x);//1
    var bar = new foo();
    console.log(bar.x);//1
    
    

```

**注意如何构造函数return返回的是基本字面量时或空,反正返回的不是对象时,解析器会自动实例化构造函数
```
    var a = function () {
        return 'aaaaa';
    }

    console.log(new a() instanceof a);

    var a = function () {
        return;
    }

    console.log(new a() instanceof a);


    var a = function () {
        return 0;
    }

    console.log(new a() instanceof a);



```
以上都为true


***预解析方面:function和var,函数名优于,var**
```
    var a = 1;
    var b = function a(x) {
        console.log(a);
        
        function a() {

        }
        var a = 1;
    };
    b();



    var a = 1;
    var b = function a(x) {
        console.log(a);
        var a = 1;
        function a() {

        }

    };
    b();


    var a = 1;
    var b = function a(x) {
        console.log(a);

    };
    b();

```
 
 
除非,非的预解析状态下:函数名和var正常流程时,并有初始化数据时,var优于函数名
```
    var c = 0;
    function c() {
    }
    console.log(c);// 0
    
    这里,并非是预解析,这里可以看成:
    
    var c;
    function c() {
    }

    c = 1;
    console.log(c);

```
Now the variable value is set to 1. The variable initialization overrides the function declaration.

https://www.nczonline.net/blog/2010/01/26/answering-baranovskiys-javascript-quiz/




##5.label码块标记不是变量和变量是变量,label可以是多层的但不用运算

Syntax
```
label :
   statement
```

```
label
Any JavaScript identifier that is not a reserved word.
```
label不是变量,只能是标记

**注意:它容易误解成是变量名,并且,它不能作为label名不能key,否则报错:**

```
    var a={};

     a:{b:a}

    console.log(a);

```

这里打印的是空对象,而label名不是变量


```
    var a={};

     a:{:'a'}

    console.log(a);

```


label可以是多层的但不用运算
```
a: b: c: d: e: f: g: 1, 2, 3, 4, 5;
```
结果是5

**-----此处报错,因为用了label名作为map key-----**



##6. 多重条件对比时,应该多左到右,然后,左边的结果再对比

```
10 > 9 > 8 === true
```

这里是false,

因为看成
```
10 > 9 > 8 =》 (10>9) > 8 => true > 8 => false;   false === true; => false; 
```



##7 函数参数是arguments属性引用,像with(obj)一样的用法
```
    function foo(a, b) {
        console.log(arguments);
        arguments[1] = 2;
        alert(b);
    }
    foo(1,1);//2
    
   如with:
   var obj = {richard:'richardgong'};
   with (obj){
       richard = 'hahahahaha';
   }
   console.log(obj.richard); //hahahahaha
   
```

而如果没有传参数,则引用无效。

```
    function foo(a, b) {
        console.log(arguments);
        arguments[1] = 2;
        alert(b);
    }
    foo(1);//2

```


##8. 
```
if (!("a" in window)) {
    var a = 1;
}
alert(a);
```

##9,诡异换行符! 在运算表达式中,号换行是被忽略的。而在return里, 却不会被忽略的。

如在"+"号换行是被忽略的
```
var
  b = 10,
  c = (
    20,
    function (x) { return x + 100},
    function () { return arguments[0]}
  );
 
a = b + c
({x: 10}).x

被看成:(忽略换行)

a = b + c({x: 10}).x

```

return里, 却不会被忽略的。
```
 function aa() {
        return 
        {
            name:'richard'
        }
    }

    console.log(aa());//undefined
    
    这里的换行并没有忽略,而是当成undefined处理
```

##10. with和call结合之后之深渊

```
var a = ({
    x: 10,
    foo: function () {
        function bar() {
            console.log(x);
            console.log(y);
            console.log(this.x);
        }
        with (this) {
            var x = 20;
            var y = 30;
            bar.call(this);
        }
    }
}).foo();

//undefined
//30
//20

```

详解:

```
function foo(a) {
    // var x, y, bar - hoisting
    function bar() {
        console.log(x);
        console.log(y);
        console.log(a.x);
    }
    with (a) {
        var x = 20;
        var y = 30;
        bar();
    }
}
foo({x:10});

```

```
var a = ({
    x: 10,
    foo: function () {

        // entering `with(this)`: all variables are searched against `{}`
        // before the engine attempts to create a new variable:
        with (this) {

            // `var x` creates a local `x`. But, during assignment,
            // `x` matches `{}.x`, so `{}.x` is set. **local `x`** 
            // remains `undefined`.
            var x = 20;

            // `y` isn't found in `{}`, so it's a local variable
            var y = 30;

            // execute `bar()`
            bar.call(this);
        }

        // we're now in scope of `{}.foo()`, but not `with(this)`.
        function bar() {

            // local variable `x` was declared, but never defined.
            console.log(x);

            // local variable `y` exists in the scope of `{}.foo()`
            console.log(y);

            // we're still in the "macro" scope of `{}`. So, `this` refers
            // to `{}`, which was changed to 20.
            console.log(this.x);
        }

    }
}).foo();

```


##11. js书写时的障眼法。


```
foreach (k in {a: 10, b: 20})
{
  // ...
}
```

这样的写法,什么时候会是对的。

```
    function foreach() {
    }

    foreach (k in {a: 10, b: 20});//注意这里用分号分开的,用于更清析的理解
    
    {
        //....这部分是普通执行代码块和foreach方法没有任何关系;
    }

```

##12. 运算符优先级,'. [] ()'都要优先于放在前面"++"算数符。 

```
++'52'.split('')[0];

执行顺序是:

'52'.split('')[0] =》 再到 ++ 

=》++'5' =》 6

```

##13.  条件运算符,关系操作符要优先于,连接操作符

```
var a = 'c'+ true ? 'aaaaaaaa':'bbbbbbbb';

a //aaaaaaaa

var b = ({} + 'b' > {} + 'a')

//true; 这里先运算 'b' > {} ,并快速返回数据,注意是返回。它和 'foo'.split('') + []不能,这里能返回数据是"+"连接符号

```