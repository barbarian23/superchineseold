<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="konec.superchinese.models.loginzalo.ZaloLoginInfo"%>
<%
String context = request.getContextPath();
ZaloLoginInfo zaloInfo = (ZaloLoginInfo) session.getAttribute("ZaloLoginInfo");
String zaloName = "";
String accesstokenZalo = "";
String loginZalo ="";
boolean agencyCallBack =false;
if (zaloInfo != null) {
	accesstokenZalo = zaloInfo.getAccesstoken();
	loginZalo = zaloInfo.getLoginFlag();
	zaloName = zaloInfo.getZaloName();
	request.setAttribute("loginZalo", loginZalo);
	agencyCallBack = zaloInfo.isAgencyCallBack();
}
%>
<link rel="stylesheet" href="/css/profile/profile.css">
<!-- <script src='https://www.google.com/recaptcha/api.js?hl=vi' async defer></script> -->
<div id="service" class="service">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="titlepage">
					<h2>Chúng tôi đáp ứng nhiều nghành nghề</h2>
					<!--<span>
                            luptatum. Libero eligendi molestias iure error animi totam laudantium, aspernatur similique id eos a
                            t consectetur illo culpa,
                        </span>-->
				</div>
			</div>
		</div>
	</div>
	<div class="service_bg">
		<div class="container-fluid">
			<div class="row g-2">
				<div class="col col-xs-3">
					<div class="service_box">
						<img src="/images/u42.png" title="" alt="" />
					</div>
				</div>
				<div class="col col-xs-3">
					<div class="service_box">
						<img src="/images/u44.png" title="" alt="" />
					</div>
				</div>
				<div class="col col-xs-3">
					<div class="service_box">
						<img src="/images/u46.png" title="" alt="" />
					</div>
				</div>
				<div class="col col-xs-3">
					<div class="service_box">
						<img src="/images/u41.png" title="" alt="" />
					</div>
				</div>
				<div class="col col-xs-3">
					<div class="service_box">
						<img src="/images/u47.png" title="" alt="" />
					</div>
				</div>
				<div class="col col-xs-3">
					<div class="service_box">
						<img src="/images/u45.png" title="" alt="" />
					</div>
				</div>
				<div class="col col-xs-3">
					<div class="service_box">
						<img src="/images/u43.png" title="" alt="" />
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="agency" class="agency">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="titlepage">
						<h2>Đăng ký làm đại lý</h2>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row g-3">
				<div class="col-sm-12 col-md-4 col-lg-4 col-xl-4">
					<input type="text" name="email" id="emailId"
						class="form-control email" class="form-control email"
						placeholder="Email" aria-label="Email"
						aria-describedby="basic-addon1" required> <span
						class="error-email error-class"></span>
				</div>
				<div class="col-sm-12 col-md-4 col-lg-4 col-xl-4">
					<input type="text" name="phone" id="phoneId"
						class="form-control phoneNumber" placeholder="Số điện thoại"
						aria-label="Phonenumber" aria-describedby="basic-addon1" required>
					<span class="error-phone error-class"></span>
				</div>
				<div class="col-sm-12 col-md-4 col-lg-4 col-xl-4">
					<button class="btn btn-light btn-register">Đăng ký</button>
					<!-- 						<div class="g-recaptcha hidden" style="margin-top: 10px;" -->
					<!-- 							data-sitekey="6Le_9GgfAAAAAN76iebotKcmAbB8bkznqZ-_NaI8"></div> -->
				</div>
				<span class="error-general error-class col-sm-12 col-md-4 col-lg-8 col-xl-8"></span> <span
					class="regist-success success-class col-sm-12 col-md-4 col-lg-8 col-xl-8"></span>
			</div>
		</div>
	</div>
</div>
<div class="loader">
	<div class="loading-full"></div>
