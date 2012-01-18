<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="row">
	<div class="span12">
		<section class="first">
			<h2>Overview</h2>
			<div class="grid">
				<div class="gridBody">
					<div class="row">
						<div class="span3 gridLabel">TODO:</div>
						<div class="span8">
							TODO
						</div>
					</div>
				</div>
			</div>
		</section>
		<section>
			<h2>Farms (<c:out value="${fn:length(farmList)}" />)</h2>
			<c:choose>
				<c:when test="${empty farmList}">
					<p>No farms.</p>
				</c:when>
				<c:otherwise>
					<table class="bordered-table zebra-striped">
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
	</div>
	<div class="span4">
	</div>
</div>
