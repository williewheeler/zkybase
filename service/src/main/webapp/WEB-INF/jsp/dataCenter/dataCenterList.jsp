<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<c:choose>
		<c:when test="${empty dataCenterList}">
			<p>No data centers.</p>
		</c:when>
		<c:otherwise>
			<table class="bordered-table zebra-striped sortable">
				<thead>
					<tr>
						<th>Name</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dataCenter" items="${dataCenterList}">
						<c:url var="dataCenterUrl" value="/datacenters/${region.id}" />
						<tr>
							<td><a href="${dataCenterUrl}"><c:out value="${dataCenter.name}" /></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
</div>
