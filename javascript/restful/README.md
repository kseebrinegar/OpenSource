#nodejs下实现restful风格
* 建立一个名为restfull的nodejs项目
* cd到你的项目下
* 安装express
```sh
npm install express
```
*安装好了后，创建文件rest.js 添加如下代码
```js
var express = require('express') //加载模块
var app = express() //实例化

var map = {"1": {id: 1, name: "test"}, "2": {id: 2, name: "test"}} //定义一个集合资源，key为字符串完全是模仿java MAP<T,E>，否则谁会这么去写个hash啊！

app.get('/devices', function (req, res) { //Restful Get方法,查找整个集合资源
    res.set({'Content-Type': 'text/json', 'Encodeing': 'utf8'});
    res.send(map)
})
app.get('/devices/:id', function (req, res) { //Restful Get方法,查找一个单一资源
    res.set({'Content-Type': 'text/json', 'Encodeing': 'utf8'});
    res.send(map[req.param('id')])
    //console.log(req.param('id'))
})

app.listen(8888);
```


* 执行命令

```sh
node rest.js
```
###如下应该如下图
![alt](https://raw.githubusercontent.com/richardgong1987/openMaterial/master/javascript/restful/get.png)


