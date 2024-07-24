package ru.skypro.homework.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.LoginDto;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Service
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "userPhoto.image", target = "image")
    UserDto userToUserDto(User user);


    @Mapping(source = "pk", target = "id", ignore = true)
    @Mapping(target = "role", defaultValue = "USER")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "image", target = "userPhoto.image")
    User userDtoToUser(UserDto dto);


    @Mapping(source = "username", target = "email")
    RegisterDto registerDtoToUser(User user);

    @Mapping(source = "email", target = "username")
    User loginDtoToUser(LoginDto loginDto);

    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "phone", target = "phone")
    User updateUserDtoToUser(UpdateUserDto updateUserDto);










}
