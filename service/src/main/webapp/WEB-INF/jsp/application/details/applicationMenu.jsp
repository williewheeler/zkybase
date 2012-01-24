<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="applicationPath" value="/applications/${application.id}" />
<c:set var="scmPath" value="${applicationPath}/scm" />

<c:url var="overviewUrl" value="${applicationPath}" />
<c:url var="scmUrl" value="${scmPath}" />
<c:url var="collaboratorsUrl" value="${scmPath}/collaborators" />
<c:url var="commitsUrl" value="${scmPath}/commits" />
<c:url var="watchersUrl" value="${scmPath}/watchers" />

<div class="sideMenu">
	<ul>
		<li><a href="${overviewUrl}">Overview</a></li>
		<li><a href="${scmUrl}">SCM</a>
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
