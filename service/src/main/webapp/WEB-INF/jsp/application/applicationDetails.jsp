<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="/WEB-INF/jsp/pageTemplate/urls.jsp" %>

<section class="first">
	<div class="well">
		<table class="grid">
			<tbody>
				<tr>
					<td>Short description:</td>
					<td><c:out value="${application.shortDescription}" default="None" /></td>
				</tr>
			</tbody>
		</table>
	</div>
</section>
<section>
	<div class="sectionTitleBar">
		<h2>Farms (<c:out value="${fn:length(farmList)}" />)</h2>
		<div class="pull-right">
			<h2></h2>
			<ul class="inlineLinks">
				<li><a class="btn" href="#"><span class="iconx add">Add farm</span></a></li>
			</ul>
		</div>
	</div>
	<c:choose>
		<c:when test="${empty farmList}">
			<p>No farms.</p>
		</c:when>
		<c:otherwise>
			<table class="table table-bordered table-striped sortable">
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
