package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.exceptions.WrongCurrentPasswordException;
import ru.skypro.homework.service.UserService;

import java.util.Optional;
import javax.validation.Valid;

@RestController
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersApiController{

    private final NativeWebRequest request;

    private final UserService userService;

    public Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
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
    public ResponseEntity<UserDto> getUser() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"firstName\", \"lastName\" : \"lastName\", \"image\" : \"image\", \"role\" : \"USER\", \"phone\" : \"phone\", \"id\" : 0, \"email\" : \"email\" }";
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    @Operation(
            operationId = "setPassword",
            summary = "Обновление пароля",
            tags = { "Пользователи" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/set_password",
            consumes = { "application/json" }
    )
    public ResponseEntity<NewPasswordDto> setPassword(@Valid @RequestBody NewPasswordDto dto, Authentication authentication) {
        try {
            userService.updatePassword(dto, authentication.getName());
        } catch (WrongCurrentPasswordException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(dto);
        }
        return ResponseEntity.ok(dto);
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
            value = "/me",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public ResponseEntity<UpdateUserDto> updateUser(@Valid @RequestBody UpdateUserDto dto, Authentication authentication) {
        return ResponseEntity.ok(userService.updateInformationAboutUser(dto, authentication.getName()));
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
    public ResponseEntity<Void> updateUserImage(
            @Parameter(name = "image", description = "", required = true) @RequestPart(value = "image", required = true) MultipartFile image
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }
}
