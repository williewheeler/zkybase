<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="scm" value="${application.scm}" />

<h2>SCM</h2>

<div class="well">
	<table class="grid">
		<tbody>
			<tr>
				<td>GitHub SCM:</td>
				<td><c:out value="${scm.displayName}" default="None" /></td>
			</tr>
			<tr>
				<td>Description:</td>
				<td><c:out value="${scm.description}" default="None" /></td>
			</tr>
		</tbody>
	</table>
</div>
<div class="well">
	<table class="grid">
		<tbody>
			<tr>
				<td>HTML URL:</td>
				<td>
					<c:choose>
						<c:when test="${empty scm.htmlUrl}">
							None
						</c:when>
						<c:otherwise>
							<a href="<c:out value="${scm.htmlUrl}" />"><c:out value="${scm.htmlUrl}" /></a>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>Clone URL:</td>
				<td><c:out value="${scm.cloneUrl}" default="None" /></td>
			</tr>
			<tr>
				<td>Git URL:</td>
				<td><c:out value="${scm.gitUrl}" default="None" /></td>
			</tr>
			<tr>
				<td>SSH URL:</td>
				<td><c:out value="${scm.sshUrl}" default="None" /></td>
			</tr>
			<tr>
				<td>SVN URL:</td>
				<td><c:out value="${scm.svnUrl}" default="None" /></td>
			</tr>
			<tr>
				<td>API URL:</td>
				<td>
					<c:choose>
						<c:when test="${empty scm.url}">
							None
						</c:when>
						<c:otherwise>
							<a href="<c:out value="${scm.url}" />"><c:out value="${scm.url}" /></a>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</tbody>
	</table>
</div>
