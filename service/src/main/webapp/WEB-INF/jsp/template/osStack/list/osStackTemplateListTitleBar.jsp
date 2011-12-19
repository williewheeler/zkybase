<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="createTemplateUrl" value="/templates/osstacktemplates/new" />

<div class="row">
	<div class="span8">
		<h1 style="display:inline">OS stack templates</h1>
		<ul class="menu" style="display:inline;margin-left:20px">
			<li><span class="json icon"><a href="#" title="Render a JSON view of all OS stack templates">JSON</a></span></li>
			<li><span class="xml icon"><a href="#" title="Render an XML view of all OS stack templates">XML</a></span></li>
		</ul>
	</div>
	<div class="span8" style="text-align:right">
		<h1 style="display:inline"></h1>
		<ul class="menu" style="display:inline;margin-left:20px">
			<li><a href="${createTemplateUrl}" title="Create a new OS stack template" class="btn"><span class="add icon">Create template</span></a></li>
		</ul>
	</div>
</div>
