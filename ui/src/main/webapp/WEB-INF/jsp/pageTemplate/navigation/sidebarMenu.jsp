<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="sidebarMenu" value="${navigation.sidebarMenu}" />

<div class="well" style="padding: 8px 0">
	<ul class="nav nav-list">
		<c:forEach var="item" items="${sidebarMenu}">
			<c:url var="itemUrl" value="${item.path}" />
			<c:choose>
				<c:when test="${item.header and item.active}">
					<li class="nav-header active"><a href="${itemUrl}"><c:out value="${item.name}" /></a></li>
				</c:when>
				<c:when test="${item.header and not item.active}">
					<li class="nav-header"><a href="${itemUrl}"><c:out value="${item.name}" /></a></li>
				</c:when>
				<c:when test="${not item.header and item.active}">
					<li class="active"><a href="${itemUrl}"><c:out value="${item.name}" /></a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${itemUrl}"><c:out value="${item.name}" /></a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</ul>
</div>
