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
String loginZalo ="";
if (zaloInfo != null) {
	accesstokenZalo = zaloInfo.getAccesstoken();
	loginZalo = zaloInfo.getLoginFlag();
	zaloName = zaloInfo.getZaloName();
	request.setAttribute("loginZalo", loginZalo);
}
%>
<div class="sub-menu">
	<div class="container-fluid">
		<div class="row">
			<div class="col item">
				<a href="/ky-thi-hsk-la-gi" title="Kỳ thi HSK là gì?">Kỳ thi HSK là gì?</a>
			</div>
			<div class="col item">
				<a href="/thi-thu-hsk" title="Thi thử HSK">Thi thử HSK</a>
			</div>
			<div class="col item">
				<a href="https://link.konec.vn/app-superchinese"
					title="Tải xuống Super Chinese">Tải xuống Super Chinese</a>
			</div>
			<div class="col item">
				<a href="https://link.konec.vn/app-supertest"
					title="Tải xuống Super Test">Tải xuống Super Test</a>
			</div>

			<c:if test="${loginZalo == null}">
				<c:if test="${superchinese == null}">
					<div class="col item active">
				</c:if>
				<c:if test="${superchinese != null}">
					<div class="col item s-chinese active">
				</c:if>
				<a href="javascript:void(0)" onclick="loginZalo()"
					title="Đăng nhập Zalo">Kích hoạt VIP</a>
				</div>
			</c:if>
			<c:if test="${loginZalo != null}">
				<c:if test="${superchinese == null}">
					<div class="col item active">
				</c:if>
				<c:if test="${superchinese != null}">
					<div class="col item s-chinese active">
				</c:if>
				<a href="/kich-hoat-vip"
					title="Đăng nhập Zalo">Kích hoạt VIP</a>
					</div>
			</c:if>
		</div>
	</div>
</div>
</div>
<script type="text/javascript">
	function loginZalo() {
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
			window.location.href = '/dang-nhap?login=1';
		}
	}
</script>