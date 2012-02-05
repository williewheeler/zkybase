<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/jsp/pageTemplate/urls.jsp" %>

<div>
	<c:choose>
		<c:when test="${empty farmList}">
			<p>No farms.</p>
		</c:when>
		<c:otherwise>
			<table class="table table-bordered table-striped sortable">
				<thead>
					<tr>
						<th>Name</th>
						<th>Environment</th>
						<th>Data center</th>
						<th>Instances</th>
						<th class="editDeleteColumn"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="farm" items="${farmList}">
						<c:url var="farmUrl" value="/farms/${farm.id}" />
						<c:url var="editUrl" value="/farms/${farm.id}/edit" />
						<tr>
							<td><a href="${farmUrl}"><c:out value="${farm.name}" /></a></td>
							
							<%-- FIXME Make these links --%>
							<td><c:out value="${farm.environment.displayName}" /></td>
							<td><c:out value="${farm.dataCenter.displayName}" /></td>
							
							<td>TODO</td>
							<td class="editDeleteColumn">
								<a class="editLink" href="${editUrl}" title="Edit"><img src="${editIconUrl}" /></a>
								<a class="deleteLink" href="${farmUrl}" title="Delete"><img src="${deleteIconUrl}" /></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
</div>
