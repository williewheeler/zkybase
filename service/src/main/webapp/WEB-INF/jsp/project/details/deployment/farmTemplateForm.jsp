<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<c:url var="submitUrl" value="/projects/${project.key}/farmtemplates" />

<div id="farmTemplateForm">
	<form:form cssClass="main" modelAttribute="farmTemplate" action="${submitUrl}" method="post">
		<fieldset>
			<input type="hidden" name="_method" value="post" />
	<!-- 		<div class="formItem required first"> -->
			<div class="clearfix">
				<label for="name">Farm name:</label>
				<div class="input"><form:input type="text" path="name" cssClass="span8" /></div>
				<form:errors path="name">
					<div class="formErrors">
						<span class="warning icon"><form:errors path="name" /></span>
					</div>
				</form:errors>
			</div>
			<div class="clearfix">
				<label for="name">Instance type:</label>
				<div class="input"><form:input type="text" path="instanceType" cssClass="span8" /></div>
				<form:errors path="instanceType">
					<div class="formErrors">
						<span class="warning icon"><form:errors path="instanceType" /></span>
					</div>
				</form:errors>
			</div>
			<div class="clearfix">
				<label for="name">Image ID:</label>
				<div class="input"><form:input type="text" path="imageId" cssClass="span8" /></div>
				<form:errors path="imageId">
					<div class="formErrors">
						<span class="warning icon"><form:errors path="imageId" /></span>
					</div>
				</form:errors>
			</div>
			<div class="clearfix">
				<label for="name">Security group:</label>
				<div class="input"><form:input type="text" path="securityGroup" cssClass="span8" /></div>
				<form:errors path="securityGroup">
					<div class="formErrors">
						<span class="warning icon"><form:errors path="securityGroup" /></span>
					</div>
				</form:errors>
			</div>
			<div class="clearfix">
				<label for="name">Key pair:</label>
				<div class="input"><form:input type="text" path="keyPair" cssClass="span8" /></div>
				<form:errors path="keyPair">
					<div class="formErrors">
						<span class="warning icon"><form:errors path="keyPair" /></span>
					</div>
				</form:errors>
			</div>
			<div class="clearfix">
				<label for="name">IP address:</label>
				<div class="input"><form:input type="text" path="ipAddress" cssClass="span8" /></div>
				<form:errors path="ipAddress">
					<div class="formErrors">
						<span class="warning icon"><form:errors path="ipAddress" /></span>
					</div>
				</form:errors>
			</div>
			<div class="actions">
				<input class="btn primary" type="submit" value="Save" />
				<a href="#section1" class="btn">Cancel</a>
			</div>
		</fieldset>
	</form:form>
</div>
