<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<section class="first">
	<div class="grid">
		<div class="gridBody">
			<div class="row">
				<div class="span3 gridLabel">Title:</div>
				<div class="span8">
					<c:out value="${person.title}" default="None" />
				</div>
			</div>
			<div class="row">
				<div class="span3 gridLabel">Work phone:</div>
				<div class="span8">
					<c:choose>
						<c:when test="${not empty person.workPhone}">
							<span class="telephone icon"><c:out value="${person.workPhone}" /></span>
						</c:when>
						<c:otherwise>None</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="row">
				<div class="span3 gridLabel">Mobile phone:</div>
				<div class="span8">
					<c:choose>
						<c:when test="${not empty person.mobilePhone}">
							<span class="mobilePhone icon"><c:out value="${person.mobilePhone}" /></span>
						</c:when>
						<c:otherwise>None</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="row">
				<div class="span3 gridLabel">E-mail:</div>
				<div class="span8">
					<c:choose>
						<c:when test="${not empty person.email}">
							<span class="email icon"><a href="mailto:<c:out value="${person.email}" />"><c:out value="${person.email}" /></a></span>
						</c:when>
						<c:otherwise>None</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="row">
				<div class="span3 gridLabel">GitHub user:</div>
				<div class="span8"><c:out value="${person.gitHubUser}" default="None" /></div>
			</div>
		</div>
	</div>
</section>
<section>
	<h2>Manager</h2>
	<c:set var="manager" value="${person.manager}" />
	<c:choose>
		<c:when test="${not empty manager}">
			<c:url var="managerUrl" value="/people/${manager.id}" />
			<a href="${managerUrl}"><c:out value="${manager.firstNameLastName}" /></a>
		</c:when>
		<c:otherwise>None</c:otherwise>
	</c:choose>
</section>
<c:if test="${not empty directReports}">
	<section>
		<h2>Direct reports</h2>
		<ul style="margin:0;padding:0;list-style-type:none">
			<c:forEach var="report" items="${directReports}">
				<c:url var="reportUrl" value="/people/${report.id}" />
				<li><a href="${reportUrl}"><c:out value="${report.firstNameLastName}" /></a></li>
			</c:forEach>
		</ul>
	</section>
</c:if>
