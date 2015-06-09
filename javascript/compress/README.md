# 使用gulp压缩并合并js,css,html文件教程
###例如：一个项目名叫：NodeTest，
###该项目下有src文件目录，
###目录包含a.js,b.js,c.js,分别包含不同的内容

#### window，mac端的同学，先确保安装好nodejs在自己的机器上,然后，cd 到你的项目文件根目录下
#### Getting Started 在nodejs上安装gulp
```sh
 npm install  gulp -g
```
#### 安装项目环境（不要问为什么，照做就是)

```sh
npm install gulp --save-dev
```

#### 安装gulp下的html压缩,css压缩,js压缩，文件合并，文件重命名，文件删除的插件，（更多插件大家可以在https://www.npmjs.com 上找）

```sh
npm install gulp-minify-html gulp-minify-css gulp-uglify gulp-concat  gulp-rename del --save-dev
```

#### 在项目的根目录新建gulpfile.js，require需要的module

####压缩并合并js实例



```js
var gulp = require('gulp'),
    minifycss = require('gulp-minify-css'),
    concat = require('gulp-concat'),
    uglify = require('gulp-uglify'),
    rename = require('gulp-rename'),
    del = require('del');


gulp.task('minifyjs', function() {
	return gulp.src('src/*.js')
		.pipe(concat('main.js')) //合并所有js到main.js
		.pipe(gulp.dest('dest')) //输出main.js到文件夹
		.pipe(uglify()) //压缩
		.pipe(gulp.dest('compress')); //输出
});


gulp.task('default',['minifyjs']);


```

####保存好gulpfile.js后。命令行执行
```sh
gulp
```

即可
#### dest是合并好的main.js文件，compress下的压缩好的文件main.js



#以此类推
##压缩css并合并css文件
如项目下有一个css文件夹
添加css压缩任务

```js

gulp.task('minifycss', function() {
    return gulp.src('css/*.css')//文件源
    .pipe(minifycss())//执行压缩
    .pipe(concat("main.css"))//执行合并
    .pipe(gulp.dest('destcss'))//输出文件夹
});


```




##压缩html并合并html文件
```js
gulp.task('minifyhtml', function() {
    return gulp.src('html/*.html')//文件源
    .pipe(minifyHTML())//执行压缩
    .pipe(concat("main.html"))//执行合并
    .pipe(rename({suffix: '.min'}))//重命名
    .pipe(gulp.dest('desthtml'))//输出文件夹
});

```
![alt](http://images.88art.com/uploads/artwork/2014/12/16/Art_14187020647203_186x241.JPG)