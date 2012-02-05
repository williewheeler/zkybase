<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:url var="excanvasJsUrl" value="/scripts/jit/Extras/excanvas.js" />
<c:url var="jitJsUrl" value="/scripts/jit/jit.js" />
<c:url var="exampleJsUrl" value="/scripts/jit/Examples/Hypertree/example1.js" />

<!--[if IE]><script language="javascript" type="text/javascript" src="../../Extras/excanvas.js"></script><![endif]-->

<section class="first">
	<div id="infovis"></div>
</section>
<section>
	<h2>Data centers</h2>
	<c:choose>
		<c:when test="${empty region.dataCenters}">
			<p>None.</p>
		</c:when>
		<c:otherwise>
			<%-- TODO Would be cool to show this on a Google map or something --%>
			<ul>
				<c:forEach var="dataCenter" items="${region.dataCenters}">
					<c:url var="dataCenterUrl" value="/datacenters/${dataCenter.id}" />
					<li><a href="${dataCenterUrl}"><c:out value="${dataCenter.displayName}" /></a>
				</c:forEach>
			</ul>
		</c:otherwise>
	</c:choose>
</section>
<section>
	<div id="inner-details"></div>
	<div id="log"></div>
</section>

<script type="text/javascript" src="${jitJsUrl}"></script>
<script type="text/javascript" src="${exampleJsUrl}"></script>

<script type="text/javascript">
	$(function() { init(); });
</script>
