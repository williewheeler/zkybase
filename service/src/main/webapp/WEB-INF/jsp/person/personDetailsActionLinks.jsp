<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="personPath" value="/people/${person.id}" />

<c:url var="personUrl" value="${personPath}" />
<c:url var="editUrl" value="${personPath}/edit" />

<spring:message var="deletePersonTooltip" code="tooltip.personDetails.deletePerson" />
<spring:message var="editPersonTooltip" code="tooltip.personDetails.editPerson" />

<script type="text/javascript">
$(function() {
	$("#deletePersonLink").click(function() {
		$("#deletePersonDialog").modal("show");
		return false;
	});
});
</script>

<ul class="inlineLinks">
	<li><a href="${editUrl}" title="${editPersonTooltip}" class="btn"><span class="editPerson icon"><spring:message code="label.common.edit" /></span></a></li>
	<li><a id="deletePersonLink" href="#" title="${deletePersonTooltip}" class="btn"><span class="deletePerson icon"><spring:message code="label.common.delete" /></span></a></li>
</ul>

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
