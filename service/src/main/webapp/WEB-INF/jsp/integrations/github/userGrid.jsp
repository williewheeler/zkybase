<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="userRow" items="${userRows}">
	<div class="row" style="margin-top:20px">
		<c:forEach var="user" items="${userRow}">
			<c:set var="userUrl" value="https://github.com/${user.login}" />
			<c:url var="avatarUrl" value="${user.avatarUrl}" />

			<div class="span1"><a href="${userUrl}" target="_blank"><img src="${avatarUrl}" style="width:50px;height:50px" /></a></div>
			<div class="span3"><a href="${userUrl}" target="_blank"><c:out value="${user.login}" /></a></div>
		</c:forEach>
	</div>
</c:forEach>
