$(function() {
	$("a").twipsy({ delayIn: 1000 });
	$(".alert-message").hide().slideDown();
	$("form.main :input:not(:hidden, :submit):first").focus();
});
