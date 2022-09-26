<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="konec.superchinese.models.loginzalo.ZaloLoginInfo"%>

<%@ page
	import="konec.superchinese.models.vippackages.VipStoreInformoder"%>
<%
String context = request.getContextPath();
ZaloLoginInfo zaloInfo = (ZaloLoginInfo) session.getAttribute("ZaloLoginInfo");
String accesstokenZalo = "";
String zaloName = "";
String loginZalo = "";
boolean paymentCallBack = false;
if (zaloInfo != null) {
	accesstokenZalo = zaloInfo.getAccesstoken();
	loginZalo = zaloInfo.getLoginFlag();
	zaloName = zaloInfo.getZaloName();
	request.setAttribute("loginZalo", loginZalo);
	paymentCallBack = zaloInfo.isPaymentCallBack();
}
VipStoreInformoder vipStoreInformoder = (VipStoreInformoder) request.getAttribute("vipStoreInformoder");
Integer scPrice = vipStoreInformoder.originalPrice_sc;
Integer scPriceSale = vipStoreInformoder.sellPrice_sc;
Integer stPrice = vipStoreInformoder.originalPrice_st;
Integer stPriceSale = vipStoreInformoder.sellPrice_st;
Integer totalPrice = vipStoreInformoder.totalPrice;
Integer vatPrice = vipStoreInformoder.vatPrice;
Integer totalPriceVat = vipStoreInformoder.totalPriceVat;
%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<jsp:include page="../common/metaCommon.jsp" flush="true" />
<meta name="keywords"
	content="Super,chinese, ứng, dụng, hoc, tiếng, trung, chính, hãng, số 1, việt, nam" />
<meta name="description"
	content="Super chinese ứng dụng học tiếng Trung chính hãng số 1 Việt Nam, ứng dụng học tiếng Trung giá rẻ tốt nhất 2022, ứng dụng học tiếng trung HSK mới nhất " />
<meta name="generator" content="Cty TNHH Konec" />
<jsp:include page="../common/cssCommon.jsp" flush="true" />
<link rel="stylesheet" href="css/paymentMethod/paymentMethod.css">
<!-- <script src='https://www.google.com/recaptcha/api.js?hl=vi' async defer></script> -->
<title>Thanh toán gói vip</title>
</head>

