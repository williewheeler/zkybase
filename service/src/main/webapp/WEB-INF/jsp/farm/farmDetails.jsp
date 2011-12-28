<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="row">
	<div class="span12">
		<div class="section first">
			<div class="grid">
				<div class="gridBody">
					<div class="row">
						<div class="span3 gridLabel">Name:</div>
						<div class="span8">
							<c:out value="${farm.name}" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
