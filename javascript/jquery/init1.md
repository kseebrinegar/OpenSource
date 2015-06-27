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







