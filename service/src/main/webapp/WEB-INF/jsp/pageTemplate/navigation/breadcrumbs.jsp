<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="breadcrumbs" value="${navigation.breadcrumbs}" />

<c:if test="${fn:length(breadcrumbs) > 1}">
	<ul class="breadcrumb">
		<c:forEach var="breadcrumb" items="${breadcrumbs}" varStatus="status">
			<c:url var="breadcrumbUrl" value="${breadcrumb.path}" />
			<c:choose>
				<c:when test="${not status.last}">
					<li><a href="${breadcrumbUrl}"><c:out value="${breadcrumb.name}" /></a> <span class="divider">/</span></li>
				</c:when>
				<c:otherwise>
					<li class="active"><c:out value="${breadcrumb.name}" /></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</ul>
</c:if>
