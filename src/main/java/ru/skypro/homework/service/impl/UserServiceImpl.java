package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
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
import ru.skypro.homework.exceptions.UserNotFoundException;
import ru.skypro.homework.exceptions.WrongCurrentPasswordException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final ImageService imageService;

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

    @Transactional
    @Override
    public void updateUserImage(MultipartFile file, Authentication authentication){
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(UserNotFoundException::new);
        //log.info("User found: {}", user);
        try {
            Image image = imageService.downloadImage(file);
            user.setUserPhoto(image);
            //log.info("Image downloaded and set for user: {}", user.getId());
            userRepository.save(user);
            //log.info("User updated successfully: {}", user.getId());
        } catch (IOException e) {
            //log.error("Error occurred while updating user image", e);
            throw new RuntimeException("Failed to update user image", e);
        }

    }

    @Override
    public UserDto getAuthorizedUserDto(Authentication authentication) {
        //log.info("Getting authorized-user information: {}", authentication.getName());
        return userMapper.toUserDto(userRepository.findByEmail(authentication.getName()).orElseThrow(UserNotFoundException::new));
    }

    @Transactional
    @Override
    public byte[] getUserImage(Integer userId) throws IOException {
        //log.info("Request to getting image");
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (user.getUserPhoto() != null) {
            return user.getUserPhoto().getData();
        } else {
            File emptyAvatar = new File("src/main/resources/defaultPhoto/6700.jpg");
            return Files.readAllBytes(emptyAvatar.toPath());
        }
    }
}
