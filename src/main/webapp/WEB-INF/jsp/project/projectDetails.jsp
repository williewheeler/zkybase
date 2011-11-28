<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url var="editUrl" value="/projects/${project.id}/edit" />
<c:url var="jsonUrl" value="/projects/${project.id}.json" />
<c:url var="xmlUrl" value="/projects/${project.id}.xml" />
<c:url var="personSearchUrl" value="/people/search.json" />

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
	
	$("#addMemberDialog")
		.bind("hidden", function() { $("#newMemberInput").val(""); })
		.bind("shown", function() { $("#newMemberInput").focus(); });
	
	$("#addMemberLink").click(function() {
		$("#addMemberDialog").modal("show");
		return false;
	});
	
	$("#newMemberInput").autocomplete({
		source: "${personSearchUrl}?q=dummy",
		minLength: 2
	});
	
	$("#addMemberAddButton").click(function() {
		$("#addMemberDialog").modal("hide");
	});
});
</script>
</head>
<body>

<div class="row">
	<div class="span16">
		<h1 style="display:inline"><c:out value="${project.name}" /></h1>
		<span style="color:#666;">(<c:out value="${project.id}" />)</span>
		<ul class="menu" style="display:inline;margin-left:20px">
			<li><span class="editProject icon"><a href="${editUrl}" title="${editProjectTooltip}"><spring:message code="label.common.edit" /></a></span></li>
			<li><span class="deleteProject icon"><a id="deleteProjectLink" href="#" title="${deleteProjectTooltip}"><spring:message code="label.common.delete" /></a></span></li>
			<li></li>
			<li><span class="json icon"><a href="${jsonUrl}" title="${jsonTooltip}"><spring:message code="label.common.json" /></a></span></li>
			<li><span class="xml icon"><a href="${xmlUrl}" title="${xmlTooltip}"><spring:message code="label.common.xml" /></a></span></li>
		</ul>
	</div>
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
		<jsp:include page="farmsPane.jsp" />
		<jsp:include page="packagesPane.jsp" />
		<jsp:include page="teamPane.jsp" />
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
		<a href="#" class="btn modalCancelButton">Cancel</a>
		<a href="#" class="btn danger">Delete</a>
	</div>
</div>

<%-- Add member dialog --%>
<div id="addMemberDialog" class="modal hide fade">
	<div class="modal-header">
		<a href="#" class="close">&times;</a>
		<h3>Add member</h3>
	</div>
	<div class="modal-body">
		<div class="ui-widget">
			<p>Start typing the name or username of a team member to add:</p>
			<input type="text" id="newMemberInput" class="span8" />
		</div>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn modalCancelButton">Cancel</a>
		<a id="addMemberAddButton" href="#" class="btn primary">Add</a>
	</div>
</div>

</body>
</html>
			
