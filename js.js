 var curIndex = 0; //当前index
 $(".index-button").click(function() {
   if (curIndex < $(".imgList li").length - 1) {
     curIndex++;
   } else {
     curIndex = 0;
   }
   //调用变换处理函数
   changeTo(curIndex);
 });


 function changeTo(num) {
   $(".banner_pic").find("li").removeClass("imgOn").hide().eq(num).fadeIn().addClass("imgOn");
 }