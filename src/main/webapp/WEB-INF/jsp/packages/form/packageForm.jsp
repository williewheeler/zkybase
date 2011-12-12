<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:useAttribute name="submitPath" />
<tiles:useAttribute name="formMethod" />
<tiles:useAttribute name="instructionsCode" />
<tiles:useAttribute name="readOnly" />
<tiles:useAttribute name="cancelPath" />

<c:url var="submitUrl" value="${submitPath}" />
<c:url var="cancelUrl" value="${cancelPath}" />

<div id="instructions">
	<spring:message code="${instructionsCode}" />
</div>

<form:form cssClass="main" modelAttribute="pkg" action="${submitUrl}" method="post">
	<fieldset>
		<input type="hidden" name="_method" value="${formMethod}" />
		<div class="clearfix">
			<label for="name">Name:</label>
			<div class="input">
				<form:input cssClass="span8" path="name" />
			</div>
		</div>
		<div class="actions">
			<input class="btn primary" type="submit" value="Save" />
			<a class="btn" href="${cancelUrl}">Cancel</a>
		</div>
	</fieldset>
</form:form>
