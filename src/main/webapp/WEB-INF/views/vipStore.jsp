<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="konec.superchinese.models.loginzalo.ZaloLoginInfo"%>
<%
String context = request.getContextPath();
ZaloLoginInfo zaloInfo = (ZaloLoginInfo) session.getAttribute("ZaloLoginInfo");
String zaloName = "";
String accesstokenZalo = "";
String loginZalo = "";
boolean adviseCallBack = false;
if (zaloInfo != null) {
	accesstokenZalo = zaloInfo.getAccesstoken();
	loginZalo = zaloInfo.getLoginFlag();
	zaloName = zaloInfo.getZaloName();
	request.setAttribute("loginZalo", loginZalo);
	adviseCallBack = zaloInfo.isAdviseCallBack();
}
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
<link rel="stylesheet" href="/css/vipstore/vipstore.css">
<title>Gói vip Super Chinese</title>
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
				<c:if test="${ vipPackageStore.have_sc_packages == true}">
					<div class="row g-3 s-chinese">
						<div class="col-sm-6 col-md-3 col-lg-3 col-xl-3">
							<div class="item">
								<img src="/images/supperchinese.svg" title="supperchinese"
									alt="supperchinese" />
                                                                <h4>Mở khoá tất cả các bài tập học và luyện tập không giới hạn cấp độ</h4>
								<h2>Super Chinese</h2>
								<p>Tiếng Quan thoại</p>
								<span>PLUS</span>
							</div>
						</div>
						<div class="col-sm-6 col-md-3 col-lg-3 col-xl-3 chinese-package">
							<div class="box-item pack-month">
								<div class="unchecked">
									<img src="/images/unchecked.svg" title="unchecked"
										alt="unchecked" />
								</div>
								<p class="pgk">
									<span>1</span> tháng
								</p>
								<c:if
									test="${vipPackageStore.originalPrice_m == vipPackageStore.sellPrice_m}">
									<p class="price price-fm">
										<span>${vipPackageStore.originalPrice_m}</span> vnđ
									</p>
								</c:if>
								<c:if
									test="${vipPackageStore.originalPrice_m != vipPackageStore.sellPrice_m}">
									<p class="origin price-fm">
										<span>${vipPackageStore.originalPrice_m}</span> vnđ
									</p>
									<p class="discount price-fm">
										<span>${vipPackageStore.sellPrice_m}</span> vnđ
									</p>
								</c:if>

							</div>
							<div class="total">
								<p>
<%-- 									<span>${vipPackageStore.totalPurchased_m}</span> người đã mua --%>
									<span>13.523</span> người đã mua
								</p>
							</div>
						</div>
						<div class="col-sm-6 col-md-3 col-lg-3 col-xl-3 chinese-package">
							<div class="box-item pack-year">
								<div class="unchecked">
									<img src="/images/unchecked.svg" title="unchecked"
										alt="unchecked" />
								</div>
								<p class="pgk">
									<span>1</span> năm
								</p>
								<c:if
									test="${vipPackageStore.originalPrice_y == vipPackageStore.sellPrice_y}">
									<p class="price price-fm">
										<span>${vipPackageStore.originalPrice_y}</span> vnđ
									</p>
								</c:if>
								<c:if
									test="${vipPackageStore.originalPrice_y != vipPackageStore.sellPrice_y}">
									<p class="origin price-fm">
										<span>${vipPackageStore.originalPrice_y}</span> vnđ
									</p>
									<p class="discount price-fm">
										<span>${vipPackageStore.sellPrice_y}</span> vnđ
									</p>
								</c:if>
							</div>
							<div class="total">
								<p>
