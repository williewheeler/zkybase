<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="dashboardCssUrl" value="/styles/dashboard.css" />
<c:url var="addProjectUrl" value="/projects/new.do" />
<c:url var="starIconUrl" value="/images/icons/star.png" />

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Skybase Dashboard</title>
<link rel="stylesheet" type="text/css" href="${dashboardCssUrl}" />
</head>
<body>

<h1 id="dashboardTitle">Skybase Dashboard</h1>

<div class="yui-g">
	<div class="yui-u first">
		<div id="projectsPane">
			<div class="paneHead">
				<h2>Projects</h2>
				<span id="addProjectLink"><a href="${addProjectUrl}">Add project</a></span>
			</div>
			<div class="paneBody">
				<c:choose>
					<c:when test="${empty projectList}">
						<p>None.</p>
					</c:when>
					<c:otherwise>
						<table>
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
	</div>
	<div class="yui-u">
		<div id="updatesPane">
			<div class="paneHead">
				<h2>Updates</h2>
				<span id="rssLink"><a href="#">RSS</a></span>
			</div>
			<div class="paneBody">
				<p>None.</p>
			</div>
		</div>
	</div>
</div>

</body>
</html>
