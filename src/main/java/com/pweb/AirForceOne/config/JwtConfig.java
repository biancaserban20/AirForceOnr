package com.pweb.AirForceOne.config;


import com.pweb.AirForceOne.config.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JwtConfig {
    private final JwtFilter jwtFilter;

    @Bean
    FilterRegistrationBean<JwtFilter> jwtPatternFilter() {
        var filterBean = new FilterRegistrationBean<JwtFilter>();
        filterBean.setFilter(jwtFilter);
        filterBean.addUrlPatterns(
                "/users/*"
        );
        return filterBean;
    }
}
