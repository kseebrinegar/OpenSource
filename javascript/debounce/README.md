#debounce  implement

```js

 function debounce(time, callback) {
        var last;
        return function() {
            var ctx = this,
                args = arguments;
            clearTimeout(last);
            last = setTimeout(function() {
                callback.apply(ctx, args);
            }, time);
        };
    };

	


    var debounceIt = debounce(800, function() {
    	console.log('stop');   
    });

    document.body.addEventListener('mousemove',function(){ 
    	debounceIt();
    });

```