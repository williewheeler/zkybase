<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${param.a == 'updated'}">
	<div class="alert-message success fade in">
		<a href="#" class="close">&times;</a>
		<p>Project successfully updated.</p>
	</div>
</c:if>

<c:if test="${param.a == 'cancelled'}">
	<div class="alert-message info fade in">
		<a href="#" class="close">&times;</a>
		<p>Project edit cancelled.</p>
	</div>
</c:if>
