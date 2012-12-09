<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/jsp/pageTemplate/urls.jsp" %>

<div>
	<c:choose>
		<c:when test="${empty teamList}">
			<p>No teams.</p>
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
					<c:forEach var="team" items="${teamList}">
						<c:url var="teamUrl" value="/teams/${team.id}" />
						<c:url var="editUrl" value="/teams/${team.id}/edit" />
						<tr>
							<td><a href="${teamUrl}"><c:out value="${team.name}" /></a></td>
							<td class="editDeleteColumn">
								<a class="editLink" href="${editUrl}" title="Edit"><img src="${editIconUrl}" /></a>
								<a class="deleteLink" href="${teamUrl}" title="Delete"><img src="${deleteIconUrl}" /></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
</div>
