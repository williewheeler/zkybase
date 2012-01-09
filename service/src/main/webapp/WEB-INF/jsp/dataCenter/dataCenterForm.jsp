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
			<label for="name"><span class="required"><spring:message code="label.common.name" />:</span></label>
			<div class="input">
				<form:input cssClass="span6" cssErrorClass="span6 error" path="name" />
				<form:errors path="name">
					<span class="help-inline"><span class="error icon"><form:errors path="name" /></span></span>
				</form:errors>
			</div>
		</div>
		<div class="clearfix">
			<label for="region"><span class="required"><spring:message code="entity.region.sentenceCase.singular" />:</span></label>
			<div class="input">
				<form:select path="region">
					<form:option value="" label="-- Choose one--" />
					<form:options items="${regionList}" itemValue="id" itemLabel="displayName" />
				</form:select>
				<form:errors path="region">
					<span class="help-inline"><span class="error icon"><form:errors path="region" /></span></span>
				</form:errors>
			</div>
		</div>
		<div class="actions">
			<input class="btn primary" type="submit" value="Save" />
			<a class="btn" href="${cancelUrl}">Cancel</a>
		</div>
	</fieldset>
</form:form>
