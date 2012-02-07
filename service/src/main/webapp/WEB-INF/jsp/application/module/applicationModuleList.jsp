<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ include file="/WEB-INF/jsp/pageTemplate/urls.jsp" %>

<%-- <c:set var="moduleList" value="${application.modules}" /> --%>
<c:set var="modulesPath" value="/applications/${application.id}/modules" />

<c:url var="modulesUrlCreateOk" value="${modulesPath}">
	<c:param name="a" value="created" />
</c:url>
<c:url var="newModuleUrl" value="${modulesPath}/new" />

<section>
	<div class="sectionTitleBar">
		<h2>Modules (<c:out value="${fn:length(moduleList)}" />)</h2>
		<div class="pull-right">
			<h2></h2>
			<ul class="inlineLinks">
				<li><a id="newModuleButton" class="btn"><span class="iconx add">New module</span></a></li>
			</ul>
		</div>
	</div>
	<c:choose>
		<c:when test="${empty moduleList}">
			<p>No modules.</p>
		</c:when>
		<c:otherwise>
			<table class="table table-bordered table-striped sortable">
				<thead>
					<tr>
						<th>Module</th>
						<th>Group ID</th>
						<th>Module ID</th>
						<th>Description</th>
						<th class="editDeleteColumn"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="module" items="${moduleList}">
						<c:url var="moduleUrl" value="/applications/${application.id}/modules/${module.id}" />
						<tr>
							<td><a href="${moduleUrl}"><c:out value="${module.displayName}" /></a></td>
							<td><c:out value="${module.groupId}" /></td>
							<td><c:out value="${module.moduleId}" /></td>
							<td><c:out value="${module.shortDescription}" /></td>
							<td class="editDeleteColumn">
								<a class="editLink" href="#" title="Edit module"><img src="${editIconUrl}" /></a>
								<a class="deleteLink" href="#" title="Delete module"><img src="${deleteIconUrl}" /></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
</section>

<div id="newModuleDialog" class="modal fade">
	<div class="modal-header">
		<a href="#" class="close" data-dismiss="modal">&times;</a>
		<h3>New module</h3>
	</div>
	<div class="modal-body"></div>
	<div class="modal-footer">
		<a id="createModuleButton" href="#" class="btn btn-primary">Create</a>
		<a href="#" class="btn cancel" data-dismiss="modal">Cancel</a>
	</div>
</div>

<script type="text/javascript">
	$(function() {
		$("#newModuleButton").click(function() {
			$.get("${newModuleUrl}")
				.success(function(data) {
					$("#newModuleDialog .modal-body").html(data);
					$("#newModuleDialog #name").focus();
					$("#newModuleDialog").modal("show");
				})
				.error(function(xhr) {
					alert("Error loading module form");
				});
		});
		
		$("#createModuleButton").click(function() {
			var dialog = $(this).closest(".modal");
			var form = dialog.find("form");
			var url = form.attr("action");
			
			$.post(url, {
					name : form.find("#name").val(),
					shortDescription : form.find("#shortDescription").val(),
					groupId : form.find("#groupId").val(),
					moduleId : form.find("#moduleId").val()
				})
				.success(function() {
					dialog.modal("hide");
					window.location.href = "${modulesUrlCreateOk}";
				})
				.error(function(xhr) {
					dialog.find(".modal-body").html(xhr.responseText);
				});
		});
	});
</script>	
