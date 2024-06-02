package com.marketplace.service;

import com.marketplace.dto.UserDto;
import com.marketplace.model.User;
import com.marketplace.repository.UsersRepository;
import com.marketplace.service.exception.AccountAlreadyRegisteredException;
import com.marketplace.service.exception.AccountNotRegisteredException;
import com.marketplace.service.exception.InvalidEmailOrPasswordException;
import com.marketplace.service.exception.InvalidUsernameException;
import com.marketplace.util.ModelConverter;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;
    private final ModelConverter modelConverter;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository, ModelConverter modelConverter) {
        this.usersRepository = usersRepository;
        this.modelConverter = modelConverter;
    }

    @Override
    public UserDto getUser(String email) {
        return modelConverter.convert(usersRepository.findByEmail(email));
    }

    @Override
    public UserDto checkUserDataValidation(UserDto userDto) {
        User user = usersRepository.findByEmail(userDto.getEmail());
        if (user == null) {
            throw new AccountNotRegisteredException();
        } else if (!user.getPassword().equals(userDto.getPassword())) {
            throw new InvalidEmailOrPasswordException();
        }
        return modelConverter.convert(user);
    }

    @Override
    public void addUser(UserDto userDto) {
        User user = usersRepository.findByEmail(userDto.getEmail());
        if (user != null) {
            throw new AccountAlreadyRegisteredException();
        }

        Pattern pattern = Pattern.compile("^\\w{8,20}$");
        Matcher matcher = pattern.matcher(userDto.getName());
        if (!matcher.matches()) {
            throw new InvalidUsernameException();
        }

        try {
            InternetAddress emailAddr = new InternetAddress(userDto.getEmail());
            emailAddr.validate();
        } catch (AddressException ex) {
            throw new InvalidEmailOrPasswordException();
        }

        usersRepository.save(modelConverter.convert(userDto));
    }
}
