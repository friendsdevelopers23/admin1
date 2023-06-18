package com.calsoft.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configurable
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private CustomAuthenticationEntryPoint authenticationEntryPoint;
	
	
	// This method is for overriding some configuration of the WebSecurity
	// If you want to ignore some request or request patterns then you can
	// specify that inside this method
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers(
				"/api/**",
				"/api1/**",
				"/api/auth/user/login/**",
				"/api/auth/user/logout/**",
				"/api/auth/forgetpassword/**",
				"/api/ig/**", 
				"/api/verify/captcha/**",
				"/api/catalog/**",
				"/api/currency/**",
				"/api/core/download/**",
				"/api/auth/customer/username/**",
				"/api/auth/refresh/**",
				"/api/auth/site/user/password/**",
				"/api/auth/verify/**",
				"/api/auth/reverify/**",
				"/api/auth/password/verifyToken/**",
				"/api/auth/changePassword/**",
				"/api/customer/authorize/**",
				"/api/newslettersubscriber",
				"/api/purchase/**",
				"/api/manager/**",
				"/api/account/indexbyDomainName/**"); 
	}

	// This method is used for override HttpSecurity of the web Application.
	// We can specify our authorization criteria inside this method.
	@Override
	protected void configure(HttpSecurity http) {
		try {
			http
					// starts authorizing configurations
					.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
					// authenticate all remaining URLS
					.anyRequest().fullyAuthenticated().and()
					// adding JWT filter
					.addFilterBefore(new WebFilter(), UsernamePasswordAuthenticationFilter.class)
					// enabling the basic authentication
					.httpBasic().authenticationEntryPoint(authenticationEntryPoint).and()
					// configuring the session as state less. Which means there is
					// no session in the server
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
					// disabling the CSRF - Cross Site Request Forgery
					.csrf().disable();
		} catch (Exception e) {
			log.error("Error occured at..", e);
		}
	}

}
