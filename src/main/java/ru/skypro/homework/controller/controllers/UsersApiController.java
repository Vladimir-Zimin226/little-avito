package ru.skypro.homework.controller.controllers;


import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.controller.interfaces.UsersApi;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;


import javax.validation.Valid;

@RestController
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
public class UsersApiController implements UsersApi {


    @GetMapping(
            value = "/me",
            produces = { "application/json" }
    )
    public ResponseEntity<UserDto> getUser() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @PostMapping(
            value = "/set_password",
            consumes = { "application/json" }
    )
    public ResponseEntity<Void> setPassword(
            @Parameter(name = "NewPasswordDto", description = "") @Valid @RequestBody(required = false) NewPasswordDto newPasswordDto
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @PatchMapping(
            value = "/me",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public ResponseEntity<UpdateUserDto> updateUser(
            @Parameter(name = "UpdateUserDto", description = "") @Valid @RequestBody(required = false) UpdateUserDto updateUserDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @PatchMapping(
            value = "/me/image",
            consumes = { "multipart/form-data" }
    )
    public ResponseEntity<Void> updateUserImage(
            @Parameter(name = "image", description = "", required = true) @RequestPart(value = "image", required = true) MultipartFile image
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }
}
