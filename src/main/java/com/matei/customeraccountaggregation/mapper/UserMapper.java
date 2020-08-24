package com.matei.customeraccountaggregation.mapper;

import com.matei.customeraccountaggregation.dto.UserDTO;
import com.matei.customeraccountaggregation.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User mapToEntity(UserDTO userDTO);
    UserDTO mapToDto(User user);
}
