#canvas简单画图
###学习canvas没有什么技巧.这不是学习js语言本身.这只是学习浏览器提供的API所以.这是一个经验活.当然了.这也是一个有文化的人才能作的.这里涉及到数学问题.所以,如果只有小学文化是学不了的.

####html
```html
<canvas id="canvas" width="400" height="400"></canvas>
<style>
    body{background: #000000;}
    canvas{background: #ffffff;}
</style>
```

#1.写字

####(1)写字填充fillText
key word:fillText

js
```js
    var canvas = document.getElementById('canvas');
    var context = canvas.getContext('2d');

    //1.设置文字大小和字体，这里跟设置css一样。
    context.font="40px Arial";
    //2.设置文字的颜色（所有设置颜色都可以用fillStyle
    context.fillStyle="red";
    //3.fillText方法，第一个参数代表：要填入的文字。第二，三个参数代表：文字绘制的起始坐标（x,y)。 第四个参数代表：[可选]允许的最大文本宽度，以像素计。
    context.fillText("Hello Canvas", canvas.width/2,canvas.height/2);

```

####（2）对文字描边strokeText

key word:strokeText

js

```js
  var canvas = document.getElementById('canvas');
    var context = canvas.getContext('2d');

    //1.设置文字大小和字体，这里跟设置css一样。
    context.font="40px Arial";
    //2.设置文字的颜色（所有设置颜色都可以用fillStyle
    context.fillStyle="red";

    context.strokeStyle = 'green';
    //3.fillText方法，第一个参数代表：要填入的文字。第二，三个参数代表：文字绘制的起始坐标（x,y)。 第四个参数代表：[可选]允许的最大文本宽度，以像素计。
    context.fillText("Hello Canvas", canvas.width/2-100,canvas.height/2);

    context.strokeText("Hello Canvas", canvas.width/2-100, canvas.height/2 );

```

#2line 画线
key word:lineTo,stroke

js
```js
  var canvas = document.getElementById('canvas');
    var context = canvas.getContext('2d');
    var offsetLeft = canvas.offsetLeft;
    var offsetTop = canvas.offsetTop;

    context.fillStyle = 'green';
    context.lineWidth = 1;
    context.beginPath();
    canvas.addEventListener('mousemove',function(e){
        draw(e.pageX-offsetLeft, e.pageY-offsetTop)
    },false);
    function draw(x,y) {
        context.lineTo(x,y);
      context.stroke();
    }

```



#3.画圆
####1.circle
key word:arc



js
```js
    var canvas = document.getElementById('canvas');
    var context = canvas.getContext('2d');
    //1.translate方法，用于平移画布，注意：舞台和画布是不一样的。这里的舞台指的是canvas这个html5元素,画布就是画布。。
    context.translate(canvas.width/2,canvas.height/2);//canvas.width/2,canvas.height/2这里移动画布到舞台中间。
    //(1)开始一个路径（想像机器人的手）
    context.beginPath();
    //（2）arc前两个参数代表圆心座标：(x,y)。 第三个参数代表：半径。 第四个参数代表：起始角（弧度制），第五个参数代表：结束角（弧度制），第六个参数代表：规定应该逆时针还是顺时针绘图。False = 顺时针，true = 逆时针。
    context.arc(0,0,50,0,(Math.PI * 2),true);
    //(3)结束路径
    context.closePath();

    //(4)添加填充颜色
    context.fillStyle = "#ff0000";

    //最后一步，填充，结束
    context.fill();
```

####2.breathe circle呼吸的球.
key word:scale

这里用三角函数的控制致关重要.用来控制放大的比例,才能作到呼吸的效果
```js
//三角函数公式:f(x)=A+sin(Q)*B;
//A代表初相,B代表振副
 scaleX = scaleY =1+ Math.sin(angel)*1/2;
```
js
```js
  var canvas = document.getElementById('canvas');
    var context = canvas.getContext('2d');
    var scaleY = 1;
    var scaleX = 1;
    var angel = 1;
    function draw() {
        context.save();
        context.translate(canvas.width / 2, canvas.height / 2);
        angel+=0.05;
        scaleX = scaleY =1+ Math.sin(angel);
        //1.scale(scalewidth,scaleheight).缩放当前绘图的宽高 (1=100%, 0.5=50%, 2=200%, 依次类推)
        //注意这里:scale一定要在beginPath之前,不然,无法放大功能
        context.scale(scaleX, scaleY);
        context.beginPath();
        context.arc(0, 0, 100, 0, (Math.PI * 2), true);
        context.closePath();
        context.fillStyle = 'green';
        context.fill();
        context.restore();
    }

    (function drawFrame() {
        window.requestAnimationFrame(drawFrame, canvas);
        context.clearRect(0, 0, 500, 500);
        draw();
    }());


```

####3.random circle随机运动的球.

####3. 运动的球.
key word:translate

这里用三角函数的控制致关重要.用来控制放大的比例,才能作到运动的效果
```js
//三角函数公式:f(x)=A+sin(Q)*B;f(y)=A+cos(Q)*B
//A代表初相,B代表振副
```
**注意：atan(Q) = b/a; 求角度的方法：Q = Math.atan(b/a)/(2*Math.PI)*360;**

