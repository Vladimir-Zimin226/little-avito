package ru.skypro.homework.controller.controllers;


import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.controller.interfaces.LoginApi;
import ru.skypro.homework.dto.LoginDto;

import javax.validation.Valid;

@RestController
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
public class LoginApiController implements LoginApi {

    @PostMapping(
            value = "/login",
            consumes = {"application/json"}
    )
    public ResponseEntity<Void> login(
            @Parameter(name = "LoginDto", description = "") @Valid @RequestBody(required = false) LoginDto loginDto
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }
}
