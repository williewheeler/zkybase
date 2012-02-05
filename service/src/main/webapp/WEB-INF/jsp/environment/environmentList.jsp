<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/jsp/pageTemplate/urls.jsp" %>

<script type="text/javascript">
	$(function() {
		$(".deleteLink").click(function() {
			var entityName = $(this).closest("tr").find(".displayName").html();
			var entityUrl = $(this).attr("href");
			$("#deleteDialog .entityName").html(entityName);
			$("#deleteDialog form").attr("action", entityUrl);
			$("#deleteDialog").modal("show");
			return false;
		});
	});
</script>

<div>
	<c:choose>
		<c:when test="${empty environmentList}">
			<p>No environments.</p>
		</c:when>
		<c:otherwise>
			<table class="table table-bordered table-striped sortable">
				<thead>
					<tr>
						<th>Name</th>
						<th class="editDeleteColumn"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="environment" items="${environmentList}">
						<c:url var="environmentUrl" value="/environments/${environment.id}" />
						<c:url var="editUrl" value="/environments/${environment.id}/edit" />
						<tr>
							<td><a href="${environmentUrl}"><span class="displayName"><c:out value="${environment.name}" /></span></a></td>
							<td class="editDeleteColumn">
								<a class="editLink" href="${editUrl}" title="Edit"><img src="${editIconUrl}" /></a>
								<a class="deleteLink" href="${environmentUrl}" title="Delete"><img src="${deleteIconUrl}" /></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
</div>

<jsp:include page="/WEB-INF/jsp/pageTemplate/dialogs/deleteDialog.jsp" />
