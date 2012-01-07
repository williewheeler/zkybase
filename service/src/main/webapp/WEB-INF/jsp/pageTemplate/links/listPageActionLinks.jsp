<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="entityPath" value="${navigation.currentNode.path}" />

<c:url var="entityCreatorUrl" value="${entityPath}/new" />

<ul class="inlineLinks">
	<li><a href="${entityCreatorUrl}" class="btn"><span class="create icon">New</span></a></li>
</ul>
