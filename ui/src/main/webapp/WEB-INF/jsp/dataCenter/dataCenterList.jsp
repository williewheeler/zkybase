<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/jsp/pageTemplate/urls.jsp" %>

<div>
	<c:choose>
		<c:when test="${empty dataCenterList}">
			<p>No data centers.</p>
		</c:when>
		<c:otherwise>
			<table class="table table-bordered table-striped sortable">
				<thead>
					<tr>
						<th>Name</th>
						<th>Region</th>
						<th class="editDeleteColumn"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dataCenter" items="${dataCenterList}">
						<c:url var="dataCenterUrl" value="/datacenters/${dataCenter.id}" />
						<c:url var="editUrl" value="/datacenters/${dataCenter.id}/edit" />
						<tr>
							<td><a href="${dataCenterUrl}"><c:out value="${dataCenter.name}" /></a></td>
							<td><c:out value="${dataCenter.region.displayName}" /></td>
							<td class="editDeleteColumn">
								<a class="editLink" href="${editUrl}" title="Edit"><img src="${editIconUrl}" /></a>
								<a class="deleteLink" href="${dataCenterUrl}" title="Delete"><img src="${deleteIconUrl}" /></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
</div>
