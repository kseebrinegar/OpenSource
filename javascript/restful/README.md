#nodejs下实现restful风格
* 建立一个名为restfull的nodejs项目
* cd到你的项目下
* 安装express
```sh
npm install express
```
* 安装好了后，创建文件rest.js 添加如下代码
```js

var map = {"1": {id: 1, name: "richard"}, "2": {id: 2, name: "ricahrd"}} //返回json数据

app.get('/restfull/api', function (req, res) { //提供api '/restfull/api' Get方法,查找整资源
    res.set({'Content-Type': 'text/json', 'Encodeing': 'utf8'});
    res.send(map)
})
app.get('/restfull/api/:id', function (req, res) { //提供 ‘/restfull/api’ Get方法,查找一个单一资源
    res.set({'Content-Type': 'text/json', 'Encodeing': 'utf8'});
    res.send(map[req.param('id')])
    //console.log(req.param('id'))
})

app.listen(8888);//最后，监听8888端口
```

启动nodejs
* 执行命令

```sh
node rest.js
```

即可完成restfull

###如下应该如下图
![alt](https://raw.githubusercontent.com/richardgong1987/openMaterial/master/javascript/restful/get.png)


