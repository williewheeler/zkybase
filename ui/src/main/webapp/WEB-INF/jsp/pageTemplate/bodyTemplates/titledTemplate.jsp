<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div>
	<div id="titleBar">
		<h1><c:out value="${navigation.pageTitle}" /></h1>
		<tiles:insertAttribute name="viewLinks" />
		<div class="pull-right">
			<h1></h1>
			<tiles:insertAttribute name="actionLinks" />
		</div>
	</div>
	<tiles:insertAttribute name="content" />
</div>
