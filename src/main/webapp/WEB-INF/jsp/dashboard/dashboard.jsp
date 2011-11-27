<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="dashboardCssUrl" value="/styles/dashboard.css" />

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Dashboard</title>
<link rel="stylesheet" type="text/css" href="${dashboardCssUrl}" />
</head>
<body>

<div id="pageHead">
	<h1 id="dashboardTitle">Dashboard</h1>
</div>
<div id="pageBody">
	<c:if test="${param.created}">
		<div class="alert-message success">
			<a class="close" href="#">x</a>
			<p>Project successfully created.</p>
		</div>
	</c:if>
	<c:if test="${param.updated}">
		<div class="alert-message success">
			<a class="close" href="#">x</a>
			<p>Project successfully updated.</p>
		</div>
	</c:if>
	<div class="yui-g">
		<div class="yui-u first">
			<jsp:include page="projectPane.jsp" />
		</div>
		<div class="yui-u">
			<jsp:include page="updatePane.jsp" />
		</div>
	</div>
</div>

</body>
</html>
