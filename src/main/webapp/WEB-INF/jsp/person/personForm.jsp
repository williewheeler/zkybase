<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url var="peopleUrl" value="/people" />

<%-- FIXME Need to handle edit mode too --%>
<c:url var="cancelUrl" value="/people" />

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Add a new person</title>
<script type="text/javascript">
	$(function() {
		$("#cancelButton").click(function() { window.location = "${cancelUrl}"; });
	});
</script>
</head>
<body>

<h1><span class="createPerson icon">Add a new person</span></h1>

<p>You can add a new person using the form below.</p>

<form:form cssClass="form-stacked pageForm main" modelAttribute="person" action="${peopleUrl}" method="post">
	<div class="formItem required first">
		<label for="username">Username:</label>
		<div class="formField"><form:input type="text" path="username" cssClass="span4" /></div>
		<form:errors path="username">
			<div class="formErrors">
				<span class="warning icon"><form:errors path="username" /></span>
			</div>
		</form:errors>
		<div class="formHint">
			<p>The username must be unique across users.</p>
			<p><span class="label important">Important</span> Choose carefully, as you cannot change the username once it's been set.</p>
		</div>
	</div>
	<div class="formItem required">
		<label for="firstName">First name:</label>
		<div class="formField"><form:input type="text" path="firstName" cssClass="span4" /></div>
		<form:errors path="firstName">
			<div class="formErrors">
				<span class="warning icon"><form:errors path="firstName" /></span>
			</div>
		</form:errors>
	</div>
	<div class="formItem required">
		<label for="lastName">Last name:</label>
		<div class="formField"><form:input type="text" path="lastName" cssClass="span4" /></div>
		<form:errors path="lastName">
			<div class="formErrors">
				<span class="warning icon"><form:errors path="lastName" /></span>
			</div>
		</form:errors>
	</div>
	<div class="formItem">
		<label for="title">Title:</label>
		<div class="formField"><form:input type="text" path="title" cssClass="span6" /></div>
		<form:errors path="title">
			<div class="formErrors">
				<span class="warning icon"><form:errors path="title" /></span>
			</div>
		</form:errors>
	</div>
	<div class="formItem">
		<label for="workPhone">Work phone:</label>
		<div class="formField"><form:input type="text" path="workPhone" cssClass="span4" /></div>
		<form:errors path="workPhone">
			<div class="formErrors">
				<span class="warning icon"><form:errors path="workPhone" /></span>
			</div>
		</form:errors>
		<div class="formHint">Format is xxx-xxx-xxxx.</div>
	</div>
	<div class="formItem">
		<label for="mobilePhone">Mobile phone:</label>
		<div class="formField"><form:input type="text" path="mobilePhone" cssClass="span6" /></div>
		<form:errors path="mobilePhone">
			<div class="formErrors">
				<span class="warning icon"><form:errors path="mobilePhone" /></span>
			</div>
		</form:errors>
		<div class="formHint">Format is xxx-xxx-xxxx.</div>
	</div>
	<div class="formItem">
		<label for="email">E-mail:</label>
		<div class="formField"><form:input type="text" path="email" cssClass="span6" /></div>
		<form:errors path="email">
			<div class="formErrors">
				<span class="warning icon"><form:errors path="email" /></span>
			</div>
		</form:errors>
	</div>
	<div class="formSubmit">
		<input class="btn primary" type="submit" value="Save person" />
		<input id="cancelButton" class="btn" type="button" value="Cancel" style="margin-left:5px" />
	</div>
</form:form>

</body>
</html>
