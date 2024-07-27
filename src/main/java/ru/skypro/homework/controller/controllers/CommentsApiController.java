package ru.skypro.homework.controller.controllers;


import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.controller.interfaces.CommentsApi;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;

import javax.validation.Valid;

@RestController
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
public class CommentsApiController implements CommentsApi {

    @PostMapping(
            value = "/{id}/comments",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    public ResponseEntity<CommentDto> addComment(
            @Parameter(name = "id", description = "", required = true) @PathVariable("id") Integer id,
            @Parameter(name = "CreateOrUpdateCommentDto", description = "") @Valid @RequestBody(required = false) CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @DeleteMapping(
            value = "/{adId}/comments/{commentId}"
    )
    public ResponseEntity<Void> deleteComment(
            @Parameter(name = "adId", description = "", required = true) @PathVariable("adId") Integer adId,
            @Parameter(name = "commentId", description = "", required = true) @PathVariable("commentId") Integer commentId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @GetMapping(
            value = "/{id}/comments",
            produces = {"application/json"}
    )
    public ResponseEntity<CommentsDto> getComments(
            @Parameter(name = "id", description = "", required = true) @PathVariable("id") Integer id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @PatchMapping(
            value = "/{adId}/comments/{commentId}",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    public ResponseEntity<CommentDto> updateComment(
            @Parameter(name = "adId", description = "", required = true) @PathVariable("adId") Integer adId,
            @Parameter(name = "commentId", description = "", required = true) @PathVariable("commentId") Integer commentId,
            @Parameter(name = "CreateOrUpdateCommentDto", description = "") @Valid @RequestBody(required = false) CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
