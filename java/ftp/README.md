
#Use ncftp to down or update you source on mac


If you dont have brew  pass below command

```sh
ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```

######get started, install ncftp
```sh
brew install ncftp
```


if your ftp host is：127.0.0.1  username ：richard  password：123456

######connect ftp service
```sh
ncftp -u  richard -p 123456 127.0.0.1
```

After sucessfull it should print a message like below:

```sh
NcFTP 3.2.5 (Feb 02, 2011) by Mike Gleason (http://www.NcFTP.com/contact/).
Connecting to 127.0.0.1...
(vsFTPd 2.2.2)
Logging in...
Login successful.
Logged in to 127.0.0.1.
```

That's mean you have connected service

######update your file

```sh

 put -fr /filespath

 ```

######download your file
```sh
 put -fr /filespath
```

