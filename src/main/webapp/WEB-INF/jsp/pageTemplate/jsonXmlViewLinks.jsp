<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="jsonUrl" value="${navigation.currentNode.path}.json" />
<c:url var="xmlUrl" value="${navigation.currentNode.path}.xml" />

<ul class="inlineLinks">
	<li><span class="json icon"><a href="${jsonUrl}" title="<c:out value="${navigation.currentNode.name}" /> as JSON">JSON</a></span></li>
	<li><span class="xml icon"><a href="${xmlUrl}" title="<c:out value="${navigation.currentNode.name}" /> as XML">XML</a></span></li>
</ul>
