(function($) {
	"use strict"; // Start of use strict

	$("#loadLayout").load("/component/layout", ()=>{
		$("#loadPageContent").load("/tables/content");
	});

})(jQuery); // End of use strict