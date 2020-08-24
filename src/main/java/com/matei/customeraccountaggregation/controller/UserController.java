package com.matei.customeraccountaggregation.controller;

import com.matei.customeraccountaggregation.dto.UserDTO;
import com.matei.customeraccountaggregation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserDTO createUser(@Valid @RequestBody UserDTO userDto) {
        return userService.createUser(userDto);
    }

    @GetMapping
    @RequestMapping("/{user_id}")
    public UserDTO getUser(@PathVariable("user_id") Long userId) {
        return userService.getUser(userId);
    }
}
