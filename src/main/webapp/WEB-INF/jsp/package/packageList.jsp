<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="createPackageUrl" value="/packages/new" />

<div>
	<c:choose>
		<c:when test="${empty packageList}">
			<p>No packages.</p>
		</c:when>
		<c:otherwise>
			<table class="bordered-table zebra-striped sortable">
				<thead>
					<tr>
						<th>Group</th>
						<th>Package</th>
						<th>Version</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="package" items="${packageList}">
						<tr>
							<td><c:out value="${package.groupId}" /></td>
							<td><c:out value="${package.packageId}" /></td>
							<td><c:out value="${package.version}" /></td>
							<td></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
</div>
