<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<c:url var="submitUrl" value="${submitPath}" />
<c:url var="cancelUrl" value="${cancelPath}" />

<form:form cssClass="main" modelAttribute="formData" action="${submitUrl}" method="post">
	<fieldset>
		<input type="hidden" name="_method" value="${formMethod}" />
		<div class="clearfix">
			<label for="username">Username:</label>
			<div class="input">
				<form:input type="text" path="username" cssClass="span4" />
				<form:errors path="username">
					<span class="help-inline"><span class="error icon"><form:errors path="username" /></span></span>
				</form:errors>
			</div>
		</div>
		<div class="clearfix">
			<label for="firstName">First name:</label>
			<div class="input">
				<form:input type="text" path="firstName" cssClass="span4" />
				<form:errors path="firstName">
					<span class="help-inline"><span class="error icon"><form:errors path="firstName" /></span></span>
				</form:errors>
			</div>
		</div>
		<div class="clearfix">
			<label for="lastName">Last name:</label>
			<div class="input">
				<form:input type="text" path="lastName" cssClass="span4" />
				<form:errors path="lastName">
					<span class="help-inline"><span class="error icon"><form:errors path="lastName" /></span></span>
				</form:errors>
			</div>
		</div>
		<div class="clearfix">
			<label for="title">Title:</label>
			<div class="input">
				<form:input type="text" path="title" cssClass="span6" />
				<form:errors path="title">
					<span class="help-inline"><span class="error icon"><form:errors path="title" /></span></span>
				</form:errors>
			</div>
		</div>
		<div class="clearfix">
			<label for="workPhone">Work phone:</label>
			<div class="input">
				<form:input type="text" path="workPhone" cssClass="span4" />
				<form:errors path="workPhone">
					<span class="help-inline"><span class="error icon"><form:errors path="workPhone" /></span></span>
				</form:errors>
				<span class="help-block">Format is xxx-xxx-xxxx.</span>
			</div>
		</div>
		<div class="clearfix">
			<label for="mobilePhone">Mobile phone:</label>
			<div class="input">
				<form:input type="text" path="mobilePhone" cssClass="span4" />
				<form:errors path="mobilePhone">
					<span class="help-inline"><span class="error icon"><form:errors path="mobilePhone" /></span></span>
				</form:errors>
				<span class="help-block">Format is xxx-xxx-xxxx.</span>
			</div>
		</div>
		<div class="clearfix">
			<label for="email">E-mail:</label>
			<div class="input">
				<form:input type="text" path="email" cssClass="span6" />
				<form:errors path="email">
					<span class="help-inline"><span class="error icon"><form:errors path="email" /></span></span>
				</form:errors>
			</div>
		</div>
		<div class="clearfix">
			<label for="gitHubUser">GitHub user:</label>
			<div class="input">
				<form:input type="text" path="gitHubUser" cssClass="span4" />
				<form:errors path="gitHubUser">
					<span class="help-inline"><span class="error icon"><form:errors path="email" /></span></span>
				</form:errors>
			</div>
		</div>
		<div class="row actions">
			<input class="btn primary" type="submit" value="Save" />
			<a class="btn" href="${cancelUrl}">Cancel</a>
		</div>
	</fieldset>
</form:form>
