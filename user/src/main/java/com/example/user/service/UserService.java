package com.example.user.service;



import com.example.user.dto.UserDTO;
import com.example.user.exception.GenericServiceException;
import com.example.user.mapper.UserMapper;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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

    public UserDTO getUserById(UUID id){
        return userMapper.toDTO(userRepository.findById(id).
                orElseThrow(() -> new GenericServiceException(GenericServiceException.NOT_FOUND,"User not found")));
    }

    public UserDTO getUserByEmail(String email){
        return userMapper.toDTO(userRepository.findByEmail(email).orElseThrow(() -> new GenericServiceException(GenericServiceException.NOT_FOUND,"User Email not found")));
    }

    public boolean existsUser(UUID id){
        return userRepository.existsById(id);
    }

    @Transactional
    public UserDTO update(UserDTO userDTO) {
        getUserById(userDTO.getId());
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(userDTO)));
    }

}
