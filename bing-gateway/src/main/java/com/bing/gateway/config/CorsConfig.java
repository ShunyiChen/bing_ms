package com.bing.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * 跨域配置
 *
 * @author ruoyi
 */
@Configuration
public class CorsConfig
{
    /**
     * 这里为支持的请求头，如果有自定义的header字段请自己添加
     */
    private static final String ALLOWED_HEADERS = "X-Requested-With, Content-Type, Authorization, credential, Admin-Token";
    private static final String ALLOWED_METHODS = "GET,POST";
    private static final String ALLOWED_ORIGIN = "cbsapp-dev.ey.com.cn";
    private static final String ALLOWED_EXPOSE = "*";
    private static final String MAX_AGE = "18000L";

    @Bean
    public WebFilter corsFilter()
    {
        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            ServerHttpRequest request = ctx.getRequest();
            if (CorsUtils.isCorsRequest(request))
            {
                ServerHttpResponse response = ctx.getResponse();
                HttpHeaders headers = response.getHeaders();
                headers.add("Access-Control-Allow-Headers", ALLOWED_HEADERS);
                headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS);
                headers.add("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
//                headers.add("Access-Control-Expose-Headers", ALLOWED_EXPOSE);
                headers.add("Access-Control-Max-Age", MAX_AGE);
                headers.add("Access-Control-Allow-Credentials", "true");
                headers.add("X-Frame-Options", "DENY");
                headers.add("Cache-Control", "no-cache,no-store,max-age=0");
//                headers.add("Content-Security-Policy","script-src 'self' *.example.cn; style-src 'self' http://*;");
                headers.add("X-Content-Type-Options", "nosniff");
                headers.add("X-XSS-Protection","1; mode=block");
                headers.add("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
                headers.add("Connection","close");
//                headers.add("Content-Type", "application/json;charset=UTF-8");
                if (request.getMethod() == HttpMethod.OPTIONS)
                {
                    response.setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                }
            }
            return chain.filter(ctx);
        };
    }
}