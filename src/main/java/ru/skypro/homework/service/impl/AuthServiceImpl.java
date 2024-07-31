package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.config.OurSecutiryDetailsService;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.entity.Role;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final OurSecutiryDetailsService ourSecurityDeatailsService;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public AuthServiceImpl(OurSecutiryDetailsService ourSecurityDeatailsService, PasswordEncoder passwordEncoder, UserRepository userRepository, UserMapper userMapper) {
        this.ourSecurityDeatailsService = ourSecurityDeatailsService;
        this.encoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public boolean login(String userName, String password) {
        UserDetails userDetails = ourSecurityDeatailsService.loadUserByUsername(userName);
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
