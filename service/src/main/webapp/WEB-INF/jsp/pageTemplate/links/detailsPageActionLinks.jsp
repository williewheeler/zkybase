<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="entityName" value="${navigation.currentNode.name}" />

<%-- FIXME The path to the entity is not necessarily the current node's path. See, e.g., person SCM. --%>
<c:set var="entityPath" value="${navigation.currentNode.path}" />

<c:url var="entityUrl" value="${entityPath}" />
<c:url var="entityEditorUrl" value="${entityPath}/edit" />

<ul class="inlineLinks">
	<li><a href="${entityEditorUrl}" class="btn"><span class="iconx edit"><spring:message code="label.common.edit" /></span></a></li>
	<li><a id="deleteLink" class="btn" data-toggle="modal" href="#deleteModal"><span class="iconx delete"><spring:message code="label.common.delete" /></span></a></li>
</ul>

<%-- FIXME Delegate to deleteDialog.jsp --%>
<div id="deleteModal" class="modal fade">
	<div class="modal-header">
		<a href="#" class="close" data-dismiss="modal">&times;</a>
		<h3>Delete <c:out value="${entityName}" /></h3>
	</div>
	<div class="modal-body">
		<p>This will delete <c:out value="${entityName}"/> permanently. Are you sure?</p>
	</div>
	<div class="modal-footer">
		<form action="${entityUrl}" method="post">
			<input type="hidden" name="_method" value="delete" />
			<input id="#reallyDeleteButton" type="submit" value="Delete" class="btn btn-danger" />
			<a href="#" class="btn cancel" data-dismiss="modal">Cancel</a>
		</form>
	</div>
</div>
