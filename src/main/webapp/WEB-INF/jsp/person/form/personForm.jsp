<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:useAttribute name="formMethod" />
<tiles:useAttribute name="instructionsCode" />
<tiles:useAttribute name="submitPath" />
<tiles:useAttribute name="cancelPath" />

<c:url var="submitUrl" value="${submitPath}" />
<c:url var="cancelUrl" value="${cancelPath}" />

<div id="instructions">
	<spring:message code="${instructionsCode}" />
</div>

<form:form cssClass="pageForm main" modelAttribute="person" action="${submitUrl}" method="post">
	<fieldset>
		<input type="hidden" name="_method" value="${formMethod}" />
		<div class="grid">
			<div class="gridBody">
				<div class="row clearfix <form:errors path="username">error</form:errors>">
					<label for="username">Username:</label>
					<div class="input">
						<form:input type="text" path="username" cssClass="span4" />
						<form:errors path="username">
							<span class="help-inline"><form:errors path="username" /></span>
						</form:errors>
					</div>
				</div>
				<div class="row clearfix <form:errors path="firstName">error</form:errors>">
					<label for="firstName">First name:</label>
					<div class="input">
						<form:input type="text" path="firstName" cssClass="span4" />
						<form:errors path="firstName">
							<span class="help-inline"><form:errors path="firstName" /></span>
						</form:errors>
					</div>
				</div>
				<div class="row clearfix <form:errors path="lastName">error</form:errors>">
					<label for="lastName">Last name:</label>
					<div class="input">
						<form:input type="text" path="lastName" cssClass="span4" />
						<form:errors path="lastName">
							<span class="help-inline"><form:errors path="lastName" /></span>
						</form:errors>
					</div>
				</div>
				<div class="row clearfix <form:errors path="title">error</form:errors>">
					<label for="title">Title:</label>
					<div class="input">
						<form:input type="text" path="title" cssClass="span6" />
						<form:errors path="title">
							<span class="help-inline"><form:errors path="title" /></span>
						</form:errors>
					</div>
				</div>
				<div class="row clearfix <form:errors path="workPhone">error</form:errors>">
					<label for="workPhone">Work phone:</label>
					<div class="input">
						<form:input type="text" path="workPhone" cssClass="span4" />
						<form:errors path="workPhone">
							<span class="help-inline"><form:errors path="workPhone" /></span>
						</form:errors>
						<span class="help-block">Format is xxx-xxx-xxxx.</span>
					</div>
				</div>
				<div class="row clearfix <form:errors path="mobilePhone">error</form:errors>">
					<label for="mobilePhone">Mobile phone:</label>
					<div class="input">
						<form:input type="text" path="mobilePhone" cssClass="span4" />
						<form:errors path="mobilePhone">
							<span class="help-inline"><form:errors path="mobilePhone" /></span>
						</form:errors>
						<span class="help-block">Format is xxx-xxx-xxxx.</span>
					</div>
				</div>
				<div class="row clearfix <form:errors path="email">error</form:errors>">
					<label for="email">E-mail:</label>
					<div class="input">
						<form:input type="text" path="email" cssClass="span6" />
						<form:errors path="email">
							<span class="help-inline"><form:errors path="email" /></span>
						</form:errors>
					</div>
				</div>
				<div class="row actions">
					<input class="btn primary" type="submit" value="Save" />
					<a class="btn" href="${cancelUrl}">Cancel</a>
				</div>
			</div>
		</div>
	</fieldset>
</form:form>
