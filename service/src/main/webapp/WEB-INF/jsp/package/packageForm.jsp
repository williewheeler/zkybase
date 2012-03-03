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
			<label for="groupId">Group ID:</label>
			<div class="input">
				<form:input cssClass="span6" cssErrorClass="span6 error" path="groupId" />
				<form:errors path="groupId">
					<span class="help-inline"><span class="error icon"><form:errors path="groupId" /></span></span>
				</form:errors>
			</div>
		</div>
		<div class="clearfix">
			<label for="packageId">Package ID:</label>
			<div class="input">
				<form:input cssClass="span6" path="packageId" />
				<form:errors path="packageId">
					<span class="help-inline"><span class="error icon"><form:errors path="packageId" /></span></span>
				</form:errors>
			</div>
		</div>
		<div class="clearfix">
			<label for="version">Version:</label>
			<div class="input">
				<form:input cssClass="span4" path="version" />
				<form:errors path="version">
					<span class="help-inline"><span class="error icon"><form:errors path="version" /></span></span>
				</form:errors>
			</div>
		</div>
		<div class="form-actions">
			<input class="btn primary" type="submit" value="Save" />
			<a class="btn" href="${cancelUrl}">Cancel</a>
		</div>
	</fieldset>
</form:form>
