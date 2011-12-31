<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
						<th>Environment</th>
						<th>Data center</th>
						<th>Instances</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="farm" items="${farmList}">
						<c:url var="farmUrl" value="/farms/${farm.id}" />
						<tr>
							<td><a href="${farmUrl}"><c:out value="${farm.name}" /></a></td>
							<td><c:out value="${farm.environment.displayName}" /></td>
							<td><c:out value="${farm.dataCenter.displayName}" /></td>
							<td>TODO</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
</div>
