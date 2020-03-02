/*package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception{
		builder
		.inMemoryAuthentication()
		.withUser("lucas").password("{noop}123").roles("USER");
		
	} 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			
				//.antMatchers("/livros").permitAll()//.hasAnyRole("USER")
				//.antMatchers("/livros/{codigo}").permitAll()//hasAnyRole("USER")
				//.antMatchers("/livros/{codigo}/comentarios").permitAll()
				//.antMatchers("/autores/{id}").permitAll()//hasAnyRole("USER")
				//.antMatchers("/autores").permitAll()//hasAnyRole("USER")
				//.antMatchers("/swagger-ui.html").permitAll()
				.anyRequest()
				.authenticated();
		
				//.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				//.logoutSuccessUrl("/logout-success").permitAll();
			
		}
		
	
}
*/