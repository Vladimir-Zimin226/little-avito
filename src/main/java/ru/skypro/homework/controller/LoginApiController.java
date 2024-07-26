package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import ru.skypro.homework.dto.LoginDto;

import java.util.Optional;
import javax.annotation.Generated;
import javax.validation.Valid;

@RestController
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("${openapi.aPIDocumentation.base-path:}")
public class LoginApiController{

    private final NativeWebRequest request;

    @Autowired
    public LoginApiController(NativeWebRequest request) {
        this.request = request;
    }


    public Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @Operation(
            operationId = "login",
            summary = "Авторизация пользователя",
            tags = {"Авторизация"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/login",
            consumes = {"application/json"}
    )
    public ResponseEntity<Void> login(
            @Parameter(name = "LoginDto", description = "") @Valid @RequestBody(required = false) LoginDto loginDto
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }
}
