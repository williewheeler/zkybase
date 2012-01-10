<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<section class="first">
	<h2>Data centers</h2>
	<c:choose>
		<c:when test="${empty region.dataCenters}">
			<p>None.</p>
		</c:when>
		<c:otherwise>
			<%-- TODO Would be cool to show this on a Google map or something --%>
			<ul>
				<c:forEach var="dataCenter" items="${region.dataCenters}">
					<c:url var="dataCenterUrl" value="/datacenters/${dataCenter.id}" />
					<li><a href="${dataCenterUrl}"><c:out value="${dataCenter.displayName}" /></a>
				</c:forEach>
			</ul>
		</c:otherwise>
	</c:choose>
</section>
