package com.example.advert.service;


import com.example.advert.dto.UserDTO;
import com.example.advert.exception.GenericServiceException;
import com.example.advert.mapper.UserMapper;
import com.example.advert.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public List<UserDTO> getAllList() {
        return userMapper.toDTOList(userRepository.findAll());
    }

    @Transactional
    public UserDTO create(UserDTO userDTO) {
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(userDTO)));
    }

    public UserDTO getUserById(Long id){
        return userMapper.toDTO(userRepository.findById(id).
                orElseThrow(() -> new GenericServiceException(GenericServiceException.NOT_FOUND,"User not found")));
    }

    @Transactional
    public UserDTO update(UserDTO userDTO) {
        getUserById(userDTO.getId());
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(userDTO)));
    }

}
