<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<c:choose>
		<c:when test="${empty environmentList}">
			<p>No environments.</p>
		</c:when>
		<c:otherwise>
			<table class="bordered-table zebra-striped sortable">
				<thead>
					<tr>
						<th>Name</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="environment" items="${environmentList}">
						<c:url var="environmentUrl" value="/environments/${environment.id}" />
						<tr>
							<td><a href="${environmentUrl}"><c:out value="${environment.name}" /></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
</div>
