<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="userRows" value="${collaboratorRows}" scope="request" />

<h2>Collaborators (${fn:length(collaboratorList)})</h2>

<jsp:include page="/WEB-INF/jsp/integrations/github/userGrid.jsp" />
