package br.com.projeto.wanda.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class AuthorizationFilter implements Filter {

	private FilterConfig config = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		config = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		req.getRequestURI();

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		config = null;
	}

	private void invalidateRequest(ServletResponse res) throws IOException {
		HttpServletResponse response = (HttpServletResponse) res;
		response.setStatus(401);
		response.sendRedirect(config.getInitParameter("unauthorized_page"));
	}

}
