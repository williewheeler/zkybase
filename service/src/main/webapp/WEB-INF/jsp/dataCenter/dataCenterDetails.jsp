<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="region" value="${dataCenter.region}" />

<div class="row">
	<div class="span12">
		<div class="section first">
			<div class="grid">
				<div class="gridBody">
					<div class="row">
						<div class="span3 gridLabel">Region:</div>
						<div class="span8">
							<c:choose>
								<c:when test="${empty region}">
									None
								</c:when>
								<c:otherwise>
									<c:url var="regionUrl" value="/regions/${region.id}" />
									<a href="${regionUrl}"><c:out value="${region.displayName}" /></a>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