<%-- 									<span>${vipPackageStore.totalPurchased_y}</span> người đã mua --%>
									<span>10.525</span> người đã mua
								</p>
							</div>
						</div>
						<div class="col-sm-6 col-md-3 col-lg-3 col-xl-3 chinese-package">
							<div class="box-item unlimited pack-limited">
								<div class="unchecked">
									<img src="/images/unchecked.svg" title="unchecked"
										alt="unchecked" />
								</div>
								<p class="pgk">
									<span>Life Time</span><br /> Không giới hạn
								</p>
								<c:if
									test="${vipPackageStore.originalPrice_l == vipPackageStore.sellPrice_l}">
									<p class="price price-fm">
										<span>${vipPackageStore.originalPrice_l}</span> vnđ
									</p>
								</c:if>
								<c:if
									test="${vipPackageStore.sellPrice_l != vipPackageStore.originalPrice_l}">
									<p class="origin price-fm">
										<span>${vipPackageStore.originalPrice_l}</span> vnđ
									</p>
									<p class="discount price-fm">
										<span>${vipPackageStore.sellPrice_l}</span> vnđ
									</p>
								</c:if>
								<!-- 							<div class="discount-desc"> -->
								<!-- 								<p>-30% hết ngày 28/02/2022</p> -->
								<!-- 							</div> -->
							</div>
							<div class="total">
								<p>
<%-- 									<span>${vipPackageStore.totalPurchased_l}</span> người đã mua --%>
									<span>7.892</span> người đã mua
								</p>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${ vipPackageStore.have_st_packages == true}">
					<div class="row g-3 s-test">
						<div class="col-sm-6 col-md-3 col-lg-3 col-xl-3">
							<div class="item">
								<img src="/images/suppertest2.svg" title="suppertest"
									alt="suppertest" />
                                                                <h4>Mở khoá tất cả các bài tập học và luyện tập không giới hạn cấp độ</h4>
								<h2>Super Test</h2>
								<p>HSK Online</p>
								<span>VIP</span>
							</div>
						</div>
						<div class="col-sm-6 col-md-3 col-lg-3 col-xl-3 test-package">
							<div class="box-item pack-month">
								<div class="unchecked">
									<img src="/images/unchecked.svg" title="unchecked"
										alt="unchecked" />
								</div>
								<p class="pgk">
									<span>1</span> tháng
								</p>
								<c:if
									test="${vipPackageStore.originalPrice_st_m == vipPackageStore.sellPrice_st_m}">
									<p class="price price-fm">
										<span>${vipPackageStore.sellPrice_st_m}</span> vnđ
									</p>
								</c:if>
								<c:if
									test="${vipPackageStore.originalPrice_st_m != vipPackageStore.sellPrice_st_m}">
									<p class="origin price-fm">
										<span>${vipPackageStore.originalPrice_st_m}</span> vnđ
									</p>
									<p class="discount price-fm">
										<span>${vipPackageStore.sellPrice_st_m}</span> vnđ
									</p>
								</c:if>
							</div>
							<div class="total">
								<p>
<%-- 									<span>${vipPackageStore.totalPurchased_st_m}</span> người đã --%>
<!-- 									mua -->
									<span>11.258</span> người đã mua
								</p>
							</div>
						</div>
						<div class="col-sm-6 col-md-3 col-lg-3 col-xl-3 test-package">
							<div class="box-item pack-year">
								<div class="unchecked">
									<img src="/images/unchecked.svg" title="unchecked"
										alt="unchecked" />
								</div>
								<p class="pgk">
									<span>1</span> năm
								</p>
								<c:if
									test="${vipPackageStore.originalPrice_st_y == vipPackageStore.sellPrice_st_y}">
									<p class="price price-fm">
										<span>${vipPackageStore.sellPrice_st_y}</span> vnđ
									</p>
								</c:if>
								<c:if
									test="${vipPackageStore.originalPrice_st_y != vipPackageStore.sellPrice_st_y}">
									<p class="origin price-fm">
										<span>${vipPackageStore.originalPrice_st_y}</span> vnđ
									</p>
									<p class="discount price-fm">
										<span>${vipPackageStore.sellPrice_st_y}</span> vnđ
									</p>
								</c:if>
							</div>
							<div class="total">
								<p>
