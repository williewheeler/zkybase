<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ include file="/WEB-INF/jsp/pageTemplate/urls.jsp" %>

<c:url var="newPackageUrl" value="/applications/${application.id}/modules/${module.id}/packages/new" />

<section class="first">
	<div class="well">
		<table class="grid">
			<tbody>
				<tr>
					<td>Name:</td>
					<td><c:out value="${module.name}" default="None" /></td>
				</tr>
				<tr>
					<td>Group ID:</td>
					<td><c:out value="${module.groupId}" default="None" /></td>
				</tr>
				<tr>
					<td>Module ID:</td>
					<td><c:out value="${module.moduleId}" default="None" /></td>
				</tr>
			</tbody>
		</table>
	</div>
</section>
<section>
	<div class="sectionTitleBar">
		<h2>Packages (<c:out value="${fn:length(packageList)}" />)</h2>
		<div class="pull-right">
			<h2></h2>
			<ul class="inlineLinks">
				<li><a class="btn" href="${newPackageUrl}"><span class="iconx add">Add package</span></a></li>
			</ul>
		</div>
	</div>
	<c:choose>
		<c:when test="${empty packageList}">
			<p>No packages.</p>
		</c:when>
		<c:otherwise>
			<table class="table table-bordered table-striped sortable">
				<thead>
					<tr>
						<th>Version</th>
						<th>Created</th>
						<th class="editDeleteColumn"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="package" items="${packageList}">
						<tr>
							<td><span class="iconx package"><c:out value="${package.version}" /></span></td>
							<td>
								<c:if test="${not empty package.dateCreated}">
									<span class="iconx date"><fmt:formatDate value="${package.dateCreated}" type="both" /></span>
								</c:if>
							</td>
							<td class="editDeleteColumn">
								<a class="editLink" href="#" title="Edit package"><img src="${editIconUrl}" /></a>
								<a class="deleteLink" href="#" title="Delete package"><img src="${deleteIconUrl}" /></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
</section>
