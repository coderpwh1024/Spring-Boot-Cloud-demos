package com.coderpwh.gatewayservice.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

/**
 * @author coderpwh
 * @version V1.0
 * @date 2018/9/13 9:45
 */

@Component
public class LoggerFilter extends ZuulFilter {

    @Autowired
    Tracer tracer;

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 900;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        tracer.addTag("operator","forezp");
        System.out.print(tracer.getCurrentSpan().traceIdString());
        return null;
    }
}
