package ru.skypro.homework.controller.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;

import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-07-21T12:05:35.370405200+05:00[Asia/Tashkent]")
@Controller
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("${openapi.aPIDocumentation.base-path:}")
public class UsersApiController implements UsersApi {

    private final NativeWebRequest request;

    @Autowired
    public UsersApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Operation(
            operationId = "getUser",
            summary = "Получение информации об авторизованном пользователе",
            tags = { "Пользователи" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/users/me",
            produces = { "application/json" }
    )
    @Override
    public ResponseEntity<UserDto> getUser() {
        return UsersApi.super.getUser();
    }

    @Operation(
            operationId = "setPassword",
            summary = "Обновление пароля",
            tags = { "Пользователи" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/users/set_password",
            consumes = { "application/json" }
    )
    @Override
    public ResponseEntity<Void> setPassword(NewPasswordDto newPasswordDto) {
        return UsersApi.super.setPassword(newPasswordDto);
    }

    @Operation(
            operationId = "updateUserDto",
            summary = "Обновление информации об авторизованном пользователе",
            tags = { "Пользователи" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateUserDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @RequestMapping(
            method = RequestMethod.PATCH,
            value = "/users/me",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    @Override
    public ResponseEntity<UpdateUserDto> updateUser(UpdateUserDto updateUserDto) {
        return UsersApi.super.updateUser(updateUserDto);
    }

    @Operation(
            operationId = "updateUserImage",
            summary = "Обновление аватара авторизованного пользователя",
            tags = { "Пользователи" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @RequestMapping(
            method = RequestMethod.PATCH,
            value = "/users/me/image",
            consumes = { "multipart/form-data" }
    )
    @Override
    public ResponseEntity<Void> updateUserImage(MultipartFile image) {
        return UsersApi.super.updateUserImage(image);
    }
}
