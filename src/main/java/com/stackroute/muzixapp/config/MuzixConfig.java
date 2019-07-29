package com.stackroute.muzixapp.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MuzixConfig {

    @Bean
    ServletRegistrationBean h2ServletRegistration(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new WebServlet());
    servletRegistrationBean.addUrlMappings("/console/*");
        return servletRegistrationBean;
    }
}
