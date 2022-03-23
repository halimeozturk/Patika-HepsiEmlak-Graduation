package com.example.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import com.example.user.dto.UserDTO;
import com.example.user.mapper.UserMapper;
import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.ZonedDateTime;
import java.util.UUID;


@SpringBootTest
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @BeforeEach
    void setup() {
        Mockito
                .when(userRepository.findAll())
                .thenReturn(prepareMockUserList());
        Mockito
                .when(userMapper.toDTOList(any()))
                .thenReturn(prepareMockUserDTOList());
        Mockito
                .when(userMapper.toDTO(any()))
                .thenReturn(prepareMockUserDTO());
        Mockito
                .when(userMapper.toEntity(any())).
                thenReturn(prepareMockUser());

    }

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
    void getAllUserTest() {
        //when
        //given
        //verify
        List<UserDTO> allUser = userService.getAllList();
        assertNotNull(allUser);
        assertThat(allUser.size()).isNotZero();
    }

    @Test
    void create() {
        Mockito.when(userRepository.save(any())).thenReturn(prepareMockUser());
        UserDTO user = userService.create(prepareMockUserDTO());
        assertNotNull(user);
    }

    @Test
    void getUserById() {
        Mockito.when(userRepository.findById(any())).thenReturn(java.util.Optional.of(prepareMockUser()));
        UserDTO userDTO = userService.getUserById(any());
        assertNotNull(userDTO);
    }

    @Test
    void update() {
        Mockito.when(userRepository.save(any())).thenReturn(prepareMockUser());
        Mockito.when(userRepository.findById(any())).thenReturn(java.util.Optional.of(prepareMockUser()));
        UserDTO user = userService.update(prepareMockUserDTO());
        assertNotNull(user);
    }

}
