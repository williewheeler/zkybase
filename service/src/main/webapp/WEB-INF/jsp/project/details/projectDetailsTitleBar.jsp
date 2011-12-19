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

<script type="text/javascript">
$(function() {
	$("#deleteProjectLink").click(function() {
		$("#deleteProjectDialog").modal("show");
		return false;
	});
});
</script>

<div class="row">
	<div class="span10">
		<h1 style="display:inline"><c:out value="${project.name}" /></h1>
		<span style="color:#666;">(<c:out value="${project.key}" />)</span>
		<ul class="menu" style="display:inline;margin-left:20px;white-space:nowrap;">
			<li><span class="json icon"><a href="${jsonUrl}" title="${jsonTooltip}"><spring:message code="label.common.json" /></a></span></li>
			<li><span class="xml icon"><a href="${xmlUrl}" title="${xmlTooltip}"><spring:message code="label.common.xml" /></a></span></li>
		</ul>
	</div>
	<div class="span6" style="text-align:right">
		<h1 style="display:inline"></h1> <%-- Valigns the following menu --%>
		<ul class="menu" style="display:inline;margin-left:20px">
			<li><a href="${editUrl}" title="${editProjectTooltip}" class="btn"><span class="editProject icon"><spring:message code="label.common.edit" /></span></a></li>
			<li><a id="deleteProjectLink" href="#" title="${deleteProjectTooltip}" class="btn"><span class="deleteProject icon"><spring:message code="label.common.delete" /></span></a></li>
		</ul>
	</div>
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
