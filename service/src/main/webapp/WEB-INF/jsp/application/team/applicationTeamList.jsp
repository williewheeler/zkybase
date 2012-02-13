<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ include file="/WEB-INF/jsp/pageTemplate/urls.jsp" %>

<c:set var="teamsPath" value="/applications/${application.id}/teams" />

<section>
	<div class="sectionTitleBar">
		<h2>Teams (<c:out value="${fn:length(applicationTeamList)}" />)</h2>
		<div class="pull-right">
			<h2></h2>
			<ul class="inlineLinks">
				<li><a id="addTeamButton" class="btn"><span class="iconx add">Add team</span></a></li>
			</ul>
		</div>
	</div>
	<c:choose>
		<c:when test="${empty applicationTeamList}">
			<p>No teams.</p>
		</c:when>
		<c:otherwise>
			<c:forEach var="appTeam" items="${applicationTeamList}">
				<h3><c:out value="${appTeam.type}" /> - <c:out value="${appTeam.team.displayName}" /></h3>
				<table class="table table-bordered table-striped sortable">
					<thead>
						<tr>
							<th>Member</th>
							<th>Role</th>
							<th>Work phone</th>
							<th>Mobile phone</th>
							<th>E-mail</th>
						</tr>
					</thead>
					<tbody>
						<%-- TODO --%>
					</tbody>
				</table>
			</c:forEach>
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
