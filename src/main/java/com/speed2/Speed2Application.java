package com.speed2;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Speed2Application extends SpringBootServletInitializer implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        CacheControl cc = CacheControl.maxAge(Duration.ofHours(24)).cachePublic().sMaxAge(Duration.ofDays(30));
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/assets/").setCacheControl(cc);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/contact").setViewName("contact");
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/gallery").setViewName("gallery");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/testimonial").setViewName("testimonial");
    }

    public static void main(String[] args) {
        SpringApplication.run(Speed2Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Speed2Application.class);
    }
}
