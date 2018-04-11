package guru.springframework.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@EnableWebMvc
/*@Configuration
@EnableAutoConfiguration*/
public class MvcConfig extends WebMvcConfigurerAdapter {

    public MvcConfig() {
        super();
    }

    // API

     @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor());
        registry.addInterceptor(new SessionTimerInterceptor());
    }
}