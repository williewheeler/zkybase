<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
	<div class="span10">
		<c:if test="${not empty project.shortDescription}">
			<div style="margin:20px 0">
				<p><c:out value="${project.shortDescription}" /></p>
			</div>
		</c:if>
		<jsp:include page="farms.jsp" />
	</div>
	<div class="span6"><jsp:include page="updates.jsp" /></div>
</div>
