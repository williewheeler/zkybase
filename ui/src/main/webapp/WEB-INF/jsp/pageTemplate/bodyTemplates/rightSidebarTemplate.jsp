<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class="row">
	<div class="span9">
		<tiles:insertAttribute name="content" />
	</div>
	<div class="span3">
		<div class="sidebar">
			<tiles:insertAttribute name="sidebar" />
		</div>
	</div>
</div>
