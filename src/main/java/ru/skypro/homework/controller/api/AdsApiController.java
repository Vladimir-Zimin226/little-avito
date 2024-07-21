package ru.skypro.homework.controller.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-07-21T12:05:35.370405200+05:00[Asia/Tashkent]")
@Controller
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("${openapi.aPIDocumentation.base-path:}")
public class AdsApiController implements AdsApi {

    private final NativeWebRequest request;

    @Autowired
    public AdsApiController(NativeWebRequest request) {
        this.request = request;
    }


    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

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
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/ads",
            produces = {"application/json"},
            consumes = {"multipart/form-data"}
    )
    @Override
    public ResponseEntity<AdDto> addAd(CreateOrUpdateAdDto properties, MultipartFile image) {
        return AdsApi.super.addAd(properties, image);
    }

    @Operation(
            operationId = "addComment",
            summary = "Добавление комментария к объявлению",
            tags = {"Комментарии"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CommentDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/ads/{id}/comments",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    @Override
    public ResponseEntity<CommentDto> addComment(Integer id, CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        return AdsApi.super.addComment(id, createOrUpdateCommentDto);
    }

    @Operation(
            operationId = "deleteComment",
            summary = "Удаление комментария",
            tags = {"Комментарии"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/ads/{adId}/comments/{commentId}"
    )
    @Override
    public ResponseEntity<Void> deleteComment(Integer adId, Integer commentId) {
        return AdsApi.super.deleteComment(adId, commentId);
    }

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
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/ads/{id}",
            produces = {"application/json"}
    )
    @Override
    public ResponseEntity<ExtendedAdDto> getAds(Integer id) {
        return AdsApi.super.getAds(id);
    }

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
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/ads/me",
            produces = {"application/json"}
    )
    @Override
    public ResponseEntity<AdsDto> getAdsMe() {
        return AdsApi.super.getAdsMe();
    }

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
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/ads",
            produces = {"application/json"}
    )
    @Override
    public ResponseEntity<AdsDto> getAllAds() {
        return AdsApi.super.getAllAds();
    }

    @Operation(
            operationId = "getComments",
            summary = "Получение комментариев объявления",
            tags = {"Комментарии"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CommentsDto.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/ads/{id}/comments",
            produces = {"application/json"}
    )
    @Override
    public ResponseEntity<CommentsDto> getComments(Integer id) {
        return AdsApi.super.getComments(id);
    }

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
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/ads/{id}"
    )
    @Override
    public ResponseEntity<Void> removeAd(Integer id) {
        return AdsApi.super.removeAd(id);
    }

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
    @RequestMapping(
            method = RequestMethod.PATCH,
            value = "/ads/{id}",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    @Override
    public ResponseEntity<AdDto> updateAds(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto) {
        return AdsApi.super.updateAds(id, createOrUpdateAdDto);
    }


    @Operation(
            operationId = "updateComment",
            summary = "Обновление комментария",
            tags = {"Комментарии"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CommentDto.class))
                    }),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    @RequestMapping(
            method = RequestMethod.PATCH,
            value = "/ads/{adId}/comments/{commentId}",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    @Override
    public ResponseEntity<CommentDto> updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        return AdsApi.super.updateComment(adId, commentId, createOrUpdateCommentDto);
    }


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
    @RequestMapping(
            method = RequestMethod.PATCH,
            value = "/ads/{id}/image",
            produces = {"application/octet-stream"},
            consumes = {"multipart/form-data"}
    )
    @Override
    public ResponseEntity<List<byte[]>> updateImage(Integer id, MultipartFile image) {
        return AdsApi.super.updateImage(id, image);
    }

}
