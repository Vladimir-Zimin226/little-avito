package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;
    private final ImageService imageService;


    @Override
    public UserDto getAuthorizedUserDto(Authentication authentication) {
        log.info("Getting authorized-user information: {}", authentication.getName());
        return mapper.toUserDto(userRepository.findUserByEmailIgnoreCase(authentication.getName()).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public void newPasswordDto(NewPasswordDto newPasswordDto, Authentication authentication) {
        log.info("Updating password for user: {}", authentication.getName());
        User user = userRepository.findUserByEmailIgnoreCase(authentication.getName()).orElseThrow(UserNotFoundException::new);
        user.setPassword(encoder.encode(newPasswordDto.getNewPassword()));
        userRepository.save(user);
        mapper.toUserDto(user);
    }

    @Override
    public UserDto updateUserDto(UpdateUserDto updateUserDto, Authentication authentication) {
        log.info("Updating user information for user:{}", authentication.getName());
        log.info("Request to update user");
        User user = userRepository.findUserByEmailIgnoreCase(authentication.getName()).orElseThrow(UserNotFoundException::new);
        user.setFirstName(updateUserDto.getFirstName());
        user.setLastName(updateUserDto.getLastName());
        user.setPhone(updateUserDto.getPhone());
        userRepository.save(user);
        return mapper.toUserDto(user);
    }


    @Transactional
    @Override
    public void updateUserImage(MultipartFile file, Authentication authentication){
        User user = userRepository.findUserByEmailIgnoreCase(authentication.getName())
                .orElseThrow(UserNotFoundException::new);

        log.info("User found: {}", user);

        try {
            Image image = imageService.downloadImage(file);
            user.setUserPhoto(image);
            log.info("Image downloaded and set for user: {}", user.getId());
            userRepository.save(user);
            log.info("User updated successfully: {}", user.getId());
        } catch (IOException e) {
            log.error("Error occurred while updating user image", e);
            throw new RuntimeException("Failed to update user image", e);
        }

    }

}
