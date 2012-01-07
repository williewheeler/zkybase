<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="deleteDialog" class="modal hide fade">
	<div class="modal-header">
		<a href="#" class="close">&times;</a>
		<h3>Delete <span class="entityName"></span></h3>
	</div>
	<div class="modal-body">
		<p>This will delete <span class="entityName"></span> permanently. Are you sure?</p>
	</div>
	<div class="modal-footer">
		<form action="#" method="post">
			<input type="hidden" name="_method" value="delete" />
			<input id="#reallyDeleteButton" type="submit" value="Delete" class="btn danger" />
			<a href="#" class="btn cancel">Cancel</a>
		</form>
	</div>
</div>
