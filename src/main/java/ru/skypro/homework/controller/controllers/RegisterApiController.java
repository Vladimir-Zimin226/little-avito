package ru.skypro.homework.controller.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.controller.interfaces.RegisterApi;
import ru.skypro.homework.dto.RegisterDto;

import javax.validation.Valid;

@RestController
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
public class RegisterApiController implements RegisterApi {

    @PostMapping(
            value = "/register",
            consumes = {"application/json"}
    )
    public ResponseEntity<Void> register(
            @Parameter(name = "RegisterDto", description = "") @Valid @RequestBody(required = false) RegisterDto registerDto
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }
}
