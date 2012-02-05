<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<section class="first">
	<div class="well">
	</div>
</section>
<section>
	<h2>Farms (<c:out value="${fn:length(farmList)}" />)</h2>
	<c:choose>
		<c:when test="${empty farmList}">
			<p>No farms.</p>
		</c:when>
		<c:otherwise>
			<table class="table table-bordered table-striped sortable">
				<thead>
					<tr>
						<th>Farm</th>
						<th>Data center</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="farm" items="${farmList}">
						<c:url var="farmUrl" value="/farms/${farm.id}" />
						<tr>
							<td><a href="${farmUrl}"><c:out value="${farm.displayName}" /></a></td>
							<td>
								<%-- Data center is a required field --%>
								<c:out value="${farm.dataCenter.displayName}" />
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
</section>