</div>
<jsp:include page="../common/jsCommon.jsp" flush="true" />
<script type="text/javascript">
var hasLogin ='<%=loginZalo%>';
var agencyCallBack ='<%=agencyCallBack%>';
$(document).ready(function() {
	//agency
	if(agencyCallBack == 'true') {
		var email = localStorage.getItem('email');
		var phone = localStorage.getItem('phone');
		$('#emailId').val(email);
		$('#phoneId').val(phone);
		setTimeout(function(){registerAgency(email, phone)}, 1000);
	}
});

$('#emailId').keyup(function() {
	$('.error-general').text('');
	$('.error-general').css('display', 'none');
	$('.regist-success').text('');
	$('.regist-success').css('display', 'none');
	var email = $(this).val();
	if(!validateEmail(email)){
		$('#emailId').addClass('form-error-control');
// 		$('.error-email').text('Email không hợp lệ!');
// 		$('.error-email').css('display', 'block');
	} else {
		$('#emailId').removeClass('form-error-control')
// 		$('.error-email').text('');
// 		$('.error-email').css('display', 'none');
	}
	
});

$('#phoneId').keyup(function() {
	$('.error-general').text('');
	$('.error-general').css('display', 'none');
	$('.regist-success').text('');
	$('.regist-success').css('display', 'none');
	var phone = $(this).val();
	if(!validatePhone(phone)){
		$('#phoneId').addClass('form-error-control');
// 		$('.error-phone').text('Số điện thoại không hợp lệ!');
// 		$('.error-phone').css('display', 'block');
	} else {
		$('#phoneId').removeClass('form-error-control')
// 		$('.error-phone').text('');
// 		$('.error-phone').css('display', 'none');
	}
	
});
$('.btn-register').on('click', function() {
	var email = $('#emailId').val();
	var phone = $('#phoneId').val();
	if(!validateEmail(email)){
		$('#emailId').addClass('form-error-control');
		$('#emailId').focus();
		return;
	} else {
		$('#emailId').removeClass('form-error-control');
	}
	if(!validatePhone(phone)){
		$('#phoneId').addClass('form-error-control');
		$('#phoneId').focus();
		return;
	} else {
		$('#phoneId').removeClass('form-error-control');
	}
// 	var capcha = grecaptcha.getResponse();
// 	if(!capcha) {
// 		$('.error-email').text("Mã captcha không hợp lệ!");
// 		$('.error-email').css('display', 'block');
// 		return;
// 	}
	if(hasLogin == '') {
		localStorage.setItem('email', email);
		localStorage.setItem('phone', phone);
		loginAgency();
	} else {
		$('.loader').css('display','block');
		setTimeout(function(){registerAgency(email, phone)}, 1000);
	}
});

// $(".agency").keyup(function(event) {
// 	$(this).find('.g-recaptcha').removeClass('hidden');
//  });
 
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
			localStorage.removeItem("email");
			localStorage.removeItem("phone");
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
			} else if (data.return_code == 99) {
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
			} else if (data.return_code == 98) {
				window.location.href = 'https://zalo.me/4513138367536811082';
			}
		scrollToAgency();
		$('.loader').css('display','none');
		}
	});
}

function loginAgency() {
	var accesstoken = window.localStorage.getItem('accesstoken');
	var newAccesstoken ='<%=accesstokenZalo%>';
	
	if (newAccesstoken && newAccesstoken != accesstoken) {
		window.localStorage.setItem('accesstoken', newAccesstoken);
		accesstoken = newAccesstoken;
	}
	if (accesstoken) {
		window.location.href = '/dang-nhap?accesstoken='
				+ accesstoken + '&agency=1';
	} else {
		window.location.href = '/dang-nhap?agency=1';
	}
}
function validateEmail(email) {
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}
function validatePhone(phone) {
    const re = /^[0-9]{10}$/;
    return re.test(String(phone).toLowerCase());
}
function scrollToAgency() {
	$('#agency')[0].scrollIntoView();
}
</script>