<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="/WEB-INF/jsp/pageTemplate/urls.jsp" %>

<section class="first">
	<div class="well">
		<table class="grid">
			<tbody>
				<tr>
					<td>Title:</td>
					<td><c:out value="${person.title}" default="None" /></td>
				</tr>
				<tr>
					<td>Work phone:</td>
					<td>
						<c:choose>
							<c:when test="${not empty person.workPhone}">
								<span class="telephone icon"><c:out value="${person.workPhone}" /></span>
							</c:when>
							<c:otherwise>None</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td>Mobile phone:</td>
					<td>
						<c:choose>
							<c:when test="${not empty person.mobilePhone}">
								<span class="mobilePhone icon"><c:out value="${person.mobilePhone}" /></span>
							</c:when>
							<c:otherwise>None</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td>E-mail:</td>
					<td>
						<c:choose>
							<c:when test="${not empty person.email}">
								<span class="email icon"><a href="mailto:<c:out value="${person.email}" />"><c:out value="${person.email}" /></a></span>
							</c:when>
							<c:otherwise>None</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td>GitHub user:</td>
					<td><c:out value="${person.gitHubUser}" default="None" /></td>
				</tr>
			</tbody>
		</table>
	</div>
</section>
