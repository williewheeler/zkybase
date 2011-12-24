$(function() {
	
	// Alerts
	$(".alert-message").alert().hide().fadeIn("fast");
	
	// Links
	$.localScroll();
	$("a").twipsy({ delayIn: 1000 });
	
	// Tables
	$(".sortable").tablesorter();
	
	// Forms
	$("form.main :input:not(:hidden, :submit):first").focus();
	
	// Modal dialogs
	$(".modal").modal({ backdrop: true, keyboard: true });
	$(".modal .btn.cancel").click(function() { $(this).closest(".modal").modal("hide"); });
	
	// Show the delete dialog
	$(function() {
		$("#deleteLink").click(function() {
			$("#deleteDialog").modal("show");
			return false;
		});
	});
	
	// Really delete the entity in question
	$("#reallyDeleteButton").click(function() {
		$(this).closest("form").submit();
	});
	
	// Tabs and pills
	$(".tabs").tabs();
	$(".pills").pills();
});
