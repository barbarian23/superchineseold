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
<title>Trạng thái thanh toán</title>
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
			<div class="container success-container">
				<div class="success-pay-mess" >
					<p>${paymentModel.message }</p>
					<button class="btn-home">Đi tới trang chủ</button>
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
	$(".btn-home").click(function(){
		window.location.href = "/";
	});
</script>
</html>