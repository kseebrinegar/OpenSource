#chmod is used to change the permissions of files or directories.

##In general, chmod commands take the form:
```bash

chmod options permissions filename

```

The letters u, g, and o stand for "user", "group", and "other". 


* u: user
* g: group
* o: other
* a: all user

This command will do the trick:

```bash
chmod u=rwx,g=rx,o=r myfile
```


The equals sign ("=") means "set the permissions exactly like this," and the letters "r", "w", and "x" stand for "read", "write", and "execute", respectively. The commas separate the different classes of permissions, and there are no spaces in between them.


* 4 stands for "read",

* 2 stands for "write",

*  1 stands for "execute", and

* 0 stands for "no permission."




