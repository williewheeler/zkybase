<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
	<div class="span4">
		<section class="first">
			<%-- FIXME Use Tiles for this --%>
			<jsp:include page="applicationMenu.jsp" />
		</section>
	</div>
	<div class="span12">
		<section class="first">
			<div class="row">
				<div class="span3">Short description:</div>
				<div class="span9">
					<c:out value="${application.shortDescription}" default="None" />
				</div>
			</div>
		</section>
	</div>
</div>
