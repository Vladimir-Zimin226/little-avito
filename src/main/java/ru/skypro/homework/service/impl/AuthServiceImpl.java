package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;

import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserService userService;

    public AuthServiceImpl(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, UserMapper userMapper, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @Override public boolean login(String userName, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        return Objects.nonNull(userDetails) && passwordEncoder.matches(password, userDetails.getPassword()); }

    @Override
    public boolean register(RegisterDto register) {
        if (userService.userExists(register.getUsername())) {
            return false;
        }
        User user = userMapper.fromRegisterDto(register);
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        userService.createUser(user);
        return true;
    }
}
