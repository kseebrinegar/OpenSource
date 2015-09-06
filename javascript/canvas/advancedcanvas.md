#canvas高级动画
####html
```html
<canvas id="canvas" width="400" height="400"></canvas>
<style>
    body{background: #000000;}
    canvas{background: #ffffff;}
</style>
```
##1.重力加物理碰撞效果.
key word:translate,         vx *= bounce;        vy += gravity;

```js
  //一,创建球对象
    function Ball (radius) {
        this.x = 0;
        this.y = 0;
        this.radius =radius ||  20;
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
    
    var canvas = document.getElementById('canvas');
    var context = canvas.getContext('2d');
    
   var  ball = new Ball();
    //位移量
    var vx = Math.random() * 10 - 5;
    var vy = -10;
    //弹性值,也就是往反方向走的值.
    var bounce = -0.9;
    //重力加速度
    var gravity = 0.2;
    ball.x = canvas.width / 2;
    ball.y = canvas.height / 2;

    function checkBoundaries () {
        var left = 0,
                right = canvas.width,
                top = 0,
                bottom = canvas.height;

        vy += gravity;
        ball.x += vx;
        ball.y += vy;

        //能进这里,说明球到了右边界
        if (ball.x + ball.radius > right) {
            ball.x = right - ball.radius;
            vx *= bounce;
            //能进这里,说明球到了左边界
        } else if (ball.x - ball.radius < left) {
            ball.x = left + ball.radius;
            vx *= bounce;
        }
        //能进这里,说明球到了底边
        if (ball.y + ball.radius > bottom) {
            ball.y = bottom - ball.radius;
            vy *= bounce;
            //能进这里,说明球到了顶部
        } else if (ball.y - ball.radius < top) {
            ball.y = top + ball.radius;
            vy *= bounce;
        }
    }

    (function drawFrame () {
        window.requestAnimationFrame(drawFrame, canvas);
        context.clearRect(0, 0, canvas.width, canvas.height);
        checkBoundaries();
        ball.draw(context);
    }());


```
##2.动量的应用

* 动量 = 质量x速度,p = m x v;

如:4kg x 15m/s = 60kg m/s

* 动量守衡定理:系统在碰撞前总动量等于系统碰撞后总动量

A+B = B+A;

* A,B两个球在碰撞前和碰撞后动量可以表达成:m1 + m0 = m0f + m1f;

(物体A质量x物体A速度)+(物体B质量x物体B速度)=(物体A质量x物体A最终速度)+(物体B质量x物体B最终速度);

* 动能公式=1/2 x 质量 x 速度²; KE = 0.5m x v²
 
KE0  KE1 = KE0Final+KE1Final;
于是有:
= 
 
(0.5m x m0 x v²) + (0.5m x m1 x v²) = (0.5m x m0 x Final²) + (0.5m x m1 x Final²);
 
再有: 
= 

(m x m0 x v²) + (m x m1 x v²) = (m x m0 x v0Final²) + (m x m1 x v1Final²);

所以碰撞后的速度应该是:
v0Final = ((m0 - m1) x v0 + 2 x m1 x v1) / m0 + m1;

v1Final = ((m1 - m0) x v1 + 2 x m0 x v0) / m0 + m1;



js

```js
  //一,创建球对象
    function Ball(radius) {
        this.x = 0;
        this.y = 0;
        this.radius = radius || 20;
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


    var canvas = document.getElementById('canvas');
    var context = canvas.getContext('2d');
    var ball0 = new Ball(),
            ball1 = new Ball();

    //1.设置属性
    ball0.mass = 2.5;
    ball0.x = 50;
    ball0.y = canvas.height / 2;
    ball0.vx = 1;

    ball1.mass = 1;
    ball1.x = 300;
    ball1.y = canvas.height / 2;
    ball1.vx = -1;

    (function drawFrame () {
        window.requestAnimationFrame(drawFrame, canvas);
        context.clearRect(0, 0, canvas.width, canvas.height);

        //2.设置偏移
        ball0.x += ball0.vx;
        ball1.x += ball1.vx;


        var dist = ball1.x - ball0.x;
        //3.如果能进这里,说明已经相碰.
        if (Math.abs(dist) < ball0.radius + ball1.radius) {
            
            //4.碰撞后速度公式:v0Final = ((m0 - m1) x v0 + 2 x m1 x v1) / m0 + m1;
            var vx0Final = ((ball0.mass - ball1.mass) * ball0.vx + 2 * ball1.mass * ball1.vx) /
                            (ball0.mass + ball1.mass),
                    //.碰撞后速度公式:v1Final = ((m1 - m0) x v1 + 2 x m0 x v0) / m0 + m1;
                    vx1Final = ((ball1.mass - ball0.mass) * ball1.vx + 2 * ball0.mass * ball0.vx) /
                            (ball0.mass + ball1.mass);
            
            ball0.vx = vx0Final;
            ball1.vx = vx1Final;
            ball0.x += ball0.vx;
            ball1.y += ball1.vx;
        }
        ball0.draw(context);
        ball1.draw(context);
    }());

```


