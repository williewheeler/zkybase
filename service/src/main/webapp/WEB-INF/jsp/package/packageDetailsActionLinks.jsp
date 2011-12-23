<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="packagePath" value="/packages/${package.id}" />

<c:url var="packageUrl" value="${packagePath}" />
<c:url var="editUrl" value="${packagePath}/edit" />

<spring:message var="deletePackageTooltip" code="tooltip.packageDetails.deletePackage" />
<spring:message var="editPackageTooltip" code="tooltip.packageDetails.editPackage" />

<script type="text/javascript">
$(function() {
	$("#deletePackageLink").click(function() {
		$("#deletePackageDialog").modal("show");
		return false;
	});
});
</script>

<ul class="inlineLinks">
	<li><a href="${editUrl}" title="${editPackageTooltip}" class="btn"><span class="editPackage icon"><spring:message code="label.common.edit" /></span></a></li>
	<li><a id="deletePackageLink" href="#" title="${deletePackageTooltip}" class="btn"><span class="deletePackage icon"><spring:message code="label.common.delete" /></span></a></li>
</ul>

<%-- Delete package dialog --%>
<div id="deletePackageDialog" class="modal hide fade">
	<div class="modal-header">
		<a href="#" class="close">&times;</a>
		<h3>Delete package</h3>
	</div>
	<div class="modal-body">
		<p>This will delete the package permanently. Are you sure?</p>
	</div>
	<div class="modal-footer">
		<form action="${packageUrl}" method="post">
			<input type="hidden" name="_method" value="delete" />
			<input type="submit" value="Delete" class="btn danger" />
			<a href="#" class="btn cancel">Cancel</a>
		</form>
	</div>
</div>