<body class="main-layout">
	<!--<div class="loader_bg">
        <div class="loader"><img src="/images/loading.gif" alt="#" /></div>
    </div>-->

	<header>
		<div class="header-top">
			<jsp:include page="../common/headerCommon.jsp" flush="true" />
			<jsp:include page="../common/subMenuCommon.jsp" flush="true" />
		</div>
	</header>

	<section id="vipstore" class="vipstore">
		<c:if test="${zaloName != null}">
			<div class="notification">
				<div class="container-fluid">
					<div class="row">
						<div class="col-12">
							<h1>Chào mừng ${zaloName} đến với superchinese.vn, Nhanh tay
								chọn mua gói VIP để tham gia học tiếng Trung cùng với chúng tôi!</h1>
						</div>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${zaloName == null}">
			<div class="notification">
				<div class="container-fluid">
					<div class="row">
						<div class="col-12">
							<h1>Chào mừng bạn đến với superchinese.vn, hãy đăng nhập để
								mua gói VIP!</h1>
						</div>
					</div>
				</div>
			</div>
		</c:if>

		<div class="vipinfo">
			<div class="container">
				<c:if test="${ paymentModel.status == false}">
					<div class="error-message">
						${paymentModel.message }
						<c:if test="${ followOa == '1'}">
							<a href="javascript:void(0)" id="followOa"
								style="text-decoration-line: underline !important; color: #1c999d;!important">follow
								ngay</a>
						</c:if>
					</div>
				</c:if>
				<c:if test="${ paymentModel.status == true}">
					<div class="success-message">${paymentModel.message }</div>
				</c:if>
				<div class="oa-message zaloOa-error"></div>
				<div class="row g-3 s-chinese">
					<div class="col-sm-6 col-md-3 col-lg-3 col-xl-3">
						<div class="item">
							<img src="/images/internet-banking.png" title="internet-banking"
								alt="internet-banking" />
							<h2>Thanh toán qua</h2>
							<p>Internet Banking</p>
						</div>
					</div>

					<div class="col-sm-6 col-md-3 col-lg-3 col-xl-3">
						<div class="box-item info">
							<!-- 							<div class="plus"> -->
							<!-- 								<p>+</p> -->
							<!-- 							</div> -->
							<%-- 							<c:if test="${vipStoreInformoder.packageCode_sc != ''}"> --%>
							<!-- 								<p class="t-chinese money adjust-margin price-fm"> -->
							<%-- 									<span>${vipStoreInformoder.sellPrice_sc}</span> vnđ --%>
							<!-- 								</p> -->
							<%-- 							</c:if> --%>
							<%-- 							<c:if test="${vipStoreInformoder.packageCode_st !=''}"> --%>
							<!-- 								<p class="t-chinese money adjust-margin price-fm"> -->
							<%-- 									<span>${vipStoreInformoder.sellPrice_st}</span> vnđ --%>
							<!-- 								</p> -->
							<%-- 							</c:if> --%>
							<%-- 							<c:if test="${vipStoreInformoder.discountCode != ''}"> --%>
							<!-- 								<p class="money red price-fm"> -->
							<%-- 									giảm <span>${vipStoreInformoder.discountValue }</span> vnđ --%>
							<!-- 								</p> -->
							<%-- 							</c:if> --%>
							<!-- 							<div class="t-line"></div> -->
							<c:if test="${vipStoreInformoder.vatStatus == false}">
								<p class="money price-fm adjust-padding">
									<span class="red">${vipStoreInformoder.totalPrice }</span> vnđ
								</p>
							</c:if>
							<c:if test="${vipStoreInformoder.vatStatus == true}">
								<!-- 								<p class="money price-fm"> -->
								<%-- 									<span>${vipStoreInformoder.vatPrice }</span> vnđ --%>
								<!-- 								</p> -->
								<!-- 								<p class="money red">(10% VAT)</p> -->
								<!-- 								<div class="t-line"></div> -->
								<div class="total">
									<p class="money price-fm adjust-padding">
										<span class="red">${vipStoreInformoder.totalPriceVat}</span>
										vnđ
									</p>
								</div>
							</c:if>

						</div>
					</div>

					<div class="col-sm-12 col-md-6 col-lg-6 col-xl-6">
						<input type="hidden" name="scCode" id="scCode"
							value="${vipStoreInformoder.packageCode_sc}"> <input
							type="hidden" name="stCode" id="stCode"
							value="${vipStoreInformoder.packageCode_st}">
						<div class="vipdesc">
							<button class="btn btn-zalopay btn-appotapay">Thanh toán
								ngay</button>
							<!-- 							<p>Gói tháng hoặc năm sẽ được thông báo tự động qua Zalo OA khi hết hạn</p> -->
							<!-- 							<p class="red">Khuyến mại: Từ ngày 01/01/2022 đến hết ngày -->
							<!-- 								28/02/2022, mọi thanh toán qua ví Zalo Pay đều được hoàn 50% -->
							<!-- 								tiền.</p> -->
							<!-- 							<button class="btn btn-zalopay btn-tu-van" style="margin-top: 90px;">Tư vấn giá rẻ</button> -->
						</div>
						<div class="banklist">
							<div class="form-group">
								<div class="row g-2">
									<div class="col">
										<img src="images/u872.svg" title="" alt="">
									</div>
									<div class="col">
										<img src="images/u873.svg" title="" alt="">
									</div>
									<div class="col">
										<img src="images/u874.png" title="" alt="" width="80">
									</div>
									<div class="col">
										<img src="images/u875.png" title="" alt="" width="80">
									</div>
									<div class="col">
										<img src="images/u876.svg" title="" alt="">
									</div>
									<div class="col">
										<img src="images/u877.svg" title="" alt="">
									</div>
									<!--<div class="col">
                            
                                <img src="images/u878.svg" title="" alt="" />
                            </a>
                        </div>-->
								</div>
							</div>
							<div class="form-group">
								<div class="row g-2">
									<div class="col">
										<img src="images/u878.svg" title="" alt="">
									</div>
									<div class="col">
										<img src="images/u879.svg" title="" alt="">
									</div>
									<div class="col">
										<img src="images/u880.svg" title="" alt="">
									</div>
									<div class="col">
										<img src="images/u881.svg" title="" alt="">
									</div>
									<div class="col">
										<img src="images/u882.svg" title="" alt="">
									</div>
									<div class="col">
										<img src="images/u883.svg" title="" alt="">
									</div>
									<!--<div class="col">
                            
                                <img src="images/u884.svg" title="" alt="" />
                            </a>
                        </div>
                        <div class="col">
                            
                                <img src="images/u885.svg" title="" alt="" />
                            </a>
                        </div>-->
								</div>
							</div>
						</div>
					</div>
				</div>

				<%--zalo pay --%>
				<%-- <div class="row g-3 s-chinese">
					<div class="col-sm-6 col-md-3 col-lg-3 col-xl-3">
						<div class="item">
							<img src="/images/zalopay.svg" title="zalopay" alt="zalopay" />
							<h2>Ví Zalo Pay</h2>
							<p>Liên kết thanh toán</p>
							<span>Gia hạn định kỳ</span>
						</div>
					</div>

					<div class="col-sm-6 col-md-3 col-lg-3 col-xl-3">
						<div class="box-item info">
							<div class="plus">
								<p>+</p>
							</div>
							<c:if test="${vipStoreInformoder.packageCode_sc != ''}">
								<p class="t-chinese money adjust-margin price-fm">
									<span>${vipStoreInformoder.sellPrice_sc}</span> vnđ
								</p>
							</c:if>
							<c:if test="${vipStoreInformoder.packageCode_st !=''}">
								<p class="t-chinese money adjust-margin price-fm">
									<span>${vipStoreInformoder.sellPrice_st}</span> vnđ
								</p>
							</c:if>
							<c:if test="${vipStoreInformoder.discountCode != ''}">
								<p class="money red price-fm">
									giảm <span>${vipStoreInformoder.discountValue }</span> vnđ
								</p>
							</c:if>
							<div class="t-line"></div>

							<p class="money price-fm">
								<span>${vipStoreInformoder.totalPrice }</span> vnđ
							</p>
							<c:if test="${vipStoreInformoder.vatStatus == true}">
								<p class="money price-fm">
									<span>${vipStoreInformoder.vatPrice }</span> vnđ
								</p>
								<p class="money red">(10% VAT)</p>
								<div class="t-line"></div>
								<div class="total">
									<p class="money red price-fm">
										<span>${vipStoreInformoder.totalPriceVat}</span> vnđ
									</p>
								</div>
							</c:if>

						</div>
					</div>

					<div class="col-sm-12 col-md-6 col-lg-6 col-xl-6">
						<input type="hidden" name="scCode" id="scCode"
							value="${vipStoreInformoder.packageCode_sc}"> <input
							type="hidden" name="stCode" id="stCode"
							value="${vipStoreInformoder.packageCode_st}">
						<div class="vipdesc">
							<button class="btn btn-zalopay btn-zalopayment">Thanh
								toán ngay</button>
							<p>Gói tháng hoặc năm sẽ được tự động gia hạn khi đến hạn</p>
