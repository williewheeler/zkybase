<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${param.a == 'created'}">
	<div class="row">
		<div class="span16">
			<div class="alert-message success fade in">
				<a href="#" class="close">&times;</a>
				<p>Person successfully created.</p>
			</div>
		</div>
	</div>
</c:if>
