$(function() {
	$("a").tooltip();
	$(".alert").alert().hide().fadeIn("fast");
	$(".sortable").tablesorter();

	// Forms
	$("form.main :input:not(:hidden, :submit):first").focus();
	
	// Really delete the entity in question
	$("#reallyDeleteButton").click(function() {
		$(this).closest("form").submit();
	});
});
