package ru.skypro.homework.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;

import javax.validation.Valid;

public interface UsersApi {

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
    @GetMapping(
            value = "/me",
            produces = { "application/json" }
    )
    ResponseEntity<UserDto> getUser();

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
    @PostMapping(
            value = "/set_password",
            consumes = { "application/json" }
    )
    ResponseEntity<Void> setPassword(
            @Parameter(name = "NewPasswordDto", description = "") @Valid NewPasswordDto newPasswordDto
    );


    @Operation(
            operationId = "updateUser",
            summary = "Обновление информации об авторизованном пользователе",
            tags = { "Пользователи" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateUserDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PatchMapping(
            value = "/me",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    ResponseEntity<UpdateUserDto> updateUser(
            @Parameter(name = "UpdateUserDto", description = "") @Valid UpdateUserDto updateUserDto
    );


    @Operation(
            operationId = "updateUserImage",
            summary = "Обновление аватара авторизованного пользователя",
            tags = { "Пользователи" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PatchMapping(
            value = "/me/image",
            consumes = { "multipart/form-data" }
    )
    ResponseEntity<Void> updateUserImage(
            @Parameter(name = "image", description = "", required = true) MultipartFile image
    );
}
