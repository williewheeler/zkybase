<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="createPackageUrl" value="/projects/${project.key}/packages/new" />

<div class="titleDecorator">
	<div class="title">
		<div class="table">
			<div class="tr">
				<div class="td"><h2><span class="package icon">Packages</span></h2></div>
				<div class="td" style="text-align:right">
					<a href="${createPackageUrl}" title="Add an existing package to this project" class="btn"><span class="add icon">Create package</span></a>
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
				<table class="bordered-table zebra-striped">
					<thead>
						<tr>
							<th>Group</th>
							<th>Package</th>
							<th>Version</th>
							<th>Status</th>
						</tr>
					</thead>
					<tbody>
						<%-- TODO --%>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
</div>
