package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import ru.skypro.homework.dto.LoginDto;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;

import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    @Mapping(source = "id", target = "pk")
    @Mapping(target = "image", expression = "java(imageMapper(user))")
    UserDto toUserDto(User user);

    @Mapping(source = "pk", target = "id", ignore = true)
    @Mapping(source = "roleDto", target = "role", defaultValue = "USER")
    @Mapping(target = "userPhoto.id", expression = "java(pathToImage(userDto))")
    User fromUserDto(UserDto dto);

    @Mapping(source = "username", target = "email")
    User fromRegisterDto(RegisterDto registerDto);

    @Mapping(source = "username", target = "email")
    User fromLoginDto(LoginDto loginDto);

    @Mapping(target = "id", ignore = true)
    void updateUserFromDto(UpdateUserDto updateUserDto, @MappingTarget User user);

    UpdateUserDto toUpdateUserDto(User user);

    User createUserFromDto(UpdateUserDto updateUserDto);

    default String imageMapper(User user){
        return "/users/"+ user.getId() + "/image";
    }

    default Integer pathToImage(UserDto userDto) {
        String imagePath = userDto.getImage();
        if (imagePath == null || imagePath.isEmpty()) {
            return null;
        }
        Image image = new Image();
        try {
            Integer id = extractIdFromPath(imagePath);
            image.setId(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid image path: " + imagePath, e);
        }
        return image.getId();
    }

    private Integer extractIdFromPath(String path) {
        try {
            String[] parts = path.split("/");
            return Integer.valueOf(parts[2]); // Получаем id из пути
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid path format: " + path, e);
        }
    }

    default String map(byte[] image) {
        return image != null ? new String(image) : null;
    }
    default byte[] map(String image) {
        return image != null ? image.getBytes() : null;
    }
}
