package com.example.advert.mapper;


import com.example.advert.dto.UserDTO;
import com.example.advert.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDTO>{
}
