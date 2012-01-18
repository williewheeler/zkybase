<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="createApplicationUrl" value="/applications/new.do" />
<c:url var="starIconUrl" value="/images/icons/star.png" />
<c:url var="emptyStarIconUrl" value="/images/icons/star-empty.png" />

<div class="titleDecorator">
	<div class="title">
		<h2 style="display:inline">Favorite applications</h2>
		<span class="label success">New!</span>
	</div>
	<div class="target">
		<c:choose>
			<c:when test="${empty applicationList}">
				<p>None.</p>
			</c:when>
			<c:otherwise>
				<table class="bordered-table zebra-striped" style="border-top:0">
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
	</div>
</div>

<div class="titleDecorator" style="margin-top:20px">
	<div class="title">
		<div class="table">
			<div class="tr">
				<div class="td">
					<h2>All applications</h2>
				</div>
				<div class="td" style="text-align:right">
					<a href="${createApplicationUrl}" class="btn"><span class="add icon">New app</span></a>
				</div>
			</div>
		</div>
	</div>
	<div class="target">
		<c:choose>
			<c:when test="${empty applicationList}">
				<p>None.</p>
			</c:when>
			<c:otherwise>
				<table class="bordered-table zebra-striped" style="border-top:0">
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
	</div>
</div>
