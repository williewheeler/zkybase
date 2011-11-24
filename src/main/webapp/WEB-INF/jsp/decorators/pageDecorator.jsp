<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<c:url var="faviconUrl" value="/images/icons/favicon.ico" />
<c:url var="coreCssUrl" value="/styles/core.css" />
<c:url var="logoUrl" value="/images/skybase.png" />
<c:url var="dashboardUrl" value="/" />

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><decorator:title /></title>
<link rel="shortcut icon" type="image/x-icon" href="${faviconUrl}" />

<!-- Individual YUI CSS files -->
<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/yui/2.9.0/build/reset-fonts-grids/reset-fonts-grids.css" />
<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/yui/2.9.0/build/base/base-min.css" />

<link rel="stylesheet" type="text/css" href="${coreCssUrl}" />
<decorator:head />
</head>
<body>

<div id="doc4">
	<div id="superhd">
		<div class="yui-ge">
			<div class="yui-u first">
				<div id="breadcrumbs">
					<ul>
						<c:forEach var="breadcrumb" items="${breadcrumbList}">
							<c:url var="breadcrumbHref" value="${breadcrumb.href}" />
							<li><a href="${breadcrumbHref}"><c:out value="${breadcrumb.label}" /></a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="yui-u">
				<div id="sessionInfo">
					<ul>
						<li><a href="#">Willie Wheeler</a></li>
						<li><a href="#">Log out</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div id="hd">
		<div id="logo"><a href="${dashboardUrl}"><img src="${logoUrl}" alt="Skybase" /></a></div>
	</div>
	<div id="bd">
		<decorator:body />
	</div>
	<div id="ft">
		<div>[FOOTER STUFF]</div>
	</div>
</div>

</body>
</html>
