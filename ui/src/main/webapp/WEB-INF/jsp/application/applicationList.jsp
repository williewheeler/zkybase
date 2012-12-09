<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/jsp/pageTemplate/urls.jsp" %>

<c:choose>
	<c:when test="${empty applicationList}">
		<p>No applications.</p>
	</c:when>
	<c:otherwise>
		<table class="table table-bordered table-striped sortable">
			<thead>
				<tr>
					<th>Name</th>
					<th>Description</th>
					<th class="editDeleteColumn"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="application" items="${applicationList}">
					<c:url var="applicationUrl" value="/applications/${application.id}" />
					<c:url var="editUrl" value="/applications/${application.id}/edit" />
					<tr>
						<td><a href="${applicationUrl}"><c:out value="${application.name}" /></a></td>
						<td><c:out value="${application.shortDescription}" /></td>
						<td class="editDeleteColumn">
							<a class="editLink" href="${editUrl}" title="Edit"><img src="${editIconUrl}" /></a>
							<a class="deleteLink" href="${applicationUrl}" title="Delete"><img src="${deleteIconUrl}" /></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:otherwise>
</c:choose>
