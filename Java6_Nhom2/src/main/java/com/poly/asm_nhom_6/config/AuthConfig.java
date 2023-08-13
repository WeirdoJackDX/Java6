package com.poly.asm_nhom_6.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().cors().disable();

		http.authorizeRequests().antMatchers("C", "/rest/authorities/**").hasAnyRole("ADMIN", "USER").anyRequest()
				.permitAll();

		http.exceptionHandling().accessDeniedPage("/auth/access/denied");

		http.httpBasic();

		http.formLogin().loginPage("/auth/login/form").loginProcessingUrl("/auth/login")
				.defaultSuccessUrl("/auth/login/success", false).failureUrl("/auth/login/error")
				.usernameParameter("username").passwordParameter("password");
		http.rememberMe().rememberMeParameter("remember");

		http.logout().logoutUrl("/auth/logout").logoutSuccessUrl("/auth/logout/success");

		http.oauth2Login().loginPage("/auth/login/").defaultSuccessUrl("/oauth2/login/success", true)
				.failureUrl("/auth/login/error").authorizationEndpoint().baseUri("/oauth2/authorization");
	}
}

