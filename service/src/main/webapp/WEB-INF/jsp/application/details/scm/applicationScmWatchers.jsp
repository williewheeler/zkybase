<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div>
	<h2>Watchers (${fn:length(watcherList)})</h2>
	<c:set var="userRows" value="${watcherRows}" scope="request" />
	<jsp:include page="/WEB-INF/jsp/integrations/github/userGrid.jsp" />
</div>
