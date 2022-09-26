<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
	<meta charset="UTF-8">
    <jsp:include page="../common/metaCommon.jsp" flush="true" />
	<meta name="keywords" content="Super,chinese, ứng, dụng, hoc, tiếng, trung, chính, hãng, số 1, việt, nam" /> 
	<meta name="description" content="Super chinese ứng dụng học tiếng Trung chính hãng số 1 Việt Nam, ứng dụng học tiếng Trung giá rẻ tốt nhất 2022, ứng dụng học tiếng trung HSK mới nhất " /> 
	<meta name="generator" content="Cty TNHH Konec" />
    <jsp:include page="../common/cssCommon.jsp" flush="true" />
    <title>Thi thử HSK</title>
</head>

<body class="main-layout">
    <!--<div class="loader_bg">
        <div class="loader"><img src="resources/images/images/loading.gif" alt="#" /></div>
    </div>-->

    <header>
        <div class="header-top">
            <jsp:include page="../common/headerCommon.jsp" flush="true" />
            <jsp:include page="../common/subMenuCommon.jsp" flush="true" />
        </div>
    </header>

    <section id="hsk" class="hsk">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <iframe id="u1193_input" scrolling="auto" frameborder="1" webkitallowfullscreen="" mozallowfullscreen="" allowfullscreen="" src="https://www.hskonline.com/vi/1/1" style="width: 100%; height: 1000px"></iframe>
                </div>
            </div>
        </div>
    </section>
   
    <jsp:include page="../common/footerCommon.jsp" flush="true" />
    <jsp:include page="../common/zaloChatCommon.jsp" flush="true" />
    <jsp:include page="../common/jsCommon.jsp" flush="true" />
</body>

</html>