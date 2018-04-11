package guru.springframework.config;

import org.jasypt.springsecurity3.authentication.encoding.PasswordEncoder;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SpringSecConfig extends WebSecurityConfigurerAdapter {
	
	 @Autowired
	 private CustomAuthenticationProvider customAuthenticationProvider;
	 @Autowired
	 private UserDetailsService userDetailsService;

    private AuthenticationProvider authenticationProvider;

    @Autowired
    @Qualifier("daoAuthenticationProvider")
    public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(StrongPasswordEncryptor passwordEncryptor){
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        passwordEncoder.setPasswordEncryptor(passwordEncryptor);
        return passwordEncoder;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(PasswordEncoder passwordEncoder,
                                                               UserDetailsService userDetailsService){

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Autowired
    public void configureAuthManager(AuthenticationManagerBuilder authenticationManagerBuilder,PasswordEncoder passwordEncoder){
      //  authenticationManagerBuilder.authenticationProvider(authenticationProvider);
    	/*customAuthenticationProvider.setPasswordEncoder(passwordEncoder);
    	customAuthenticationProvider.setUserDetailsService(userDetailsService);*/
    	authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider);
    	
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
    	//set access to all pages including session time out page where session time out is set in the application.properties page
           httpSecurity
                .authorizeRequests().antMatchers("/","/products","/product/show/*","/session","/console/*","/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().permitAll();
           //delete cookies so it won't get forwarded to session out page
           httpSecurity.logout().deleteCookies("auth_code", "JSESSIONID").invalidateHttpSession(true);                          

        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
       httpSecurity.sessionManagement().invalidSessionUrl("/session");
    }
    
    


}