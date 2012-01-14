<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="applicationPath" value="/applications/${application.id}" />

<c:url var="overviewUrl" value="${applicationPath}" />
<c:url var="scmUrl" value="${applicationPath}/scm" />

<ul>
	<li><a href="${overviewUrl}">Overview</a></li>
	<li><a href="${scmUrl}">SCM</a></li>
</ul>
