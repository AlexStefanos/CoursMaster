$(function() {
    $('.Part').on("click", function() {
        if($(this).hasClass('central')) {
            if($(this).width() == 450) {
                $('Part').each(function() {
                    $(this).animate({width: '100px'});
                    $(this).find(".txtPannel").hide();
                });
                $(this).addClass('image');
                $(this).removeClass('central');
            }
        } else {
            $('.central').each(function() {
                $(this).find(".txtPannel").hide();
                $(this).animate({width: '100px' });
                $(this).addClass('image');
                $(this).removeClass('central');
            });
            $(this).find('.txtPannel').css({
                'top': $(this).position().top + 117,
                'left': $(this).position().left + 117
            });
            $(this).find('.txtPannel').show();
            $(this).animate({ width: '450px' });
            $(this).addClass('central');
            $(this).removeClass('image');
        }
    });
});