<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<c:url var="submitUrl" value="${submitPath}" />
<c:url var="cancelUrl" value="${cancelPath}" />

<form:form cssClass="form-horizontal main" modelAttribute="formData" action="${submitUrl}" method="post">
	<fieldset>
		<input type="hidden" name="_method" value="${formMethod}" />
		<div class="control-group <form:errors path="name">error</form:errors>">
			<label class="control-label" for="name">Name:</label>
			<div class="controls">
				<form:input cssClass="span4" path="name" />
				<form:errors path="name">
					<div class="help-block"><form:errors path="name" /></div>
				</form:errors>
			</div>
		</div>
		<div class="control-group <form:errors path="shortDescription">error</form:errors>">
			<label class="control-label" for="shortDescription">Short description:</label>
			<div class="controls">
				<form:input cssClass="span6" path="shortDescription" />
				<form:errors path="shortDescription">
					<div class="help-block"><form:errors path="shortDescription" /></div>
				</form:errors>
			</div>
		</div>
		<div class="control-group <form:errors path="scm.user">error</form:errors>">
			<label class="control-label" for="scm.user">GitHub user:</label>
			<div class="controls">
				<form:input cssClass="span4" path="scm.user" />
				<form:errors path="scm.user">
					<div class="help-block"><form:errors path="scm.user" /></div>
				</form:errors>
			</div>
		</div>
		<div class="control-group <form:errors path="scm.repo">error</form:errors>">
			<label class="control-label" for="scm.repo">GitHub repo:</label>
			<div class="controls">
				<form:input cssClass="span4" path="scm.repo" />
				<form:errors path="scm.repo">
					<div class="help-block"><form:errors path="scm.repo" /></div>
				</form:errors>
			</div>
		</div>
		<div class="form-actions">
			<input class="btn btn-primary" type="submit" value="Submit" />
			<a class="btn" href="${cancelUrl}">Cancel</a>
		</div>
	</fieldset>
</form:form>
