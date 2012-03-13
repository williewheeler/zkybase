<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<c:url var="faviconUrl" value="/images/icons/favicon.ico" />

<%-- CSS --%>
<c:url var="bootstrapCssUrl" value="/bootstrap/css/bootstrap.min.css" />
<c:url var="coreCssUrl" value="/styles/core.css" />
<c:url var="jitCssUrl" value="/scripts/jit/Examples/css/base.css" />
<c:url var="hypertreeCssUrl" value="/scripts/jit/Examples/css/Hypertree.css" />

<%-- JavaScript --%>
<c:url var="bootstrapJsUrl" value="/bootstrap/js/bootstrap.min.js" />
<c:url var="jqueryJsUrl" value="/scripts/jquery/jquery-1.7.1.min.js" />
<%-- <c:url var="jqueryLocalScrollJsUrl" value="/scripts/jquery/jquery.localscroll-1.2.7-min.js" /> --%>
<%-- <c:url var="jqueryScrollToJsUrl" value="/scripts/jquery/jquery.scrollTo-1.4.2-min.js" /> --%>
<c:url var="jqueryTablesorterJsUrl" value="/scripts/jquery/jquery.tablesorter.min.js" />
<c:url var="coreJsUrl" value="/scripts/core.js" />

<html lang="en">
	<head>
		<title><c:out value="${navigation.pageTitle}" /> - Zkybase</title>
		<link rel="shortcut icon" type="image/x-icon" href="${faviconUrl}" />
		<link rel="stylesheet" type="text/css" href="${bootstrapCssUrl}" />
		<link rel="stylesheet" type="text/css" href="${jitCssUrl}" />
		<link rel="stylesheet" type="text/css" href="${hypertreeCssUrl}" />
		<link rel="stylesheet" type="text/css" href="${coreCssUrl}" />
		
		<%-- Have to put this at the top since internal pages make jQuery calls. Can fix that though. --%>
		<script type="text/javascript" src="${jqueryJsUrl}"></script>
<%-- 		<script type="text/javascript" src="${jqueryScrollToJsUrl}"></script> --%>
<%-- 		<script type="text/javascript" src="${jqueryLocalScrollJsUrl}"></script> --%>
		<script type="text/javascript" src="${jqueryTablesorterJsUrl}"></script>
		<script type="text/javascript" src="${bootstrapJsUrl}"></script>
		<script type="text/javascript" src="${coreJsUrl}"></script>
	</head>
	<body>
		<jsp:include page="navigation/topbar.jsp" />
		<div class="container">
			<jsp:include page="navigation/breadcrumbs.jsp" />
			<jsp:include page="alerts.jsp" />
			<tiles:insertAttribute name="content" />
			<jsp:include page="footer.jsp" />
		</div>
	</body>
</html>
