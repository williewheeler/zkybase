<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url var="modulesUrl" value="/applications/${application.id}/modules" />

<form:form id="moduleForm" modelAttribute="module" action="${modulesUrl}" method="post">
	<fieldset>
		<div class="control-group <form:errors path="name">error</form:errors>">
			<label for="name">Name:</label>
			<div class="controls">
				<form:input cssClass="span4" path="name" />
				<form:errors path="name">
					<div class="help-block"><form:errors path="name" /></div>
				</form:errors>
			</div>
		</div>
		<div class="control-group <form:errors path="shortDescription">error</form:errors>">
			<label for="shortDescription">Short description:</label>
			<div class="controls">
				<form:input cssClass="span6" path="shortDescription" />
				<form:errors path="shortDescription">
					<div class="help-block"><form:errors path="shortDescription" /></div>
				</form:errors>
			</div>
		</div>
		<div class="control-group <form:errors path="groupId">error</form:errors>">
			<label for="groupId">Group ID:</label>
			<div class="controls">
				<form:input cssClass="span4" path="groupId" />
				<form:errors path="groupId">
					<div class="help-block"><form:errors path="groupId" /></div>
				</form:errors>
			</div>
		</div>
		<div class="control-group <form:errors path="moduleId">error</form:errors>">
			<label for="moduleId">Module ID:</label>
			<div class="controls">
				<form:input cssClass="span4" path="moduleId" />
				<form:errors path="moduleId">
					<div class="help-block"><form:errors path="moduleId" /></div>
				</form:errors>
			</div>
		</div>
	</fieldset>
</form:form>
