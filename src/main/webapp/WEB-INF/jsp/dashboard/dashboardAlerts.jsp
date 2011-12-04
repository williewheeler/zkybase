<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${param.a == 'created'}">
	<div class="alert-message success">
		<a href="#" class="close">&times;</a>
		<p>Project successfully created.</p>
	</div>
</c:if>

<c:if test="${param.a == 'cancelled'}">
	<div class="alert-message info">
		<a href="#" class="close">&times;</a>
		<p>Project creation cancelled.</p>
	</div>
</c:if>
