<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Menu items -->
<%-- FIXME Source from Paths --%>
<c:url var="applicationsUrl" value="/applications" />
<c:url var="dashboardUrl" value="/" />
<c:url var="dataCentersUrl" value="/datacenters" />
<c:url var="environmentsUrl" value="/environments" />
<c:url var="farmsUrl" value="/farms" />
<c:url var="logoutUrl" value="/logout" />
<c:url var="packagesUrl" value="/packages" />
<c:url var="peopleUrl" value="/people" />
<c:url var="regionsUrl" value="/regions" />
<c:url var="teamsUrl" value="/teams" />
<c:url var="currentUserUrl" value="/useraccounts/current" />

<script type="text/javascript">
	$(function() {
		$("#<c:out value="${navigation.currentArea}" />Area").addClass("active");
	});
</script>

<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="brand" href="${dashboardUrl}">Zkybase</a>
			<ul class="nav">
				<li id="developmentArea" class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						Development <b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li><a href="${applicationsUrl}">Applications</a></li>
<!-- 						<li class="divider"></li> -->
					</ul>
				</li>
				<li id="opsArea" class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						Operations <b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li><a href="${regionsUrl}">Regions</a></li>
						<li><a href="${dataCentersUrl}">Data centers</a></li>
						<li><a href="${environmentsUrl}">Environments</a></li>
						<li><a href="${farmsUrl}">Farms</a></li>
					</ul>
				</li>
				<li id="peopleArea" class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						People <b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li><a href="${peopleUrl}">People</a></li>
						<li><a href="${teamsUrl}">Teams</a></li>
					</ul>
				</li>
				<li id="adminArea" class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						Admin <b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li><a href="#">Team types</a></li>
					</ul>
				</li>
			</ul>
			<form class="navbar-search" action="">
				<input type="text" class="search-query pull-left" placeholder="Search" />
			</form>
			<ul class="nav pull-right">
				<li id="accountArea"><a href="${currentUserUrl}">Account</a>
				<li><a href="${logoutUrl}">Logout</a>
			</ul>
		</div>
	</div>
</div>
