<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="titleDecorator">
	<div class="title">
		<div class="table">
			<div class="tr">
				<div class="td"><h2><span class="servers icon">Farm templates</span></h2></div>
				<div class="td" style="text-align:right">
					<a href="#section2" title="Creates a farm template for this project" class="btn"><span class="add icon">Create farm template</span></a>
				</div>
			</div>
		</div>
	</div>
	<div class="target">
		<table class="bordered-table zebra-striped">
			<thead>
				<tr>
					<th>Farm</th>
					<th>Instances</th>
					<th>Instance Type</th>
					<th>Image ID</th>
					<th>Security Group</th>
					<th>Key Pair</th>
					<th>IP Address</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="farmTemplate" items="${farmTemplateList}">
					<tr>
						<td><a href="#"><c:out value="${farmTemplate.name}" /></a></td>
						<td></td>
						<td><c:out value="${farmTemplate.instanceType}" /></td>
						<td><c:out value="${farmTemplate.imageId}" /></td>
						<td><c:out value="${farmTemplate.securityGroup}" /></td>
						<td><c:out value="${farmTemplate.keyPair}" /></td>
						<td><c:out value="${farmTemplate.ipAddress}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
