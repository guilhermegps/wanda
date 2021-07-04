package br.com.projeto.wanda.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;


/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Slf4j
@Controller
public class ErrorCustomController implements ErrorController {

	@Override
	public String getErrorPath() {
		return "/error";
	}

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "/error/404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "/error/500";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "/error/403";
            }
            
            log.error(String.format("STATUS ERRO REQUISIÇÃO: %d", statusCode));
        }

        return "/error/error";
    }
}
