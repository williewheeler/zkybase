<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Menu items -->
<%-- FIXME Source from Paths --%>
<c:url var="dashboardUrl" value="/" />
<c:url var="applicationsUrl" value="/applications" />
<c:url var="packagesUrl" value="/packages" />
<c:url var="regionsUrl" value="/regions" />
<c:url var="dataCentersUrl" value="/datacenters" />
<c:url var="environmentsUrl" value="/environments" />
<c:url var="farmsUrl" value="/farms" />
<c:url var="osStackTemplatesUrl" value="/templates/osstacks" />
<c:url var="middlewareStackTemplatesUrl" value="/templates/middlewarestacks" />
<c:url var="appStackTemplatesUrl" value="/templates/appstacks" />
<c:url var="peopleUrl" value="/people" />
<c:url var="logoutUrl" value="/logout" />

<script type="text/javascript">
	$(function() {
		$("#<c:out value="${navigation.currentArea}" />Area").addClass("active");
	});
</script>

<div class="topbar-wrapper" style="z-index: 5;">
	<div class="topbar" data-dropdown="dropdown">
		<div class="topbar-inner">
			<div class="container">
				<h3><a href="${dashboardUrl}">Skybase</a></h3>
				<ul class="nav">
					<li id="currentArea" class="dropdown">
						<a href="#" class="dropdown-toggle">Current</a>
						<ul class="dropdown-menu">
							<li><a href="${applicationsUrl}">Applications</a></li>
							<li class="divider"></li>
							<li><a href="${packagesUrl}">Packages</a></li>
							<li class="divider"></li>
							<li><a href="${regionsUrl}">Regions</a></li>
							<li><a href="${dataCentersUrl}">Data centers</a></li>
							<li><a href="${environmentsUrl}">Environments</a></li>
							<li><a href="${farmsUrl}">Farms</a></li>
							<li><a href="#">Instances</a></li>
						</ul>
					</li>
					<li id="personListArea"><a href="${peopleUrl}">People</a></li>
				</ul>
				<form class="pull-left" action="">
					<input type="text" placeholder="Search" class="span3" />
				</form>
				<ul class="nav secondary-nav">
					<li id="accountArea"><a href="#">Account</a>
					<li><a href="${logoutUrl}">Logout</a>
				</ul>
			</div>
		</div>
	</div>
</div>
