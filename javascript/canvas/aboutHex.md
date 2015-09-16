#操作像素

画色带-->>红绿蓝
```js
      var canvas = document.getElementById('canvas'),
          context = canvas.getContext('2d');

      for (var i = 0,index=1; i < canvas.width; i += 10,index++) {
            var color = {0:"#00f",2:"#0f0",1:"#f00"}[index%3];
          context.fillStyle = color;
          context.fillRect(i, 0, 10, canvas.height);
      }
```




