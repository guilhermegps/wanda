package br.com.projeto.wanda.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/component")
public class ComponentController {
	@GetMapping("/layout")
	public String sidebar() {
		return "/page/component/layout";
	}

	@GetMapping("/index")
	public String css() {
		return "/page/component/content_index";
	}
}
