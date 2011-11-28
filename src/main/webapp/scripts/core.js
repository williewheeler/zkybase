$(function() {
	$("a").twipsy({ delayIn: 1000 });
	$("form.main :input:not(:hidden, :submit):first").focus();
	$(".alert-message").alert().hide().fadeIn("fast");
	
	// Modal dialogs
	$(".modal").modal({ backdrop: true, keyboard: true });
	$(".modalCancelButton").click(function() { $(this).closest(".modal").modal("hide"); });
});
