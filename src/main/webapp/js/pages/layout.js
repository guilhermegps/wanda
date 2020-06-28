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
			let ativo = (!isBlank(menu.url) && menu.url==window.location.pathname) ? true : false;
			if(menu.codMenuPai!=null){
				if(isBlank(itensMenu[menu.codMenuPai])){
					itensMenu[menu.codMenuPai] = $(`
						<li class="nav-item">
							<a class="nav-link collapsed" href="#"
									data-toggle="collapse" data-target="#collapse-${menu.codMenuPai}"
									aria-expanded="true" aria-controls="collapse-${menu.codMenuPai}"> 
								<i class="fas fa-fw ${menu.iconeMenuPai}"></i> 
								<span>${menu.descMenuPai}</span>
							</a>
							<div id="collapse-${menu.codMenuPai}" class="collapse" aria-labelledby="heading-${menu.codMenuPai}"
								data-parent="#accordionSidebar">
								<div class="bg-white py-2 collapse-inner rounded">
								</div>
							</div>
						</li>
					`);
				}
				itensMenu[menu.codMenuPai].children('div.collapse').children('div.collapse-inner').append(`
					<a class="collapse-item ${ativo ? 'active' : ''}" href="${menu.url}">${menu.descricao}</a>
				`);
				if(ativo){
					itensMenu[menu.codMenuPai].addClass('active');
					itensMenu[menu.codMenuPai].children('a.nav-link').removeClass('collapsed');
					itensMenu[menu.codMenuPai].children('div.collapse').addClass('show');
				}
			} else if(!isBlank(menu.url)){
				itensMenu[menu.codigo] = $(`
					<hr class="sidebar-divider">
					<li class="nav-item ${ativo ? 'active' : ''}">
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