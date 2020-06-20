(function($) {
	"use strict"; // Start of use strict

	$("#btnRegistrar").click(()=>{
		let headers = new Object();
		headers[$.cookie("XSRF-HEADERNAME")] = $.cookie("XSRF-TOKEN");
		$.ajax({
			url: "/usuario/registrar",
			method: "POST",
			headers: headers,
			data: deleteBlank(convertFormToObj($('#formRegistroUsuario')))
		}).done(function(data, textStatus, jqXHR) {
			if(data.codigo == HttpStatus.SUCCESS){
				Swal.fire({
					title: 'Sucesso',
					text: "Usuário criado com sucesso",
					icon: 'success',
					confirmButtonColor: '#3085d6',
					confirmButtonText: 'Ok'
				}).then((result) => {
					window.location.replace("/");
				});
			} else{
				swal.fire("Falha", "Não foi possível registrar este usuário", "warning");
			}
		}).fail(function(data, textStatus, jqXHR) {
			swal.fire("Erro", "Houve uma falha ao tentar registrar o usuário", "error");
		});
	});
})(jQuery); // End of use strict