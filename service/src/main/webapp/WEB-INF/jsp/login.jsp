<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="postLoginUrl" value="/j_spring_security_check" />

<div style="margin-top:80px">
	<h1>Please log in</h1>
	
	<c:if test="${param.failed == true}">
		<div class="alert alert-error" style="margin:20px 0">
			Your login attempt failed. Please try again, or contact technical support for further assistance.
		</div>
	</c:if>
	
	<div class="row" style="margin-top:20px;margin-bottom:20px">
		<form class="span4 well" action="${postLoginUrl}" method="post">
			<label>Username:</label>
			<input type="text" name="j_username" class="span3" />
			
			<label>Password:</label>
			<input type="password" name="j_password" class="span3" />
			
			<div>
				<input type="checkbox" name="_spring_security_remember_me" /> Remember me
			</div>
			<div style="margin-top:20px">
				<button type="submit" class="btn btn-primary">Log in</button>
			</div>
		</form>
	</div>
</div>