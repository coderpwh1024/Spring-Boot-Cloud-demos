package com.coderpwh.nacos.service;

import com.coderpwh.nacos.model.User;
import org.springframework.stereotype.Service;

/**
 * @author hexu.hxy
 * @date 2019/1/7
 */

@Service
public interface UserService {

    User findById(Long id);

}