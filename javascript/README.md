#javascript怪癖语法

##1. var 和 函数,优先提函数名到最前

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

    c =0;
    console.log(c);

```





##5.label码块标记不是变量和变量是变量

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

