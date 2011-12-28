<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<c:url var="submitUrl" value="${submitPath}" />
<c:url var="cancelUrl" value="${cancelPath}" />

<form:form cssClass="pageForm main" modelAttribute="formData" action="${submitUrl}" method="post">
	<fieldset>
		<input type="hidden" name="_method" value="${formMethod}" />
		<div class="grid">
			<div class="gridBody">
				<div class="row clearfix <form:errors path="name">error</form:errors>">
					<label for="name">Name:</label>
					<div class="input">
						<form:input cssClass="span6" cssErrorClass="span6 error" path="name" />
						<form:errors path="name">
							<span class="help-inline"><form:errors path="name" /></span>
						</form:errors>
					</div>
				</div>
				<div class="row actions">
					<input class="btn primary" type="submit" value="Save" />
					<a class="btn" href="${cancelUrl}">Cancel</a>
					<form:errors>
						<span class="globalError help-inline"><form:errors /></span>
					</form:errors>
				</div>
			</div>
		</div>
	</fieldset>
</form:form>
