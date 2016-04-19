#git remote: 

###1.git remote add origin url
```sh
git remote add origin url
```
'origin' is a remote name

we can use custom remote name

```
git remote add mycustomorigin url
```

this is use to add mutiple remote repositoriy

then push it to our remote repository

```sh 
git push -u origin master
git push -u mycustomorigin master
```






#Use git
###1.short operative

* 1.
```
git init
git add .
git commit -m'first commit'
```
the short operative is:

```
git commit -am'test comm'

```

* 2.
```
git add .
git commit -m'aa'
git push origin master

```

the short operative is:

```
git commit -am'test comm' && git push origin master
```




