<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<c:url var="faviconUrl" value="/images/icons/favicon.ico" />

<%-- CSS --%>
<c:url var="jqueryUiCssUrl" value="/scripts/jquery/jquery-ui-1.8.16.custom/css/cupertino/jquery-ui-1.8.16.custom.css" />
<c:url var="coreCssUrl" value="/styles/core.css" />
<c:url var="jitCssUrl" value="/scripts/jit/Examples/css/base.css" />
<c:url var="hypertreeCssUrl" value="/scripts/jit/Examples/css/Hypertree.css" />

<%-- jQuery --%>
<c:url var="jqueryJsUrl" value="/scripts/jquery/jquery-1.7.1.min.js" />
<c:url var="jqueryLocalScrollJsUrl" value="/scripts/jquery/jquery.localscroll-1.2.7-min.js" />
<c:url var="jqueryScrollToJsUrl" value="/scripts/jquery/jquery.scrollTo-1.4.2-min.js" />
<c:url var="jqueryTablesorterJsUrl" value="/scripts/jquery/jquery.tablesorter.min.js" />
<c:url var="jqueryUiJsUrl" value="/scripts/jquery/jquery-ui-1.8.16.custom/js/jquery-ui-1.8.16.custom.min.js" />

<%-- Twitter Bootstrap --%>
<c:url var="bsAlertsJsUrl" value="/scripts/bootstrap/bootstrap-alerts-1.4.0.js" />
<c:url var="bsButtonsJsUrl" value="/scripts/bootstrap/bootstrap-buttons-1.4.0.js" />
<c:url var="bsDropdownJsUrl" value="/scripts/bootstrap/bootstrap-dropdown-1.4.0.js" />
<c:url var="bsModalJsUrl" value="/scripts/bootstrap/bootstrap-modal-1.4.0.js" />
<c:url var="bsTabsJsUrl" value="/scripts/bootstrap/bootstrap-tabs-1.4.0.js" />
<c:url var="bsTwipsyJsUrl" value="/scripts/bootstrap/bootstrap-twipsy-1.4.0.js" />

<%-- Custom JS --%>
<c:url var="coreJsUrl" value="/scripts/core.js" />

<html lang="en">
	<head>
		<title><c:out value="${navigation.currentNode.name}" /> - Skybase</title>
		
		<link rel="shortcut icon" type="image/x-icon" href="${faviconUrl}" />
		<link rel="stylesheet" type="text/css" href="${jqueryUiCssUrl}" />
		<link rel="stylesheet" type="text/css" href="http://twitter.github.com/bootstrap/1.4.0/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="${coreCssUrl}" />
		<link rel="stylesheet" type="text/css" href="${jitCssUrl}" />
		<link rel="stylesheet" type="text/css" href="${hypertreeCssUrl}" />

		<script type="text/javascript" src="${jqueryJsUrl}"></script>
		<script type="text/javascript" src="${jqueryScrollToJsUrl}"></script>
		<script type="text/javascript" src="${jqueryLocalScrollJsUrl}"></script>
		<script type="text/javascript" src="${jqueryTablesorterJsUrl}"></script>
		<script type="text/javascript" src="${jqueryUiJsUrl}"></script>
		<script type="text/javascript" src="${bsAlertsJsUrl}"></script>
		<script type="text/javascript" src="${bsButtonsJsUrl}"></script>
		<script type="text/javascript" src="${bsDropdownJsUrl}"></script>
		<script type="text/javascript" src="${bsModalJsUrl}"></script>
		<script type="text/javascript" src="${bsTabsJsUrl}"></script>
		<script type="text/javascript" src="${bsTwipsyJsUrl}"></script>
		<script type="text/javascript" src="${coreJsUrl}"></script>
	</head>
	<body>
		<jsp:include page="topbar.jsp" />
		<div id="body">
			<div class="container">
				<jsp:include page="breadcrumbs.jsp" />
				<jsp:include page="alerts.jsp" />
				<jsp:include page="titleBar.jsp" />
				<div id="content"><tiles:insertAttribute name="content" /></div>
			</div>
		</div>
		<jsp:include page="footer.jsp" />
	</body>
</html>
