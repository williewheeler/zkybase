<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="alerts">
	<c:if test="${param.a == 'created'}">
		<div class="alert-message success fade in">
			<a href="#" class="close">&times;</a>
			<p>Created.</p>
		</div>
	</c:if>
	<c:if test="${param.a == 'deleted'}">
		<div class="alert-message success fade in">
			<a href="#" class="close">&times;</a>
			<p>Deleted.</p>
		</div>
	</c:if>
	<c:if test="${param.a == 'updated'}">
		<div class="alert-message success fade in">
			<a href="#" class="close">&times;</a>
			<p>Updated.</p>
		</div>
	</c:if>
	<c:if test="${param.a == 'cancelled'}">
		<div class="alert-message info fade in">
			<a href="#" class="close">&times;</a>
			<p>Cancelled.</p>
		</div>
	</c:if>

	<%-- This one's for form validation errors. Can't use HTTP params here. --%>
	<c:if test="${hasErrors}">
		<div class="alert-message error fade in">
			<a href="#" class="close">&times;</a>
			<p><spring:message code="error.global" /></p>
		</div>
	</c:if>
</div>
