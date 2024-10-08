package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.entity.Role;

@Data
public class RegisterDto {

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String phone;

    private Role role;

}

