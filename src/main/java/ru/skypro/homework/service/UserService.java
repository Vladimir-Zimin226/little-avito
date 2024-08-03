package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;

public interface UserService {
    void updatePassword(NewPasswordDto newPassword, String username);

    UpdateUserDto updateInformationAboutUser(UpdateUserDto updateUser, String username);

}
