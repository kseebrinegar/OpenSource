
#在mac上使用ncftp来上传你的文件


如果没有brew的同学可以执行如下命令安装brew
```sh
ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```

#######首先安装ncftp
```sh
brew install ncftp
```


例如你的ftp服务器地址是：127.0.0.1  用户名是：richard  密码是：123456

#######连接ftp服务
```sh
ncftp -u  richard -p 123456 127.0.0.1
```
连接成功后，应该有像如下的提示：

```sh
NcFTP 3.2.5 (Feb 02, 2011) by Mike Gleason (http://www.NcFTP.com/contact/).
Connecting to 127.0.0.1...
(vsFTPd 2.2.2)
Logging in...
Login successful.
Logged in to 127.0.0.1.
```

这就表示你连接成功了

######批量上传文件

```sh

 put -fr /filespath

 ```

######批量下载文件
```sh
 put -fr /filespath
```

