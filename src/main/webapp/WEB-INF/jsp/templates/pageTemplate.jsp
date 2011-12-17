<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%-- CSS --%>
<c:url var="jqueryUiCssUrl" value="/scripts/jquery/jquery-ui-1.8.16.custom/css/cupertino/jquery-ui-1.8.16.custom.css" />
<c:url var="coreCssUrl" value="/styles/core.css" />

<%-- jQuery --%>
<c:url var="jqueryJsUrl" value="/scripts/jquery/jquery-1.7.1.min.js" />
<c:url var="jqueryLocalScrollJsUrl" value="/scripts/jquery/jquery.localscroll-1.2.7-min.js" />
<c:url var="jqueryScrollToJsUrl" value="/scripts/jquery/jquery.scrollTo-1.4.2-min.js" />
<c:url var="jqueryTablesorterJsUrl" value="/scripts/jquery/jquery.tablesorter.min.js" />
<c:url var="jqueryUiJsUrl" value="/scripts/jquery/jquery-ui-1.8.16.custom/js/jquery-ui-1.8.16.custom.min.js" />

<%-- Twitter Bootstrap --%>
<c:url var="bsAlertsJsUrl" value="/scripts/bootstrap/bootstrap-alerts-1.4.0.js" />
<c:url var="bsModalJsUrl" value="/scripts/bootstrap/bootstrap-modal-1.4.0.js" />
<c:url var="bsTabsJsUrl" value="/scripts/bootstrap/bootstrap-tabs-1.4.0.js" />
<c:url var="bsTwipsyJsUrl" value="/scripts/bootstrap/bootstrap-twipsy-1.4.0.js" />

<%-- Custom JS --%>
<c:url var="coreJsUrl" value="/scripts/core.js" />

<c:url var="faviconUrl" value="/images/icons/favicon.ico" />
<c:url var="logoUrl" value="/images/skybase.png" />

<c:url var="dashboardUrl" value="/" />
<c:url var="peopleUrl" value="/people" />

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><tiles:insertAttribute name="title" /> - Skybase</title>
<link rel="shortcut icon" type="image/x-icon" href="${faviconUrl}" />

<link rel="stylesheet" type="text/css" href="${jqueryUiCssUrl}" />
<link rel="stylesheet" href="http://twitter.github.com/bootstrap/1.4.0/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${coreCssUrl}" />

<script type="text/javascript" src="${jqueryJsUrl}"></script>
<script type="text/javascript" src="${jqueryScrollToJsUrl}"></script>
<script type="text/javascript" src="${jqueryLocalScrollJsUrl}"></script>
<script type="text/javascript" src="${jqueryTablesorterJsUrl}"></script>
<script type="text/javascript" src="${jqueryUiJsUrl}"></script>
<script type="text/javascript" src="${bsAlertsJsUrl}"></script>
<script type="text/javascript" src="${bsModalJsUrl}"></script>
<script type="text/javascript" src="${bsTabsJsUrl}"></script>
<script type="text/javascript" src="${bsTwipsyJsUrl}"></script>
<script type="text/javascript" src="${coreJsUrl}"></script>
</head>
<body>
<div id="hd">
	<a name="top" />
	<div id="logo" style="float:left"><a href="${dashboardUrl}">Skybase</a></div>
	<div id="sessionInfo" style="float:right">
		<ul>
			<li><a href="${peopleUrl}">People</a></li>
			<li><a href="#">Willie Wheeler</a></li>
			<li><a href="#">Log out</a></li>
		</ul>
	</div>
	<div style="clear:both"></div>
</div>
<div id="bdFtWrapper">
	<div class="row">
		<div class="span16">
			<div id="bd">
				<div id="breadcrumbsAndTitleBar">
					<c:if test="${not empty breadcrumbList}">
						<div id="breadcrumbs">
							<ul>
								<c:forEach var="breadcrumb" items="${breadcrumbList}">
									<c:url var="breadcrumbHref" value="${breadcrumb.href}" />
									<li><a href="${breadcrumbHref}"><c:out value="${breadcrumb.label}" /></a></li>
								</c:forEach>
							</ul>
						</div>
					</c:if>
					<div id="titleBar"><tiles:insertAttribute name="titleBar" /></div>
				</div>
				<div id="alerts"><jsp:include page="alerts.jsp" /></div>
				<div id="content"><tiles:insertAttribute name="content" /></div>
			</div>
			<div id="ft">
				<p>Skybase is open source software.
				To participate in its development, please visit <a href="https://github.com/williewheeler/skybase">https://github.com/williewheeler/skybase</a>.</p>
				<p>Copyright &copy; 2011-2012 <a href="http://wheelersoftware.com/">Willie Wheeler</a> and the original authors. All rights reserved.<br />
				Licensed under the <a href="http://www.apache.org/licenses/LICENSE-2.0">Apache License, Version 2.0</a>.</p>
				<p><a href="#top">Top</a></p>
			</div>
		</div>
	</div>
</div>

</body>
</html>
