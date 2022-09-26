$('.btn-extend').on('click', function() {
	window.location.href = "goi-vip-superchinese";
});

//$(".agency").keyup(function(event) {
//	$(this).find('.g-recaptcha').removeClass('hidden');
// });
// 
// $(".active-margin").keyup(function(event) {
//	$(this).find('.g-recaptcha').removeClass('hidden');
// });

$('.btn-active').on('click', function() {
//	var height = window.scrollY;
//	var loadheight = $('.loading-full').height();
//	$('.loading-full')
	var email = $('#avEmailId').val();
	var codeActive = $('#avCodeId').val();
	if(email =='') {
		$('#avEmailId').addClass('form-error-control');
		$('#avEmailId').focus();
		return;
	} else {
		$('#avEmailId').removeClass('form-error-control');
	}
	if(codeActive =='') {
		$('#avCodeId').addClass('form-error-control');
		$('#avCodeId').focus();
		return;
	} else {
		$('#avCodeId').removeClass('form-error-control');
	}
//	var capcha = grecaptcha.getResponse();
//	if(!capcha) {
//		$('.at-error-email').text("Mã captcha không hợp lệ!");
//		$('.at-error-email').css('display', 'block');
//		return;
//	}
	$('.loader').css('display','block');
	setTimeout(function(){activePackage(email, codeActive)}, 1000);
})

function activePackage(email, codeActive) {
	var licenseModel = {
		email: email,
		codeAc: codeActive
	};
	$.ajax({
		type: "POST",
		url: "activeLicense",
		data: licenseModel,
		dataType: 'json',
		cache: false,
		success: function(data) {
			$('.at-error-general').text('');
			$('.at-error-general').css('display', 'none');
			$('.at-error-email').text('');
			$('.at-error-email').css('display', 'none');
			$('.at-error-code').text('');
			$('.at-error-code').css('display', 'none');
			$('.at-license-success').text('');
			$('.at-license-success').css('display', 'none');
			if (data.return_code == 1) {
				$('.at-license-success').css('display', 'block');
				$('.at-license-success').text('Kích hoạt mã VIP thành công!');
				$(".class-code").each(function() {
					 var $this = $(this);
					 if($this.text() == $('#avCodeId').val()) {
						$this.closest('tr').find('.class-active').text("Đã kích hoạt");
					}
				});
			} else if (data.return_code == 100) {
				//timeout token
				localStorage.removeItem("accesstoken");
				window.location.href = 'error';

			} else if (data.return_code == 99) {
				if (data.error.general !== undefined) {
					$('.at-error-general').text(data.error.general);
					$('.at-error-general').css('display', 'block');
				}
				if (data.error.email !== undefined) {
					$('.at-error-email').text(data.error.email);
					$('.at-error-email').css('display', 'block');
				}
				if (data.error.codeAc !== undefined) {
					$('.at-error-code').text(data.error.codeAc);
					$('.at-error-code').css('display', 'block');
				}
			}
			$('.loader').css('display','none');
		}
	});
}


function loginZalo() {
	if (accesstoken != null) {
		window.location.href = pathName + '/dang-nhap?accesstoken=' + accesstoken;
	} else {
		window.location.href = pathName + '/dang-nhap';
	}
}

$('.btn-register').on('click', function() {
	var email = $('#emailId').val();
	var phone = $('#phoneId').val();
	if(email == '') {
		$('#emailId').addClass('form-error-control');
		$('#emailId').focus();
		return;
	} else {
		$('#emailId').removeClass('form-error-control');
	}
	if(phone=='') {
		$('#phoneId').addClass('form-error-control');
		$('#phoneId').focus();
		return;
	} else {
		$('#phoneId').removeClass('form-error-control');
	}
//	var capcha = grecaptcha.getResponse();
//	if(!capcha) {
//		$('.error-email').text("Mã captcha không hợp lệ!");
//		$('.error-email').css('display', 'block');
//		return;
//	}
	
	$('.loader').css('display','block');
	setTimeout(function(){registerAgency(email, phone)}, 1000);
})

function registerAgency(email, phoneNumber) {
	var agencyModel = {
		email: email,
		phoneNumber: phoneNumber
	};
	$.ajax({
		type: "POST",
		url: "agency",
		data: agencyModel,
		dataType: 'json',
		cache: false,
		success: function(data) {
			$('.error-email').text('');
			$('.error-email').css('display', 'none');
			$('.error-phone').text('');
			$('.error-phone').css('display', 'none');
			$('.error-general').text('');
			$('.error-general').css('display', 'none');
			$('.regist-success').text('');
			$('.regist-success').css('display', 'none');
			if (data.return_code == 1) {
				$('.regist-success').css('display', 'block');
				$('.regist-success').text('Cảm ơn bạn đã liên hệ, thông tin của bạn đã được ghi nhận. Chúng tôi sẽ liên hệ với bạn trong thời gian sớm nhất!');
			} else if (data.return_code == 1400) {
				if (data.error.general !== undefined) {
					$('.error-general').text(data.error.general);
					$('.error-general').css('display', 'block');
				}
			} else {
				if (data.error.email !== undefined) {
					$('.error-email').text(data.error.email);
					$('.error-email').css('display', 'block');
				}
				if (data.error.phoneNumber !== undefined) {
					$('.error-phone').text(data.error.phoneNumber);
					$('.error-phone').css('display', 'block');
				}
				if (data.error.general !== undefined) {
					$('.error-general').text(data.error.general);
					$('.error-general').css('display', 'block');
				}
			}
		$('.loader').css('display','none');
		}
	});
}