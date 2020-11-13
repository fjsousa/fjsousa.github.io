$('.masonry-grid').each(function() {
  var container = $(this);

  container.masonry({
    itemSelector: '.masonry-item',
    fitWidth: true,
    gutter: 30
  });
//???????
  container.find('img').one("load", function() {
    container.masonry('layout');
  }).each(function() {
    if(this.complete) {
      $(this).trigger('load');
    }
  });
});

$('.open-lightbox').click(function() {
  var lightbox = $(this).attr('data-lightbox');

  $('.lightbox[data-lightbox="' + lightbox + '"]').addClass('open');

  return false;
});

$('.lightbox .close').click(function() {
  $(this).parents('.lightbox').removeClass('open');
  return false;
});

$(document).click(function(e) {
  if(!$(e.target).is('.lightbox .inner') && !$(e.target).is('.lightbox .inner *')) {
    $('.lightbox').removeClass('open');
  }
});

$(document).keyup(function(e) {
  if(e.keyCode == 27) {
    $('.lightbox').removeClass('open');
  }
});

$(window).scroll(function() {
  var scrollTop = $('html, body').scrollTop();

  if(scrollTop > $(window).height()) {
    $('.back-to-top').show();
  }
  else {
    $('.back-to-top').hide();
  }
});

$('.back-to-top').click(function() {
  $('html, body').animate({
      scrollTop: 0
  }, 400);

  return false;
});
