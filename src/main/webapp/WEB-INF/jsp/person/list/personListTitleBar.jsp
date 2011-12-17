<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="addPersonUrl" value="/people/new" />

<div class="row">
	<div class="span8">
		<h1 style="display:inline">People</h1>
		<ul class="menu" style="display:inline;margin-left:20px">
			<li><span class="json icon"><a href="#" title="Render a JSON view of all people">JSON</a></span></li>
			<li><span class="xml icon"><a href="#" title="Render an XML view of all people">XML</a></span></li>
		</ul>
	</div>
	<div class="span8" style="text-align:right">
		<h1 style="display:inline"></h1>
		<ul class="menu" style="display:inline;margin-left:20px">
			<li><a href="${addPersonUrl}" title="Create a new person" class="btn"><span class="addUser icon">Create person</span></a></li>
		</ul>
	</div>
</div>
