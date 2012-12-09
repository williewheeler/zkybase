<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div>
	<h2>Followers (${fn:length(followerList)})</h2>
	<c:set var="userRows" value="${followerRows}" scope="request" />
	<jsp:include page="/WEB-INF/jsp/integrations/github/userGrid.jsp" />
</div>
