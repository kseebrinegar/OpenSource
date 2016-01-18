#throttle用于处理以下情况

#事件频繁被触发，因而频繁执行DOM操作、资源加载等重行为，导致UI停顿甚至浏览器崩溃。

 * 1. window对象的resize、scroll事件

 * 2. 拖拽时的mousemove事件

 * 3. 射击游戏中的mousedown、keydown事件

 * 4. 文字输入、自动完成的keyup事件

 * 实际上对于window的resize事件，实际需求大多为停止改变大小n毫秒后执行后续处理；而其他事件大多的需求是以一定的频率执行后续处理。针对这两种需求就出现了debounce和throttle两种解决办法。
 

# throttle 和 debounce 是解决请求和响应速度不匹配问题的两个方案。二者的差异在于选择不同的策略。

我们可以用电梯来形容它们的区别:
##电梯超时

###想象每天上班大厦底下的电梯。把电梯完成一次运送，类比为一次函数的执行和响应。假设电梯有两种运行策略 throttle 和 debounce ，超时设定为15秒，不考虑容量限制。

* throttle 策略的电梯。保证如果电梯第一个人进来后，15秒后准时运送一次，不等待。如果没有人，则待机。
* debounce 策略的电梯。如果电梯里有人进来，等待15秒。如果又人进来，15秒等待重新计时，直到15秒超时，开始运送。

```
   function throttle(func, wait, options) {
         var context, args, result;
         var timeout = null;
         var previous = 0;
         if (!options) options = {};
         var later = function () {
             previous = options.leading === false ? 0 : new Date().getTime();
             timeout = null;
             result = func.apply(context, args);
             if (!timeout) context = args = null;
         };
         return function () {
             var now = new Date().getTime();
             if (!previous && options.leading === false) previous = now;
             var remaining = wait - (now - previous);
             context = this;
             args = arguments;
             if (remaining <= 0 || remaining > wait) {
                 if (timeout) {
                     clearTimeout(timeout);
                     timeout = null;
                 }
                 previous = now;
                 result = func.apply(context, args);
                 if (!timeout) context = args = null;
             } else if (!timeout && options.trailing !== false) {
                 timeout = setTimeout(later, remaining);
             }
             return result;
         };
     }
 
 
     function debounce(func, wait, immediate) {
         var timeout, args, context, timestamp, result;
 
         var later = function() {
             var last = new Date().getTime() - timestamp;
 
             if (last < wait && last >= 0) {
                 timeout = setTimeout(later, wait - last);
             } else {
                 timeout = null;
                 if (!immediate) {
                     result = func.apply(context, args);
                     if (!timeout) context = args = null;
                 }
             }
         };
 
         return function() {
             context = this;
             args = arguments;
             timestamp = new Date().getTime();
             var callNow = immediate && !timeout;
             if (!timeout) timeout = setTimeout(later, wait);
             if (callNow) {
                 result = func.apply(context, args);
                 context = args = null;
             }
 
             return result;
         };
     };

```

