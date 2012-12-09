<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<h2>Downloads (${fn:length(downloadList)})</h2>

<table class="table">
	<thead>
		<tr>
			<th>Name</th>
			<th>Description</th>
			<th style="text-align:right">Size</th>
			<th style="text-align:right">Download count</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="download" items="${downloadList}">
			<tr>
				<td><a href="${download.htmlUrl}"><c:out value="${download.name}" /></a></td>
				<td><c:out value="${download.description}" /></td>
				<td style="text-align:right"><c:out value="${download.size}" /></td>
				<td style="text-align:right"><c:out value="${download.downloadCount}" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
