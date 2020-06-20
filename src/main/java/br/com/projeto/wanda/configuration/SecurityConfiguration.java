package br.com.projeto.wanda.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import br.com.projeto.wanda.services.CustomUserDetailService;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	@Autowired
	private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;
	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		AffirmativeBased affirmativeBased = new AffirmativeBased(Arrays.asList(new RoleVoter(),new WebExpressionVoter()));
		http.authorizeRequests().accessDecisionManager(affirmativeBased)
			.anyRequest().authenticated()
			.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
			    public <O extends FilterSecurityInterceptor> O postProcess(
			            O fsi) {
			    	fsi.setSecurityMetadataSource(dynamicSecurityMetadataSource);
			        return fsi;
			    }
			})
			.and()
			.formLogin()
				.loginPage("/logar")
				.permitAll()
				.failureUrl("/logar/falha")
				.permitAll()
			.and()
				.logout()
					.logoutSuccessUrl("/logout")
	                .invalidateHttpSession(true)
	                .clearAuthentication(true)
	                .logoutSuccessHandler(logoutSuccessHandler)
					.permitAll()
			.and()
			.rememberMe()
				.userDetailsService(customUserDetailService);
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
	        .antMatchers("/css/**", "/js/**", "/images/**","/vendor/**","/fonts/**","/img/**", "/favicon.ico");
    }
}
