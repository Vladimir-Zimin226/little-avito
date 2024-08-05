package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;


import java.io.IOException;
import java.util.Optional;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")

@RequiredArgsConstructor
public class UsersApiController {


    private final UserService userService;

    public UsersApiController(UserService userService) {
        this.userService = userService;
    }


    @Operation(
            operationId = "getUser",
            summary = "Получение информации об авторизованном пользователе",
            tags = {"Пользователи"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/me",
            produces = {"application/json"}

    )
    public ResponseEntity<UserDto> getUser(Authentication authentication) {
        return ResponseEntity.ok(userService.getAuthorizedUserDto(authentication));

    }


    @Operation(
            operationId = "setPassword",
            summary = "Обновление пароля",
            tags = {"Пользователи"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,

            value = "/set_password",
            consumes = {"application/json"}
    )

    public ResponseEntity<NewPasswordDto> setPassword(@Valid @RequestBody NewPasswordDto dto, Authentication authentication) {
        userService.updatePassword(dto, authentication.getName());
        return ResponseEntity.ok().build();
    }

    @Operation(
            operationId = "updateUserDto",
            summary = "Обновление информации об авторизованном пользователе",
            tags = {"Пользователи"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateUserDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @RequestMapping(
            method = RequestMethod.PATCH,

            value = "/me",
            produces = {"application/json"},
            consumes = {"application/json"}
    )

    public ResponseEntity<UserDto> updateUser(
            @Parameter(name = "UpdateUserDto") @Valid @RequestBody(required = false) UpdateUserDto updateUserDto, Authentication authentication) {
        return ResponseEntity.ok(userService.updateUserDto(updateUserDto, authentication));

    }

    @Operation(
            operationId = "updateUserImage",
            summary = "Обновление аватара авторизованного пользователя",
            tags = {"Пользователи"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @RequestMapping(
            method = RequestMethod.PATCH,
            value = "/me/image",
            consumes = {"multipart/form-data"}

    )
    public ResponseEntity<Void> updateUserImage(
            @Parameter(name = "image",required = true) @RequestPart(value = "image", required = true) MultipartFile image, Authentication authentication) throws IOException {
        userService.updateUserImage(image, authentication);
        return ResponseEntity.ok().build();
    }

    @GetMapping(
            value = "/{id}/image",
            produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id) throws IOException {
        log.info("Get user image with id" + id);
        return ResponseEntity.ok(userService.getUserImage(id));
    }
}
