<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<spring:message var="saveLabel" code="label.common.save" />

<c:url var="submitUrl" value="${submitPath}" />
<c:url var="cancelUrl" value="${cancelPath}" />

<form:form cssClass="main" modelAttribute="formData" action="${submitUrl}" method="post">
	<fieldset>
		<input type="hidden" name="_method" value="${formMethod}" />
<%-- 		<div class="clearfix <form:errors path="name">error</form:errors>"> --%>
		<div class="clearfix">
			<label for="name"><spring:message code="label.common.name" />:</label>
			<div class="input">
				<form:input cssClass="span6" cssErrorClass="span6 error" path="name" />
				<form:errors path="name">
					<span class="help-inline"><span class="error icon"><form:errors path="name" /></span></span>
				</form:errors>
			</div>
		</div>
		<div class="clearfix">
			<label for="environment"><spring:message code="entity.environment.sentenceCase.singular" />:</label>
			<div class="input">
<%-- 				<form:input cssClass="span6" cssErrorClass="span6 error" path="environment" /> --%>
				<form:select path="environment">
					<form:option value="-- Choose one--" />
					<form:options items="${environmentList}" itemLabel="displayName" itemValue="id" />
				</form:select>
				<form:errors path="environment">
					<span class="help-inline"><span class="error icon"><form:errors path="environment" /></span></span>
				</form:errors>
			</div>
		</div>
		<div class="clearfix">
			<label for="dataCenter"><spring:message code="entity.dataCenter.sentenceCase.singular" />:</label>
			<div class="input">
<%-- 				<form:input cssClass="span6" cssErrorClass="span6 error" path="dataCenter" /> --%>
				<form:select path="dataCenter">
					<form:option value="-- Choose one--" />
					<form:options items="${dataCenterList}" itemLabel="displayName" itemValue="id" />
				</form:select>
				<form:errors path="dataCenter">
					<span class="help-inline"><span class="error icon"><form:errors path="dataCenter" /></span></span>
				</form:errors>
			</div>
		</div>
		<div class="actions">
			<input class="btn primary" type="submit" value="${saveLabel}" />
			<a class="btn" href="${cancelUrl}"><spring:message code="label.common.cancel" /></a>
			<form:errors>
				<span class="globalError help-inline"><form:errors /></span>
			</form:errors>
		</div>
	</fieldset>
</form:form>
