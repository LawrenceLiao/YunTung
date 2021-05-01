package com.yuntung.services;

import com.yuntung.dtos.UserDto;
import com.yuntung.entities.UserEntity;
import com.yuntung.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean isEmailExisted(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public void addUser(UserDto userPostDto) {
        UserEntity newUser = UserEntity.builder()
                .email(userPostDto.getEmail())
                .username(userPostDto.getUsername())
                .password(userPostDto.getPassword())
                .build();

        userRepository.add(newUser);
    }

    public UserDto login(String email, String password) {
        UserEntity foundUser = userRepository.findByEmail(email);

        if(foundUser == null) {
            return null;
        }

        if(password.equals(foundUser.getPassword())) {
            UserDto userLoginDto = UserDto.builder()
                    .email(email)
                    .username(foundUser.getUsername())
                    .build();
            return userLoginDto;
        }
        return null;
    }
}