<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<c:url var="submitUrl" value="${submitPath}" />
<c:url var="cancelUrl" value="${cancelPath}" />

<form:form cssClass="form-horizontal main" modelAttribute="formData" action="${submitUrl}" method="post">
	<fieldset>
		<input type="hidden" name="_method" value="${formMethod}" />
		<div class="control-group">
			<label class="control-label" for="name"><span class="required"><spring:message code="label.common.name" />:</span></label>
			<div class="controls">
				<form:input cssClass="span6" cssErrorClass="span6 error" path="name" />
				<form:errors path="name">
					<div class="help-block"><form:errors path="name" /></div>
				</form:errors>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="region"><span class="required"><spring:message code="entity.region.sentenceCase.singular" />:</span></label>
			<div class="controls">
				<form:select path="region">
					<form:option value="" label="-- Choose one--" />
					<form:options items="${regionList}" itemValue="id" itemLabel="displayName" />
				</form:select>
				<form:errors path="region">
					<div class="help-block"><form:errors path="region" /></div>
				</form:errors>
			</div>
		</div>
		<div class="form-actions">
			<input class="btn btn-primary" type="submit" value="Save" />
			<a class="btn" href="${cancelUrl}">Cancel</a>
		</div>
	</fieldset>
</form:form>
