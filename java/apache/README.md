#Apache 配置多个项目方法
**准备工作:找到apache httpd.conf文件

##1. 配置多个端口

在文件上添加 Listen:端口

如:
```
Listen 82
Listen 83
Listen 84 
Listen 85

```

##2.对相应的端映射不同的文件源

VirtualHost *:端口

DocumentRoot "文件地址"

<Directory "文件地址">
	权限	
</Directory>
如:
```
<VirtualHost *:85>  
    DocumentRoot "C:\kod-ui\webapp\client"  
    <Directory "/var/www/html/goodfoison"> 
       allow from all 
      Options +Indexes 
    </Directory> 
</VirtualHost>  
```


#2.mac上的xampp服务器,本地同一个ip,配置多个域名指向不同的文件方式

## 1.先配置host文件  /etc/host,也就是作本地域名DNS映射
```
127.0.0.1 www.server.com
127.0.0.1 www.test.com test.com
127.0.0.1 www.admin.com admin.com
```
## 2. 我在我的mac的目录/Users/richardgong/Documents/workspace/  创建三个目录,分别为:test1, test2,test3,表示不同的文件夹

然后创建各自的index.html内容的话,随便写,只要能分别开来就可以了

## 3. 修改httpd.conf文件，目录是/Applications/XAMPP/xamppfiles/etc/httpd.conf  我这里让它指向我的项目目录下
```
DocumentRoot "/Users/richardgong/Documents/workspace"
<Directory "/Users/richardgong/Documents/workspace">

```
其它的,可以不用动

## 4. 在httpd.conf文件中,搜索 “httpd-vhosts.conf”，去掉前面的 # 注释符，确保引入了 vhosts 虚拟主机配置文件


## 5.修改httpd-vhosts.conf文件，目录是/Applications/XAMPP/xamppfiles/etc/extra/httpd-vhosts.conf,添加虚拟目录和域名

```
<VirtualHost *:80>
    ServerAdmin webmaster@dummy-host.example.com
    DocumentRoot "/Users/richardgong/Documents/workspace/test1"
    ServerName server.com
    ServerAlias www.server.com
    ErrorLog "logs/dummy-host.example.com-error_log"
    CustomLog "logs/dummy-host.example.com-access_log" common
</VirtualHost>

<VirtualHost *:80>
    ServerAdmin webmaster@dummy-host2.example.com
    DocumentRoot "/Users/richardgong/Documents/workspace/test2"
    ServerName test.com
    ServerAlias www.test.com
    ErrorLog "logs/dummy-host2.example.com-error_log"
    CustomLog "logs/dummy-host2.example.com-access_log" common
</VirtualHost>

<VirtualHost *:80>
    ServerAdmin webmaster@dummy-host2.example.com
    DocumentRoot "/Users/richardgong/Documents/workspace/test3"
    ServerName www.admin.com
    ErrorLog "logs/dummy-host2.example.com-error_log"
    CustomLog "logs/dummy-host2.example.com-access_log" common
</VirtualHost>


```

重启服务器,即可
























 



