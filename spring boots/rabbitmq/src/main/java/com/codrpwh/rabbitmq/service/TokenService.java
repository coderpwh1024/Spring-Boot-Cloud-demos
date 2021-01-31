package com.codrpwh.rabbitmq.service;



import com.codrpwh.rabbitmq.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {

    ServerResponse createToken();

    void checkToken(HttpServletRequest request);

}
