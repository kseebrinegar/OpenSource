/*!

 */

    (function($, window, document, undefined) {
        $.fn.loadImg = function(options) {
            var img = $(this).find('img');
            var length = img.length;

            function isFinish(){
                if(length<=0){
                    options.complete && options.complete();
                }
            }

            function handler(){
                length--;
                isFinish();
            }

            img.on({'load':handler,'error': handler});

            img.each(function(){
                var src = $(this).data('src');
                $(this).attr('src',src);
            });

            return this;
        };

    })(jQuery, window, document);

