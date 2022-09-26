<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<jsp:include page="../common/metaCommon.jsp" flush="true" />
<meta name="keywords" content="Super,chinese, ứng, dụng, hoc, tiếng, trung, chính, hãng, số 1, việt, nam" /> 
<meta name="description" content="Super chinese ứng dụng học tiếng Trung chính hãng số 1 Việt Nam, ứng dụng học tiếng Trung giá rẻ tốt nhất 2022, ứng dụng học tiếng trung HSK mới nhất " /> 
<meta name="generator" content="Cty TNHH Konec" />
<jsp:include page="../common/cssCommon.jsp" flush="true" />
<link rel="stylesheet" href="css/paymentMethod/paymentMethod.css">
<title>Đăng ký giá sinh viên</title>
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
		<div class="vipinfo">
			<div class="container success-container-oa">
				<div class="success-pay-mess" >
					<p class="text-left">${zaloOaModel.message }</p>
					<div class="col-sm-6 col-md-3 col-lg-3 col-xl-3 mr-auto">
						<button class="btn btn-lien-he fs13">Liên hệ giảm giá</button>
					</div>
				</div>
			</div>
		</div>
	</section>

	<jsp:include page="../common/footerCommon.jsp" flush="true" />
	<jsp:include page="../common/zaloChatCommon.jsp" flush="true" />
	<jsp:include page="../common/jsCommon.jsp" flush="true" />
	<script src="/js/vipstore/vipstore.js"></script>
</body>
<script type="text/javascript">
	$(".btn-lien-he").click(function(){
		window.location.href = "https://zalo.me/4513138367536811082";
	});
</script>
</html>