<!-- 							<p class="red">Khuyến mại: Từ ngày 01/01/2022 đến hết ngày -->
<!-- 								28/02/2022, mọi thanh toán qua ví Zalo Pay đều được hoàn 50% -->
<!-- 								tiền.</p> -->
							<button class="btn btn-zalopay btn-tu-van" style="margin-top: 90px;">Tư vấn giá rẻ</button>
						</div>
					</div>
				</div> --%>
			</div>
		</div>
		<%-- <section id="activevip" class="activevip">
			<div id="agency" class="agency" style="height: 300px;">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<div class="titlepage">
								<h2>Đặt hàng</h2>
							</div>
						</div>
					</div>
				</div>
				<div class="container">
					<div class="row g-3">
						<div class="col-sm-12 col-md-4 col-lg-4 col-xl-4">
							<div class="form-group">
								<label class="control-label">Điền số điện thoại</label>
								<div class="p-name phone-selector">
									<input type="text" class="form-control" name="phoneNumber"
										id="phoneNumber" value="${orderByPhoneModel.phoneNumber}"
										placeholder="Điền số điện thoại của bạn" aria-label="Email"
										aria-describedby="basic-addon1">
								</div>
								<c:if test="${orderByPhoneModel.hasError == true}">
									<p class="phone-error error-class">${orderByPhoneModel.message}</p>
								</c:if>
							</div>
						</div>
						<div class="col-sm-12 col-md-4 col-lg-4 col-xl-4">
							<div class="form-group">
								<label class="control-label">Điền email</label>
								<div class="p-name">
									<input type="text" class="form-control" name="emailOa"
										id="emailOa" value="${orderByPhoneModel.emailOa}"
										placeholder="Điền email của ban" aria-label="codeActive"
										aria-describedby="basic-addon1">
								</div>
							</div>
						</div>
						<div class="col col-sm-4 col-md-4 col-lg-4 col-xl-4">
							<div class="form-group">
								<button class="btn btn-zalopay btn-payment-order"
									style="margin-top: 32.55px">Đặt hàng</button>
