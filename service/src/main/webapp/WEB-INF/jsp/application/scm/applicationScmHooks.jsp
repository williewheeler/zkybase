<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div>
	<h2>Hooks (${fn:length(hookList)})</h2>
	
	<c:choose>
		<c:when test="${empty hookList}">
			<p>No hooks.</p>
		</c:when>
		<c:otherwise>
			<table class="table table-bordered table-striped sortable">
				<thead>
					<tr>
						<th>Name</th>
						<th>Date created</th>
						<th>Date updated</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="hook" items="${hookList}">
						<tr>
							<td><c:out value="${hook.name}" /></td>
							<td><span class="iconx date"><fmt:formatDate value="${hook.createdAt}" /></span></td>
							<td><span class="iconx date"><fmt:formatDate value="${hook.updatedAt}" /></span></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
</div>