##3.双轴上的动量守恒

* 以上的例子中,我们知道,两个球在平行线上相碰后的速度和方向,也就是夹角180度情况,我们可以直接减他们的值,这是一种理想状态下的, 如果两个球在不同,不在同一直线上,速度和方向都不一样时.是不可
以直接用:动量守恒公式的.

我们的解决办法是:让不同于一个直线上的相碰物体,转化成同一线直线的物体.

假设:两个球相碰了后,两个圆心夹角为:30°.我们逆时针,旋转-30°,
那么,我们就可以作到:向量相加了...说白了,就是两个向量进行相减.得到两个不同的方向的向量.


实例:

js
```js
  //一,创建球对象
    function Ball(radius) {
        this.x = 0;
        this.y = 0;
        this.radius = radius || 20;
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


    var canvas = document.getElementById('canvas');
    var context = canvas.getContext('2d');
    var ball0 = new Ball(80),
            ball1 = new Ball(20),
            bounce = -1.0;
    ball0.mass = 3;
    ball0.x = canvas.width - 200;
    ball0.y = canvas.height - 200;
    ball0.vx = Math.random() * 10 - 5;
    ball0.vy = Math.random() * 10 - 5;

    ball1.mass = 1;
    ball1.x = 100;
    ball1.y = 100;
    ball1.vx = Math.random() * 10 - 5;
    ball1.vy = Math.random() * 10 - 5;

    //相碰操作检查
    function checkCollision(ball0, ball1) {
        var dx = ball1.x - ball0.x,
                dy = ball1.y - ball0.y,
        //勾股定理
                dist = Math.sqrt(dx * dx + dy * dy);

        //1.能进这里的,说明球已经相碰
        if (dist < ball0.radius + ball1.radius) {
            //利用正切公式,得到他们的夹角
            var angle = Math.atan2(dy, dx),
                    sin = Math.sin(angle),
                    cos = Math.cos(angle);

            //(1),重置第一个球的位置,假设是移到了坐标系的0,0位置.
            var x0 = 0, y0 = 0;
            //这里是坐标换算.  比较不好解释,照作就是了.只是需要注意的是:这里是作一个旋转后的坐标换算
            var x1 = dx * cos + dy * sin,
                    y1 = dy * cos - dx * sin;

            //动量换算.这里是作一个旋转后的动量换算
            var vx0 = ball0.vx * cos + ball0.vy * sin,
                    vy0 = ball0.vy * cos - ball0.vx * sin,
                    vx1 = ball1.vx * cos + ball1.vy * sin,
                    vy1 = ball1.vy * cos - ball1.vx * sin;


            var vxTotal = vx0 - vx1;
            vx0 = ((ball0.mass - ball1.mass) * vx0 + 2 * ball1.mass * vx1) /
                    (ball0.mass + ball1.mass);
            vx1 = vxTotal + vx0;
            x0 += vx0;
            x1 += vx1;


            //旋转回来
            var x0Final = x0 * cos - y0 * sin,
                    y0Final = y0 * cos + x0 * sin,
                    x1Final = x1 * cos - y1 * sin,
                    y1Final = y1 * cos + x1 * sin;
            //调整到实际位置.
            ball1.x = ball0.x + x1Final;
            ball1.y = ball0.y + y1Final;
            ball0.x = ball0.x + x0Final;
            ball0.y = ball0.y + y0Final;
            //rotate velocities back
            ball0.vx = vx0 * cos - vy0 * sin;
            ball0.vy = vy0 * cos + vx0 * sin;
            ball1.vx = vx1 * cos - vy1 * sin;
            ball1.vy = vy1 * cos + vx1 * sin;
        }
    }

    //边检操作,很简单,不解释了.
    function checkWalls(ball) {
        if (ball.x + ball.radius > canvas.width) {
            ball.x = canvas.width - ball.radius;
            ball.vx *= bounce;
        } else if (ball.x - ball.radius < 0) {
            ball.x = ball.radius;
            ball.vx *= bounce;
        }
        if (ball.y + ball.radius > canvas.height) {
            ball.y = canvas.height - ball.radius;
            ball.vy *= bounce;
        } else if (ball.y - ball.radius < 0) {
            ball.y = ball.radius;
            ball.vy *= bounce;
        }
    }

    (function drawFrame() {
        window.requestAnimationFrame(drawFrame, canvas);
        context.clearRect(0, 0, canvas.width, canvas.height);

        ball0.x += ball0.vx;
        ball0.y += ball0.vy;
        ball1.x += ball1.vx;
        ball1.y += ball1.vy;
        checkCollision(ball0, ball1);
        checkWalls(ball0);
        checkWalls(ball1);
        ball0.draw(context);
        ball1.draw(context);
    }());

```










