package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.config.OurSecurityDetailsService;
import ru.skypro.homework.dto.RegisterDto;

import ru.skypro.homework.entity.Role;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;

import ru.skypro.homework.service.AuthService;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {


    private final OurSecurityDetailsService ourSecurityDetailsService;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public boolean login(String userName, String password) {
        UserDetails userDetails = ourSecurityDetailsService.loadUserByUsername(userName);

        return encoder.matches(password, userDetails.getPassword());
    }

    @Override

    public boolean register(RegisterDto registerDto) {
        if (userRepository.findUserByEmailIgnoreCase(registerDto.getUsername()).isPresent()) {
            return false;
        }
        User registerUser = userMapper.fromRegisterDto(registerDto);
        registerUser.setEmail(registerDto.getUsername());
        registerUser.setRole(Role.USER);
        registerUser.setPassword(encoder.encode(registerUser.getPassword()));
        userRepository.save(registerUser);

        return true;
    }
}
