package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.config.OurSecurityDetailsService;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

/**
 * Реализация сервиса аутентификации и регистрации пользователей.
 * <p>
 * Содержит методы для входа пользователя в систему и регистрации нового пользователя.
 * </p>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final OurSecurityDetailsService ourSecurityDetailsService;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Выполняет вход пользователя в систему, проверяя соответствие предоставленных данных.
     *
     * @param userName имя пользователя.
     * @param password пароль пользователя.
     * @return {@code true}, если пароль соответствует сохраненному паролю пользователя; {@code false} в противном случае.
     */
    @Override
    public boolean login(String userName, String password) {
        UserDetails userDetails = ourSecurityDetailsService.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    /**
     * Регистрирует нового пользователя в системе.
     * <p>
     * Проверяет наличие пользователя с таким же email в базе данных. Если пользователь уже существует, регистрация не выполняется.
     * </p>
     *
     * @param registerDto данные для регистрации пользователя.
     * @return {@code true}, если регистрация прошла успешно; {@code false}, если пользователь с таким email уже существует.
     */
    @Override
    public boolean register(RegisterDto registerDto) {
        if (userRepository.findUserByEmailIgnoreCase(registerDto.getUsername()).isPresent()) {
            return false;
        }
        User registerUser = userMapper.fromRegisterDto(registerDto);
        registerUser.setPassword(encoder.encode(registerUser.getPassword()));
        userRepository.save(registerUser);
        return true;
    }
}
