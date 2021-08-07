package org.softcits.cn.config;

import org.softcits.cn.interceptor.AdminInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class AdminInterceptorConfig implements WebMvcConfigurer {

    @Bean
    public AdminInterceptor getAdminInterceptor(){
        return new AdminInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAdminInterceptor()).addPathPatterns("/admin/**");
    }
}
