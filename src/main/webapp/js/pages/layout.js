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
		});
	});

	$.ajax({
		url: "/usuario/dados",
		method: "GET"
	}).done(function(data, textStatus, jqXHR) {
		if(data!=null){
			$('#nomeUsuarioLogado').html(data.nome);
			montarSidebar(data.listaMenus);
		}
	}).fail(function(data, textStatus, jqXHR) {
	});

	function montarSidebar(lista){
		let itensMenu = {};
		$.each(lista, function(index, menu) {
			if(menu.codMenuPai!=null){
				if(isBlank(itensMenu[menu.codMenuPai])){
					itensMenu[menu.codMenuPai] = $(`
						<li class="nav-item">
							<a class="nav-link collapsed" href="#"
									data-toggle="collapse" data-target="#collapseTwo"
									aria-expanded="true" aria-controls="collapseTwo"> 
								<i class="fas fa-fw ${menu.iconeMenuPai}"></i> 
								<span>${menu.descMenuPai}</span>
							</a>
							<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
								data-parent="#accordionSidebar">
								<div class="bg-white py-2 collapse-inner rounded">
								</div>
							</div>
						</li>
					`);
				}
				itensMenu[menu.codMenuPai].children('div.collapse').children('div.collapse-inner').append(`
					<a class="collapse-item" href="${menu.url}">${menu.descricao}</a>
				`);
			} else if(!isBlank(menu.url)){
				itensMenu[menu.codigo] = $(`
					<hr class="sidebar-divider">
					<li class="nav-item active">
						<a class="nav-link" href="${menu.url}"> 
							<i class="fas fa-fw ${menu.icone}"></i>
							<span>${menu.descricao}</span>
						</a>
					</li>
				`);
			}
		});
		
		$.each(itensMenu, function(index, item) {
			$('#sidebarBody').append(item);
		});
	}
})(jQuery); // End of use strict