package com.exam.onlineexam.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.exam.onlineexam.model.RoleAssign;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	
	@Autowired
	UserDetailService userdetailservice;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("authentication call");
		auth.userDetailsService(userdetailservice);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http
		    .authorizeRequests()
		    .antMatchers("/adminpanel","/addQuestion","/updateQuestion/{id}","/deleteQuestion/{id}").hasRole("ADMIN")
			.antMatchers("/programmingExam","/aptitudeExam","/reasoningExam").hasAnyRole("USER","ADMIN")
			//.antMatchers().permitAll()
		    .antMatchers(
		    		"/javascript/**",
		    		"/registration",
		    		"/userRegistration",
		    		"/checkAnswer",
	                "/js/**",
	                "/css/**",
	                "/images/**",
	                "/fonts/**",
	                "/").permitAll()
		    
		    .anyRequest().authenticated()
		    .and()
		    .formLogin()
		    .loginPage("/login").permitAll()
		    .and()
		    .logout()
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login?logout")
			.permitAll();
	}
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}
