# 使用gulp压缩并合并js,css,html文件教程
###例如：一个项目名叫：NodeTest，
###该项目下有src文件目录，如下图
![alt](https://raw.githubusercontent.com/richardgong1987/openMaterial/master/javascript/compress/project.png)

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

#### 在项目的根目录新建gulpfile.js，并在文件中添加任务

####压缩并合并html实例

######定义初始变量
```js
var gulp = require('gulp'),
	minifyHTML = require('gulp-minify-html'),
    minifycss = require('gulp-minify-css'),
    uglify = require('gulp-uglify'),
    concat = require('gulp-concat'),
    rename = require('gulp-rename'),
    del = require('del');
```


######添加压缩合并html任务
```js
gulp.task('minifyhtml', function() {
    return gulp.src('html/*.html')//文件源
    .pipe(minifyHTML())//执行压缩
    .pipe(concat("main.html"))//执行合并
    .pipe(rename({suffix: '.min'}))//重命名
    .pipe(gulp.dest('desthtml'))//输出文件夹
});

```


####以此类推
######添加压缩合并css任务

```js

//压缩合并css
gulp.task('minifycss', function() {
    return gulp.src('css/*.css')//文件源
    .pipe(minifycss())//执行压缩
    .pipe(concat("main.css"))//执行合并
    .pipe(rename({suffix: '.min'}))//重命名
    .pipe(gulp.dest('destcss'))//输出文件夹
});

```

######添加压缩合并js任务
```js
gulp.task('minifyjs', function() {
	return gulp.src('src/*.js')
		.pipe(concat('main.js')) //合并所有js到main.js //.pipe(gulp.dest('dest')) //输出main.js到文件夹
		.pipe(rename({suffix: '.min'}))////rename压缩后的文件名
		.pipe(uglify()) //压缩
		.pipe(gulp.dest('destjs')); //输出
});

```

######设置默认执行任务
```js
gulp.task('default',['minifyjs','minifycss','minifyhtml']);

```

##完整的代码如下：
```js
var gulp = require('gulp'),
	minifyHTML = require('gulp-minify-html'),
    minifycss = require('gulp-minify-css'),
    uglify = require('gulp-uglify'),
    concat = require('gulp-concat'),
    rename = require('gulp-rename'),
    del = require('del');

//压缩合并html
gulp.task('minifyhtml', function() {
    return gulp.src('html/*.html')//文件源
    .pipe(minifyHTML())//执行压缩
    .pipe(concat("main.html"))//执行合并
    .pipe(rename({suffix: '.min'}))//重命名
    .pipe(gulp.dest('desthtml'))//输出文件夹
});

//压缩合并css
gulp.task('minifycss', function() {
    return gulp.src('css/*.css')//文件源
    .pipe(minifycss())//执行压缩
    .pipe(concat("main.css"))//执行合并
    .pipe(rename({suffix: '.min'}))//重命名
    .pipe(gulp.dest('destcss'))//输出文件夹
});
//压缩合并js
gulp.task('minifyjs', function() {
	return gulp.src('src/*.js')
		.pipe(concat('main.js')) //合并所有js到main.js //.pipe(gulp.dest('dest')) //输出main.js到文件夹
		.pipe(rename({suffix: '.min'}))////rename压缩后的文件名
		.pipe(uglify()) //压缩
		.pipe(gulp.dest('destjs')); //输出
});


gulp.task('default',['minifyjs','minifycss','minifyhtml']);

```

####保存好gulpfile.js后。命令行执行
```sh
gulp
```


