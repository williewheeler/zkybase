<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h2>Commits (${fn:length(commitList)})</h2>

<table class="table">
	<tbody>
		<c:forEach var="commit" items="${commitList}">
			<c:set var="author" value="${commit.author}" />
			<c:set var="authorUrl" value="https://github.com/${author.login}" />
			<c:set var="committer" value="${commit.committer}" />
			<c:set var="committerUrl" value="https://github.com/${committer.login}" />
			
			<tr>
				<td><img src="${author.avatarUrl}" style="width:64px;height:64px" /></td>
				<td>
					<div>[Message goes here]</div>
					<div>Authored by <a href="${authorUrl}"><c:out value="${author.login}" /></a></div>
					<div>Committed by <a href="${committerUrl}"><c:out value="${committer.login}" /></a></div>
				</td>
				<td style="text-align:right">
					<div><a href="#" class="btn" style="padding:2px 5px"><c:out value="${fn:substring(commit.sha, 0, 10)}" /></a></div>
					<div><a href="#">Browse code &raquo;</a></div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
