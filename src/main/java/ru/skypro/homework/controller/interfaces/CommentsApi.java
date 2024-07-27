package ru.skypro.homework.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;

import javax.validation.Valid;

public interface CommentsApi {

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
    @PostMapping(
            value = "/{id}/comments",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    ResponseEntity<CommentDto> addComment(
            @Parameter(name = "id", description = "", required = true) @PathVariable("id") Integer id,
            @Parameter(name = "CreateOrUpdateCommentDto", description = "") @Valid @RequestBody(required = false) CreateOrUpdateCommentDto createOrUpdateCommentDto
    );

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
    @DeleteMapping(
            value = "/{adId}/comments/{commentId}"
    )
    ResponseEntity<Void> deleteComment(
            @Parameter(name = "adId", description = "", required = true) @PathVariable("adId") Integer adId,
            @Parameter(name = "commentId", description = "", required = true) @PathVariable("commentId") Integer commentId
    );

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
    @GetMapping(
            value = "/{id}/comments",
            produces = {"application/json"}
    )
    ResponseEntity<CommentsDto> getComments(
            @Parameter(name = "id", description = "", required = true) @PathVariable("id") Integer id
    );

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
    @PatchMapping(
            value = "/{adId}/comments/{commentId}",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    ResponseEntity<CommentDto> updateComment(
            @Parameter(name = "adId", description = "", required = true) @PathVariable("adId") Integer adId,
            @Parameter(name = "commentId", description = "", required = true) @PathVariable("commentId") Integer commentId,
            @Parameter(name = "CreateOrUpdateCommentDto", description = "") @Valid @RequestBody(required = false) CreateOrUpdateCommentDto createOrUpdateCommentDto
    );
}
