<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page
	import="konec.superchinese.models.vippackages.VipStoreInformoder"%>
<%
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
<meta name="keywords" content="Super,chinese, ứng, dụng, hoc, tiếng, trung, chính, hãng, số 1, việt, nam" /> 
<meta name="description" content="Super chinese ứng dụng học tiếng Trung chính hãng số 1 Việt Nam, ứng dụng học tiếng Trung giá rẻ tốt nhất 2022, ứng dụng học tiếng trung HSK mới nhất " /> 
<meta name="generator" content="Cty TNHH Konec" />
<jsp:include page="../common/cssCommon.jsp" flush="true" />
<link rel="stylesheet"
	href="/css/vipStoreInformorder/vipStoreInformorder.css">
<title>Xác nhận thanh toán gói vip Super Chinese</title>
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

		<%-- <div class="vipinfo">
			<div class="container">
				<c:if
					test="${ vipStoreInformoder.packageCode_sc != null && vipStoreInformoder.packageCode_sc != ''}">
					<div class="row g-3 s-chinese">
						<div class="col-sm-6 col-md-3 col-lg-3 col-xl-3">
							<div class="item">
								<img src="/images/supperchinese.svg" title="supperchinese"
									alt="supperchinese" />
								<h2>Super Chinese</h2>
								<p>Tiếng Quan thoại</p>
								<span>PLUS</span>
							</div>
						</div>

						<div class="col-sm-6 col-md-3 col-lg-3 col-xl-3">
							<div class="box-item unlimited">
								<div class="checked">
									<img src="/images/checked.svg" title="checked" alt="checked" />
								</div>
								<p class="pgk">
									<c:if test="${vipStoreInformoder.packageCode_sc == 'SCM'}">
										<span>1</span> tháng
										</c:if>
									<c:if test="${vipStoreInformoder.packageCode_sc == 'SCY'}">
										<span>1</span> năm
										</c:if>
									<c:if test="${vipStoreInformoder.packageCode_sc == 'SCL'}">
										<span>Life Time</span>
										<br /> Không giới hạn
										</c:if>
								</p>
								<c:if
									test="${vipStoreInformoder.originalPrice_sc == vipStoreInformoder.sellPrice_sc}">
									<p class="price sc-price price-fm">
										<span>${vipStoreInformoder.originalPrice_sc}</span> vnđ
									</p>
								</c:if>
								<c:if
									test="${vipStoreInformoder.originalPrice_sc != vipStoreInformoder.sellPrice_sc}">
									<p class="origin price-fm">
										<span>${vipStoreInformoder.originalPrice_sc}</span> vnđ
									</p>
									<p class="discount price-fm sc-price-sale">
										<span>${vipStoreInformoder.sellPrice_sc}</span> vnđ
									</p>
								</c:if>


							</div>
						</div>

						<div class="col-sm-12 col-md-6 col-lg-6 col-xl-6">
							<div class="vipdesc">
								<label>Điền tài khoản Super Chinese của bạn</label> <input
									type="text" class="form-control sc-account"
									placeholder="Email hoặc số điện thoại của bạn" />
								<div class="result-account">
									<p class="sc-account-exist"></p>
								</div>
							</div>

						</div>
					</div>
				</c:if>
				<c:if
					test="${ vipStoreInformoder.packageCode_st != null && vipStoreInformoder.packageCode_st != ''}">
					<div class="row g-3">
						<div class="col-sm-6 col-md-3 col-lg-3 col-xl-3">
							<div class="item">
								<img src="/images/suppertest2.svg" title="suppertest"
									alt="suppertest" />
								<h2>Super Test</h2>
								<p>HSK Online</p>
								<span>VIP</span>
							</div>
						</div>

						<div class="col-sm-6 col-md-3 col-lg-3 col-xl-3">
							<div class="box-item unlimited">
								<div class="checked">
									<img src="/images/checked2.svg" title="checked" alt="checked" />
								</div>
								<p class="pgk">
									<c:if test="${vipStoreInformoder.packageCode_st == 'STM'}">
										<span>1</span> tháng
										</c:if>
									<c:if test="${vipStoreInformoder.packageCode_st == 'STY'}">
										<span>1</span> năm
										</c:if>
									<c:if test="${vipStoreInformoder.packageCode_st == 'STL'}">
										<span>Life Time</span>
										<br /> Không giới hạn
										</c:if>
								</p>
								<c:if
									test="${vipStoreInformoder.originalPrice_st == vipStoreInformoder.sellPrice_st}">
									<p class="price sc-price price-fm">
										<span>${vipStoreInformoder.originalPrice_st}</span> vnđ
									</p>
								</c:if>
								<c:if
									test="${vipStoreInformoder.originalPrice_st != vipStoreInformoder.sellPrice_st}">
									<p class="origin price-fm">
										<span>${vipStoreInformoder.originalPrice_st}</span> vnđ
									</p>
									<p class="discount price-fm sc-price-sale">
										<span>${vipStoreInformoder.sellPrice_st}</span> vnđ
									</p>
								</c:if>
							</div>
						</div>
						<div class="col-sm-12 col-md-6 col-lg-6 col-xl-6">
							<div class="vipdesc">
								<label>Điền tài khoản Super Chinese của bạn</label> <input
									type="text" class="form-control st-account"
									placeholder="Email hoặc số điện thoại của bạn" />
								<div class="result-account">
									<p class="st-account-exist"></p>
								</div>
							</div>

						</div>
					</div>
				</c:if>
				<div class="row">
					<div class="col">
						<p class="note">Hãy chắc chắn rằng bạn điền đúng email hoặc số
							điện thoại tài khoản của bạn, chúng tôi không chịu trách nhiệm
							nếu bạn điền sai tài khoản của mình</p>
					</div>
				</div>
			</div>
		</div> --%>

		<div class="ordertotal">
			<div class="container">
				<div class="row g-3">
					<div class="col-sm-6 col-md-3 col-lg-3 col-xl-3">
						<div class="item">
							<h2>Thông tin</h2>
						</div>

						<div class="back">
							<button class="btn btn-light btn-back">Quay lại</button>
						</div>
					</div>
					<div class="col-sm-6 col-md-3 col-lg-3 col-xl-3">
						<div class="orderdtl">
							<h3 class="pb3">Mã khuyến mại</h3>
						</div>
						<div class="box-item info">
							<p>Nhập mã khuyến mại</p>
							<input class="form-control enter-code" type="text" />
							<div class="result-voucher">
								<p class="note illegal-code-success">Mã khuyến mại hợp lệ,
									bạn được giảm thêm tiền khi mua sản phẩm Super Chinese</p>
								<p class="note  illegal-code-false">Mã khuyến mại không hợp
									lệ!</p>
								<p class="discount price-fm sale-period">
									Bạn được giảm <br /> <span class="price-fm"></span></span> vnđ
								</p>
							</div>
						</div>


					</div>
                                        <c:if test="${false}">
                                        <div class="col-sm-6 col-md-3 col-lg-3 col-xl-3">
						<div class="orderdtl">
							<h3>Hóa đơn VAT</h3>
						</div>
						<div class="box-item info">
							<p>Bạn có muốn nhận hóa đơn VAT không?</p>
							<div class="form-check">
								<input class="form-check-input vat-control" type="radio"
									value="isCheck" name="orderVat" id="eInvoiceYes" disabled="disabled"> <label
									class="form-check-label" for="eInvoiceYes"> Có </label>
							</div>
							<div class="form-check">
								<input class="form-check-input vat-control" type="radio"
									value="unCheck" name="orderVat" id="eInvoiceNo" checked disabled="disabled">
								<label class="form-check-label" for="eInvoiceNo"> Không
								</label>
							</div>
							<p class="note">Việc xuất hóa đơn VAT sẽ tốn thêm 10% chi
								phí.</p>
							<p class="discount price discount-vat total-vat-unsale">
								Bạn phải trả thêm <br /> <span class="price-fm">${vipStoreInformoder.vatPrice }</span>
								vnđ
							</p>
						</div>
