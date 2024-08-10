package ru.skypro.homework.controller;



import io.swagger.v3.oas.annotations.Parameter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.service.AuthService;

import javax.validation.Valid;

@RestController
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
public class RegisterApiController{

    private final AuthService authService;


    public RegisterApiController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping(
            value = "/register",
            consumes = {"application/json"}
    )
    public ResponseEntity<Void> register(
            @Parameter(name = "RegisterDto") @Valid @RequestBody(required = false) RegisterDto registerDto
    ) {
        if (authService.register(registerDto)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


    }
}
