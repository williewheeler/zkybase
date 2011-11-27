<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url var="peopleUrl" value="/people" />

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Add a new person</title>
<style type="text/css">
	#addPersonTitle {
		padding-left: 20px;
		background: url('../images/icons/user--plus.png') left no-repeat;
	}
</style>
</head>
<body>

<h1 id="addPersonTitle">Add a new person</h1>

<p>You can add a new person using the form below.</p>

<form:form cssClass="pageForm main" modelAttribute="person" action="${peopleUrl}" method="post">
	<div class="formItem required">
		<div class="formLabel">Username:</div>
		<div class="formField"><form:input type="text" path="username" cssClass="short" /></div>
		<form:errors path="username">
			<div class="formErrors">
				<span class="warning icon"><form:errors path="username" /></span>
			</div>
		</form:errors>
	</div>
	<div class="formItem required">
		<div class="formLabel">First name:</div>
		<div class="formField"><form:input type="text" path="firstName" cssClass="short" /></div>
		<form:errors path="firstName">
			<div class="formErrors">
				<span class="warning icon"><form:errors path="firstName" /></span>
			</div>
		</form:errors>
	</div>
	<div class="formItem required">
		<div class="formLabel">Last name:</div>
		<div class="formField"><form:input type="text" path="lastName" cssClass="short" /></div>
		<form:errors path="lastName">
			<div class="formErrors">
				<span class="warning icon"><form:errors path="lastName" /></span>
			</div>
		</form:errors>
	</div>
	<div class="formItem">
		<div class="formLabel">E-mail:</div>
		<div class="formField"><form:input type="text" path="email" cssClass="medium" /></div>
		<form:errors path="email">
			<div class="formErrors">
				<span class="warning icon"><form:errors path="email" /></span>
			</div>
		</form:errors>
	</div>
	<div class="formSubmit"><input type="submit" value="Add person" /></div>
</form:form>

</body>
</html>