<!-- 						<div class="total tuvan"> -->
<!-- 							<button class="btn btn-tu-van btn-buy">Tư vấn giá rẻ</button> -->
<!-- 						</div> -->
					</div>
                                        </c:if>                        
					<div class="col-sm-6 col-md-3 col-lg-3 col-xl-3">
						<div>
							<h3>Tổng cộng</h3>
						</div>
						<div class="box-item info">
<!-- 							<div class="plus"> -->
<!-- 								<p>+</p> -->
<!-- 							</div> -->
							<%-- <c:if
								test="${ vipStoreInformoder.packageCode_sc != null && vipStoreInformoder.packageCode_sc != ''}">
								<p class="t-chinese money price-fm adjust-margin">
									<span>${vipStoreInformoder.sellPrice_sc}</span> vnđ
								</p>
							</c:if>
							<c:if
								test="${vipStoreInformoder.packageCode_st != null && vipStoreInformoder.packageCode_st != ''}">
								<p class="t-test money price-fm adjust-margin">
									<span>${vipStoreInformoder.sellPrice_st}</span> vnđ
								</p>
							</c:if>
							<p class="money red price-sale-total">
								giảm <span></span> vnđ
							</p>
							<div class="t-line total-vat"></div>

							<p class="money price-fm total-price-unsale total-vat">
								<span>${vipStoreInformoder.totalPrice}</span> vnđ
							</p>
							<p class="money price-fm total-vat-unsale total-vat">
								<span>${vipStoreInformoder.vatPrice}</span> vnđ
							</p>
							<p class="money red total-vat">(10% VAT)</p>
							<div class="t-line"></div> --%>
							<div class="total total-price-final adjust-padding">
								<p class="money price-fm">
									<span class="red">${vipStoreInformoder.totalPrice}</span> vnđ
								</p>
							</div>
						</div>
						<div class="total">
							<input type="hidden" name="discountCode" id="discountCode" /> <input
								type="hidden" name="vatStatus" id="vatStatus" /> <input
								type="hidden" name="sc_account" id="sc_account" /> <input
								type="hidden" name="st_account" id="st_account" />
								<input type="hidden" name="sccode" id="packageCode_sc" value="${vipStoreInformoder.packageCode_sc }"/>
								<input type="hidden" name="stcode" id="packageCode_st" value="${vipStoreInformoder.packageCode_st }"/>
							<button class="btn btn-buy btn-buy-order fs14">Xác nhận mua hàng</button>
						</div>
					</div>
				</div>
				<div class="row" style="margin-top:10px;">
					<div class="oa-message zaloOa-error">
						
					</div>
				</div>
			</div>
		</div>
	</section>
	<div class="loader">
		<div class="loading-full"></div>
	</div>

	<jsp:include page="../common/footerCommon.jsp" flush="true" />
	<jsp:include page="../common/zaloChatCommon.jsp" flush="true" />
	<jsp:include page="../common/jsCommon.jsp" flush="true" />
	<!-- 	<script src="js/vipStoreInformorder/vipStoreInformorder.js"></script> -->
	<script type="text/javascript">
		var scPrice =
	<%=scPrice%>
		;
		var scPriceSale =
	<%=scPriceSale%>
		;
		;
		var stPrice =
	<%=stPrice%>
		;
		;
		var stPriceSale =
	<%=stPriceSale%>
		;
		;
		var totalPrice =
	<%=totalPrice%>
		;
		;
		var vatPrice =
	<%=vatPrice%>
		;
		;
		var totalPriceVat =
	<%=totalPriceVat%>
		;
		var newTotalPrice = totalPrice;
		var newTatPrice = vatPrice;
		var newTotalPriceVat = totalPriceVat;
		var salePrice = 0;
		$(document).ready(function() {
			convertPrice();
		});

		$('.btn-back').click(function() {
			window.location.href = "/goi-vip-superchinese";
		});

		$('.vat-control').click(function() {
			newTotalPrice = totalPrice - salePrice;
			newVatPrice = parseInt((totalPrice - salePrice) * 0.1, 10);
			newTotalPriceVat = parseInt((totalPrice - salePrice) * 1.1, 10);
			setSaleTotalPrice(formatPrice(newTotalPrice));
			if ($('input[name="orderVat"]:checked').val() == 'isCheck') {
				$('#vatStatus').val(true);
				setVatPrice(formatPrice(newVatPrice));
				setTotalPrice(formatPrice(newTotalPriceVat));
				discountVatBlock();
				totalVatBlock();

			} else {
				$('#vatStatus').val(false);
				setTotalPrice(formatPrice(newTotalPrice));
				discountVatNone();
				totalVatNone();
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

		$('.btn-buy-order').click(function() {
			var scValue;
			var stValue;
			if (typeof $('.sc-account').val() === 'undefined') {
				scValue="";
			} else {
				scValue=$('.sc-account').val();
			}
			if (typeof $('.st-account').val() === 'undefined') {
				stValue="";
			} else {
				stValue=$('.st-account').val();
			}
			const objects = {
				'discountCode': $('#discountCode').val(), 
				'vatStatus': $('#vatStatus').val() == ''?false:$('#vatStatus').val(), 
				'sc_account' : scValue,
				'st_account' : stValue,
				};
			var path = '/thanh-toan-goi-vip';
			var method = 'post';
			post(path, objects, method);
		});

		function convertPrice() {
			$(".price-fm").each(function() {
				var value = $(this).find('span').text();
				$(this).find('span').text(formatPrice(value));
			})
		}

		var requestTimer;
		var xhr;
		var saleCodeOld = $(".enter-code").val();
		;
		$('.enter-code').keyup(function() {
			var newSaleCode = $(".enter-code").val();
			if (newSaleCode != '') {
				if (saleCodeOld != newSaleCode) {
					$('.result-voucher').find('p').css('display','none');
				    $('.result-voucher').addClass('loading');
					requestTimer = setTimeout(checkSaleCode, 1000);
					saleCodeOld = newSaleCode;
				}
			} else {
				$('#discountCode').val($('.enter-code').val());
				salePrice = 0;
				if ($('input[name="orderVat"]:checked').val() == 'isCheck') {
					setVatPrice(formatPrice(vatPrice));
					setTotalPrice(formatPrice(totalPriceVat));
				} else {
					setTotalPrice(formatPrice(totalPrice));
				}
				illegalFalseDisplayNone();
				illegalSuccessDisplayNone();
				salePeriodNone();
				saleDisplayNone();
			}
		})

		function checkSaleCode() {
			xhr = $
					.ajax({
						type : "POST",
						url : "/xac-nhan-thanh-toan-goi-vip-superchinese/salecode",
						data : {
							saleCode : $(".enter-code").val()
						},
						dataType : 'json',
						cache : false,
						
						success : function(data) {
							$('.result-voucher').removeClass('loading');
							if (data.code == 200) {
								salePrice = data.data.discount;
								illegalFalseDisplayNone();
								illegalSuccessDisplayBlock();
								$('.sale-period').find('span').text(
										data.data.discount);
								salePeriodBlock();
								$('.price-sale-total').find('span').text(
										data.data.discount);
								newTotalPrice = totalPrice - salePrice;
								newVatPrice = parseInt(
										(totalPrice - salePrice) * 0.1, 10);
								newTotalPriceVat = parseInt(
										(totalPrice - salePrice) * 1.1, 10);
								$('#discountCode').val($('.enter-code').val());
								setSaleTotalPrice(formatPrice(newTotalPrice));
								if ($('input[name="orderVat"]:checked').val() == 'isCheck') {
									setVatPrice(formatPrice(newVatPrice));
									setTotalPrice(formatPrice(newTotalPriceVat));
								} else {
									setTotalPrice(parseInt(newTotalPrice));
								}

								saleDisplayBlock();
							} else {
								$('#discountCode').val('');
								salePrice = 0;
								illegalFalseDisplayBlock();
								illegalSuccessDisplayNone();
								salePeriodNone();
								saleDisplayNone();
								setSaleTotalPrice(formatPrice(totalPrice));
								if ($('input[name="orderVat"]:checked').val() == 'isCheck') {
									setVatPrice(formatPrice(vatPrice));
									setTotalPrice(formatPrice(totalPriceVat));
								} else {
									setTotalPrice(formatPrice(totalPrice));
								}
							}
						}
					});
		}
		var requestAccount;
		var valscOld;
		$('.sc-account').keyup(function() {
			var valsc = $(".sc-account").val();
			if (valsc != '' && valsc != valscOld) {
				$('.sc-account-exist').css('display', 'none');
				$('.result-account').addClass('loading-account');
				valscOld = valsc;
				requestAccount = setTimeout(function(){checkExistAccount('sc', valsc)}, 500);
			}
			if (valsc == '') {
				$('.sc-account-exist').css('display', 'none');
				$('.sc-account-exist').text('');
			}

		})
		var valstOld;
		
		$('.st-account').keyup(function() {
			var valst = $(".st-account").val();
			if (valst != '' && valst != valstOld) {
				$('.st-account-exist').css('display', 'none');
				$('.result-account').addClass('loading-account');
				valstOld = valst;
				requestAccount = setTimeout(function(){checkExistAccount('st', valst)}, 500);
			}
			if (valst == '') {
				$('.st-account-exist').css('display', 'none');
				$('.st-account-exist').text('');
			}
		})

		function checkExistAccount(pack, val) {
			xhr = $.ajax({
				type : "POST",
				url : "xac-nhan-thanh-toan-goi-vip-superchinese/existAccount",
				data : {
					packages : pack,
					account : val
				},
				dataType : 'json',
				cache : false,
				success : function(data) {
					$('.result-account').removeClass('loading-account');
					if (data.code == 200) {
						if (pack == 'sc') {
							$('.sc-account-exist').css('display', 'block');
							$('.sc-account-exist').text(data.message);
						} else {
							$('.st-account-exist').css('display', 'block');
							$('.st-account-exist').text(data.message);
						}

					} else {
						if (pack == 'sc') {
							$('.sc-account-exist').css('display', 'block');
							$('.sc-account-exist').text(
									'Lỗi hệ thống, vui lòng thử lại sau');
						} else {
							$('.st-account-exist').css('display', 'block');
							$('.st-account-exist').text(
									'Lỗi hệ thống, vui lòng thử lại sau');
						}
					}
				}
			});
		}

		function setSaleTotalPrice(value) {
			$('.total-price-unsale').find('span').text(value);
		}

		function setVatPrice(value) {
			$('.total-vat-unsale').find('span').text(value);
		}

		function setTotalPrice(value) {
			$('.total-price-final').find('span').text(value);
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

		function formatPrice(price) {
			if (price != null && price != '') {
				return new Intl.NumberFormat('de-DE').format(price);
			} else {
				return 0;
			}
		}
		function followOa() {
			window.open("https://zalo.me/4513138367536811082");
		};
	</script>
</body>

</html>