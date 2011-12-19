<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>




<div id="viewport">
	<div class="scrollPane">
		<div id="section1" class="sub">
			<jsp:include page="farmTemplates.jsp" />
		</div>
		<div id="section2" class="sub">
			<jsp:include page="farmTemplateForm.jsp" />
		</div>
	</div>
</div>
<div style="clear:both"></div>

<script type="text/javascript">
	$(function() {
		
		// The default axis is 'y', but in this demo, I want to scroll both. You can modify any default like this.
		// FIXME There is an interaction between the hscroll and the Top vscroll.
		$.localScroll.defaults.axis = 'xy';
		
		// Scroll initially if there's a hash (#something) in the url 
// 		$.localScroll.hash({
// 			target: '#viewport', // Could be a selector or a jQuery object too.
// 			queue: true,
// 			duration: 800
// 		});
		
		/**
		 * NOTE: I use $.localScroll instead of $('#navigation').localScroll() so I
		 * also affect the >> and << links. I want every link in the page to scroll.
		 */
		$.localScroll({
			target: '#viewport', // could be a selector or a jQuery object too.
			queue: true,
			duration: 800,
			hash: true,
			onBefore:function( e, anchor, $target ){
				// The 'this' is the settings object, can be modified
			},
			onAfter:function( anchor, settings ){
				// The 'this' contains the scrolled element (#viewport)
			}
		});
	});
</script>