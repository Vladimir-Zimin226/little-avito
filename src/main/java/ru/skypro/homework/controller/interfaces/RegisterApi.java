package ru.skypro.homework.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.RegisterDto;

import javax.validation.Valid;

public interface RegisterApi {

    @Operation(
            operationId = "register",
            summary = "Регистрация пользователя",
            tags = {"Регистрация"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created"),
                    @ApiResponse(responseCode = "400", description = "Bad Request")
            }
    )
    @PostMapping(
            value = "/register",
            consumes = {"application/json"}
    )
    ResponseEntity<Void> register(
            @Parameter(name = "RegisterDto", description = "") @Valid @RequestBody(required = false) RegisterDto registerDto);
}
