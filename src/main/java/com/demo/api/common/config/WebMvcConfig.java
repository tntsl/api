package com.demo.api.common.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Lye
 * 配置spring mvc
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 设置错误页面
     */
    private static class MyErrorPageRegistrar implements ErrorPageRegistrar {

        @Override
        public void registerErrorPages(ErrorPageRegistry registry) {
            registry.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400"));
            registry.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401"));
            registry.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/error/403"));
            registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
            registry.addErrorPages(new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/error/405"));
            registry.addErrorPages(new ErrorPage(HttpStatus.NOT_ACCEPTABLE, "/error/406"));
            registry.addErrorPages(new ErrorPage(HttpStatus.PROXY_AUTHENTICATION_REQUIRED, "/error/407"));
            registry.addErrorPages(new ErrorPage(HttpStatus.REQUEST_TIMEOUT, "/error/408"));
            registry.addErrorPages(new ErrorPage(HttpStatus.CONFLICT, "/error/409"));
            registry.addErrorPages(new ErrorPage(HttpStatus.GONE, "/error/410"));
            registry.addErrorPages(new ErrorPage(HttpStatus.LENGTH_REQUIRED, "/error/411"));
            registry.addErrorPages(new ErrorPage(HttpStatus.PRECONDITION_FAILED, "/error/412"));
            registry.addErrorPages(new ErrorPage(HttpStatus.PAYLOAD_TOO_LARGE, "/error/413"));
            registry.addErrorPages(new ErrorPage(HttpStatus.URI_TOO_LONG, "/error/414"));
            registry.addErrorPages(new ErrorPage(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "/error/415"));
            registry.addErrorPages(new ErrorPage(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE, "/error/416"));
            registry.addErrorPages(new ErrorPage(HttpStatus.EXPECTATION_FAILED, "/error/417"));
            registry.addErrorPages(new ErrorPage(HttpStatus.UNPROCESSABLE_ENTITY, "/error/422"));
            registry.addErrorPages(new ErrorPage(HttpStatus.LOCKED, "/error/423"));
            registry.addErrorPages(new ErrorPage(HttpStatus.FAILED_DEPENDENCY, "/error/424"));
            registry.addErrorPages(new ErrorPage(HttpStatus.UPGRADE_REQUIRED, "/error/426"));
            registry.addErrorPages(new ErrorPage(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS, "/error/451"));
            registry.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
            registry.addErrorPages(new ErrorPage(HttpStatus.NOT_IMPLEMENTED, "/error/501"));
            registry.addErrorPages(new ErrorPage(HttpStatus.BAD_GATEWAY, "/error/502"));
            registry.addErrorPages(new ErrorPage(HttpStatus.SERVICE_UNAVAILABLE, "/error/503"));
            registry.addErrorPages(new ErrorPage(HttpStatus.GATEWAY_TIMEOUT, "/error/504"));
            registry.addErrorPages(new ErrorPage(HttpStatus.HTTP_VERSION_NOT_SUPPORTED, "/error/505"));
            registry.addErrorPages(new ErrorPage(HttpStatus.VARIANT_ALSO_NEGOTIATES, "/error/506"));
            registry.addErrorPages(new ErrorPage(HttpStatus.INSUFFICIENT_STORAGE, "/error/507"));
            registry.addErrorPages(new ErrorPage(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED, "/error/509"));
            registry.addErrorPages(new ErrorPage(HttpStatus.NOT_EXTENDED, "/error/510"));
        }

    }

    /**
     * 配置cors跨域访问
     *
     * @return
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**");
            }
        };
    }

    /**
     * 注册错误页面
     *
     * @return
     */
    @Bean
    public ErrorPageRegistrar errorPageRegistrar() {
        return new MyErrorPageRegistrar();
    }

}
