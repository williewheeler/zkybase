<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="row">
	<div class="span4">
		<section class="first">
			<%-- FIXME Use Tiles for this --%>
			<jsp:include page="applicationMenu.jsp" />
		</section>
	</div>
	<div class="span12">
		<section class="first">
			<h2>Watchers (${fn:length(watcherList)})</h2>
			<c:forEach var="watcher" items="${watcherList}">
				<c:set var="watcherHtmlUrl" value="https://github.com/${watcher.login}" />
				<c:url var="avatarUrl" value="${watcher.avatarUrl}" />
				<c:url var="watcherJsonUrl" value="${watcher.url}" />
				
				<div class="row" style="padding:10px 0;border-bottom:1px solid #DDD">
					<div class="span1"><img src="${avatarUrl}" style="width:40px;height:40px" /></div>
					<div class="span11"><a href="${watcherHtmlUrl}" target="_blank"><c:out value="${watcher.login}" /></a></div>
				</div>
			</c:forEach>
		</section>
	</div>
</div>
