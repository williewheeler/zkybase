<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<c:url var="coreCssUrl" value="/styles/core.css" />
<c:url var="jqueryJsUrl" value="/scripts/jquery-1.7.1.min.js" />
<c:url var="coreJsUrl" value="/scripts/core.js" />

<c:url var="faviconUrl" value="/images/icons/favicon.ico" />
<c:url var="logoUrl" value="/images/skybase.png" />

<c:url var="dashboardUrl" value="/" />
<c:url var="peopleUrl" value="/people" />

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><decorator:title /></title>
<link rel="shortcut icon" type="image/x-icon" href="${faviconUrl}" />

<!-- Individual YUI CSS files -->
<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/yui/2.9.0/build/reset-fonts-grids/reset-fonts-grids.css" />
<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/yui/2.9.0/build/base/base-min.css" />

<link rel="stylesheet" href="http://twitter.github.com/bootstrap/1.4.0/bootstrap.min.css" />

<link rel="stylesheet" type="text/css" href="${coreCssUrl}" />

<script type="text/javascript" src="${jqueryJsUrl}"></script>
<script type="text/javascript" src="${coreJsUrl}"></script>
<decorator:head />
</head>
<body>

<div id="doc3">
	<div id="hd">
		<div class="yui-gf">
			<div class="yui-u first">
				<div id="logo"><a href="${dashboardUrl}">Skybase</a></div>
			</div>
			<div class="yui-u">
				<div id="sessionInfo">
					<ul>
						<li><a href="${peopleUrl}">People</a></li>
						<li><a href="#">Willie Wheeler</a></li>
						<li><a href="#">Log out</a></li>
					</ul>
				</div>
			</div>
		</div>
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
			<div>[FOOTER STUFF]</div>
		</div>
	</div>
</div>

</body>
</html>
