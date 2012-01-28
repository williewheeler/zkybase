<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div>
	<h2>Commits (${fn:length(commitList)})</h2>
	<c:forEach var="commit" items="${commitList}">
		<c:set var="author" value="${commit.author}" />
		<c:set var="authorUrl" value="https://github.com/${author.login}" />
		<c:set var="committer" value="${commit.committer}" />
		<c:set var="committerUrl" value="https://github.com/${committer.login}" />

		<div class="row" style="border-bottom:1px solid #EEE;padding:15px 0">
			<div class="span1">
				<img src="${author.avatarUrl}" style="width:48px;height:48px" />
			</div>
			<div class="span7">
				<div>[Message goes here]</div>
				<div>Authored by <a href="${authorUrl}"><c:out value="${author.login}" /></a></div>
				<div>Committed by <a href="${committerUrl}"><c:out value="${committer.login}" /></a></div>
			</div>
			<div class="span4" style="text-align:right">
				<div><a href="#" class="btn" style="padding:2px 5px"><c:out value="${fn:substring(commit.sha, 0, 10)}" /></a></div>
				<div><a href="#">Browse code &raquo;</a></div>
			</div>
		</div>
	</c:forEach>
</div>
