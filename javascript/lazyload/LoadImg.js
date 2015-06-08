/*!

 */

(function($, window, document, undefined) {
    $.fn.loadImg = function(options) {
        var elements = this;
        var settings = {
            data_attribute  : "src",
            onload: $.noop(),
            loadCount:0,
            placeholder     : "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAANSURBVBhXYzh8+PB/AAffA0nNPuCLAAAAAElFTkSuQmCC"
        };

        if(elements.prop('tagName') == 'IMG'){
            load();
        } else{
            elements = elements.find("img");
            load();
        }


        function load(){
            elements.each(function(){
                var src = $(this).data('src');
                $(this).attr('src',src);
            });
        }

        return this;
    };

})(jQuery, window, document);
