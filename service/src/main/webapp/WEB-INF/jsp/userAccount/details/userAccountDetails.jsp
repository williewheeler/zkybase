<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="social" uri="http://www.springframework.org/spring-social/social/tags" %>

<c:url var="githubUrl" value="/connect/github" />

<sec:authentication var="user" property="principal" />

<h1>Account details</h1>

<section class="first">
	<div class="well">
		<table class="grid">
			<tr>
				<td>Username:</td>
				<td><c:out value="${user.username}" /></td>
			</tr>
		</table>
	</div>
</section>

<section>
	<h2>GitHub</h2>
	
	<social:connected provider="github">
		<p>Your Skybase and GitHub accounts are connected.</p>
		<div class="well">
			<table class="grid">
				<tr>
					<td>Blog:</td>
					<td><c:out value="${gitHubUserProfile.blog}" default="None" /></td>
				</tr>
				<tr>
					<td>Location:</td>
					<td><c:out value="${gitHubUserProfile.location}" default="None" /></td>
				</tr>
			</table>
		</div>
		<form method="post" action="${githubUrl}">
			<input type="hidden" name="_method" value="delete" />
			<input class="btn btn-danger" type="submit" value="Disconnect from GitHub" />
		</form>
	</social:connected>
	
	<social:notConnected provider="github">
		<p>Your Skybase and GitHub accounts are not yet connected. Connect them for additional Skybase features.</p>
		<form method="post" action="${githubUrl}">
			<input type="hidden" name="scope" value="user, repo, gist" />
			<input class="btn btn-primary" type="submit" value="Connect to GitHub" />
		</form>
	</social:notConnected>
</section>
