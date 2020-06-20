(function($) {
	"use strict"; // Start of use strict
	
	$("#envia").click(()=>{
		let headers = new Object();
		headers[$.cookie("XSRF-HEADERNAME")] = $.cookie("XSRF-TOKEN");
		$.ajax({
			url: "/logar",
			method: "POST",
			headers: headers,
			data: deleteBlank(convertFormToObj($('#formLogin')))
		}).done(function(data, textStatus, jqXHR) {
			if(data.falhou){
				swal.fire("Falha", "Dados de autenticação inválidos", "warning");
			} else{
				cleanCookies();
			}
		}).fail(function(data, textStatus, jqXHR) {
			if(data!=null){
				if(data.status == HttpStatus.FORBIDDEN
						|| data.status == HttpStatus.NOT_FOUND){
					cleanCookies();
					return;
				}
			}
			swal.fire("Erro", "Houve uma falha ao tentar autenticar", "error");
		});
	});
	
	function cleanCookies(){
		$.removeCookie('XSRF-TOKEN', { path: '/' });
		$.removeCookie('XSRF-TOKEN', { path: '/logar' });
		
		window.location.replace("/");
	}
})(jQuery); // End of use strict