#在mac，window上登陆ssh服务器的方法

###1.  mac上登陆
mac本来就是unix系统。所以对登陆linux服务器是有非常好原生的支持，直接打开mac自带的terminal登陆
例如：
>服务器地址:100.162.88.100

>用户名：soho

>密码:123456

那么在终端上输入
```
ssh soho@100.162.88.100
```

如果有端口:
```
ssh soho@100.162.88.100 -p 端口号 
```


回车后，会要求输入密码如下
```
soho@100.162.88.100's password:
```
输入正确密码后，就成功愉快的登陆了

###2. window下登陆
在window下登陆的话，如果有安装有git客户端的同学，直接可以用git登陆,登陆方法几乎和mac一样
例如：
>服务器地址:100.162.88.100

>用户名：soho

>密码:123456

那么在git上输入
```
ssh soho@100.162.88.100  
```

如果有端口:
```
ssh soho@100.162.88.100 -p 端口号 
```

默认是22



回车后，会出现如下提示：
```
The authenticity of host '100.162.88.100 (100.162.88.100)' can't be established
RSA key fingerprint is 0f:de:3e:d1:80:cf:22:0f:49:1a:77:0a:e5:15:b8:50.
Are you sure you want to continue connecting (yes/no)?
```
然后，输入"yes",然后回车
会出来如下，需要我们输入密码
```
soho@100.162.88.100's password:
```
输入正确密码后，就成功愉快的登陆了



