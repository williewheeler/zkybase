<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div id="titleBar">
	<div class="row">
		<div class="span12">
			<h1 style="display:inline"><c:out value="${navigation.currentNode.name}" /></h1>
			<tiles:insertAttribute name="viewLinks" />
		</div>
		<div class="span4" style="text-align:right">
			<h1 style="display:inline"></h1>
			<tiles:insertAttribute name="actionLinks" />
		</div>
	</div>
</div>
