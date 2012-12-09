<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<c:choose>
		<c:when test="${empty packageList}">
			<p>No packages.</p>
		</c:when>
		<c:otherwise>
			<table class="bordered-table zebra-striped sortable">
				<thead>
					<tr>
						<th>Group ID</th>
						<th>Package ID</th>
						<th>Version</th>
						<th>Status</th>
						<th style="width:0"></th>
<!-- 						<th style="width:0"></th> -->
<!-- 						<th style="width:0"></th> -->
					</tr>
				</thead>
				<tbody>
					<c:forEach var="package" items="${packageList}">
						<c:set var="packagePath" value="/packages/${package.id}" />
						<c:url var="packageUrl" value="${packagePath}" />
						<c:url var="editPackageUrl" value="${packagePath}/edit" />
						<c:url var="deletePackageUrl" value="${packagePath}/delete" />
						<tr>
							<td><c:out value="${package.groupId}" /></td>
							<td><c:out value="${package.packageId}" /></td>
							<td><c:out value="${package.version}" /></td>
							<td>TODO</td>
							<td><a href="${packageUrl}">Details</a></td>
<%-- 							<td><a href="${editPackageUrl}">Edit</a></td> --%>
<%-- 							<td><a href="${deletePackageUrl}">Delete</a></td> --%>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
</div>
