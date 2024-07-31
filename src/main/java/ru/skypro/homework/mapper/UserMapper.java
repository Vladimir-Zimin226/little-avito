package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "userPhoto.id", target = "image")
    UserDto toUserDto(User user);

    @Mapping(source = "pk", target = "id", ignore = true)
    @Mapping(source = "roleDto", target = "role", defaultValue = "USER")
    @Mapping(source = "image", target = "userPhoto.id")
    User fromUserDto(UserDto dto);

    @Mapping(source = "username", target = "email")
    User fromRegisterDto(RegisterDto registerDto);

    @Mapping(source = "username", target = "email")
    User fromLoginDto(LoginDto loginDto);

    User createUserFromDto(UpdateUserDto updateUserDto);

    SecurityDto toSecurityDto(User user);

    default String map(byte[] image) {
        return image != null ? new String(image) : null;
    }

    default byte[] map(String image) {
        return image != null ? image.getBytes() : null;
    }
}
