<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="githubUrl" value="/connect/github" />

<c:forEach var="providerId" items="${providerIds}">
	<h2><c:out value="${providerId}" /></h2>
	<form method="post" action="${githubUrl}">
		<input type="submit" value="Connect" />
	</form>
</c:forEach>
