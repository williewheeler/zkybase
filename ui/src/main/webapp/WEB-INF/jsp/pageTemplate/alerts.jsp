<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="alerts">
	<c:if test="${param.a == 'created'}">
		<div class="alert alert-success fade in">
			<a class="close" data-dismiss="alert">&times;</a>
			Created.
		</div>
	</c:if>
	<c:if test="${param.a == 'deleted'}">
		<div class="alert alert-success fade in">
			<a class="close" data-dismiss="alert">&times;</a>
			Deleted.
		</div>
	</c:if>
	<c:if test="${param.a == 'updated'}">
		<div class="alert alert-success fade in">
			<a class="close" data-dismiss="alert">&times;</a>
			Updated.
		</div>
	</c:if>
	<c:if test="${param.a == 'cancelled'}">
		<div class="alert alert-info fade in">
			<a class="close" data-dismiss="alert">&times;</a>
			Cancelled.
		</div>
	</c:if>
	
	<%-- This one's for form validation errors. Can't use HTTP params here. --%>
	<c:if test="${hasErrors}">
		<div class="alert alert-error fade in">
			<a class="close" data-dismiss="alert">&times;</a>
			<spring:message code="error.global" />
		</div>
	</c:if>
</div>
