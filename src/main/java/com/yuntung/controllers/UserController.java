package com.yuntung.controllers;

import com.yuntung.dtos.UserDto;
import com.yuntung.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity register(@RequestBody UserDto userDto) {

        if (userService.isEmailExisted(userDto.getEmail())) {
            return new ResponseEntity("The email already exists!", HttpStatus.CONFLICT);
        }
        userService.addUser(userDto);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDto userDto) {
        UserDto returnedDto = userService.login(userDto.getEmail(),userDto.getPassword());
        if (userDto == null) {
            return new ResponseEntity("Email or password is invalid", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(returnedDto, HttpStatus.OK);
    }


}

