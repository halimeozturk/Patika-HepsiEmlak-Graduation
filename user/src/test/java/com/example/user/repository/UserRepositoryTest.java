package com.example.user.repository;


import com.example.user.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmail(){
        Optional<User> user = userRepository.findByEmail("ozturkk.halimee@gmail.com");
        assertNotNull(user);
    }

    @Test
    void existsById(){
        boolean user = userRepository.existsById(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        Assertions.assertTrue(user);
    }

    @Test
    void findById(){
        Optional<User> user = userRepository.findById(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        assertNotNull(user);
    }
}
