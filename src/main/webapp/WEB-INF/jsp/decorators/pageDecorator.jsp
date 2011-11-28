<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<%-- CSS --%>
<c:url var="jqueryUiCssUrl" value="/scripts/jquery-ui-1.8.16.custom/css/cupertino/jquery-ui-1.8.16.custom.css" />
<c:url var="coreCssUrl" value="/styles/core.css" />

<%-- JavaScript --%>
<c:url var="jqueryJsUrl" value="/scripts/jquery-1.7.1.min.js" />
<c:url var="jqueryUiJsUrl" value="/scripts/jquery-ui-1.8.16.custom/js/jquery-ui-1.8.16.custom.min.js" />
<c:url var="bsAlertsJsUrl" value="/scripts/bootstrap-alerts-1.4.0.js" />
<c:url var="bsModalJsUrl" value="/scripts/bootstrap-modal-1.4.0.js" />
<c:url var="bsTwipsyJsUrl" value="/scripts/bootstrap-twipsy-1.4.0.js" />
<c:url var="coreJsUrl" value="/scripts/core.js" />

<c:url var="faviconUrl" value="/images/icons/favicon.ico" />
<c:url var="logoUrl" value="/images/skybase.png" />

<c:url var="dashboardUrl" value="/" />
<c:url var="peopleUrl" value="/people" />

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><decorator:title /></title>
<link rel="shortcut icon" type="image/x-icon" href="${faviconUrl}" />

<link rel="stylesheet" type="text/css" href="${jqueryUiCssUrl}" />
<link rel="stylesheet" href="http://twitter.github.com/bootstrap/1.4.0/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${coreCssUrl}" />

<script type="text/javascript" src="${jqueryJsUrl}"></script>
<script type="text/javascript" src="${jqueryUiJsUrl}"></script>
<script type="text/javascript" src="${bsAlertsJsUrl}"></script>
<script type="text/javascript" src="${bsModalJsUrl}"></script>
<script type="text/javascript" src="${bsTwipsyJsUrl}"></script>
<script type="text/javascript" src="${coreJsUrl}"></script>

<decorator:head />
</head>
<body>

<div id="doc3">
	<div id="hd">
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
		<div id="bd">
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
			<decorator:body />
		</div>
		<div id="ft">
			<p>Skybase is open source software.
			To participate in its development, please visit <a href="https://github.com/williewheeler/skybase">https://github.com/williewheeler/skybase</a>.</p>
			
			<p>Copyright &copy; 2011-2012 <a href="http://wheelersoftware.com/">Willie Wheeler</a> and the original authors. All rights reserved.<br />
			Licensed under the <a href="http://www.apache.org/licenses/LICENSE-2.0">Apache License, Version 2.0</a>.</p>
		</div>
	</div>
</div>

</body>
</html>
