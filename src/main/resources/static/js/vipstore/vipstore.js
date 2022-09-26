$(document).ready(function() {
	convertPrice();
});
var scPrice = 0;
var stPrice = 0;
$('.chinese-package').click(function() {
	if ($('.test-package').find('.box-item').find('.checked').length > 0) {
		var uncheckedSt = '<div class="unchecked"><img src="images/unchecked.svg" title="unchecked" alt="unchecked"></div>';
		stPrice = 0;
		typePackTest = 0;
		$('#packageCode_st').val('');
		$('.test-package').find('.box-item').each(function() {
			if ($(this).find('.checked').length > 0) {
				$(this).removeClass('item-choised');
				$(this).find('.checked').remove();
				$(this).prepend(uncheckedSt);
			}
		});
		$('.test-store').css('display', 'none');
		$('.test-store').find('.box-item').remove();
	}
	$('.orderDisplay').css("display", "block");

	//	$(this).find('.box-item').css("border", "1px solid #da423e;");
	var checked = '<div class="checked"><img src="images/checked.svg" title="checked" alt="checked"></div>';
	var unchecked = '<div class="unchecked"><img src="images/unchecked.svg" title="unchecked" alt="unchecked"></div>';
	var typePackChinese = 0;
	if ($(this).find('.pack-month').length > 0) {
		typePackChinese = 1;
		$('#packageCode_sc').val('SCM');
		scPrice = $('#sellPrice_m').val();
	}
	if ($(this).find('.pack-year').length > 0) {
		typePackChinese = 2;
		$('#packageCode_sc').val('SCY');
		scPrice = $('#sellPrice_y').val();
	}
	if ($(this).find('.pack-limited').length > 0) {
		typePackChinese = 3;
		$('#packageCode_sc').val('SCL');
		scPrice = $('#sellPrice_l').val();
	}

	if ($(this).find('.box-item').find('.checked').length > 0) {
		scPrice = 0;
		typePackChinese = 0;
		$('#packageCode_sc').val('');
		$(this).find('.box-item').find('.checked').remove();
		$(this).find('.box-item').prepend(unchecked);
		$(this).find('.box-item').removeClass('item-choised');
	} else {
		$(this).find('.box-item').addClass("item-choised");
		$(this).find('.box-item').find('.unchecked').remove();
		$('.chinese-package').find('.box-item').each(function() {
			if ($(this).find('.checked').length > 0) {
				$(this).find('.checked').remove();
				$(this).removeClass('item-choised');
				$(this).prepend(unchecked);
			}
		})
		$(this).find('.box-item').prepend(checked);

	}

	var pgk = '';
	var origin = '';

	if (typePackChinese == 0) {
		$('.chinese-store').css('display', 'none');
		$('.chinese-store').find('.box-item').remove();
	} else {
		if (typePackChinese == 1) {
			pgk = '<p class="pgk"><span>1</span> tháng </p>';
			origin = '<p class="price"><span>' + formatPrice($('#originalPrice_m').val()) + '</span> vnđ </p>';
			if ($('#sellPrice_m').val() != $('#originalPrice_m').val()) {
				origin = '<p class="origin"><span>' + formatPrice($('#originalPrice_m').val()) + '</span> vnđ </p>' +
					'<p class="discount"><span>' + formatPrice($('#sellPrice_m').val()) + '</span> vnđ </p>';
			}
		}
		if (typePackChinese == 2) {
			pgk = '<p class="pgk"><span>1</span> năm </p>';
			origin = '<p class="price"><span>' + formatPrice($('#originalPrice_y').val()) + '</span> vnđ </p>';
			if ($('#sellPrice_y').val() != $('#originalPrice_y').val()) {
				origin = '<p class="origin"><span>' + formatPrice($('#originalPrice_y').val()) + '</span> vnđ </p>' +
					'<p class="discount"><span>' + formatPrice($('#sellPrice_y').val()) + '</span> vnđ </p>';
			}
		}
		if (typePackChinese == 3) {
			pgk = '<p class="pgk"><span>Life Time</span><br> Không giới hạn</p>';
			origin = '<p class="price"><span>' + formatPrice($('#originalPrice_l').val()) + '</span> vnđ </p>';
			if ($('#sellPrice_l').val() != $('#originalPrice_l').val()) {
				origin = '<p class="origin"><span>' + formatPrice($('#originalPrice_l').val()) + '</span> vnđ </p>' +
					'<p class="discount"><span>' + formatPrice($('#sellPrice_l').val()) + '</span> vnđ </p>';
			}
		}
		var content = '\
		    <div class="box-item unlimited">'
			+ pgk
			+ origin +
			'\
			</div>\
			';
		$('.chinese-store').css('display', 'block');
		$('.chinese-store').find('.box-item').remove();
//		$('.chinese-store').find('.tuvan').remove();
		$('.chinese-store').find('.tuvan').before(content);
//		$('.chinese-store').append(tuvan);
		scrollToOrder();

	}
	$('.t-chinese').find('p').remove();
	$('.t-test').find('p').remove();
	$('.total-prices').find('p').remove();

	$('.t-chinese').append('<p><span>' + formatPrice(scPrice) + '</span> vnđ</p>');
	$('.t-test').append('<p><span>' + formatPrice(stPrice) + '</span> vnđ</p>');
	$('.total-prices').append('<p class="t-total"><span>' + formatPrice(parseInt(scPrice) + parseInt(stPrice)) + '</span> vnđ</p>');
	displayOrder();
});
$('.test-package').click(function() {
	if ($('.chinese-package').find('.box-item').find('.checked').length > 0) {
		var uncheckedSc = '<div class="unchecked"><img src="images/unchecked.svg" title="unchecked" alt="unchecked"></div>';
		scPrice = 0;
		typePackChinese = 0;
		$('#packageCode_sc').val('');
		$('.chinese-package').find('.box-item').each(function() {
			if ($(this).find('.checked').length > 0) {
				$(this).find('.checked').remove();
				$(this).removeClass('item-choised');
				$(this).prepend(uncheckedSc);
			}
		});
		$('.chinese-store').css('display', 'none');
		$('.chinese-store').find('.box-item').remove();
	}

	$('.orderDisplay').css("display", "block");
	var checked = '<div class="checked"><img src="images/checked2.svg" title="checked" alt="checked"></div>';
	var unchecked = '<div class="unchecked"><img src="images/unchecked.svg" title="unchecked" alt="unchecked"></div>';
	var typePackTest = 0;
	if ($(this).find('.pack-month').length > 0) {
		typePackTest = 1;
		$('#packageCode_st').val('STM');
		stPrice = $('#sellPrice_st_m').val();
	}
	if ($(this).find('.pack-year').length > 0) {
		typePackTest = 2;
		$('#packageCode_st').val('STY');
		stPrice = $('#sellPrice_st_y').val();
	}
	if ($(this).find('.pack-limited').length > 0) {
		typePackTest = 3;
		$('#packageCode_st').val('STL');
		stPrice = $('#sellPrice_st_l').val();
	}

	if ($(this).find('.box-item').find('.checked').length > 0) {
		stPrice = 0;
		typePackTest = 0;
		$('#packageCode_st').val('');
		$(this).find('.box-item').find('.checked').remove();
		$(this).find('.box-item').prepend(unchecked);
		$(this).find('.box-item').removeClass('item-choised');
	} else {
		$(this).find('.box-item').addClass("item-choised");
		$(this).find('.box-item').find('.unchecked').remove();
		$('.test-package').find('.box-item').each(function() {
			if ($(this).find('.checked').length > 0) {
				$(this).removeClass('item-choised');
				$(this).find('.checked').remove();
				$(this).prepend(unchecked);
			}
		})
		$(this).find('.box-item').prepend(checked);

	}
	var pgk = '';
	var origin = '';

	if (typePackTest == 0) {
		$('.test-store').css('display', 'none');
		$('.test-store').find('.box-item').remove();
	} else {
		if (typePackTest == 1) {
			pgk = '<p class="pgk"><span>1</span> tháng </p>';
			origin = '<p class="price"><span>' + formatPrice($('#originalPrice_st_m').val()) + '</span> vnđ </p>';
			if ($('#sellPrice_st_m').val() != $('#originalPrice_st_m').val()) {
				origin = '<p class="origin"><span>' + formatPrice($('#originalPrice_st_m').val()) + '</span> vnđ </p>' +
					'<p class="discount"><span>' + formatPrice($('#sellPrice_st_m').val()) + '</span> vnđ </p>';
			}
		}
		if (typePackTest == 2) {
			pgk = '<p class="pgk"><span>1</span> năm </p>';
			origin = '<p class="price"><span>' + formatPrice($('#originalPrice_st_y').val()) + '</span> vnđ </p>';
			if ($('#sellPrice_st_y').val() != $('#originalPrice_st_y').val()) {
				origin = '<p class="origin"><span>' + formatPrice($('#originalPrice_st_y').val()) + '</span> vnđ </p>' +
					'<p class="discount"><span>' + formatPrice($('#sellPrice_st_y').val()) + '</span> vnđ </p>';
			}
		}
		if (typePackTest == 3) {
			pgk = '<p class="pgk"><span>Life Time</span><br> Không giới hạn</p>';
			origin = '<p class="price"><span>' + formatPrice($('#originalPrice_st_l').val()) + '</span> vnđ </p>';
			if ($('#sellPrice_st_l').val() != $('#originalPrice_st_l').val()) {
				origin = '<p class="origin"><span>' + formatPrice($('#originalPrice_st_l').val()) + '</span> vnđ </p>' +
					'<p class="discount"><span>' + formatPrice($('#sellPrice_st_l').val()) + '</span> vnđ </p>';
			}
		}

		var content = '\
		    <div class="box-item unlimited">'
			+ pgk
			+ origin +
			'\
			</div>\
			';
		$('.test-store').css('display', 'block');
		$('.test-store').find('.box-item').remove();
		$('.test-store').find('.tuvan').before(content);
		scrollToOrder();
	}
	$('.t-chinese').find('p').remove();
	$('.t-test').find('p').remove();
	$('.total-prices').find('p').remove();

	$('.t-test').append('<p><span>' + formatPrice(stPrice) + '</span> vnđ</p>');
	$('.t-chinese').append('<p><span>' + formatPrice(scPrice) + '</span> vnđ</p>');
	$('.total-prices').append('<p class="t-total"><span>' + formatPrice(parseInt(scPrice) + parseInt(stPrice)) + '</span> vnđ</p>');
	displayOrder();
});

function displayOrder() {
	if ($('.chinese-store').find('.box-item').length == 0 && $('.test-store').find('.box-item').length == 0) {
		$('.orderDisplay').css('display', 'none');
	}
}
function formatPrice(price) {
	if (price != null && price != '') {
		return new Intl.NumberFormat('de-DE').format(price);
	} else {
		return 0;
	}
}
function convertPrice() {
	$(".price-fm").each(function() {
		var value = $(this).find('span').text();
		$(this).find('span').text(formatPrice(value));
	})
}

function followOa() {
	window.open("https://zalo.me/4513138367536811082");
};
function scrollToOrder() {
	$('.ordertotal')[0].scrollIntoView();
}