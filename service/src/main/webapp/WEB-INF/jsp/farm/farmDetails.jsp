<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<section class="first">
	<div class="well">
		<table class="grid">
			<tbody>
				<tr>
					<td>Environment:</td>
					<td><c:out value="${farm.environment.displayName}" default="None" /></td>
				</tr>
				<tr>
					<td>Data center:</td>
					<td><c:out value="${farm.dataCenter.displayName}" default="None" /></td>
				</tr>
			</tbody>
		</table>
	</div>
</section>
