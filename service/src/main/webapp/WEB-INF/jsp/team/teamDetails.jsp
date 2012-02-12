<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url var="personSearchUrl" value="/people/search.json" />
<c:url var="membersUrl" value="/projects/${project.id}/members" />

<div class="titleDecorator">
	<div class="title">
		<div class="table">
			<div class="tr">
				<div class="td"><h2><span class="users icon">Team</span></h2></div>
				<div class="td" style="text-align:right">
					<a id="addMemberLauncher" href="#" title="Add a team member to this project" class="btn"><span class="add icon">Add member</span></a>
				</div>
			</div>
		</div>
	</div>
	<div class="target">
		<c:choose>
			<c:when test="${empty team}">
				<p>No team.</p>
			</c:when>
			<c:otherwise>
				<table class="bordered-table zebra-striped sortable">
					<thead>
						<tr>
							<th>Member</th>
							<th>Role</th>
							<th>Work</th>
							<th>Mobile</th>
							<th><spring:message code="label.common.email" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="membership" items="${project.memberships}">
							<c:set var="member" value="${membership.person}" />
							<c:url var="memberUrl" value="/people/${member.username}" />
							<tr>
								<td><a href="${memberUrl}"><c:out value="${member.firstNameLastName}" /></a></td>
								<td><c:out value="${membership.role}" /></td>
								<td>
									<c:if test="${not empty member.workPhone}">
										<span class="telephone icon"><c:out value="${member.workPhone}" /></span>
									</c:if>
								</td>
								<td>
									<c:if test="${not empty member.mobilePhone}">
										<span class="mobilePhone icon"><c:out value="${member.mobilePhone}" /></span>
									</c:if>
								</td>
								<td>
									<c:if test="${not empty member.email}">
										<span class="email icon"><a href="mailto:<c:out value="${member.email}" />"><c:out value="${member.email}" /></a></span>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
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
		<a href="#" class="btn submit primary">Add</a>
		<a href="#" class="btn cancel">Cancel</a>
	</div>
</div>

<script type="text/javascript">
	$(function() {
		$("#addMemberLauncher").click(function() {
			$("#addMemberDialog").modal("show");
			return false;
		});
		
		$("#addMemberDialog")
			.bind("hidden", function() { $("#newMemberInput").val(""); })
			.bind("shown", function() { $("#newMemberInput").focus(); });
		
		$("#newMemberInput").autocomplete({
			source: "${personSearchUrl}?q=dummy",
			minLength: 2
		});
		
		$("#addMemberDialog .btn.submit").click(function() {
			var url = "<c:out value="${membersUrl}" />";
			var data = { member: $("#newMemberInput").val() };
			$.post(url, data)
				.success(function() { alert("success"); $("#addMemberDialog").modal("hide"); })
				.error(function() { alert("error"); });
			return false;
		});
	});
</script>
