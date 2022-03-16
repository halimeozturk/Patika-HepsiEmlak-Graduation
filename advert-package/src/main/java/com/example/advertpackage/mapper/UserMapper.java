package com.example.advertpackage.mapper;


import com.example.advertpackage.dto.UserDTO;
import com.example.advertpackage.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDTO> {
}
