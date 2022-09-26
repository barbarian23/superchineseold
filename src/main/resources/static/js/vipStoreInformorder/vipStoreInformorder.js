$(document).ready(function() {
	initValuePrice();
	convertPrice();
});

$('.btn-back').click(function() {
	window.location.href = "goi-vip-superchinese";
});

$('.vat-control').click(function() {
	if ($('input[name="orderVat"]:checked').val() == 'isCheck') {
		discountVatBlock();
		totalVatBlock();
		totalPriceFinalVatBlock();

	} else {
		discountVatNone();
		totalVatNone();
		totalPriceFinalVatNone();
	}
});

$('.btn-buy').click(function() {
	window.location.href = "thanh-toan-goi-vip";
});

function initValuePrice() {
	var scPrice = scPriceOrigin();
	var stPrice = stPriceOrigin();
	if (scPrice != 0) {
		$('.sc-price-origin').find('span').text(scPriceOrigin());
		scPriceBlock();
	} else {
		scPriceNone();
	}

	if (stPrice != 0) {
		$('.st-price-origin').find('span').text(stPriceOrigin());
		stPriceBlock();
	} else {
		stPriceNone();
	}

	if (scPrice != 0) {
		$('.sc-price-origin').find('span').text(scPriceOrigin());
		scPriceBlock();
	} else {
		scPriceNone();
	}

	$('.st-price-origin').find('span').text(stPriceOrigin());
	$('.price-sale-total').find('span').text(SalePrice());
}

function convertPrice() {
	$(".price-fm").each(function() {
		var value = $(this).find('span').text();
		$(this).find('span').text(formatPrice(value));
	})
}

var requestTimer;
var requestTimerExist;
var xhr;
var saleCodeOld = $(".enter-code").val();
$('.enter-code').keyup (function() {
//	if (requestTimer) window.clearTimeout(requestTimer);
	var newSaleCode = $(".enter-code").val();
	if (newSaleCode != '') {
		if(saleCodeOld != newSaleCode) {
			requestTimer = setTimeout(checkSaleCode, 500);
			saleCodeOld = newSaleCode;
		}
	} else {
		illegalFalseDisplayNone();
		illegalSuccessDisplayNone();
		salePeriodNone();
		saleDisplayNone();
		setpriceSaleTotal();
	}
})

function checkSaleCode() {
	xhr = $.ajax({
		type: "POST",
		url: "xac-nhan-thanh-toan-goi-vip-superchinese/salecode",
		data: {
			saleCode: $(".enter-code").val()
		},
		dataType: 'json',
		cache: false,
		success: function(data) {
			if (data.code == 200) {
				illegalFalseDisplayNone();
				illegalSuccessDisplayBlock();
				$('.sale-period').find('span').text(data.data.amountIsReduced);
				salePeriodBlock();
				$('.price-sale-total').find('span').text(data.data.amountIsReduced);
				saleDisplayBlock();
				setpriceSaleTotal();
			} else {
				illegalFalseDisplayBlock();
				illegalSuccessDisplayNone();
				salePeriodNone();
				saleDisplayNone();
				setpriceSaleTotal();
			}
		}
	});
}

$('.sc-account').keyup (function() {
	var valsc = $(".sc-account").val();
	requestTimerExist = setTimeout(checkExistAccount('sc',valsc), 500);
})

$('.st-account').keyup (function() {
	var valst = $(".st-account").val();
	requestTimerExist = setTimeout(checkExistAccount('st',valst), 500);
})

function checkExistAccount(pack, val) {
	xhr = $.ajax({
		type: "POST",
		url: "xac-nhan-thanh-toan-goi-vip-superchinese/existAccount",
		data: {
			packages: pack,
			account:val
		},
		dataType: 'json',
		cache: false,
		success: function(data) {
			if (data.code == 200) {
				if(pack =='sc') {
					$('.sc-account-exist').css('display','block');
					$('.sc-account-exist').text(data.message);
				} else {
					$('.st-account-exist').css('display','block');
					$('.st-account-exist').text(data.message);
				}
				
			} else {
				if(pack =='st') {
					$('.sc-account-exist').css('display','block');
					$('.sc-account-exist').text('Lỗi hệ thống, vui lòng thử lại sau');
				} else {
					$('.st-account-exist').css('display','block');
					$('.st-account-exist').text('Lỗi hệ thống, vui lòng thử lại sau');
				}
			}
		}
	});
}


function setpriceSaleTotal() {
	if (SalePrice() > 0) {
		$('.price-sale-total').find('span').text(SalePrice());
		saleDisplayBlock()
	} else {
		saleDisplayNone();
	}
}

function setpriceSaleTotal() {
	if (SalePrice() > 0) {
		$('.price-sale-total').find('span').text(SalePrice());
		saleDisplayBlock()
	} else {
		saleDisplayNone();
	}
}

function scPriceBlock() {
	$('.sc-price-origin').css('display', 'block');
}

function scPriceNone() {
	$('.sc-price-origin').css('display', 'none');
}

function stPriceBlock() {
	$('.st-price-origin').css('display', 'block');
}

function stPriceNone() {
	$('.st-price-origin').css('display', 'none');
}
function saleDisplayBlock() {
	$('.price-sale-total').css('display', 'block');
}

function saleDisplayNone() {
	$('.price-sale-total').find('span').text('');
	$('.price-sale-total').css('display', 'none');
}

function salePeriodBlock() {
	$('.sale-period').css('display', 'block');
}
function salePeriodNone() {
	$('.sale-period').css('display', 'none');
}

function illegalFalseDisplayBlock() {
	$('.illegal-code-false').css('display', 'block');
}

function illegalFalseDisplayNone() {
	$('.illegal-code-false').css('display', 'none');
}

function illegalSuccessDisplayBlock() {
	$('.illegal-code-success').css('display', 'block');
}

function illegalSuccessDisplayNone() {
	$('.illegal-code-success').css('display', 'none');
}

function discountVatNone() {
	$('.discount-vat').css('display', 'none');
}

function discountVatBlock() {
	$('.discount-vat').css('display', 'block');
}

function totalVatNone() {
	$('.total-vat').css('display', 'none');
}

function totalVatBlock() {
	$('.total-vat').css('display', 'block');
}

function totalPriceFinalVatNone() {
	$('.total-price-final-vat').css('display', 'none');
	$('.total-price-final').css('display', 'block');
}

function totalPriceFinalVatBlock() {
	$('.total-price-final-vat').css('display', 'block');
	$('.total-price-final').css('display', 'none');
}

function scPriceOrigin() {
	var price = $('.sc-price').find('span').text();
	if ($('.sc-price').find('span').text() != '') {
		return parseInt(price);
	}
	return 0;
}

function scPriceSale() {
	var price = $('.sc-price-sale').find('span').text();
	if ($('.sc-price').find('span').text() != '') {
		return parseInt(price);
	}
	return 0;
}

function stPriceOrigin() {
	var price = $('.st-price').find('span').text();
	if ($('.st-price').find('span').text() != '') {
		return parseInt(price);
	}
	return 0;
}

function stPriceSale() {
	var price = $('.st-price-sale').find('span').text();
	if ($('.st-price').find('span').text() != '') {
		return parseInt(price);
	}
	return 0;
}

function SalePrice() {
	var price = $('.sale-period').find('span').text();
	if ($('.sc-price').find('span').text() != '') {
		return parseInt(price);
	}
	return 0;
}

function formatPrice(price) {
	if (price != null && price != '') {
		return new Intl.NumberFormat('de-DE').format(price);
	} else {
		return 0;
	}
}