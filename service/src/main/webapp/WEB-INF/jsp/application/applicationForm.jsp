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
			<label for="name">Name:</label>
			<div class="input">
				<form:input cssClass="span6" cssErrorClass="span6 error" path="name" />
				<form:errors path="name">
					<span class="help-inline"><span class="error icon"><form:errors path="name" /></span></span>
				</form:errors>
			</div>
		</div>
		<div class="clearfix">
			<label for="shortDescription">Short description:</label>
			<div class="input">
				<form:input cssClass="span10" cssErrorClass="span6 error" path="shortDescription" />
				<form:errors path="shortDescription">
					<span class="help-inline"><span class="error icon"><form:errors path="shortDescription" /></span></span>
				</form:errors>
			</div>
		</div>
		<div class="clearfix">
			<label for="scm.user">GitHub user:</label>
			<div class="input">
				<form:input cssClass="span6" cssErrorClass="span6 error" path="scm.user" />
				<form:errors path="scm.user">
					<span class="help-inline"><span class="error icon"><form:errors path="scm.user" /></span></span>
				</form:errors>
			</div>
		</div>
		<div class="clearfix">
			<label for="scm.repo">GitHub repo:</label>
			<div class="input">
				<form:input cssClass="span6" cssErrorClass="span6 error" path="scm.repo" />
				<form:errors path="scm.repo">
					<span class="help-inline"><span class="error icon"><form:errors path="scm.repo" /></span></span>
				</form:errors>
			</div>
		</div>
		<div class="actions">
			<input class="btn primary" type="submit" value="Save" />
			<a class="btn" href="${cancelUrl}">Cancel</a>
		</div>
	</fieldset>
</form:form>
