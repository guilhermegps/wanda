(function($) {
	"use strict"; // Start of use strict

	$("#loadLayout").load("/component/layout", ()=>{
		$("#loadPageContent").load("/component/index");
	});

})(jQuery); // End of use strict