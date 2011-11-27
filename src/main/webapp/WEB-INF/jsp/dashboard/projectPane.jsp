<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="createProjectUrl" value="/projects/new.do" />
<c:url var="starIconUrl" value="/images/icons/star.png" />
<c:url var="emptyStarIconUrl" value="/images/icons/star-empty.png" />

<div id="favoriteProjectsPane">
	<div class="paneHead">
		<h2 style="display:inline">Favorite projects</h2>
		<span class="label success">New!</span>
	</div>
	<div class="paneBody">
		<c:choose>
			<c:when test="${empty projectList}">
				<p>None.</p>
			</c:when>
			<c:otherwise>
				<table class="zebra-striped">
					<tbody>
						<c:forEach var="project" items="${projectList}">
							<c:url var="projectUrl" value="/projects/${project.id}" />
							<tr>
								<td style="width:100%"><a href="${projectUrl}"><c:out value="${project.name}" /></a></td>
								<td><img src="${starIconUrl}" alt="Favorite" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
</div>

<div id="allProjectsPane" style="margin-top:20px">
	<div class="paneHead">
		<div class="table">
			<div class="tr">
				<div class="td">
					<h2>All projects</h2>
				</div>
				<div class="td" style="text-align:right">
					<span id="addProjectLink"><a href="${createProjectUrl}">Create project</a></span>
				</div>
			</div>
		</div>
	</div>
	<div class="paneBody">
		<c:choose>
			<c:when test="${empty projectList}">
				<p>None.</p>
			</c:when>
			<c:otherwise>
				<table class="zebra-striped">
					<tbody>
						<c:forEach var="project" items="${projectList}">
							<c:url var="projectUrl" value="/projects/${project.id}" />
							<tr>
								<td style="width:100%"><a href="${projectUrl}"><c:out value="${project.name}" /></a></td>
								<td><img src="${starIconUrl}" alt="Favorite" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
</div>
