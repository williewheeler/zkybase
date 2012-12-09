<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="createApplicationUrl" value="/applications/new.do" />
<c:url var="starIconUrl" value="/images/icons/star.png" />
<c:url var="emptyStarIconUrl" value="/images/icons/star-empty.png" />

<section class="first">
	<h2>Favorite applications</h2>
	<c:choose>
		<c:when test="${empty applicationList}">
			<p>None.</p>
		</c:when>
		<c:otherwise>
			<table class="table table-bordered table-striped">
				<tbody>
					<c:forEach var="application" items="${applicationList}">
						<c:url var="applicationUrl" value="/applications/${application.id}" />
						<tr>
							<td style="width:100%"><a href="${applicationUrl}"><c:out value="${application.name}" /></a></td>
							<td><img src="${starIconUrl}" alt="Favorite" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
</section>
<section>
	<h2>All applications</h2>
	<c:choose>
		<c:when test="${empty applicationList}">
			<p>None.</p>
		</c:when>
		<c:otherwise>
			<table class="table table-bordered table-striped">
				<tbody>
					<c:forEach var="application" items="${applicationList}">
						<c:url var="applicationUrl" value="/applications/${application.id}" />
						<tr>
							<td style="width:100%"><a href="${applicationUrl}"><c:out value="${application.name}" /></a></td>
							<td><img src="${starIconUrl}" alt="Favorite" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
</section>
