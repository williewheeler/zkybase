<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="editUrl" value="/projects/${project.id}/edit" />

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><c:out value="${project.name}" /></title>
</head>
<body>

<div id="pageHead">
	<div class="yui-gc">
		<div class="yui-u first">
			<h1 style="display:inline"><c:out value="${project.name}" /></h1>
			<span style="color:#666;">(<c:out value="${project.id}" />)</span>
			<ul class="menu" style="display:inline;margin-left:10px">
				<li><span class="editProject icon"><a href="${editUrl}">Edit</a></span></li>
				<li><span class="deleteProject icon"><a href="#">Delete</a></span></li>
			</ul>
		</div>
		<div class="yui-u" style="margin-top:12px;text-align:right">
			<ul class="menu">
				<li><span class="json icon"><a href="#">JSON</a></span></li>
				<li><span class="xml icon"><a href="#">XML</a></span></li>
			</ul>
		</div>
	</div>
</div>
<div id="pageBody">
	<c:if test="${not empty project.shortDescription}">
		<p><c:out value="${project.shortDescription}" /></p>
	</c:if>
	
	<h2>Members</h2>
	<ul>
		<c:forEach var="member" items="${project.members}">
			<li><c:out value="${member.firstName}" /></li>
		</c:forEach>
	</ul>
	
	<h2>Packages</h2>
	<ul>
		<c:forEach var="pkg" items="${project.packages}">
			<li><c:out value="${pkg.name}" /></li>
		</c:forEach>
	</ul>
</div>

</body>
</html>
