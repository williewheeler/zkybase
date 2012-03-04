<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h2>GitHub user profile</h2>

Blog: <c:out value="${gitHubUserProfile.blog}" /><br />
Location: <c:out value="${gitHubUserProfile.location}" /><br />
