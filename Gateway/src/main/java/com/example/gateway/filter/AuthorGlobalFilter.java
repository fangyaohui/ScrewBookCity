package com.example.gateway.filter;

import com.example.gateway.fegin.UserServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author fangyaohui
 * @version 0.0.3
 * @description AuthorGlobalFilter
 * @since 2024/3/17 20:35
 */

@Slf4j
@Component
public class AuthorGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    private UserServiceClient userServiceClient;

    /**
     * 全局过滤器 在这里会进行过滤所有没有进行登录的请求，如果登录了则放行，否则不放行
     * 在这里会调用userservice服务中的验证token是否合法接口，通过feign进行调用
     * 如果token有效 则进行放行，在放行之前重新给该token刷新过期时间
     * @param exchange exchange
     * @param chain chain
     * @return 是否放行
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().toString();
        log.info(path + "...");


        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        log.info(route.getId().toString());

        if(isNotFilterPath(path)){
            log.info("login or register 放行...");
            return chain.filter(exchange);
        }else{
            log.info("身份验证环节...");

            String token = request.getHeaders().getFirst("Authorization");

            boolean flag = userServiceClient.verifyToken(token);
            if(flag){
                log.info("身份验证成功...");
                return chain.filter(exchange);
            }else{
                // 拦截
                // 禁止访问，设置状态码
                log.info("身份验证失败...");
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                // 结束处理
                return exchange.getResponse().setComplete();
            }
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 该函数判断当前路径是否需要直接放行
     * 这里都是不需要登录就可以访问
     * @param path 当前路径
     * @return 是否直接放行
     */
    public boolean isNotFilterPath(String path){
        String[] paths = {
                "/login",
                "/register",
                "/static",
                "/sign",

        } ;
        for(String p : paths){
            if(p.equals(path)){
                return true;
            }
        }
        return false;
    }
}