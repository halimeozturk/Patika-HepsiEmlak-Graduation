package com.example.user.controller;


import com.example.user.dto.UserDTO;
import com.example.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping
    ResponseEntity<List<UserDTO>> getAllList(){
        return ResponseEntity.ok(userService.getAllList());
    }

    @PostMapping
    ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.create(userDTO));
    }

//    @PutMapping
//    ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO){
//        return ResponseEntity.ok(userService.update(userDTO));
//    }

    @GetMapping("/{id}")
    ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/{email}")
    ResponseEntity<UserDTO> getUserById(@PathVariable String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }


    @GetMapping("/exists/{id}")
    boolean existsUser(@PathVariable Long id){
        return userService.existsUser(id);
    }
}
