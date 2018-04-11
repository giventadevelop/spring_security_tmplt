package guru.springframework.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/")
    String index(){
        return "index";
    }
    
    @RequestMapping("/session")
    String session(HttpServletRequest request,HttpServletResponse response){
    	SecurityContextHolder.clearContext();
    	HttpSession session= request.getSession(false);
    	Cookie[] cookies = request.getCookies();
    	 
        // Delete all the cookies
        if (cookies != null) {
     
            for (int i = 0; i < cookies.length; i++) {
     
                Cookie cookie = cookies[i];
                cookies[i].setValue(null);
                cookies[i].setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    	
        SecurityContextHolder.clearContext();
        if(session != null) {
            session.invalidate();
        }
        return "session";
    }
}
