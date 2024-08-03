package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exceptions.UserNotFoundException;
import ru.skypro.homework.exceptions.WrongCurrentPasswordException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public void updatePassword(NewPasswordDto newPassword, String username) {
        User user = userRepository.findByEmail(username).orElseThrow(UserNotFoundException::new);
        if (passwordEncoder.matches(newPassword.getCurrentPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
            userRepository.save(user);
        } else {
            throw new WrongCurrentPasswordException();
        }
    }

    @Override
    public UpdateUserDto updateInformationAboutUser(UpdateUserDto updateUser, String username) {
        User user = userRepository.findByEmail(username).orElseThrow(UserNotFoundException::new);
        userMapper.updateUserFromDto(updateUser, user);
        User updatedUser = userRepository.save(user);
        return userMapper.toUpdateUserDto(updatedUser);
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.findByEmail(username)
                .isPresent();
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> findUser(String username) {
        return userRepository.findByEmail(username);
    }
}
