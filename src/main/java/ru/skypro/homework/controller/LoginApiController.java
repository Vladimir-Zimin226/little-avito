package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Parameter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.LoginDto;
import ru.skypro.homework.service.AuthService;

import javax.validation.Valid;

@RestController
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
public class LoginApiController{

    private final AuthService authService;

    public LoginApiController(AuthService authService) {
        this.authService = authService;
    }



    @PostMapping(
            value = "/login",
            consumes = {"application/json"}
    )
  
    public ResponseEntity<Void> login(
            @Parameter(name = "LoginDto") @Valid @RequestBody(required = false) LoginDto loginDto
    ) {
        if (authService.login(loginDto.getUsername(), loginDto.getPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }


    }
}
