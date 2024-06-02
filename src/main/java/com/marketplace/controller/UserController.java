package com.marketplace.controller;

import com.marketplace.dto.UserDto;
import com.marketplace.service.UserService;
import com.marketplace.service.exception.AccountAlreadyRegisteredException;
import com.marketplace.service.exception.AccountNotRegisteredException;
import com.marketplace.service.exception.InvalidEmailOrPasswordException;
import com.marketplace.service.exception.InvalidUsernameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/marketplace/login")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserDto> getUser(@RequestParam String email) {
        return ResponseEntity.ok().body(userService.getUser(email));
    }

    @PostMapping
    public ResponseEntity<UserDto> login(@RequestBody UserDto userDto) {
        try {
            return ResponseEntity.ok().body(userService.checkUserDataValidation(userDto));
        } catch (InvalidEmailOrPasswordException | AccountNotRegisteredException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserDto userDto) {
        try {
            userService.addUser(userDto);
            return ResponseEntity.ok().build();
        } catch (AccountAlreadyRegisteredException | InvalidUsernameException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.OPTIONS).build();
    }
}
