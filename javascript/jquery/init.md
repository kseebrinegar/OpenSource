#jquery
##1. reset undefined;
```
core_strundefined = typeof undefined,

```
because in ie6,7,8 they can reset 'undefined' value
```
var undefined = 'richardgong';
```
because undefined isn't key word,so in ie6,7,8  brower is reset it;

###2.  new jQuery.fn.init( selector, context, rootjQuery )

```
function jQuery(){
	new jQuery.fn.init( selector, context, rootjQuery );	
}
jQuery.prototype.init = function(){};
jQuery.prototype.css = function(){};
jQuery.prototype.addClass = function(){};
//then key operative
jQuery.prototype.init = jQuery.prototype;

```




