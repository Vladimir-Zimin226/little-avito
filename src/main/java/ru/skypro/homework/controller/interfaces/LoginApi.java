package ru.skypro.homework.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.LoginDto;

import javax.validation.Valid;

public interface LoginApi {

    @Operation(
            operationId = "login",
            summary = "Авторизация пользователя",
            tags = {"Авторизация"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PostMapping(
            value = "/login",
            consumes = {"application/json"}
    )
    ResponseEntity<Void> login(
            @Parameter(name = "LoginDto", description = "") @Valid @RequestBody(required = false) LoginDto loginDto
    );
}
