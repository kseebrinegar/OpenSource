#local respo

##1.Then add a user for Git.

```bash
sudo useradd git
passwd git
```
##2.login in ,and ,install git
```bash
ssh git@192.168.1.112


```
##3.create you own public key in your machine
```bash
ssh-keygen
```

##4. add your key to the git server host machine
```bash
cat ~/.ssh/id_rsa.pub | ssh git@192.168.1.112 "mkdir -p ~/.ssh && cat >>  ~/.ssh/authorized_keys"
```

##5. init git 

```bash

ssh git@192.168.1.112

mkdir centosgitrepos

cd centosgitrepos

git init --bare
```


##Ok,clone git

```bash

git clone git@192.168.1.112:/home/git/centosgitrepos

```



#error
#####1.git is not in the sudoers file. 
xxx is not in the sudoers file. This incident will be reported，xxx是你当前的用户名，究其原因是用户没有加入到sudo的配置文件

```bash
切换到root用户，运行visudo命令

在打开的配置文件中，找到root ALL=(ALL) ALL，在下面添加一行
xxx ALL=(ALL) ALL 其中xxx是你要加入的用户名称



输入:wq保存并退出配置文件，再次使用sudo命令就不会有上面的提示了。
```


######delete user password,no need password to ssh connected
    
  ```bash
  sudo passwd david -d
  
  
  Put the public key in .ssh/authorized_keys2
  Change the permissions of .ssh to 700
  Change the permissions of .ssh/authorized_keys2 to 640
    
  ```
  
 
  
  

