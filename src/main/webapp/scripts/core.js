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
	
	// Tabs and pills
	$(".tabs").tabs();
	$(".pills").pills();
});
