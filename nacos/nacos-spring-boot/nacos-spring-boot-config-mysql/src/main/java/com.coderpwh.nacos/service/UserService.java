package com.coderpwh.nacos.service;


import com.coderpwh.nacos.dao.UserRepository;
import com.coderpwh.nacos.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {this.userRepository = userRepository;}

    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
}