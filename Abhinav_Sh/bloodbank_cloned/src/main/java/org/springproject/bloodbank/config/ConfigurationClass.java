package org.springproject.bloodbank.config;

import javafx.scene.chart.ScatterChart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import java.nio.channels.ScatteringByteChannel;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"org.springproject.bloodbank"})
public class ConfigurationClass implements WebMvcConfigurer {
    private static final Logger logger = LogManager.getLogger("vehicles");
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new  FIrstInterceptor()).order(1).addPathPatterns("/");
    }
    @Bean
    public ViewResolver getViewResolver() {
        UrlBasedViewResolver resolver = new ChainableUrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/views/homepage/");
        resolver.setSuffix(".jsp");
        resolver.setOrder(0);
        logger.info("configured the view resolver for the /WEB-INF/views/homepage/ with .jsp");
        return resolver;
    }
    @Bean
    public ViewResolver getViewResolver2() {
        UrlBasedViewResolver resolver = new ChainableUrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/views/agent_side/");
        resolver.setSuffix(".jsp");
        resolver.setOrder(1);
        logger.info("configured the view resolver for the /WEB-INF/views/agent_side/ with .jsp");
        return resolver;
    }

    @Bean
    public ViewResolver getViewResolver3() {
        UrlBasedViewResolver resolver = new ChainableUrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/views/admin_side/");
        resolver.setSuffix(".jsp");
        resolver.setOrder(2);
        logger.info("configured the view resolver for the /WEB-INF/views/admin_side/ with .jsp");
        return resolver;
    }

    @Bean
    public ViewResolver getViewResolver4() {
        UrlBasedViewResolver viewResolver = new ChainableUrlBasedViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/wrongauth/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setOrder(3);
        logger.info("configured the view resolver for the /WEB-INF/views/wrongauth/ with .jsp");
        return viewResolver;
    }

    @Bean
    public ViewResolver getViewResolver40() {
        UrlBasedViewResolver resolver = new ChainableUrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/views/user_side/");
        resolver.setSuffix(".jsp");
        resolver.setOrder(4);
        logger.info("configured the view resolver for the /WEB-INF/views/user_side/ with .jsp");
        return resolver;
    }

    @Bean
    @Order(5)
    public InternalResourceViewResolver getViewResolver5() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        logger.info("configured the internal resource view resolver for the /WEB-INF/views/ with .jsp");
        return viewResolver;
    }
}
