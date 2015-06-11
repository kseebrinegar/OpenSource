/**
 * Created by richardgong on 6/11/15.
 */
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

//app.post('/devices/', express.bodyParser(), function (req, res) { //Restful Post方法,创建一个单一资源
//    res.set({'Content-Type': 'text/json', 'Encodeing': 'utf8'});
//    map[req.body.id] = req.body
//    res.send({status: "success", url: "/devices/" + req.body.id}) //id 一般由数据库产生
//})
//app.put('/devices/:id', express.bodyParser(), function (req, res) { //Restful Put方法,更新一个单一资源
//    res.set({'Content-Type': 'text/json', 'Encodeing': 'utf8'});
//    map[req.body.id] = req.body
//    res.send({status: "success", url: "/devices/" + req.param('id'), device: req.body});
//})
//app.delete('/devices/:id', function (req, res) { //Restful Delete方法,删除一个单一资源
//    res.set({'Content-Type': 'text/json', 'Encodeing': 'utf8'});
//    delete map[req.param('id')]
//    res.send({status: "success", url: "/devices/" + req.param('id')})
//    console.log(map)
//})
app.listen(8888); //监听8888端口，没办法，总不好抢了tomcat的8080吧！