<!-- 								<div class="g-recaptcha hidden" style="margin-top: 10px;" -->
<!-- 									data-sitekey="6Le_9GgfAAAAAN76iebotKcmAbB8bkznqZ-_NaI8"></div> -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</section> --%>
		<%-- <c:if test="${orderByPhoneList.size() == 0 }">
			<div class="trans-hist">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-12">
							<h2>Danh sách đơn hàng đã đặt</h2>
						</div>
					</div>
					<div class="row table-responsive">
						<table class="table table-striped">
							<c:if test="${orderByPhoneList.size() == 0 }">
								<p class="order-table">Bạn chưa đặt hàng sản phẩm nào</p>
							</c:if>
							<c:if test="${orderByPhoneList.size() > 0 }">
								<thead>
									<tr>
										<th scope="col" style="min-width: 60px;">#</th>
										<th scope="col" style="min-width: 170px;">Ứng dụng</th>
										<th scope="col" style="min-width: 170px;">Thời hạn</th>
										<th scope="col" style="min-width: 170px;">Account</th>
										<th scope="col" style="min-width: 370px;">Số điện thoại</th>
										<th scope="col" style="min-width: 170px;">Giá mua</th>
										<th scope="col" style="min-width: 170px;">Ngày đặt</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${orderByPhoneList}">
										<tr>
											<c:if test="${fn:containsIgnoreCase(item.packageCode, 'SC')}">
												<th scope="row"><img src="/images/supperchinese.png"
													title="SupperChinese" alt="SupperChinese"
													style="width: 40px; height: 40px;" /></th>
												<td>Super Chinese</td>
											</c:if>
											<c:if test="${fn:containsIgnoreCase(item.packageCode, 'ST')}">
												<th scope="row"><img src="/images/suppertest.svg"
													title="suppertest" alt="suppertest"
													style="width: 40px; height: 40px;"></th>
												<td>Super Test</td>
											</c:if>
											<td>${item.packageTime }</td>
											<td>${item.account }</td>
											<td>${item.phone }</td>
											<td>${item.price }</td>
											<td>${item.createTime }</td>

										</tr>
									</c:forEach>
								</tbody>
							</c:if>
						</table>
					</div>
				</div>
			</div>
		</c:if> --%>
	</section>
	<div class="loader">
		<div class="loading-full"></div>
	</div>
	<jsp:include page="../common/footerCommon.jsp" flush="true" />
	<jsp:include page="../common/zaloChatCommon.jsp" flush="true" />
	<jsp:include page="../common/jsCommon.jsp" flush="true" />
