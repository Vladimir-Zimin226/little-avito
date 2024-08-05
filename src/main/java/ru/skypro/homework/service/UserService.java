package ru.skypro.homework.service;


import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;

import java.io.IOException;

public interface UserService {

    UserDto getAuthorizedUserDto(Authentication authentication);

    void newPasswordDto(NewPasswordDto newPasswordDto, Authentication authentication);

    UserDto updateUserDto(UpdateUserDto updateUserDto, Authentication authentication);

    void updateUserImage(MultipartFile file, Authentication authentication) throws IOException;

    byte[] getUserImage(Integer userId) throws IOException;


}
