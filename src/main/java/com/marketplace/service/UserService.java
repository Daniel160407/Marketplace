package com.marketplace.service;

import com.marketplace.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto getUser(String email);
    UserDto checkUserDataValidation(UserDto userDto);

    void addUser(UserDto userDto);
}
