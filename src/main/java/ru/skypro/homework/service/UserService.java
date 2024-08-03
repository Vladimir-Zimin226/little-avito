package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.entity.User;

import java.util.Optional;

public interface UserService {
    void updatePassword(NewPasswordDto newPassword, String username);

    UpdateUserDto updateInformationAboutUser(UpdateUserDto updateUser, String username);

    boolean userExists(String username);

    void createUser(User user);

    Optional<User> findUser(String username);
}
