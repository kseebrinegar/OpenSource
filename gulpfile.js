var RootPath = '';

var app = [
    RootPath+"test.js",
]


app.concatName = 'main.js'; //输出文件名称
app.distPath = RootPath; //输出文件目录

var gulp = require('gulp'),
    minifyHTML = require('gulp-minify-html'),
    minifycss = require('gulp-minify-css'),
    uglify = require('gulp-uglify'),
    concat = require('gulp-concat'),
    rename = require('gulp-rename'),
    del = require('del');


//压缩合并css
gulp.task('css', function () {
    return gulp.src(['css/*.css'])//文件源
        .pipe(concat('app.css'))
        .pipe(minifycss())//执行压缩
        .pipe(rename({suffix: '.min'}))//重命名
        .pipe(gulp.dest('dist'))//输出文件夹
});

gulp.task('js', function () {
    return gulp.src(app)
        .pipe(concat(app.concatName)) //合并所有js到base-all.js
        .pipe(gulp.dest(app.distPath)) //输出base-all.js到文件夹
        .pipe(rename({suffix: '.min'}))//rename压缩后的文件名
        .pipe(uglify()) //压缩
        .pipe(gulp.dest(app.distPath)); //输出
});


gulp.task('html', function () {
    return gulp.src('*.html')//文件源
        .pipe(minifyHTML())//执行压缩
        .pipe(rename({suffix: '.min'}))//重命名
        .pipe(gulp.dest('./'))//输出文件夹
});

gulp.task('default', ['js','css','html']);