<%-- 									<span>${vipPackageStore.totalPurchased_st_y}</span> người đã --%>
<!-- 									mua -->
									<span>9.546</span> người đã mua
								</p>
							</div>
						</div>
						<div class="col-sm-6 col-md-3 col-lg-3 col-xl-3 test-package">
							<div class="box-item unlimited pack-limited">
								<div class="unchecked">
									<img src="/images/unchecked.svg" title="unchecked"
										alt="unchecked" />
								</div>
								<p class="pgk">
									<span>Life Time</span><br /> Không giới hạn
								</p>
								<c:if
									test="${vipPackageStore.originalPrice_st_l == vipPackageStore.sellPrice_st_l}">
									<p class="price price-fm">
										<span>${vipPackageStore.sellPrice_st_l}</span> vnđ
									</p>
								</c:if>
								<c:if
									test="${vipPackageStore.originalPrice_st_l != vipPackageStore.sellPrice_st_l}">
									<p class="origin price-fm">
										<span>${vipPackageStore.originalPrice_st_l}</span> vnđ
									</p>
									<p class="discount price-fm">
										<span>${vipPackageStore.sellPrice_st_l}</span> vnđ
									</p>
								</c:if>
							</div>
							<div class="total">
								<p>
<%-- 									<span>${vipPackageStore.totalPurchased_st_l}</span> người đã --%>
<!-- 									mua -->
									<span>8.235</span> người đã mua
								</p>
							</div>
						</div>
					</div>
				</c:if>
			</div>
		</div>

		<div class="ordertotal orderDisplay">
			<div class="container">
				<div class="row g-3">
					<div class="col-sm-6 col-md-6 col-lg-6 col-xl-6 order-text">
						<div class="item col-sm-6 col-md-6 col-lg-6 col-xl-6">
							<h2>Đơn hàng</h2>
						</div>
					</div>
					<%-- <div class="col-sm-6 col-md-3 col-lg-3 col-xl-3 total-store">
						<input type="hidden"  name="packageCode_sc" id="packageCode_sc" value="${vipPackageStore.packageCode_sc }"/>
						<input type="hidden"  name="packageCode_st" id="packageCode_st" value="${vipPackageStore.packageCode_st }"/>
						<input type="hidden"  name="packageCode_m" id="packageCode_m" value="${vipPackageStore.packageCode_m }"/>
						<input type="hidden"  name="sellPrice_m" id="sellPrice_m" value="${vipPackageStore.sellPrice_m }"/>
						<input type="hidden"  name="originalPrice_m" id="originalPrice_m" value="${vipPackageStore.originalPrice_m }"/>
						<input type="hidden"  name="packageCode_y" id="packageCode_y" value="${vipPackageStore.packageCode_y }"/>
						<input type="hidden"  name="sellPrice_y" id="sellPrice_y" value="${vipPackageStore.sellPrice_y }"/>
						<input type="hidden"  name="originalPrice_y" id="originalPrice_y" value="${vipPackageStore.originalPrice_y }"/>
						<input type="hidden"  name="packageCode_l" id="packageCode_l" value="${vipPackageStore.packageCode_l }"/>
						<input type="hidden"  name="sellPrice_l" id="sellPrice_l" value="${vipPackageStore.sellPrice_l }"/>
						<input type="hidden"  name="originalPrice_l" id="originalPrice_l" value="${vipPackageStore.originalPrice_l }"/>
						<input type="hidden"  name="packageCode_st_m" id="packageCode_st_m" value="${vipPackageStore.packageCode_st_m }"/>
						<input type="hidden"  name="sellPrice_st_m" id="sellPrice_st_m" value="${vipPackageStore.sellPrice_st_m }"/>
						<input type="hidden"  name="originalPrice_st_m" id="originalPrice_st_m" value="${vipPackageStore.originalPrice_st_m }"/>
						<input type="hidden"  name="packageCode_st_y" id="packageCode_st_y" value="${vipPackageStore.packageCode_st_y }"/>
						<input type="hidden"  name="sellPrice_st_y" id="sellPrice_st_y" value="${vipPackageStore.sellPrice_st_y }"/>
						<input type="hidden"  name="originalPrice_st_y" id="originalPrice_st_y" value="${vipPackageStore.originalPrice_st_y }"/>
						<input type="hidden"  name="packageCode_st_l" id="packageCode_st_l" value="${vipPackageStore.packageCode_st_l }"/>
						<input type="hidden"  name="sellPrice_st_l" id="sellPrice_st_l" value="${vipPackageStore.sellPrice_st_l }"/>
						<input type="hidden"  name="originalPrice_st_l" id="originalPrice_st_l" value="${vipPackageStore.originalPrice_st_l }"/>
					</div> --%>
					<div class="col-sm-6 col-md-3 col-lg-3 col-xl-3 chinese-store">
						<div class="orderdtl">
							<a href=""> <img src="/images/supperchinese2.svg"
								title="supperchinese" alt="supperchinese" /> Super Chinese
							</a>
						</div>
						<div class="total tuvan">
							<button class="btn btn-tu-van fs14">Ưu đãi cho sinh viên</button>
						</div>
					</div>
					<div class="col-sm-6 col-md-3 col-lg-3 col-xl-3 test-store">
						<div class="orderdtl">
							<a href=""> <img src="/images/suppertest3.svg"
								title="suppertest" alt="suppertest" /> Super Test
							</a>
						</div>
						<div class="total tuvan">
							<button class="btn btn-tu-van fs14">Ưu đãi cho sinh viên</button>
						</div>
					</div>
					<div class="col-sm-6 col-md-3 col-lg-3 col-xl-3 total-store">
						<form:form method="post" modelAttribute="vipPackageStore"
							action="/xac-nhan-thanh-toan-goi-vip-superchinese">
							<form:hidden path="packageCode_sc" />
							<form:hidden path="packageCode_st" />
							<form:hidden path="packageCode_m" />
							<form:hidden path="sellPrice_m" />
							<form:hidden path="originalPrice_m" />
							<form:hidden path="packageCode_y" />
							<form:hidden path="sellPrice_y" />
							<form:hidden path="originalPrice_y" />
							<form:hidden path="packageCode_l" />
							<form:hidden path="sellPrice_l" />
							<form:hidden path="originalPrice_l" />

							<form:hidden path="packageCode_st_m" />
							<form:hidden path="sellPrice_st_m" />
							<form:hidden path="originalPrice_st_m" />
							<form:hidden path="packageCode_st_y" />
							<form:hidden path="sellPrice_st_y" />
							<form:hidden path="originalPrice_st_y" />
							<form:hidden path="packageCode_st_l" />
							<form:hidden path="sellPrice_st_l" />
							<form:hidden path="originalPrice_st_l" />
							<div>
								<h3>Tổng cộng</h3>
							</div>
							<div class="box-item unlimited">
								<div class="total total-prices adjust-padding"></div>
							</div>
							<div class="total">
								<button class="btn btn-buy fs14">Mua hàng</button>
							</div>
						</form:form>
					</div>
				</div>
				<div class="row" style="margin-top: 10px;">
					<div class="oa-message zaloOa-error"></div>
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
	<script src="/js/vipstore/vipstore.js"></script>
	<script type="text/javascript">
		var hasLogin ='<%=loginZalo%>';
		var adviseCallBack ='<%=adviseCallBack%>';
		$(document).ready(function() {
			if (adviseCallBack == 'true') {
				var packCode = localStorage.getItem('packCode');
				localStorage.removeItem("packCode");
				const objects = {
						'packChoise' : packCode,
						};
				var path = '/tu-van-gia-re';
				var method = 'post';
				post(path, objects, method);
			}
		});
		function loginZaloAdvise() {
			var accesstoken = window.localStorage.getItem('accesstoken');
			var newAccesstoken ='<%=accesstokenZalo%>';
			
			if (newAccesstoken && newAccesstoken != accesstoken) {
				window.localStorage.setItem('accesstoken', newAccesstoken);
				accesstoken = newAccesstoken;
			}
			if (accesstoken) {
				window.location.href = '/dang-nhap?accesstoken='
						+ accesstoken + '&advise=1';
			} else {
				window.location.href = '/dang-nhap?advise=1';
			}
		}
		$('.btn-tu-van').on('click', function() {
			
			var scPack = $('#packageCode_sc').val();
			var stPack = $('#packageCode_st').val();
			var pack;
			if (scPack) {
				pack = scPack;
			} else {
				pack = stPack;
			}
			
			if(hasLogin == '') {
				localStorage.setItem('packCode', pack);
				loginZaloAdvise();
			} else {
				var zaloOaModel = {
						packChoise: pack,
					};
				const objects = {
						'packChoise' : pack,
						};
					var path = '/tu-van-gia-re';
					var method = 'post';
				post(path, objects, method);
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
	</script>
</body>
</html>