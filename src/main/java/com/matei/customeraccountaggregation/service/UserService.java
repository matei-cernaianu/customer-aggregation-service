package com.matei.customeraccountaggregation.service;

import com.matei.customeraccountaggregation.dto.UserDTO;
import com.matei.customeraccountaggregation.entity.User;
import com.matei.customeraccountaggregation.exception.UserNotFoundException;
import com.matei.customeraccountaggregation.mapper.UserMapper;
import com.matei.customeraccountaggregation.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDTO createUser(UserDTO userDto) {
        final User user = userRepository.save(userMapper.mapToEntity(userDto));
        return userMapper.mapToDto(user);
    }

    public UserDTO getUser(Long id) {
        return userMapper.mapToDto(userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new));
    }
}
