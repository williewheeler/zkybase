<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
	<div class="span12">
		<div class="section first">
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
						<div class="span3 gridLabel">Projects:</div>
						<div class="span8">
							<c:choose>
								<c:when test="${empty memberships}">None</c:when>
								<c:otherwise>
									<c:forEach var="membership" items="${memberships}">
										<c:set var="project" value="${membership.project}" />
										<c:url var="projectUrl" value="/projects/${project.key}" />
										<a href="${projectUrl}"><c:out value="${project.name}" /></a> - <c:out value="${membership.role}" /><br />
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="span4">
		<section class="first">
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
		
		<section>
			<h2>Collaborators</h2>
			<c:choose>
				<c:when test="${not empty collaborators}">
					<ul style="margin:0;padding:0;list-style-type:none">
						<c:forEach var="collaborator" items="${collaborators}">
							<c:url var="collaboratorUrl" value="/people/${collaborator.username}" />
							<li><a href="${collaboratorUrl}"><c:out value="${collaborator.firstNameLastName}" /></a></li>
						</c:forEach>
					</ul>
				</c:when>
				<c:otherwise>None</c:otherwise>
			</c:choose>
		</section>
	</div>
</div>
