package com.mikeba.pma.security;

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

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("select username, password, enabled " +
						" from user_accounts where username = ?")
			.authoritiesByUsernameQuery("select username, role " +
					" from user_accounts where username = ?")
			.passwordEncoder(bCryptEncoder)
			//.withUser("myuser")
			//.password("pass")
			//.roles("USER")
			//.and()
			//.withUser("manager")
			//.password("pass3")
			//.roles("ADMIN")
			;
	}
	
	@Bean
	PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/**
		http
		.csrf().ignoringAntMatchers("/app-api/**") // need this to avoid 405 Metod Not Allowed error on POST
		.and()
		.authorizeRequests()
		.antMatchers("/projects/new").hasRole("ADMIN")
		.antMatchers("/projects/save").hasRole("ADMIN")
		.antMatchers("/employees/new").hasRole("ADMIN")
		.antMatchers("/employees/save").hasRole("ADMIN")
		.antMatchers("/employees/update").permitAll()
		.antMatchers("/register/save").permitAll()
		.antMatchers("/","/**").permitAll()
		.and().formLogin();		
		**/
		
		 
		  http
		.csrf().ignoringAntMatchers("/app-api/**") // need this to avoid 405 Metod Not Allowed error on POST
		.and()
		.authorizeRequests()
		.antMatchers("/projects/new").hasRole("ADMIN")
		.antMatchers("/projects/save").hasRole("ADMIN")
		.antMatchers("/employees/new").permitAll()
		.antMatchers("/employees/save").permitAll()
		.antMatchers("/employees/update").permitAll()
		.antMatchers("/register/save").permitAll()
		.antMatchers("/","/**").permitAll()
		.and().formLogin();		
		 
		
		
	}
	
}
