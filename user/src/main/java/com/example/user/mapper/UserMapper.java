package com.example.user.mapper;



import com.example.user.dto.UserDTO;
import com.example.user.model.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Named("toEntity")
    @Mapping(target = "creationDate", ignore = true)
    User toEntity(UserDTO dto);

    @Named("toDTO")
    UserDTO toDTO(User entity);

    @IterableMapping(qualifiedByName = "toEntity")
    List<User> toEntityList(List<UserDTO> dtoList);

    @IterableMapping(qualifiedByName = "toDTO")
    List<UserDTO> toDTOList(List<User> entityList);
}
