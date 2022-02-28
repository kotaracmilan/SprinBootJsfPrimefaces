package com.dajo.proj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dajo.proj.services.UserService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserService userService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		//.requiresChannel().anyRequest().requiresSecure().and()
		.authorizeRequests()
		.antMatchers( "/index**", "/register**", "/login**", "/javax.faces.resource/**", "/rest**").permitAll()
		//.anyRequest().authenticated()
		.antMatchers("/protected/**", "/api/**", "/secured/**").authenticated()

		.and()

		.cors()
		.and()
		
		.headers().disable()

		
		.sessionManagement()
		.maximumSessions(1)
		.and()
		.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
		.and()
		
		//.formLogin()
		//.loginPage("/login.xhtml")
		//.failureUrl("/login.xhtml?error=true")
		//.successHandler(successHandler())
		//.loginProcessingUrl("/login_processing")
		//.defaultSuccessUrl("/area/dashboard.xhtml")
		//.usernameParameter("uname")
		//.passwordParameter("pwd")
		//.and()//treba i succeshandler i failurhadler
		
		.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/index.xhtml")
		.permitAll()
		.deleteCookies("JSESSIONID").invalidateHttpSession(true)
		.and()
		
		.rememberMe().rememberMeParameter("Y$laTym#87ll2wq16bxy$")
		.key("rememberme")
		.tokenValiditySeconds(1209600)
		.and()
		
		.exceptionHandling().accessDeniedPage("/access.xhtml")
		.and()
		
		.httpBasic()
		;
	}

    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setUserDetailsService(userService);
		dao.setPasswordEncoder(passwordEncoder());
		return dao;
	}
    
    

}
