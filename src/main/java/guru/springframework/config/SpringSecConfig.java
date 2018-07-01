package guru.springframework.config;

import javax.sql.DataSource;

import org.jasypt.springsecurity3.authentication.encoding.PasswordEncoder;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
public class SpringSecConfig extends WebSecurityConfigurerAdapter {
	
	 @Autowired
	 private CustomAuthenticationProvider customAuthenticationProvider;
	 
	 @Autowired
	 CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	 
	 @Autowired
	 private UserDetailsService userDetailsService;
	 
	 @Autowired
		CustomizeLogoutSuccessHandler customizeLogoutSuccessHandler;
	 
	 @Autowired
	 DataSource dataSource;

   /* private AuthenticationProvider authenticationProvider;

    @Autowired
    @Qualifier("daoAuthenticationProvider")
    public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

  

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(PasswordEncoder passwordEncoder,
                                                               UserDetailsService userDetailsService){

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }*/
	 
	  @Bean
	    public PasswordEncoder passwordEncoder(StrongPasswordEncryptor passwordEncryptor){
	        PasswordEncoder passwordEncoder = new PasswordEncoder();
	        passwordEncoder.setPasswordEncryptor(passwordEncryptor);
	        return passwordEncoder;
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
                .authorizeRequests().antMatchers("/","/products","/product/show/*","/session","/logout","/console/*","/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(new CustomAuthenticationSuccessHandler()).loginPage("/login").permitAll()
                .and()
               // .logout().logoutSuccessUrl("/login").permitAll().and()
                //.rememberMe().rememberMeParameter("remember-me").rememberMeCookieName("remember-me").tokenValiditySeconds(86400).alwaysRemember(true);
                //.useSecureCookie(true);
                .rememberMe().rememberMeParameter("remember-me").tokenValiditySeconds(86400).userDetailsService(userDetailsService).tokenRepository(tokenRepository());
           //delete cookies so it won't get forwarded to session out page
           //httpSecurity.logout().logoutSuccessUrl("/login?logout").deleteCookies("auth_code", "JSESSIONID").invalidateHttpSession(true); 
           //httpSecurity.logout().logoutSuccessUrl("/logout").deleteCookies("auth_code", "JSESSIONID").invalidateHttpSession(true);
           httpSecurity.logout().logoutSuccessUrl("/logout").deleteCookies("auth_code", "JSESSIONID").logoutSuccessHandler(customizeLogoutSuccessHandler).permitAll();
           
          // httpSecurity.logout().invalidateHttpSession(false);
          /* httpSecurity
           .rememberMe()
               .rememberMeCookieName("remember-me-cookie-name")
               .tokenValiditySeconds(86400);*/
         /*  httpSecurity
           .rememberMe().userDetailsService(userDetailsService)
               .tokenValiditySeconds(86400);*/
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
        httpSecurity.sessionManagement().invalidSessionUrl("/session");
    }
    
    @Bean
    public PersistentTokenRepository tokenRepository() {
    	
      JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl=new JdbcTokenRepositoryImpl();
      jdbcTokenRepositoryImpl.setDataSource(dataSource);
      return jdbcTokenRepositoryImpl;
    }
    
    /*  @Bean
    @Primary
    private DataSource dataSource() {
        return DataSourceBuilder
            .create()
            .username("sa")
            .password("")
            .url("jdbc:h2:mem:testdb")
            .driverClassName("org.h2.Driver")
            .build();
    }
*/

}