<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="personPath" value="/people/${person.id}" />
<c:set var="scmPath" value="${personPath}/scm" />

<c:url var="overviewUrl" value="${personPath}" />
<c:url var="followersUrl" value="${scmPath}/followers" />
<c:url var="followingUrl" value="${scmPath}/following" />

<div class="sideMenu">
	<ul>
		<li><a href="${overviewUrl}">Overview</a></li>
		<li><span class="group">SCM</span>
			<ul>
				<li><a href="${followersUrl}">Followers</a></li>
				<li><a href="${followingUrl}">Following</a></li>
			</ul>
		</li>
	</ul>
</div>
