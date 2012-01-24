<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="scm" value="${application.scm}" />

<div>
	<h2>SCM</h2>
	<section class="first">
		<div class="grid">
			<div class="row">
				<div class="span2 gridLabel">GitHub SCM:</div>
				<div class="span9 gridValue">
					<c:out value="${scm.displayName}" default="None" />
				</div>
			</div>
			<div class="row">
				<div class="span2 gridLabel">Description:</div>
				<div class="span9 gridValue">
					<c:out value="${scm.description}" default="None" />
				</div>
			</div>
		</div>
	</section>
	<section>
		<div class="grid">
			<div class="row">
				<div class="span2 gridLabel">HTML URL:</div>
				<div class="span9 gridValue">
					<c:choose>
						<c:when test="${empty scm.htmlUrl}">
							None
						</c:when>
						<c:otherwise>
							<a href="<c:out value="${scm.htmlUrl}" />"><c:out value="${scm.htmlUrl}" /></a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="row">
				<div class="span2 gridLabel">Clone URL:</div>
				<div class="span9 gridValue">
					<c:out value="${scm.cloneUrl}" default="None" />
				</div>
			</div>
			<div class="row">
				<div class="span2 gridLabel">Git URL:</div>
				<div class="span9 gridValue">
					<c:out value="${scm.gitUrl}" default="None" />
				</div>
			</div>
			<div class="row">
				<div class="span2 gridLabel">SSH URL:</div>
				<div class="span9 gridValue">
					<c:out value="${scm.sshUrl}" default="None" />
				</div>
			</div>
			<div class="row">
				<div class="span2 gridLabel">SVN URL:</div>
				<div class="span9 gridValue">
					<c:out value="${scm.svnUrl}" default="None" />
				</div>
			</div>
			<div class="row">
				<div class="span2 gridLabel">API URL:</div>
				<div class="span9 gridValue">
					<c:choose>
						<c:when test="${empty scm.url}">
							None
						</c:when>
						<c:otherwise>
							<a href="<c:out value="${scm.url}" />"><c:out value="${scm.url}" /></a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</section>
</div>
