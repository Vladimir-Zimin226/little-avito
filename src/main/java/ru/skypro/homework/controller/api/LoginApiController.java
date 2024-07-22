package ru.skypro.homework.controller.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;
import ru.skypro.homework.dto.LoginDto;

import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-07-21T12:05:35.370405200+05:00[Asia/Tashkent]")
@Controller
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("${openapi.aPIDocumentation.base-path:}")
public class LoginApiController implements LoginApi {

    private final NativeWebRequest request;

    @Autowired
    public LoginApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
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
    @Override
    public ResponseEntity<Void> login(LoginDto loginDto) {
        return LoginApi.super.login(loginDto);
    }
}
