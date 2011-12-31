<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
	<div class="span10">
		<section class="first">
			<h2>Overview</h2>
			<div class="grid">
				<div class="gridBody">
					<div class="row">
						<div class="span2 gridLabel">Environment:</div>
						<div class="span7">
							<c:out value="${farm.environment.displayName}" default="None" />
						</div>
					</div>
					<div class="row">
						<div class="span2 gridLabel">Data center:</div>
						<div class="span7">
							<c:out value="${farm.dataCenter.displayName}" default="None" />
						</div>
					</div>
				</div>
			</div>
		</section>
		<section>
			<h2>Instances</h2>
			<p>TODO</p>
		</section>
	</div>
	<div class="span6">
		<section class="first">
			<jsp:include page="/WEB-INF/jsp/dashboard/updates.jsp" />
		</section>
	</div>
</div>
