<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%-- <tiles:useAttribute name="submitPath" /> --%>
<%-- <tiles:useAttribute name="formMethod" /> --%>
<%-- <tiles:useAttribute name="instructionsCode" /> --%>
<%-- <tiles:useAttribute name="readOnly" /> --%>
<%-- <tiles:useAttribute name="cancelPath" /> --%>

<%-- <c:url var="submitUrl" value="${submitPath}" /> --%>
<%-- <c:url var="cancelUrl" value="${cancelPath}" /> --%>

<!-- <div id="instructions"> -->
<%-- 	<spring:message code="${instructionsCode}" /> --%>
<!-- </div> -->
<div id="personForm">
	<form:form cssClass="pageForm main" modelAttribute="person" action="${submitUrl}" method="post">
		<fieldset>
			<input type="hidden" name="_method" value="${formMethod}" />
			<div class="clearfix">
				<label for="username">Username:</label>
				<div class="input">
					<form:input type="text" path="username" cssClass="span4" />
					<span class="help-block">The username must be globally unique.</span>
				</div>
<%-- 				<form:errors path="username"> --%>
<!-- 					<div class="formErrors"> -->
<%-- 						<span class="warning icon"><form:errors path="username" /></span> --%>
<!-- 					</div> -->
<%-- 				</form:errors> --%>
			</div>
			<div class="clearfix">
				<label for="firstName">First name:</label>
				<div class="input"><form:input type="text" path="firstName" cssClass="span4" /></div>
<%-- 				<form:errors path="firstName"> --%>
<!-- 					<div class="formErrors"> -->
<%-- 						<span class="warning icon"><form:errors path="firstName" /></span> --%>
<!-- 					</div> -->
<%-- 				</form:errors> --%>
			</div>
			<div class="clearfix">
				<label for="lastName">Last name:</label>
				<div class="input"><form:input type="text" path="lastName" cssClass="span4" /></div>
<%-- 				<form:errors path="lastName"> --%>
<!-- 					<div class="formErrors"> -->
<%-- 						<span class="warning icon"><form:errors path="lastName" /></span> --%>
<!-- 					</div> -->
<%-- 				</form:errors> --%>
			</div>
			<div class="clearfix">
				<label for="title">Title:</label>
				<div class="input"><form:input type="text" path="title" cssClass="span6" /></div>
<%-- 				<form:errors path="title"> --%>
<!-- 					<div class="formErrors"> -->
<%-- 						<span class="warning icon"><form:errors path="title" /></span> --%>
<!-- 					</div> -->
<%-- 				</form:errors> --%>
			</div>
			<div class="clearfix">
				<label for="workPhone">Work phone:</label>
				<div class="input">
					<form:input type="text" path="workPhone" cssClass="span4" />
					<span class="help-block">Format is xxx-xxx-xxxx.</span>
				</div>
<%-- 				<form:errors path="workPhone"> --%>
<!-- 					<div class="formErrors"> -->
<%-- 						<span class="warning icon"><form:errors path="workPhone" /></span> --%>
<!-- 					</div> -->
<%-- 				</form:errors> --%>
			</div>
			<div class="clearfix">
				<label for="mobilePhone">Mobile phone:</label>
				<div class="input">
					<form:input type="text" path="mobilePhone" cssClass="span4" />
					<span class="help-block">Format is xxx-xxx-xxxx.</span>
				</div>
<%-- 				<form:errors path="mobilePhone"> --%>
<!-- 					<div class="formErrors"> -->
<%-- 						<span class="warning icon"><form:errors path="mobilePhone" /></span> --%>
<!-- 					</div> -->
<%-- 				</form:errors> --%>
			</div>
			<div class="clearfix">
				<label for="email">E-mail:</label>
				<div class="input">
					<form:input type="text" path="email" cssClass="span6" />
				</div>
<%-- 				<form:errors path="email"> --%>
<!-- 					<div class="formErrors"> -->
<%-- 						<span class="warning icon"><form:errors path="email" /></span> --%>
<!-- 					</div> -->
<%-- 				</form:errors> --%>
			</div>
			<div class="actions">
				<input class="btn primary" type="submit" value="Save person" />
				<a class="btn" href="${cancelUrl}">Cancel</a>
			</div>
		</fieldset>
	</form:form>
</div>
