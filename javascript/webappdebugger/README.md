#一，html5页面在手机上的调试 IOS篇

我们经常会遇到类似如：电脑上的模拟器上模拟的手机页面展示和js逻辑都没有问题的，在到了手机上就有问题了。又不知道出了什么问题，于是 ，我们必须在真手机上调试。在手机上调试页面是比较麻烦的事情，但苹果手机和安卓手机都提供了一套调试功能给我们。我这里只讲iphone上的页面调试


##在mac上调试iphone上的手机页面方式
**注意：一定要有数据线连你的苹果手机到到你的mac上，不然，就不行喽，准备好你的苹果数据线**
**步骤：**
* 1.  手机上设置：  设置->safri->高级->开启：（1）web审查器（2）javascript功能
![alt](https://github.com/richardgong1987/OpenSource/blob/master/javascript/webappdebugger/iphoneset.PNG)

* 2.  用手机safri打开你想调试的网页

**注意：在mac上，有些同学可能找不到develop选项，解决办法：safri偏好设置->高级->显示开发菜单在菜单工具栏上**
* 3.再在mac上打开Safari点击【Develop】菜单,我的手机名叫：richardgong-iphone6+,点开这，就有我在手机打开的网页，然后，点它，就可以在mac调试了
![alt](https://github.com/richardgong1987/OpenSource/blob/master/javascript/webappdebugger/mac.png)

**在mac上的调试效果**

![alt](https://github.com/richardgong1987/OpenSource/blob/master/javascript/webappdebugger/deb.png)



#结合charles进行手机上页面调试
**注意：手机抓包，他们一定要在同个网段内，不然，就抓不到的**
* 1.打开charles 设置 > 端口8888,记录勾选上：Enable transparent HTTP proxying
* 2.打开charles 访问控制设置 > 添加上你手机上的ip地址
* 3.打开手机 设置 > wifi>http proxy>手动>port:8888,server为mac/pc上的ip地址
* 4.第一次使用，charles会弹出访问提示，点同意。即可，mac上的修改就会影响到手机上的效果




#二，html5页面在手机上的调试 android+chrome篇

##1.mac上的谷歌浏览器地址栏上输入：chrome://inspect/#devices
##2.【设置】>【开发者选项】>【USB调试】打开手机USB调试。
##3.用数据钱让mac和android。
##4.在手机上，输入自己要浏览器的网页
##5，刷新mac上的chrome://inspect/#devices，就会检测到的设备上正在运行的网站下面那个【inspect]，即可

参考文档
http://www.codingserf.com/index.php/2014/05/debug-on-devices/


