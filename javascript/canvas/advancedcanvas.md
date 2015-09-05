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
####2.动量的应用

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



