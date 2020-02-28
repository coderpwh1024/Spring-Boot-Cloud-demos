package com.coderpwh.gatewayfilter;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author coderpwh
 * @create 2020-02-28 15:17
 * @desc ${DESCRIPTION}
 **/
public class HostAddrKeyResolver implements KeyResolver {


    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }
}
