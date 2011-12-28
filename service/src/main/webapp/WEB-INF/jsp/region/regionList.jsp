<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="createRegionUrl" value="/regions/new" />

<div>
	<c:choose>
		<c:when test="${empty regionList}">
			<p>No regions.</p>
		</c:when>
		<c:otherwise>
			<table class="bordered-table zebra-striped sortable">
				<thead>
					<tr>
						<th>Name</th>
						<th>Instances</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="region" items="${regionList}">
						<c:url var="regionUrl" value="/regions/${region.id}" />
						<tr>
							<td><a href="${regionUrl}"><c:out value="${region.name}" /></a></td>
							<td>TODO</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
</div>
