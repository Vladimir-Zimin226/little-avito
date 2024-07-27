package ru.skypro.homework.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

import javax.validation.Valid;
import java.util.List;

public interface AdsApi {

    @Operation(
            operationId = "addAd",
            summary = "Добавление объявления",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = AdDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PostMapping(
            value = "",
            produces = {"application/json"},
            consumes = {"multipart/form-data"}
    )
    ResponseEntity<AdDto> addAd(
            @Parameter(name = "properties", description = "", required = true) @Valid @RequestParam(value = "properties", required = true) CreateOrUpdateAdDto properties,
            @Parameter(name = "image", description = "", required = true) @RequestPart(value = "image", required = true) MultipartFile image
    );

    @Operation(
            operationId = "getAds",
            summary = "Получение информации об объявлении",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ExtendedAdDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @GetMapping(
            value = "/{id}",
            produces = {"application/json"}
    )
    ResponseEntity<ExtendedAdDto> getAds(
            @Parameter(name = "id", description = "", required = true) @PathVariable("id") Integer id
    );

    @Operation(
            operationId = "getAdsMe",
            summary = "Получение объявлений авторизованного пользователя",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = AdsDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @GetMapping(
            value = "/me",
            produces = {"application/json"}
    )
    ResponseEntity<AdsDto> getAdsMe();

    @Operation(
            operationId = "getAllAds",
            summary = "Получение всех объявлений",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = AdsDto.class))
                    })
            }
    )
    @GetMapping(
            value = "",
            produces = {"application/json"}
    )
    ResponseEntity<AdsDto> getAllAds();

    @Operation(
            operationId = "removeAd",
            summary = "Удаление объявления",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @DeleteMapping(
            value = "/{id}"
    )
    ResponseEntity<Void> removeAd(
            @Parameter(name = "id", description = "", required = true) @PathVariable("id") Integer id
    );

    @Operation(
            operationId = "updateAds",
            summary = "Обновление информации об объявлении",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = AdDto.class))
                    }),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @PatchMapping(
            value = "/{id}",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    ResponseEntity<AdDto> updateAds(
            @Parameter(name = "id", description = "", required = true) @PathVariable("id") Integer id,
            @Parameter(name = "CreateOrUpdateAdDto", description = "") @Valid @RequestBody(required = false) CreateOrUpdateAdDto createOrUpdateAdDto
    );

    @Operation(
            operationId = "updateImage",
            summary = "Обновление картинки объявления",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/octet-stream", schema = @Schema(implementation = byte[].class))
                    }),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @PatchMapping(
            value = "/{id}/image",
            produces = {"application/octet-stream"},
            consumes = {"multipart/form-data"}
    )
    ResponseEntity<List<byte[]>> updateImage(
            @Parameter(name = "id", description = "", required = true) @PathVariable("id") Integer id,
            @Parameter(name = "image", required = true) @RequestPart(value = "image", required = true) MultipartFile image
    );
}
