# 使用gulp压缩并合并js,css,html文件教程
##例如：一个项目名叫：NodeTest，
##该项目下有src文件目录，
##目录包含a.js,b.js,c.js,分别包含不同的内容

#### window，mac端的同学，先确保安装好nodejs在自己的机器上
#### Getting Started 在nodejs上安装gulp
```sh
$ npm install  gulp -g
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
		.pipe(gulp.dest('dest2')); //输出
});


gulp.task('default',['minifyjs']);


```
