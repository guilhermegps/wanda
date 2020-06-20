(function($) {
	"use strict"; // Start of use strict

	let headers = new Object();
	headers[$.cookie("XSRF-HEADERNAME")] = $.cookie("XSRF-TOKEN");
	$("#btnLogout").click(()=>{
		$.ajax({
			url: "/logout",
			method: "POST",
			headers: headers
		}).done(function(success) {
			window.location.replace("/");
		}).fail(function(error) {
		    alert( error.responseJSON.message );
		});
	});

})(jQuery); // End of use strict