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

import javax.validation.Valid;
import java.io.IOException;

@RestController
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
public class UsersApiController{


    private final UserService userService;

    public UsersApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(
            value = "/me",
            produces = { "application/json" }
    )
    public ResponseEntity<UserDto> getUser(Authentication authentication) {
        return ResponseEntity.ok(userService.getAuthorizedUserDto(authentication));

    }



    @PostMapping(
            value = "/set_password",
            consumes = { "application/json" }
    )
    public ResponseEntity<Void> setPassword(
            @Parameter(name = "NewPasswordDto") @Valid @RequestBody(required = false) NewPasswordDto newPasswordDto, Authentication authentication) {
        userService.newPasswordDto(newPasswordDto,authentication);
        return ResponseEntity.ok().build();
    }


    @PatchMapping(

            value = "/me",
            produces = { "application/json" },
            consumes = { "application/json" }
    )

    public ResponseEntity<UserDto> updateUser(
            @Parameter(name = "UpdateUserDto") @Valid @RequestBody(required = false) UpdateUserDto updateUserDto, Authentication authentication) {
        return ResponseEntity.ok(userService.updateUserDto(updateUserDto, authentication));

    }


    @PatchMapping(
            value = "/me/image",
            consumes = { "multipart/form-data" }
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
