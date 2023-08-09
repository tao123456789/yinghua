package com.yinghua.gateway.config.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Order(-1) //过滤器的权重
@Configuration
public class AuthorizeFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter (ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        // 2.获取authorization参数
        String auth=request.getHeaders().getFirst("token");
        System.out.println("-------------------------------------------------------");
        System.out.println("授权码："+auth);
        System.out.println("请求接口："+request.getPath());
        System.out.println("完整请求地址："+request.getURI());
        System.out.println("请求方式："+request.getMethod());
        System.out.println();
        // 3.校验
        if (request.getPath().toString().equals("/yinghua-auth/login") | request.getPath().toString().equals("/register")) {
            // 放行
            System.out.println("login接口，已放行！！！");
            System.out.println("-------------------------------------------------------");
            return chain.filter(exchange);
        }else{
            if(auth.isEmpty()){
                // 4.拦截
                System.out.println("token为空，已拦截！！！");
                System.out.println("-------------------------------------------------------");
                // 4.1.禁止访问，设置状态码
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            }else{
                // 放行
                System.out.println("Authorization："+auth);
                System.out.println("已放行！！！");
                System.out.println("-------------------------------------------------------");
                return chain.filter(exchange);
            }
        }
        // 4.2.结束处理
        return exchange.getResponse().setComplete();
    }
}
