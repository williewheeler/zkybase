<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/jsp/pageTemplate/urls.jsp" %>

<c:choose>
	<c:when test="${empty personList}">
		<p>No people.</p>
	</c:when>
	<c:otherwise>
		<table class="table table-bordered table-striped sortable">
			<thead>
				<tr>
					<th>Person</th>
					<th>Username</th>
					<th>Title</th>
					<th>Work phone</th>
					<th>Mobile phone</th>
					<th>E-mail</th>
					<th class="editDeleteColumn"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="person" items="${personList}">
					<c:set var="personPath" value="/people/${person.id}" />
					<c:url var="personUrl" value="${personPath}" />
					<c:url var="editUrl" value="${personPath}/edit" />
					<tr>
						<td>
							<a href="${personUrl}"><c:out value="${person.firstNameLastName}" /></a>
						</td>
						<td><c:out value="${person.username}" /></td>
						<td><c:out value="${person.title}" /></td>
						<td>
							<c:if test="${not empty person.workPhone}">
								<span class="telephone iconx"><c:out value="${person.workPhone}" /></span>
							</c:if>
						</td>
						<td>
							<c:if test="${not empty person.mobilePhone}">
								<span class="mobilePhone iconx"><c:out value="${person.mobilePhone}" /></span>
							</c:if>
						</td>
						<td>
							<c:if test="${not empty person.email}">
								<span class="email iconx"><a href="mailto:<c:out value="${person.email}" />"><c:out value="${person.email}" /></a></span>
							</c:if>
						</td>
						<td class="editDeleteColumn">
							<a class="editLink" href="${editUrl}" title="Edit"><img src="${editIconUrl}" /></a>
							<a class="deleteLink" href="${personUrl}" title="Delete"><img src="${deleteIconUrl}" /></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:otherwise>
</c:choose>
