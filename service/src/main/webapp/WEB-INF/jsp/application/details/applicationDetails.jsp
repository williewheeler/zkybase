<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="/WEB-INF/jsp/pageTemplate/urls.jsp" %>

<div>
	<section class="first">
		<div class="grid">
			<div class="row">
				<div class="span2 gridLabel">Short description:</div>
				<div class="span9 gridValue">
					<c:out value="${application.shortDescription}" default="None" />
				</div>
			</div>
			<div class="row">
				<div class="span2 gridLabel">Detailed description:</div>
				<div class="span9 gridValue">
Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam et est a lorem elementum dignissim ut in urna. Proin volutpat mollis odio vel posuere. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. In hac habitasse platea dictumst. Integer sit amet ligula lacus, non pharetra leo. Fusce sollicitudin, velit sed dictum vulputate, metus felis dignissim lectus, ullamcorper condimentum erat lorem id risus. Maecenas in urna velit. In ullamcorper ultricies nisl, vel pharetra lorem elementum non. Nam ac blandit nisi. Ut ut arcu tortor, et auctor lorem. Aliquam elementum nunc nec erat placerat interdum. Nullam id odio mi. Suspendisse interdum euismod quam, eget ultrices tellus feugiat tempor.
				</div>
			</div>
		</div>
	</section>
	<section>
		<div>
			<h2 style="display:inline">Farms (<c:out value="${fn:length(farmList)}" />)</h2>
			<ul class="inlineLinks">
				<li><a href="#"><span class="relate icon">Add farm</span></a></li>
			</ul>
		</div>
		<c:choose>
			<c:when test="${empty farmList}">
				<p>No farms.</p>
			</c:when>
			<c:otherwise>
				<table class="bordered-table zebra-striped sortable">
					<thead>
						<tr>
							<th>Farm</th>
							<th>Environment</th>
							<th>Data center</th>
							<th>Instances</th>
							<th style="width:0"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="farm" items="${farmList}">
							<c:url var="farmUrl" value="/farms/${farm.id}" />
							<c:url var="environmentUrl" value="/environments/${farm.environment.id}" />
							<c:url var="dataCenterUrl" value="/datacenters/${farm.dataCenter.id}" />
							<tr>
								<td><a href="${farmUrl}"><c:out value="${farm.displayName}" /></a></td>
								<td><a href="${environmentUrl}"><c:out value="${farm.environment.displayName}" /></a></td>
								<td><a href="${dataCenterUrl}"><c:out value="${farm.dataCenter.displayName}" /></a></td>
								<td>TODO</td>
								<td><a href="#" title="Remove farm"><img src="${unrelateIconUrl}" /></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</section>
</div>
