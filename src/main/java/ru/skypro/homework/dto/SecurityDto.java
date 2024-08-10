package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.entity.Role;

@Data
public class SecurityDto {

    private Integer id;

    private String email;

    private String password;

    private RoleDto roleDto;

}
