package guru.springframework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import guru.springframework.domain.User;
import guru.springframework.services.UserService;
import guru.springframework.services.security.EncryptionService;

@Component("customAuthenticationProvider")
//public class CustomAuthenticationProvider  extends DaoAuthenticationProvider  implements AuthenticationProvider  {
public class CustomAuthenticationProvider  implements AuthenticationProvider  {
	 @Autowired
	    private UserService userService;
	 @Autowired
	 private EncryptionService encryptionService;
	 @Autowired
	    private Converter<User, UserDetails> userUserDetailsConverter;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
		UserDetails user = loadUserByUsername(token.getName());
		
		/*if(!user.getPassword().equalsIgnoreCase(encryptionService.encryptString(token.getCredentials().toString()))){
			throw new BadCredentialsException("The Password entered is not correct.");
		}*/
		
		if(!encryptionService.checkPassword(token.getCredentials().toString(),user.getPassword())){
			throw new BadCredentialsException("The Password entered is not correct.");
		}
		
		
		
		return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}
	
	 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        return userUserDetailsConverter.convert(userService.findByUsername(username));
	    }

}
