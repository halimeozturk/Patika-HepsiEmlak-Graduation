package com.example.purchase.mapper;


import com.example.purchase.dto.UserDTO;
import com.example.purchase.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDTO> {
}
