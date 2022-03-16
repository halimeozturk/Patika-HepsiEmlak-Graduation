package com.example.user.mapper;



import com.example.user.dto.UserDTO;
import com.example.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDTO>{
}
