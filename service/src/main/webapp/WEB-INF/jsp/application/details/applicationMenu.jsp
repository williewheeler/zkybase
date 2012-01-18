<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="applicationPath" value="/applications/${application.id}" />

<c:url var="overviewUrl" value="${applicationPath}" />
<c:url var="collaboratorsUrl" value="${applicationPath}/collaborators" />
<c:url var="commitsUrl" value="${applicationPath}/commits" />
<c:url var="watchersUrl" value="${applicationPath}/watchers" />

<div class="sideMenu">
	<ul>
		<li><a href="${overviewUrl}">Overview</a></li>
		<li><span class="group">SCM</span>
			<ul>
				<li><a href="${collaboratorsUrl}">Collaborators</a></li>
				<li><a href="${commitsUrl}">Commits</a></li>
				<li><a href="${watchersUrl}">Watchers</a></li>
			</ul>
		</li>
		<li><a href="#">CI builds</a></li>
		<li><a href="#">Deployments</a></li>
	</ul>
</div>
