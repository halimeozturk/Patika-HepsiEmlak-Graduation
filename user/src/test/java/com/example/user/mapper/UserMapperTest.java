package com.example.user.mapper;


import com.example.user.dto.UserDTO;
import com.example.user.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserMapperTest {

    @InjectMocks
    private UserMapperImpl userMapper;
    private List<User> prepareMockUserList() {
        List<User> users = new ArrayList<>();
        users.add(prepareMockUser());
        return users;
    }

    private User prepareMockUser() {
        User user = new User();
        user.setId(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        user.setFirstName("Halime");
        user.setLastName("Öztürk");
        user.setEmail("ozturkk.halimee@gmail.com");
        user.setCreationDate(ZonedDateTime.now());
        user.setModificationDate(ZonedDateTime.now());
        return user;
    }

    private List<UserDTO> prepareMockUserDTOList() {
        List<UserDTO> userDTOS = new ArrayList<>();
        userDTOS.add(prepareMockUserDTO());
        return userDTOS;
    }

    private UserDTO prepareMockUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        userDTO.setFirstName("Halime");
        userDTO.setLastName("Öztürk");
        userDTO.setEmail("ozturkk.halimee@gmail.com");
        userDTO.setCreationDate(ZonedDateTime.now());
        userDTO.setModificationDate(ZonedDateTime.now());
        return userDTO;
    }

    @Test
    void toEntity(){
        User user = userMapper.toEntity(prepareMockUserDTO());
        assertNotNull(user);
    }

    @Test
    void toDTO(){
        UserDTO userDTO = userMapper.toDTO(prepareMockUser());
        assertNotNull(userDTO);
    }

    @Test
    void toEntityList(){
        List<User> users = userMapper.toEntityList(prepareMockUserDTOList());
        assertNotNull(users);
    }

    @Test
    void toDTOList(){
        List<UserDTO> userDTOS = userMapper.toDTOList(prepareMockUserList());
        assertNotNull(userDTOS);
    }
}
