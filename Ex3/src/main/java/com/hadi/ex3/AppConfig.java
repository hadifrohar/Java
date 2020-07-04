package com.hadi.ex3;

import com.hadi.ex3.filters.AuthInterceptor;
import com.hadi.ex3.filters.MethodInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**Configuration class*/
@Configuration
public class AppConfig implements WebMvcConfigurer {
    /**
     * Creates AuthInterceptor (so we auto wire the session data in it)
     * @return AuthInterceptor
     */
    @Bean
    AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }


    /**
     * Adds interceptors: method interceptor and authorization interceptor
     * @param registry interceptor registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MethodInterceptor()).addPathPatterns("/**").order(1);
        registry.addInterceptor(authInterceptor()).addPathPatterns("/**").order(2);

    }
}
