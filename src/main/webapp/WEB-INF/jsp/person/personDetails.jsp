<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><c:out value="${person.firstNameLastName}" /></title>
</head>
<body>

<div id="pageHead">
	<h1 style="display:inline"><c:out value="${person.firstNameLastName}" /></h1>
	<span style="color:#666;">(<c:out value="${person.username}" />)</span>
</div>
<div id="pageBody">
	<div class="pane">
		<div class="paneHead">
		</div>
		<div class="paneBody">
			<div class="yui-gf">
				<div class="yui-u first">E-mail:</div>
				<div class="yui-u"><c:out value="${person.email}" default="None" /></div>
			</div>
		</div>
	</div>
</div>

</body>
</html>
