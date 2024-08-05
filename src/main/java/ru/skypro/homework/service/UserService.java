package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

import java.io.IOException;
import java.util.Optional;


public interface UserService {

    UserDto getAuthorizedUserDto(Authentication authentication);

    void newPasswordDto(NewPasswordDto newPasswordDto, Authentication authentication);

    UserDto updateUserDto(UpdateUserDto updateUserDto, Authentication authentication);

    void updateUserImage(MultipartFile file, Authentication authentication) throws IOException;

    byte[] getUserImage(Integer userId) throws IOException;


    boolean userExists(String username);

    void createUser(User user);

    Optional<User> findUser(String username);

    void updateUserImage(MultipartFile file, Authentication authentication) throws IOException;

    UserDto getAuthorizedUserDto(Authentication authentication);

    byte[] getUserImage(Integer userId) throws IOException;
}
