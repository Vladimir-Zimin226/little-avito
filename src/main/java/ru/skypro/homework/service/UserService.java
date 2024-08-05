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
    void updatePassword(NewPasswordDto newPassword, String username);

    UpdateUserDto updateInformationAboutUser(UpdateUserDto updateUser, String username);

    boolean userExists(String username);

    void createUser(User user);

    Optional<User> findUser(String username);

    void updateUserImage(MultipartFile file, Authentication authentication) throws IOException;

    UserDto getAuthorizedUserDto(Authentication authentication);

    byte[] getUserImage(Integer userId) throws IOException;
}
