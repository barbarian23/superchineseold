<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<link rel="stylesheet" href="css/profile/profile.css">
<!-- <script src='https://www.google.com/recaptcha/api.js?hl=vi' async defer></script> -->
<title>Tài khoản</title>
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
	<section id="activevip" class="activevip background-activevip">
		<div class="container">
			<div class="form-group">
				<div class="row mb10">
					<div class="col col-sm-3 col-md-3 col-lg-3 col-xl-3">
						<p class="lable-active">Kích hoạt mã VIP</p>
					</div>
					<div
						class="col col-sm-9 col-md-9 col-lg-9 col-xl-9 active-desciption">Nếu
						bạn đã có mã kích hoạt 16 chữ số, nhập thông tin dưới đây để kích
						hoạt VIP</div>
				</div>
			</div>
			<div class="row">
				<div class="col col-sm-3 col-md-3 col-lg-3 col-xl-3">
					<p class="lable-app">Chọn ứng dụng</p>
				</div>
				<div class="col col-sm-9 col-md-9 col-lg-9 col-xl-9">
					<div class="row">
						<div class="col col-sm-3 col-md-3 col-lg-3 col-xl-3">
							<div class="form-check">
								<input class="form-check-input" type="radio" name="application"
									id="spchinese"> <label class="form-check-label"
									for="spchinese"> Super Chinese </label>
							</div>
						</div>
						<div class="col col-sm-3 col-md-3 col-lg-3 col-xl-3">
							<div class="form-check">
								<input class="form-check-input" type="radio" name="application"
									id="sptest"> <label class="form-check-label"
									for="sptest"> Super Test </label>
							</div>
						</div>
					</div>
				</div>

			</div>

			<div class="row active-margin">
				<div class="col col-sm-6 col-md-6 col-lg-6 col-xl-6">
					<div class="form-group">
						<label class="control-label control-label-active">Điền tài
							khoản ứng dụng của bạn</label>
						<div class="p-name">
							<input type="text" class="form-control" id="avEmailId"
								placeholder="Email hoặc số điện thoại của bạn"
								aria-label="Email" aria-describedby="basic-addon1"> <span
								class="at-error-email error-class"></span>
						</div>
					</div>
				</div>
				<div class="col col-sm-4 col-md-4 col-lg-4 col-xl-4">
					<div class="form-group">
						<label class="control-label control-label-active">Điền mã
							kích hoạt 16 số</label>
						<div class="p-name">
							<input type="text" class="form-control" id="avCodeId"
								placeholder="XXXX-XXXX-XXXX-XXXX" aria-label="codeActive"
								aria-describedby="basic-addon1"> <span
								class="at-error-code error-class"></span>
						</div>
					</div>
				</div>
				<div class="col col-sm-2 col-md-2 col-lg-2 col-xl-2">
					<div class="form-group">
						<button class="btn btn-active control-label-active">Kích
							hoạt</button>
						<!-- 						<div class="g-recaptcha hidden" style="margin-top: 10px;" -->
						<!-- 							data-sitekey="6Le_9GgfAAAAAN76iebotKcmAbB8bkznqZ-_NaI8"></div> -->
					</div>
				</div>
				<span class="at-error-general error-class"></span> <span
					class="at-license-success success-class"></span>
			</div>
		</div>
	</section>
	<section id="profile" class="profile">
		<%-- <div class="container">
			<div class="row">
				<c:if test="${profileModel.packageCode_sc != null}">
					<div class="col col-sm-12 col-md-6 col-lg-6 col-xl-6">
						<div class="p-chinese">
							<div class="p-info">
								<div class="row">
									<div class="col">
										<div class="pt-chinese">
											<a class="" href="supperChinese" title="SupperChinese">
												<div>
													<p class="pt-name">Super Chinese</p>
													<p class="pt-desc">Tiếng quan thoại</p>
												</div>
											</a>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col col-sm-5 col-md-5 col-lg-5 col-xl-5">
										<img src="/images/p-chinese.svg" title="SupperChinese"
											alt="SupperChinese" />
									</div>
									<div class="col col-sm-7 col-md-7 col-lg-7 col-xl-7">
										<div class="row">
											<div class="form-group">
												<label class="control-label">Tên của bạn</label>
												<div class="p-name">
													<span>${profileModel.name}</span>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="form-group">
												<label class="control-label">Tài khoản của bạn</label>
												<div class="p-name">
													<span>${profileModel.phone}</span>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col">
										<p class="l-acclevel">Hạng tài khoản</p>
									</div>
								</div>
								<div class="row">
									<div class="col">
										<p class="acclevel" style="font-size: 50px !important;">VIP</p>
									</div>
								</div>
								<div class="row">
									<div class="col">
										<p class="l-acclevel">Thời hạn sử dụng</p>
									</div>
									<c:if test="${profileModel.endTime_sc !='' }">
										<div class="col">
											<p class="expriredesc">(hết hạn
												${profileModel.endTime_sc})</p>
										</div>
									</c:if>
									<c:if test="${profileModel.endTime_sc =='' }">
										<div class="col">
											<p class="expriredesc">(hết hạn: Không giới hạn)</p>
										</div>
									</c:if>
								</div>
								<div class="row">
									<div class="col">
										<p class="expriredate">
											<c:if test="${profileModel.renewDay_sc == null }">
												<span class="limited-expire">Không giới hạn</span>
											</c:if>
											<c:if test="${profileModel.renewDay_sc != null }">
												<span>${profileModel.renewDay_sc}</span> ngày
											</c:if>
										</p>
									</div>
								</div>
							</div>
							<div class="">
								<button class="btn btn-light btn-extend">Đăng ký</button>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${profileModel.packageCode_sc == null}">
					<div class="col col-sm-12 col-md-6 col-lg-6 col-xl-6">
						<div class="p-chinese">
							<div class="p-info">
								<div class="row">
									<div class="col">
										<div class="pt-chinese">
											<a class="" href="supperChinese" title="SupperChinese">
												<div>
													<p class="pt-name">Super Chinese</p>
													<p class="pt-desc">Tiếng quan thoại</p>
												</div>
											</a>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col col-sm-5 col-md-5 col-lg-5 col-xl-5">
										<img src="/images/p-chinese.svg" title="SupperChinese"
											alt="SupperChinese" />
									</div>
									<div class="col col-sm-7 col-md-7 col-lg-7 col-xl-7">
										<div class="row">
											<div class="form-group">
												<label class="control-label">Tên của bạn</label>
												<div class="p-name">
													<span>${profileModel.name}</span>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="form-group">
												<label class="control-label">Tài khoản của bạn</label>
												<div class="p-name">
													<span></span>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col">
										<p class="l-acclevel">Hạng tài khoản</p>
									</div>
								</div>
								<div class="row">
									<div class="col">
										<p class="acclevel" style="font-size: 50px !important;">THƯỜNG</p>
									</div>
								</div>
								<div class="row">
									<div class="col">
										<p class="l-acclevel">Thời hạn sử dụng</p>
									</div>
								</div>
								<div class="row">
									<div class="col">
										<p class="expriredate">
											<span></span>
										</p>
									</div>
								</div>
							</div>
							<div class="">
								<button class="btn btn-light btn-extend">Đăng ký</button>
							</div>
						</div>
					</div>
				</c:if>
				<div class="col col-sm-12 col-md-6 col-lg-6 col-xl-6">
					<div class="p-test">
						<div class="p-info">
							<div class="row">
								<div class="col">
									<div class="pt-test">
										<a class="" href="/super-test" title="SupperChinese">
											<div>
												<p class="pt-name">Super Test</p>
												<p class="pt-desc">HSK Online</p>
											</div>
										</a>
									</div>
								</div>
							</div>
						</div>
						<div class="">
							<button class="btn btn-light btn-link btn-extend">Đăng
								ký</button>
						</div>
					</div>
				</div>
			</div>
		</div> --%>

		<div class="trans-hist">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-12">
						<h2>Lịch sử giao dịch</h2>
					</div>
				</div>
				<div class="row table-responsive">
					<table class="table table-striped">
						<c:if test="${historyModel.data != null }">
							<thead>
								<tr>
									<th scope="col" style="min-width: 60px;">#</th>
									<th scope="col" style="min-width: 170px;">Ứng dụng</th>
									<th scope="col" style="min-width: 170px;">Thời hạn</th>
									<th scope="col" style="min-width: 370px;">Mã kích hoạt</th>
									<th scope="col" style="min-width: 170px;">Ngày mua</th>
									<th scope="col" style="min-width: 170px;">Tổng tiền</th>
									<th scope="col" style="min-width: 50px;">Trạng thái</th>
								</tr>
							</thead>
						</c:if>

						<tbody>
							<c:if test="${historyModel.data != null }">
								<c:forEach var="item" items="${historyModel.data}">
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
										<td>${item.cycleText }</td>
										<td class="class-code">${item.code }</td>
										<td>${item.createTime }</td>
										<td>${item.price }</td>
										<c:if test="${item.status == 'ENABLED' }">
											<td class="class-active">Chưa kích hoạt</td>
										</c:if>
										<c:if test="${item.status == 'ACTIVATED' }">
											<td>Đã kích hoạt</td>
										</c:if>

									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${historyModel.data == null }">
								<p
									style="text-align: center; padding: 40px 20px; background-color: #fff6ed;">Chưa
									có giao dịch nào</p>
							</c:if>

						</tbody>
					</table>
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
	<script src="/js/profile/profile.js"></script>
</body>
</html>