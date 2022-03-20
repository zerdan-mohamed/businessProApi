package com.soft.business.config;

import org.springframework.context.annotation.Configuration;
import javax.servlet.ServletContext;

@Configuration
public class WebApplicationInitializer implements org.springframework.web.WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) {
        servletContext.setInitParameter(
                "spring.config.activate.on-profile", "prod");
    }
}
