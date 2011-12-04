<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<c:set var="projectPath" value="/projects/${project.key}" />

<tiles:useAttribute name="activeTab" />

<c:choose>
	<c:when test="${activeTab == 'current'}">
		<c:set var="currentClass">active</c:set>
	</c:when>
	<c:when test="${activeTab == 'deployments'}">
		<c:set var="deploymentsClass">active</c:set>
	</c:when>
	<c:when test="${activeTab == 'packages'}">
		<c:set var="packagesClass">active</c:set>
	</c:when>
	<c:when test="${activeTab == 'team'}">
		<c:set var="teamClass">active</c:set>
	</c:when>
</c:choose>

<c:url var="currentUrl" value="${projectPath}" />
<c:url var="deploymentUrl" value="${projectPath}/deployment" />
<c:url var="packagesUrl" value="${projectPath}/packages" />
<c:url var="teamUrl" value="${projectPath}/team" />

<ul class="tabs">
	<li class="${currentClass}"><a href="${currentUrl}">Current Configuration</a></li>
	<li class="${deploymentsClass}"><a href="${deploymentUrl}">Deployment Planning</a></li>
	<li class="${packagesClass}"><a href="${packagesUrl}">Packages</a></li>
	<li class="${teamClass}"><a href="${teamUrl}">Team</a></li>
</ul>
<div><tiles:insertAttribute name="content" /></div>
