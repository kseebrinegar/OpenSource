# 按需加载图片LoadImg方法使用文档
下载js文件
https://raw.githubusercontent.com/richardgong1987/openMaterial/master/javascript/lazyload/LoadImg.js

首先：
把所有图片的src属性替换成data-src
比如：
```html
<img src="a.jpeg" />
```
应该替换成：
```html
<img data-src="a.jpeg" />
```

####场景一：加载某个区域内的所有图片
如,加载load区域的所有图片：
```html
<div class="load">
    <img  alt="" data-src="a.jpeg"/><img  alt="" data-src="b.png"/>
</div>
```

js部分只需要：

```js
$('.load img').loadImg()
```
即可




####场景二：加载当前元素集合，或，单个图片。
如,

```html
    <img  alt="" data-src="a.jpeg"/><img  alt="" data-src="b.png"/>
```

```js
$('img').loadImg()
```
即可
