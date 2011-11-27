<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="addPersonUrl" value="/people/new.do" />

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>People</title>
<style type="text/css">
	.person { margin: 20px 0; }
	.personPhoto { float: left; margin-right: 10px; }
	.personName { font-size: 116%; }
</style>
</head>
<body>

<div id="pageHead">
	<div class="yui-g">
		<div class="yui-u first">
			<h1>People</h1>
		</div>
		<div class="yui-u" style="padding-top:12px;text-align:right">
			<span class="addUser icon"><a href="${addPersonUrl}">Add person</a></span>
		</div>
	</div>
</div>
<div id="pageBody">
	<c:if test="${param.created}">
		<div class="info alert">
			<div class="message">Person added successfully.</div>
		</div>
	</c:if>
	<c:choose>
		<c:when test="${empty personList}">
			<p>None.</p>
		</c:when>
		<c:otherwise>
			<c:forEach var="person" items="${personList}">
				<c:url var="personUrl" value="/people/${person.username}" />
				<div class="person">
					<div class="personPhoto">
						<div class="gravatar"><img src="http://www.gravatar.com/avatar/b0a72ae61c5c74a51fe46cf66599b6c5.png" /></div>
					</div>
					<div>
						<div>
							<span class="personName"><a href="${personUrl}"><c:out value="${person.firstNameLastName}" /></a></span>
							<span class="personUsername secondary">(<c:out value="${person.username}" />)</span>
						</div>
						<c:if test="${not empty person.email}">
							<div class="personEmail">
								<a href="mailto:<c:out value="${person.email}" />"><c:out value="${person.email}" /></a>
							</div>
						</c:if>
					</div>
					<div style="clear:both"></div>
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</div>

</body>
</html>
