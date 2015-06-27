#jQuery.prototype

##1.constructor: jQuery
```
constructor: jQuery,

```
Use for fix jQuery constructor pointer,cause jQuery.prototype is overed by '{} '

##2.  jQuery.prototype.init() method
####1.
```
if ( !selector ) {
			return this;
		}

```
In case: $(''),$(null),$(undefined),$(false)

####2.
```
if ( typeof selector === "string" )
```
In case:$("#id"),$(".class"),$("div"),$("#id .class div.div"),$('\<li>'),$("\<div>1</div>");
**(1)**
```
	if ( selector.charAt(0) === "<" && selector.charAt( selector.length - 1 ) === ">" && selector.length >= 3 ) {
```
in case:$('\<div></div>'),$('\div')

```
				match = [ null, selector, null ];
```
Use for construct a constructiv regex


**(2)**
```
				match = rquickExpr.exec( selector );
```
in case:$('\<div>hello world')
```
	rquickExpr = /^(?:\s*(<[\w\W]+>)[^>]*|#([\w-]*))$/;
```
> 1. $('\<li>hello') will match:["<li>", "<li>", undefined]

> 2. $('<li>test</li>hello') will match:will return:["<li>test</li>", "<li>test</li>", undefined]



####3.
```
		} else if ( selector.nodeType ) {

```
in case:$(document),$(element),$(this)


####4.
```
		} else if ( jQuery.isFunction( selector ) ) {

```

in case:$(function(){})

####5.

```
		if ( selector.selector !== undefined ) {
			this.selector = selector.selector;
			this.context = selector.context;
		}

```

in case: $([]),$({});







