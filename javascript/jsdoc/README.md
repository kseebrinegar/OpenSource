#jsDoc的使用


Install gulp-jsdoc as a development dependency:
```
npm install --save-dev gulp-jsdoc
```

Then, use it:

```
var jsdoc = require("gulp-jsdoc");
 
gulp.src("./src/*.js")
  .pipe(jsdoc('./documentation-output'))
  
```