package com.cag.cagbackendapi.controllers;

import com.cag.cagbackendapi.dtos.UserDto;
import com.cag.cagbackendapi.services.user.impl.UserService;
import com.cag.cagbackendapi.services.validation.impl.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {

    private final ValidationService validationService;
    private final UserService userService;

    @Autowired
    UserController(UserService userService, ValidationService validationService) {
        this.validationService = validationService;
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> registerUser(
            @RequestHeader("authKey") String authKey,
            @RequestBody UserDto userDto
    ) {
        this.validationService.validateAuthKey(authKey);
        this.userService.registerUser(userDto);

        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
}
