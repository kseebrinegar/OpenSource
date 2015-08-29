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



 



