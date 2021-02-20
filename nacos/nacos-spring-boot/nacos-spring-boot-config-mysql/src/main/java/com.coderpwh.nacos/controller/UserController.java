package com.coderpwh.nacos.controller;


import com.coderpwh.nacos.model.User;
import com.coderpwh.nacos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public User get(@RequestParam int id) {
        return userService.findById(id);
    }
}