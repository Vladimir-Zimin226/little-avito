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
import ru.skypro.homework.dto.RegisterDto;

import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-07-21T12:05:35.370405200+05:00[Asia/Tashkent]")
@Controller
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("${openapi.aPIDocumentation.base-path:}")
public class RegisterApiController implements RegisterApi {

    private final NativeWebRequest request;

    @Autowired
    public RegisterApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Operation(
            operationId = "register",
            summary = "Регистрация пользователя",
            tags = {"Регистрация"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created"),
                    @ApiResponse(responseCode = "400", description = "Bad Request")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/register",
            consumes = {"application/json"}
    )
    @Override
    public ResponseEntity<Void> register(RegisterDto registerDto) {
        return RegisterApi.super.register(registerDto);
    }
}
