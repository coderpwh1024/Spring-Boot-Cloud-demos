package com.coderpwh.servicezuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.protocol.RequestContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MyFilter extends ZuulFilter {

    private static  Logger log = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx =   RequestContext.getCurrentContext();
        HttpServletRequest request  = ctx.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");
          if(accessToken==null){
              log.warn("token is empty");
              ctx.setSendZuulResponse(false);
              ctx.setResponseStatusCode(401);

              try {
                  ctx.getResponse().getWriter().write("token is empty");
              } catch (IOException e) {
                  e.printStackTrace();
              }
              return  null;
          }
          log.info("ok");
        return null;
    }


}
