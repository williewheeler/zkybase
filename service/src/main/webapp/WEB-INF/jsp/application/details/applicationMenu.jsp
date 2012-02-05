<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="applicationPath" value="/applications/${application.id}" />
<c:set var="modulesPath" value="${applicationPath}/modules" />
<c:set var="scmPath" value="${applicationPath}/scm" />

<c:url var="overviewUrl" value="${applicationPath}" />
<c:url var="modulesUrl" value="${modulesPath}" />
<c:url var="scmUrl" value="${scmPath}" />
<c:url var="collaboratorsUrl" value="${scmPath}/collaborators" />
<c:url var="commitsUrl" value="${scmPath}/commits" />
<c:url var="hooksUrl" value="${scmPath}/hooks" />
<c:url var="watchersUrl" value="${scmPath}/watchers" />

<div class="well" style="padding: 8px 0">
	<ul class="nav nav-list">
		<li class="active"><a href="${overviewUrl}">Overview</a></li>
		<li><a href="${modulesUrl}">Modules</a></li>
		<li class="nav-header"><a href="${scmUrl}">SCM</a></li>
		<li><a href="${collaboratorsUrl}">Collaborators</a></li>
		<li><a href="${commitsUrl}">Commits</a></li>
		<li><a href="${watchersUrl}">Watchers</a></li>
		<li><a href="${hooksUrl}">Hooks</a></li>
	</ul>
</div>
