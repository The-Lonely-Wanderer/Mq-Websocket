package com.example.mq.MqConfig;


import com.example.mq.Interceport.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 重写请求拦截器
 */
@Configuration
public class InterceptConfig extends WebMvcConfigurationSupport {

    /**
     * 拦截器，添加拦截路径，和放行资源路径
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login.html","/static/**","/websocket/");
    }

    /**
     *  配置静态资源路径
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}
