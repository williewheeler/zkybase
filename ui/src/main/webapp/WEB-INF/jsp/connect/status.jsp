<%-- This page shows the connection status for all providers --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="social" uri="http://www.springframework.org/spring-social/social/tags" %>

<c:url var="githubUrl" value="/connect/github" />

<h1 style="margin:20px 0">Connection statuses</h1>

<table class="table table-bordered table-striped sortable">
	<thead>
		<tr>
			<th>Provider</th>
			<th>Status</th>
			<th>Options</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="providerId" items="${providerIds}">
			<tr>
				<td><c:out value="${providerId}" /></td>
				<social:connected provider="github">
					<td>Connected</td>
					<td>
						<form method="post" action="${githubUrl}" style="margin:0">
							<input type="hidden" name="_method" value="delete" />
							<input class="btn btn-danger" type="submit" value="Disconnect" />
						</form>
					</td>
				</social:connected>
				<social:notConnected provider="github">
					<td>Not connected</td>
					<td>
						<form method="post" action="${githubUrl}" style="margin:0">
							<input type="hidden" name="scope" value="user, repo, gist" />
							<input class="btn btn-primary" type="submit" value="Connect" />
						</form>
					</td>
				</social:notConnected>
			</tr>
		</c:forEach>
	</tbody>
</table>
