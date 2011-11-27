<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="editUrl" value="/projects/${project.id}/edit" />

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><c:out value="${project.name}" /></title>
</head>
<body>

<div id="pageHead">
	<div class="yui-gc">
		<div class="yui-u first">
			<h1 style="display:inline"><c:out value="${project.name}" /></h1>
			<span style="color:#666;">(<c:out value="${project.id}" />)</span>
			<ul class="menu" style="display:inline;margin-left:10px">
				<li><span class="editProject icon"><a href="${editUrl}">Edit</a></span></li>
				<li><span class="deleteProject icon"><a href="#">Delete</a></span></li>
			</ul>
		</div>
		<div class="yui-u" style="margin-top:12px;text-align:right">
			<ul class="menu">
				<li><span class="json icon"><a href="#">JSON</a></span></li>
				<li><span class="xml icon"><a href="#">XML</a></span></li>
			</ul>
		</div>
	</div>
</div>
<div id="pageBody">
	<c:if test="${not empty project.shortDescription}">
		<p><c:out value="${project.shortDescription}" /></p>
	</c:if>
	
	<div class="row">
		<div class="span8">
			<div class="table">
				<div class="tr">
					<div class="td"><h2><span class="servers icon">Farms</span></h2></div>
					<div class="td" style="text-align:right">
						<span class="add icon"><a href="#">Add farm</a></span>
					</div>
				</div>
			</div>
			<table class="bordered-table zebra-striped">
				<thead>
					<tr>
						<th>Farm</th>
						<th>Package</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><a href="#">Skybase Development</a></td>
						<td><a href="#">1.1.0-SNAPSHOT</a></td>
					</tr>
					<tr>
						<td><a href="#">Skybase QA</a></td>
						<td><a href="#">1.1.0.132</a></td>
					</tr>
					<tr>
						<td><a href="#">Skybase System Test</a></td>
						<td><a href="#">1.0.1.129</a></td>
					</tr>
					<tr>
						<td><a href="#">Skybase Production</a></td>
						<td><a href="#">1.0.1.129</a></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="span8">
			<div class="table">
				<div class="tr">
					<div class="td"><h2><span class="package icon">Packages</span></h2></div>
					<div class="td" style="text-align:right">
						<span class="add icon"><a href="#">Add package</a></span>
					</div>
				</div>
			</div>
			<table class="bordered-table zebra-striped">
				<thead>
					<tr>
						<th>Package</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><a href="#">1.1.0-SNAPSHOT</a></td>
						<td>In Skybase Development</td>
					</tr>
					<tr>
						<td><a href="#">1.0.1.129</a></td>
						<td>In Skybase Production</td>
					</tr>
					<tr>
						<td><a href="#">1.0.0.125</a></td>
						<td>Decommissioned</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="row">
		<div class="span16">
			<div class="table">
				<div class="tr">
					<div class="td"><h2><span class="users icon">Team</span></h2></div>
					<div class="td" style="text-align:right">
						<span class="add icon"><a href="#">Add member</a></span>
					</div>
				</div>
			</div>
			<table class="bordered-table zebra-striped">
				<thead>
					<tr>
						<th>Member</th>
						<th>Role</th>
						<th>Work</th>
						<th>Mobile</th>
						<th>E-mail</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><a href="#">Willie Wheeler</a></td>
						<td>Lead Developer</td>
						<td>602-555-1212</td>
						<td>602-555-3434</td>
						<td><a href="#">willie.wheeler@example.com</a></td>
					</tr>
					<tr>
						<td><a href="#">Thug Killa</a></td>
						<td>Developer</td>
						<td>602-555-5656</td>
						<td>602-555-7878</td>
						<td><a href="#">thug.killa@example.com</a></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
	
</div>

</body>
</html>