```js

 var canvas = document.getElementById('canvas');
    var context = canvas.getContext('2d');
    var scaleY = 1;
    var scaleX = 1;
    var angel = 1;

    function draw() {
        context.save();

        scaleX = 200+ Math.sin(angel)*100;
        scaleY = 200+Math.cos(angel)*100;

        context.translate(scaleX,scaleY);
        angel+=0.05;
        scaleX = scaleY =1+ Math.sin(angel);

        context.beginPath();
        context.arc(0, 0, 20, 0, (Math.PI * 2), true);
        context.closePath();
        context.fillStyle = 'green';
        context.fill();
        context.restore();
    }

    (function drawFrame() {
        window.requestAnimationFrame(drawFrame, canvas);
        context.clearRect(0, 0, 500, 500);
        angel+=0.01;

        draw();
    }());

```
###4.move circle会移动的圆

key word:translate

moveX+=1:moveX+=speed,speed+=0.5:用于加速度
```js
moveX+=1
```

js
```js
  var canvas = document.getElementById('canvas');
    var context = canvas.getContext('2d');
    var moveX = 0;
    var speed = 0.1;

    function draw() {
        context.save();
        speed+=0.5;
        context.translate(moveX+=speed, canvas.height / 2);
        context.beginPath();
        context.arc(0, 0, 100, 0, (Math.PI * 2), true);
        context.closePath();
        context.fillStyle = 'green';
        context.fill();
        context.restore();
    }

    (function drawFrame() {
        window.requestAnimationFrame(drawFrame, canvas);
        context.clearRect(0, 0, 2000, 2000);
        draw();
    }());
```


#4. ball
###(1),永不出边框的球
key word:translate

js
```js
  var canvas = document.getElementById('canvas');
  var context = canvas.getContext('2d');

    var left = 0;
    var right = canvas.width;
    var tops = 0;
    var bottom = canvas.height;


    var vx = Math.random() * 10 - 5;
    var vy = Math.random() * 10 - 5;

    var x = canvas.width/2;
    var y = canvas.height/2;

    var radius = 20;

    function draw() {
        context.save();
        x+=vx;
        y+=vy;
        //1.移位加半径>宽度值时,说明是到了右边界
        if((x+radius)>right){
            //定住
            x = right - radius;
            //用于取反往方向
            vx *= -1;

            //2.位移量减去半径<左边边界,说明到了到左边界
        }else if(x-radius<left){
            x=left+radius;
            //取反
            vx*=-1;
        }else if(y-radius<tops){
            y=tops+radius;
            //取反
            vy*=-1;
        }else if(y+radius>bottom){
            y=bottom-radius;
            //取反
            vy*=-1;
        }
        context.translate(x,y);
        context.beginPath();
        context.arc(0, 0, radius, 0, (Math.PI * 2), true);
        context.closePath();
        context.fillStyle = 'green';
        context.fill();
        context.restore();
    }

    (function drawFrame() {
        window.requestAnimationFrame(drawFrame, canvas);
        context.clearRect(0, 0, 400, 400);
        draw();
    }());
```
###(2),相碰的球
key word:translate

* 1.  if (Math.abs(dist) < ball0.radius + ball1.radius) 用于判断是否相碰
* 2.    ball1.vx *=-1;用于取反方向.

js
```js
     //创建球对象
     function Ball () {
         this.x = 0;
         this.y = 0;
         this.radius = 20;
         this.vx = 0;
         this.vy = 0;
     }
     //所有画图在这个方法进行
     Ball.prototype.draw = function (context) {
         context.save();
         context.translate(this.x, this.y);
         context.fillStyle = 'green';
         context.beginPath();
         context.arc(0, 0, this.radius, 0, (Math.PI * 2), true);
         context.closePath();
         context.fill();
         context.restore();
     };
 //获取当前球的边界值,用于作判断
     Ball.prototype.getBounds = function () {
         return {
             x: this.x - this.radius,
             y: this.y - this.radius,
             width: this.radius * 2,
             height: this.radius * 2
         };
     };
 
 
     var canvas = document.getElementById('canvas');
     var context = canvas.getContext('2d');
 
     var ball0 = new Ball();
     var  ball1 = new Ball();
 
     //(1)定义第一个球的属性,用于绘制时需要
     ball0.x = 50;
     ball0.y = canvas.height / 2;
     ball0.vx = 1;
 
     //定义第二个球的属性,用于绘制时需要,注意这里的vx为负数.这是为了让他们往相反方向运动的需要
     ball1.x = 300;
     ball1.y = canvas.height / 2;
     ball1.vx = -1;
 
     (function drawFrame () {
         window.requestAnimationFrame(drawFrame, canvas);
         context.clearRect(0, 0, canvas.width, canvas.height);
         //(2),累加数据,用于运动.
         ball0.x += ball0.vx;
         ball1.x += ball1.vx;
         //(3),这里是为了算出两个球的距离
         var dist = ball1.x - ball0.x;
         //(4),如果两个球的半径加起来大于两个球的圆心距离.这说明两个球有相碰在一起了.
         if (Math.abs(dist) < ball0.radius + ball1.radius) {
             //能进这个if语句的,说明球已经相碰在一起了.这里,我们只需要给一个相反的方向给他们,就可以让他们往相反方向运动了.
             ball0.vx *=-1;
             ball1.vx *=-1;
         }
         ball0.draw(context);
         ball1.draw(context);
     }());

```

