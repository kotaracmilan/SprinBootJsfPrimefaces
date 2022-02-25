package com.dajo.proj.config;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WelcomePageRedirect implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/")
        .setViewName("forward:/index.xhtml");
    registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

}
