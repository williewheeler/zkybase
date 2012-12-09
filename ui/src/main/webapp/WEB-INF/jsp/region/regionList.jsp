<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/jsp/pageTemplate/urls.jsp" %>

<div>
	<c:choose>
		<c:when test="${empty regionList}">
			<p>No regions.</p>
		</c:when>
		<c:otherwise>
			<table class="table table-bordered table-striped sortable">
				<thead>
					<tr>
						<th>Name</th>
						<th class="editDeleteColumn"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="region" items="${regionList}">
						<c:url var="regionUrl" value="/regions/${region.id}" />
						<c:url var="editUrl" value="/regions/${region.id}/edit" />
						<tr>
							<td><a href="${regionUrl}"><c:out value="${region.name}" /></a></td>
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
