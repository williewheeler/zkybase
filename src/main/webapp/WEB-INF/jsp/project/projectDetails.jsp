<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="editUrl" value="/projects/${project.id}/edit" />

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><c:out value="${project.name}" /></title>
</head>
<body>

<div class="row">
	<div class="span16">
		<h1 style="display:inline"><c:out value="${project.name}" /></h1>
		<span style="color:#666;">(<c:out value="${project.id}" />)</span>
		<ul class="menu" style="display:inline;margin-left:10px">
			<li><span class="json icon"><a href="#">JSON</a></span></li>
			<li><span class="xml icon"><a href="#">XML</a></span></li>
			<li><span class="editProject icon"><a href="${editUrl}">Edit</a></span></li>
			<li><span class="deleteProject icon"><a href="#">Delete</a></span></li>
		</ul>
	</div>
</div>
<div class="row" style="margin-top:20px">
	<div class="span10">
		<c:if test="${not empty project.shortDescription}">
			<p><c:out value="${project.shortDescription}" /></p>
		</c:if>
		<jsp:include page="farmsPane.jsp" />
		<jsp:include page="packagesPane.jsp" />
		<jsp:include page="teamPane.jsp" />
	</div>
	<div class="span6"><jsp:include page="updatesPane.jsp" /></div>
</div>

</body>
</html>
			
