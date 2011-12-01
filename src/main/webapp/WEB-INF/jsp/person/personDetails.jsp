<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="personPath" value="/people/${person.username}" />

<c:url var="editUrl" value="${personPath}/edit" />
<c:url var="jsonUrl" value="${personPath}.json" />
<c:url var="xmlUrl" value="${personPath}.xml" />

<spring:message var="deletePersonTooltip" code="tooltip.personDetails.deletePerson" />
<spring:message var="editPersonTooltip" code="tooltip.personDetails.editPerson" />
<spring:message var="jsonTooltip" code="tooltip.personDetails.json" />
<spring:message var="xmlTooltip" code="tooltip.personDetails.xml" />

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><c:out value="${person.firstNameLastName}" /></title>
</head>
<body>

<div>
	<h1 style="display:inline"><c:out value="${person.firstNameLastName}" /></h1>
	<span style="color:#666;">(<c:out value="${person.username}" />)</span>
	<ul class="menu" style="display:inline;margin-left:20px">
		<li><span class="json icon"><a href="${jsonUrl}" title="${jsonTooltip}"><spring:message code="label.common.json" /></a></span></li>
		<li><span class="xml icon"><a href="${xmlUrl}" title="${xmlTooltip}"><spring:message code="label.common.xml" /></a></span></li>
		<li></li>
		<li><a href="${editUrl}" title="${editPersonTooltip}" class="btn"><span class="editPerson icon"><spring:message code="label.common.edit" /></span></a></li>
		<li><a id="deleteProjectLink" href="#" title="${deletePersonTooltip}" class="btn"><span class="deletePerson icon"><spring:message code="label.common.delete" /></span></a></li>
	</ul>
</div>

<div class="pane">
	<h2>Contact information</h2>
	<div class="grid">
		<div class="row first">
			<div class="span3">Work phone:</div>
			<div class="span13">
				<c:out value="${person.workPhone}" default="None" />
			</div>
		</div>
		<div class="row">
			<div class="span3">Mobile phone:</div>
			<div class="span13">
				<c:out value="${person.mobilePhone}" default="None" />
			</div>
		</div>
		<div class="row">
			<div class="span3">E-mail:</div>
			<div class="span13">
				<c:choose>
					<c:when test="${not empty person.email}">
						<a href="mailto:<c:out value="${person.email}" />"><c:out value="${person.email}" /></a>
					</c:when>
					<c:otherwise>
						None
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</div>

<div>
	<h2>Collaborators</h2>
	<ul>
		<c:forEach var="collaborator" items="${collaborators}">
			<li><c:out value="${collaborator.firstNameLastName}" /></li>
		</c:forEach>
	</ul>
</div>


</body>
</html>
