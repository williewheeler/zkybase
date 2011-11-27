<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="editUrl" value="/projects/${project.id}/edit" />

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><c:out value="${project.name}" /></title>
<script type="text/javascript">
$(function() {
	$("#deleteDialog").modal({
		backdrop: true,
		keyboard: true
	});
	$("#deleteProjectLink").click(function() {
		$("#deleteDialog").modal("show");
		return false;
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
			<li><span class="editProject icon"><a href="${editUrl}">Edit</a></span></li>
			<li><span class="deleteProject icon"><a id="deleteProjectLink" href="#">Delete</a></span></li>
			<li></li>
			<li><span class="json icon"><a href="#" title="Renders a JSON view of this project">JSON</a></span></li>
			<li><span class="xml icon"><a href="#" title="Renders an XML view of this project">XML</a></span></li>
		</ul>
	</div>
</div>
<c:if test="${param.updated}">
	<div class="row">
		<div class="span16">
			<div class="alert-message success">
				<a href="#" class="close">&times;</a>
				<p>Project successfully updated.</p>
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

<!-- sample modal content -->
<div id="deleteDialog" class="modal hide fade">
	<div class="modal-header">
		<a href="#" class="close">&times;</a>
		<h3>Delete project</h3>
	</div>
	<div class="modal-body">
		<p>This will delete the project permanently. Are you sure?</p>
	</div>
	<div class="modal-footer">
		<a href="#" class="btn danger">Delete</a>
		<a href="#" class="btn">Cancel</a>
	</div>
</div>

</body>
</html>
			
