var gulp = require('gulp');
var jsdoc = require("gulp-jsdoc");

gulp.task('docTest', function() {
    return gulp.src("./src/*").pipe(jsdoc('./documentation-output'));
});
