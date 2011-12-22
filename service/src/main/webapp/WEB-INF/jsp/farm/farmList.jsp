<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="createFarmUrl" value="/farms/new" />

<div>
	<c:choose>
		<c:when test="${empty farmList}">
			<p>No farms.</p>
		</c:when>
		<c:otherwise>
			<table class="bordered-table zebra-striped sortable">
				<thead>
					<tr>
						<th>Name</th>
						<th>Instances</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="farm" items="${farmList}">
						<c:url var="farmUrl" value="/farms/${farm.id}" />
						<tr>
							<td><a href="${farmUrl}"><c:out value="${farm.name}" /></a></td>
							<td>TODO</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
</div>