</body>
<script type="text/javascript">
	var hasLogin ='<%=loginZalo%>';
	var paymentCallBack ='<%=paymentCallBack%>';
	$(document).ready(function() {
		convertPrice();
		if (paymentCallBack == 'true') {
			window.location.href = "/tien-trinh-thanh-toan-qua-appota-pay";
		}
	});
	
	function post(path, objects, method) {
		const form = document.createElement('form');
		form.method = method;
		form.action = path;
		for (const [key, value] of Object.entries(objects)) {
			const hiddenField = document.createElement('input');
			hiddenField.type = 'hidden';
			hiddenField.name = key;
			hiddenField.value = value;
	
			form.appendChild(hiddenField);
		}
	
		document.body.appendChild(form);
		form.submit();
	}
	
	function convertPrice() {
		$(".price-fm").each(function() {
			var value = $(this).find('span').text();
			$(this).find('span').text(formatPrice(value));
		})
	}

	function formatPrice(price) {
		if (price != null && price != '') {
			return new Intl.NumberFormat('de-DE').format(price);
		} else {
			return 0;
		}
	}
	
	$('.btn-zaloEwalletLink').click(function() {
		window.location.href = "/ewalletLink";
	});
	
	$('.btn-zalopayment').click(function() {
		window.location.href = "/tien-trinh-thanh-toan-qua-zalo-pay";
	});
	
	$('.btn-appotapay').click(function() {
		if(hasLogin == '') {
			loginZaloPayment();
		} else {
			window.location.href = "/tien-trinh-thanh-toan-qua-appota-pay";
		}
	});
	
	function loginZaloPayment() {
		var accesstoken = window.localStorage.getItem('accesstoken');
		var newAccesstoken ='<%=accesstokenZalo%>';
		
		if (newAccesstoken && newAccesstoken != accesstoken) {
			window.localStorage.setItem('accesstoken', newAccesstoken);
			accesstoken = newAccesstoken;
		}
		if (accesstoken) {
			window.location.href = '/dang-nhap?accesstoken='
					+ accesstoken + '&payment=1';
		} else {
			window.location.href = '/dang-nhap?payment=1';
		}
	}
	
	function post(path, objects, method) {
		const form = document.createElement('form');
		form.method = method;
		form.action = path;
		for (const [key, value] of Object.entries(objects)) {
			const hiddenField = document.createElement('input');
			hiddenField.type = 'hidden';
			hiddenField.name = key;
			hiddenField.value = value;

			form.appendChild(hiddenField);
		}

		document.body.appendChild(form);
		form.submit();
	}
	
	$('.btn-back-info').click(function() {
		window.location.href = "/xac-nhan-thanh-toan-goi-vip-superchinese";
	})

	$('.btn-payment-order').click(function() {
		var scValue;
		var stValue;
		var phoneValue;
		var emailOa;
// 		var capcha = grecaptcha.getResponse();
		if (typeof $('#scCode').val() === 'undefined') {
			scValue="";
		} else {
			scValue=$('#scCode').val();
		}
		if (typeof $('#stCode').val() === 'undefined') {
			stValue="";
		} else {
			stValue=$('#stCode').val();
		}
		if (typeof $('#phoneNumber').val() === 'undefined') {
			phoneValue="";
		} else {
			phoneValue=$('#phoneNumber').val();
		}
		if (typeof $('#emailOa').val() === 'undefined') {
			emailOa="";
		} else {
			emailOa=$('#emailOa').val();
		}
		
		if(phoneValue=='') {
			$('#phoneNumber').addClass('form-error-control');
			$('#phoneNumber').focus();
			return;
		} else {
			$('#phoneNumber').removeClass('form-error-control');
		}
		
		if(emailOa == '') {
			$('#emailOa').addClass('form-error-control');
			$('#emailOa').focus();
			return;
		} else {
			$('#emailOa').removeClass('form-error-control');
		}
		
		const objects = {
			'scCode' : scValue,
			'stCode' : stValue,
			'phoneNumber': phoneValue,
			'emailOa': emailOa
			};
		var path = 'orderByPhone';
		var method = 'post';
		post(path, objects, method);
	});
	
	$('#followOa').click(function() {
		window.open("https://zalo.me/4513138367536811082");
	});
	
// 	$(".activevip").keyup(function(event) {
// 		$(this).find('.g-recaptcha').removeClass('hidden');
// 	 });
	
	function followOa() {
		window.open("https://zalo.me/4513138367536811082");
	};
</script>
</html>