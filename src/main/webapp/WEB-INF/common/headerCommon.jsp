<%-- All Rights Reserved, Copyright(c) 2006-2007 Nihon Fukushi University & FUJITSU LIMITED. --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.apache.commons.logging.Log" %>
<%@ page import="org.apache.commons.logging.LogFactory" %>
<%@ page import="konec.superchinese.models.loginzalo.ZaloLoginInfo"%>
<%
String context = request.getContextPath();
Log logger = LogFactory.getLog( getClass() );
ZaloLoginInfo zaloInfo = (ZaloLoginInfo) session.getAttribute("ZaloLoginInfo");
String loginZalo = "";
String zaloName = "";
String accesstokenZalo = "";
if (zaloInfo != null) {
	accesstokenZalo = zaloInfo.getAccesstoken();
	loginZalo = zaloInfo.getLoginFlag();
	zaloName = zaloInfo.getZaloName();
	request.setAttribute("loginZalo", loginZalo);
}
%>
<jsp:include page="../common/cssCommon.jsp" flush="true" />
<jsp:include page="../common/sale50k.jsp" flush="true" />
<c:if test="${superchinese == null}">
	<div class="header s-test">
</c:if>
<c:if test="${superchinese != null}">
	<div class="header s-chinese">
</c:if>
<div class="container-fluid">
	<div class="row">
		<div
			class="col col-xs-6 col-sm-3 col-md-3 col-lg-3 col-xl-3 logo_section">
			<div class="logo">
				<a href="/" ><img src="/images/logo.png" alt="#" /></a>
				<h1 class="d-none">Tiếng Trung siêu việt</h1>
			</div>
		</div>
		<div class="col col-xs-6 col-sm-9 col-md-9 col-lg-9 col-xl-9">
			<div class="header_information ">
				<div class="menu-area">
					<div class="limit-box">
						<nav class="main-menu">
							<ul class="menu-area-main">
								<li class="active"><a href="/" class="home">
										<div class="memu-name">
											<p class="name">Trang chủ</p>
											<p class="desc">Phân phối chính hãng</p>
										</div>
								</a></li>
								<li><a href="/super-chinese" class="supperchinese">
										<div class="memu-name">
											<p class="name">Super Chinese</p>
											<p class="desc">Learn to Earn</p>
										</div>
								</a></li>
								<li><a href="/super-test" class="suppertest">
										<div class="memu-name">
											<p class="name">Super Test</p>
											<p class="desc">HSK 9 Cấp</p>
										</div>
								</a></li>
								<li><a href="/goi-vip-superchinese" class="shoppingcart">
										<div class="memu-name">
											<p class="name">Mua gói VIP</p>
											<p class="desc">Chính hãng</p>
										</div>
								</a></li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
			<div class="login">
                 <a href="javascript:void(0)" onclick="loginZaloSp()"><i class="fa fa-user"></i></a>
            </div>
		</div>
	</div>
</div>
</div>
<jsp:include page="../common/jsCommon.jsp" flush="true" />
<script type="text/javascript">
	var isSmartPhone ;
	$(document).ready(function() {
		if( /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) ) {
			isSmartPhone = true;
			$('.login').css('display','block');
		} else {
			isSmartPhone = false;
			$('.login').css('display','none');
		}
	});
	$('.logout-btn').click(function(){
		window.localStorage.removeItem('accesstoken');
		window.location.href = '/logout';
		
	});
	$('.btn-profile').click(function(){
		window.location.href ='/kich-hoat-vip';
	});
	
	function loginZaloSp() {
		var loginZalo = '<%=loginZalo%>';
		if(loginZalo == '') {
			var accesstoken = window.localStorage.getItem('accesstoken');
			var newAccesstoken ='<%=accesstokenZalo%>';
			
			if (newAccesstoken && newAccesstoken != accesstoken) {
				window.localStorage.setItem('accesstoken', newAccesstoken);
				accesstoken = newAccesstoken;
			}
			if (accesstoken) {
				window.location.href = '/dang-nhap?accesstoken='
						+ accesstoken + '&login=1';
			} else {
				window.location.href = '/dang-nhap'+ '?login=1';
			}
		} else {
			window.location.href ='/kich-hoat-vip';
		}
	}
</script>
