<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="personPath" value="/people/${person.id}" />

<c:url var="personUrl" value="${personPath}" />
<c:url var="editUrl" value="${personPath}/edit" />
<c:url var="jsonUrl" value="${personPath}.json" />
<c:url var="xmlUrl" value="${personPath}.xml" />

<spring:message var="deletePersonTooltip" code="tooltip.personDetails.deletePerson" />
<spring:message var="editPersonTooltip" code="tooltip.personDetails.editPerson" />
<spring:message var="jsonTooltip" code="tooltip.personDetails.json" />
<spring:message var="xmlTooltip" code="tooltip.personDetails.xml" />

<script type="text/javascript">
$(function() {
	$("#deletePersonLink").click(function() {
		$("#deletePersonDialog").modal("show");
		return false;
	});
});
</script>

<div class="row">
	<div class="span12">
		<h1 style="display:inline"><c:out value="${person.firstNameLastName}" /></h1>
		<span style="color:#666;">(<c:out value="${person.username}" />)</span>
		<ul class="menu" style="display:inline;margin-left:20px">
			<li><span class="json icon"><a href="${jsonUrl}" title="${jsonTooltip}"><spring:message code="label.common.json" /></a></span></li>
			<li><span class="xml icon"><a href="${xmlUrl}" title="${xmlTooltip}"><spring:message code="label.common.xml" /></a></span></li>
		</ul>
	</div>
	<div class="span4">
		<h1 style="display:inline"></h1>
		<ul class="menu" style="display:inline">
			<li><a href="${editUrl}" title="${editPersonTooltip}" class="btn"><span class="editPerson icon"><spring:message code="label.common.edit" /></span></a></li>
			<li><a id="deletePersonLink" href="#" title="${deletePersonTooltip}" class="btn"><span class="deletePerson icon"><spring:message code="label.common.delete" /></span></a></li>
		</ul>
	</div>
</div>

<%-- Delete project dialog --%>
<div id="deletePersonDialog" class="modal hide fade">
	<div class="modal-header">
		<a href="#" class="close">&times;</a>
		<h3>Delete person</h3>
	</div>
	<div class="modal-body">
		<p>This will delete the person permanently. Are you sure?</p>
	</div>
	<div class="modal-footer">
		<form action="${personUrl}" method="post">
			<input type="hidden" name="_method" value="delete" />
			<input type="submit" value="Delete" class="btn danger" />
			<a href="#" class="btn cancel">Cancel</a>
		</form>
	</div>
</div>
