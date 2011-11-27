<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:choose>
	<c:when test="${mode == 'create'}">
		<c:set var="title">Create a new project</c:set>
		<c:set var="iconType">createProject</c:set>
		<c:set var="formAction">/projects</c:set>
		<c:set var="formMethod">POST</c:set>
		<c:set var="instructions">
			<p>Create a new Skybase project using the form below.</p>
		</c:set>
		<c:set var="readOnly">false</c:set>
	</c:when>
	<c:otherwise>
		<c:set var="title">Edit project</c:set>
		<c:set var="iconType">editProject</c:set>
		<c:set var="formAction" value="/projects/${project.id}" />
		<c:set var="formMethod">PUT</c:set>
		<c:set var="instructions">
			<p>Edit this project using the form below.</p>
		</c:set>
		<c:set var="readOnly">true</c:set>
	</c:otherwise>
</c:choose>

<c:url var="formActionUrl" value="${formAction}" />

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><c:out value="${title}" /></title>
</head>
<body>

<div id="pageHead">
	<h1><span class="${iconType} icon"><c:out value="${title}" /></span></h1>
</div>

<div id="pageBody">
	<c:out value="${instructions}" escapeXml="false" />
	
	<form:form cssClass="form-stacked pageForm main" modelAttribute="project" action="${formActionUrl}" method="post">
		<input type="hidden" name="_method" value="${formMethod}" />
		<div class="formItem required first">
			<label for="name">Choose a project name:</label>
			<div class="formField"><form:input type="text" path="name" cssClass="span10" /></div>
			<form:errors path="name">
				<div class="formErrors">
					<span class="warning icon"><form:errors path="name" /></span>
				</div>
			</form:errors>
			<div class="formHint">The name must be unique across projects.</div>
		</div>
		<div class="formItem required">
			<label for="id">Choose a project key:</label>
			<div class="formField"><form:input type="text" path="id" cssClass="span4" readonly="${readOnly}" /></div>
			<form:errors path="id">
				<div class="formErrors">
					<span class="warning icon"><form:errors path="id" /></span>
				</div>
			</form:errors>
			<div class="formHint">
				<p>A project's key is a short, globally unique version of its name that supports external references.
				The key is case-insensitive, and it must use only alphanumeric characters, underscores and hyphens.</p>
				<c:choose>
					<c:when test="${mode == 'create'}">
						<p><span class="label important">Important</span> Choose carefully, as you cannot change the key once it's been set.</p>
					</c:when>
					<c:otherwise>
						<p><span class="label important">Important</span> You cannot change this key since it's already been set.</p>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="formItem">
			<label>Enter an optional short description (up to 160 characters):</label>
			<div class="formField"><form:textarea path="shortDescription" cssClass="span10 shortHeight" /></div>
			<form:errors path="shortDescription">
				<div class="formErrors">
					<span class="warning icon"><form:errors path="shortDescription" /></span>
				</div>
			</form:errors>
		</div>
		<div class="formSubmit"><input class="btn primary" type="submit" value="Create project" /></div>
	</form:form>
</div>

</body>
</html>
