package ru.skypro.homework.dto;


import lombok.Data;

@Data
public class UserDto {

    private Integer pk;

    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private String image;

    private RoleDto roleDto;

}

