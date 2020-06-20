package br.com.projeto.wanda.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tables")
public class TableController {

	@GetMapping
	public String tables(){
		return "/page/tables";
	}

	@GetMapping("/content")
	public String css() {
		return "/page/component/content_tables";
	}
}
