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
> (1)
```
	if ( selector.charAt(0) === "<" && selector.charAt( selector.length - 1 ) === ">" && selector.length >= 3 ) {
```
in case:$('\<div></div>'),$('\div')

```
				match = [ null, selector, null ];
```
Use for construct a constructiv regex


> (2)
```
				match = rquickExpr.exec( selector );
```
in case:$('\<div>hello world')
```
	rquickExpr = /^(?:\s*(<[\w\W]+>)[^>]*|#([\w-]*))$/;
```
> 1. $('\<li>hello') will match:["<li>", "<li>", undefined]

> 2. $('<li>test</li>hello') will match:will return:["<li>test</li>", "<li>test</li>", undefined]

> (3)
```
			if ( match && (match[1] || !context) ) {
```
handler create html,or,id, e.g:$('\<li>'),$("#id")
```
					context = context instanceof jQuery ? context[0] : context;
```
here use

```
context && context.nodeType ? context.ownerDocument || context : document,
```
handler if context is DOM

handler:$('li',iframe), to create html in iframe,**context** must be native DOM object

```
					if ( rsingleTag.test( match[1] ) && jQuery.isPlainObject( context ) ) {
```
handler:$('li',{title:1,id:"id"}), HANDLE: $(html, props)

```
					if ( elem && elem.parentNode ) {
```

					Check parentNode to catch when Blackberry 4.6 returns
					nodes that are no exite




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

in case:$($('.div')), things like this

```
		jQuery.makeArray( selector, this );
```

conert it to arraylike object,e.g:{0:'aa',1:'bb',length:2}



##



