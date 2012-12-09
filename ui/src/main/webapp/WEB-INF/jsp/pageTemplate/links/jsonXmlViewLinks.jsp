<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="jsonUrl" value="${navigation.currentNode.path}">
	<c:param name="format" value="json" />
</c:url>
<c:url var="xmlUrl" value="${navigation.currentNode.path}">
	<c:param name="format" value="xml" />
</c:url>

<ul class="inlineLinks">
	<li><a rel="tooltip" href="${jsonUrl}" title="<c:out value="${navigation.currentNode.name}" /> as JSON"><span class="iconx json">JSON</a></span></li>
	<li><a rel="tooltip" href="${xmlUrl}" title="<c:out value="${navigation.currentNode.name}" /> as XML"><span class="iconx xml">XML</span></a></li>
</ul>
