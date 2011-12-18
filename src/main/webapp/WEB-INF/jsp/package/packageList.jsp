<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="createPackageUrl" value="/projects/${project.id}/packages/new" />

<div class="titleDecorator">
	<div class="title">
		<div class="table">
			<div class="tr">
				<div class="td"><h2><span class="package icon">Packages</span></h2></div>
				<div class="td" style="text-align:right">
					<a href="${createPackageUrl}" title="Create a package and add it to this project" class="btn"><span class="add icon">Create package</span></a>
				</div>
			</div>
		</div>
	</div>
	<div class="target">
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
</div>
