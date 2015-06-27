#jQuery 2.0.3 source code  analysis

(function(){

###1.[define init variable:line 21-94](https://github.com/richardgong1987/OpenSource/blob/master/javascript/jquery/init.md)
###2.[add get and set method:line 96-283](https://github.com/richardgong1987/OpenSource/blob/master/javascript/jquery/init.md)
###3.[cread extend method:line 285-347](https://github.com/richardgong1987/OpenSource/blob/master/javascript/jquery/init.md)
###4.[jquery init variable:line 349-817](https://github.com/richardgong1987/OpenSource/blob/master/javascript/jquery/init.md)
###5.[jquery init variable:line 877-2856](https://github.com/richardgong1987/OpenSource/blob/master/javascript/jquery/init.md)
###5.[jquery init variable:line 2880-3042](https://github.com/richardgong1987/OpenSource/blob/master/javascript/jquery/init.md)
###6.[jquery init variable:line 3043-3183](https://github.com/richardgong1987/OpenSource/blob/master/javascript/jquery/init.md)
###7.[jquery init variable:line 3184-3295](https://github.com/richardgong1987/OpenSource/blob/master/javascript/jquery/init.md)
###8.[jquery init variable:line 3308-3652](https://github.com/richardgong1987/OpenSource/blob/master/javascript/jquery/init.md)
###9.[jquery init variable:line 3653-3797](https://github.com/richardgong1987/OpenSource/blob/master/javascript/jquery/init.md)
###10.[jquery init variable:line 3803-4299](https://github.com/richardgong1987/OpenSource/blob/master/javascript/jquery/init.md)
###11.[jquery init variable:line 4300-5128](https://github.com/richardgong1987/OpenSource/blob/master/javascript/jquery/init.md)
###12.[jquery init variable:line 5140-6057](https://github.com/richardgong1987/OpenSource/blob/master/javascript/jquery/init.md)
###13.[jquery init variable:line 6058-6620](https://github.com/richardgong1987/OpenSource/blob/master/javascript/jquery/init.md)
###14.[jquery init variable:line 6621-78544](https://github.com/richardgong1987/OpenSource/blob/master/javascript/jquery/init.md)
###15.[jquery init variable:line 7855-8584](https://github.com/richardgong1987/OpenSource/blob/master/javascript/jquery/init.md)
###16.[jquery init variable:line 8585-8792](https://github.com/richardgong1987/OpenSource/blob/master/javascript/jquery/init.md)
###17.[jquery init variable:line 8804-8821](https://github.com/richardgong1987/OpenSource/blob/master/javascript/jquery/init.md)
###18.[jquery init variable:line 8826](https://github.com/richardgong1987/OpenSource/blob/master/javascript/jquery/init.md)
})();



// JavaScript Document
    
(function(){
	
	√(21 , 94) 定义了一些变量和函数 jQuery = function(){};
	
	√(96 , 283) 给JQ对象，添加一些方法和属性
	
	√(285 , 347) extend : JQ的继承方法
	
	√(349 , 817) jQuery.extend() : 扩展一些工具方法
	
	(877 , 2856)  Sizzle : 复杂选择器的实现 
	
	√(2880 , 3042) Callbacks : 回调对象 : 对函数的统一管理
	
	√(3043 , 3183) Deferred : 延迟对象 : 对异步的统一管理
	
	√(3184 , 3295) support : 功能检测
	
	√(3308 , 3652) data() : 数据缓存
	
	√(3653 , 3797) queue() : 队列方法 : 执行顺序的管理 
	
	√(3803 , 4299) attr() prop() val() addClass()等 : 对元素属性的操作
	
	√(4300 , 5128) on() trigger() : 事件操作的相关方法
	
	√(5140 , 6057) DOM操作 : 添加 删除 获取 包装 DOM筛选
	
	√(6058 , 6620) css() : 样式的操作
	
	√(6621 , 7854) 提交的数据和ajax() : ajax() load() getJSON()
	
	√(7855 , 8584) animate() : 运动的方法
	
	(8585 , 8792) offset() : 位置和尺寸的方法
	
	(8804 , 8821) JQ支持模块化的模式
	
	(8826)  window.jQuery = window.$ = jQuery;
	
})();

