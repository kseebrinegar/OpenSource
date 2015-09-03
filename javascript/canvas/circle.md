#canvas简单画图

#1.画圆

key word:arc


html
```html
<canvas id="canvas" width="400" height="400"></canvas>
<style>
    body{background: #000000;}
    canvas{background: #ffffff;}
</style>

```

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

#2.写字

##(1)写字填充fillText
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

##（2）对文字描边strokeText

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