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
<div id="projectForm">
	<form:form cssClass="form-stacked pageForm main" modelAttribute="project" action="${submitUrl}" method="post">
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
			<label for="key">Choose a project key:</label>
			<div class="formField"><form:input type="text" path="key" cssClass="span4" readonly="${readOnly}" /></div>
			<form:errors path="key">
				<div class="formErrors">
					<span class="warning icon"><form:errors path="key" /></span>
				</div>
			</form:errors>
			<div class="formHint">
				<p>A project's key is a short version of its name that's globally unique, supporting external references.
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
		<div class="formSubmit">
			<input class="btn primary" type="submit" value="Save project" />
			<a href="${cancelUrl}" class="btn">Cancel</a>
		</div>
	</form:form>
</div>
