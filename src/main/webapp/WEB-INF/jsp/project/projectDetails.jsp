<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="projectPath" value="/projects/${project.key}" />

<c:url var="editUrl" value="${projectPath}/edit" />
<c:url var="jsonUrl" value="${projectPath}.json" />
<c:url var="xmlUrl" value="${projectPath}.xml" />

<spring:message var="deleteProjectTooltip" code="tooltip.projectDetails.deleteProject" />
<spring:message var="editProjectTooltip" code="tooltip.projectDetails.editProject" />
<spring:message var="jsonTooltip" code="tooltip.projectDetails.json" />
<spring:message var="xmlTooltip" code="tooltip.projectDetails.xml" />

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><c:out value="${project.name}" /></title>
<script type="text/javascript">
$(function() {
	$("#deleteProjectLink").click(function() {
		$("#deleteProjectDialog").modal("show");
		return false;
	});
});
</script>
</head>
<body>

<div>
	<h1 style="display:inline"><c:out value="${project.name}" /></h1>
	<span style="color:#666;">(<c:out value="${project.key}" />)</span>
	<ul class="menu" style="display:inline;margin-left:20px">
		<li><span class="json icon"><a href="${jsonUrl}" title="${jsonTooltip}"><spring:message code="label.common.json" /></a></span></li>
		<li><span class="xml icon"><a href="${xmlUrl}" title="${xmlTooltip}"><spring:message code="label.common.xml" /></a></span></li>
		<li></li>
		<li><a href="${editUrl}" title="${editProjectTooltip}" class="btn"><span class="editProject icon"><spring:message code="label.common.edit" /></span></a></li>
		<li><a id="deleteProjectLink" href="#" title="${deleteProjectTooltip}" class="btn"><span class="deleteProject icon"><spring:message code="label.common.delete" /></span></a></li>
	</ul>
</div>

<c:if test="${param.a == 'updated'}">
	<div class="row">
		<div class="span16">
			<div class="alert-message success fade in">
				<a href="#" class="close">&times;</a>
				<p>Project successfully updated.</p>
			</div>
		</div>
	</div>
</c:if>

<c:if test="${param.a == 'cancelled'}">
	<div class="row">
		<div class="span16">
			<div class="alert-message info fade in">
				<a href="#" class="close">&times;</a>
				<p>Project edit cancelled.</p>
			</div>
		</div>
	</div>
</c:if>

<div class="row" style="margin-top:20px">
	<div class="span10">
		<c:if test="${not empty project.shortDescription}">
			<p><c:out value="${project.shortDescription}" /></p>
		</c:if>
		
		<ul class="pills">
			<li class="active"><a href="#packages">Packages</a></li>
			<li><a href="#farms">Farms</a></li>
			<li><a href="#team">Team</a></li>
		</ul>
		<div class="pill-content">
			<div id="packages" class="active">
				<jsp:include page="packagesPane.jsp" />
			</div>
			<div id="farms">
				<jsp:include page="farmsPane.jsp" />
			</div>
			<div id="team">
				<jsp:include page="teamPane.jsp" />
			</div>
		</div>
	</div>
	<div class="span6"><jsp:include page="updatesPane.jsp" /></div>
</div>

<%-- Delete project dialog --%>
<div id="deleteProjectDialog" class="modal hide fade">
	<div class="modal-header">
		<a href="#" class="close">&times;</a>
		<h3>Delete project</h3>
	</div>
	<div class="modal-body">
		<p>This will delete the project permanently. Are you sure?</p>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn submit danger">Delete</a>
		<a href="#" class="btn cancel">Cancel</a>
	</div>
</div>

</body>
</html>
			
