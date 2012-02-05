<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="region" value="${dataCenter.region}" />

<section class="first">
	<div class="well">
		<table class="grid">
			<tbody>
				<tr>
					<td>Region:</td>
					<td>
						<c:choose>
							<c:when test="${empty region}">
								None
							</c:when>
							<c:otherwise>
								<c:url var="regionUrl" value="/regions/${region.id}" />
								<a href="${regionUrl}"><c:out value="${region.displayName}" /></a>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</section>
