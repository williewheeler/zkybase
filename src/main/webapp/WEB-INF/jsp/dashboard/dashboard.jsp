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

<h1 id="dashboardTitle">Dashboard</h1>

<c:if test="${param.created}">
	<div class="alert-message success">
		<a href="#" class="close">&times;</a>
		<p>Project successfully created.</p>
	</div>
</c:if>

<div class="row">
	<div class="span10">
		<jsp:include page="projectsPane.jsp" />
	</div>
	<div class="span6">
		<jsp:include page="updatesPane.jsp" />
	</div>
</div>

</body>
</html>
