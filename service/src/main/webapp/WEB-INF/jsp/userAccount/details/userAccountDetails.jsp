<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:url var="loginUrl" value="/useraccounts/${userAccount.id}/access/github" />

<div>
	<a href="${loginUrl}" class="btn" title="Gives you access to your private GitHub data">Connect to GitHub</a>
</div>
