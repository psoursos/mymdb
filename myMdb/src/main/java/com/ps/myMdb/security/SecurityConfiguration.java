package com.ps.myMdb.security;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// In memory authentication
		// auth.inMemoryAuthentication()
		auth.jdbcAuthentication()
		.usersByUsernameQuery("select username, password,confirmed from users where username = ?")
		.authoritiesByUsernameQuery("select username, role from users where username = ?")
		.dataSource(dataSource)
		.passwordEncoder(bCryptEncoder);
	}
	
//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.authorizeRequests()
		 	.antMatchers("/resources/**").permitAll()
		 	.antMatchers("/resourses/templates/security/**").permitAll()
		 	.antMatchers("**/admin/**").hasRole("ADMIN")
		 	.antMatchers("/","/**").permitAll()
	   .anyRequest()
	   .authenticated() //Any request to the application must be authenticated (logged in)
	   .and()//Return again the HttpSecurity Object to add more rules
       .formLogin()//We will customize the login process
		 .loginPage("/login")//The login form will be found in this url
         //.permitAll()//Allow everyone to see login page. Users don't have to be logged in
		.defaultSuccessUrl("/welcome",true) .permitAll()
         .and()
         .logout()
        .permitAll()
         .and()
         .exceptionHandling();
         //.accessDeniedPage("/access-denied");
		 //.permitAll();
		//.and()
		// .logout()
		//.permitAll();
		
		  http.csrf().disable();	
		//http.headers().frameOptions().disable();
	}

private AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
	// TODO Auto-generated method stub
	return null;
}
}
