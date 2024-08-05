package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.service.CommentService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
public class CommentsApiController {

    public final CommentService commentService;

    public CommentsApiController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(
            value = "/{id}/comments",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    public ResponseEntity<CommentDto> addComment(
            @Parameter(name = "id", required = true) @PathVariable("id") Integer id,
            @Parameter(name = "CreateOrUpdateCommentDto", description = "") @Valid @RequestBody(required = false) CreateOrUpdateCommentDto createOrUpdateCommentDto, @NotNull Authentication authentication) {
        return ResponseEntity.ok(commentService.addComment(id, createOrUpdateCommentDto, authentication));
    }


    @PreAuthorize("@commentServiceImpl.getComment(#commentId).author.email.equals(authentication.name) or hasAuthority('ROLE_ADMIN')")
    @DeleteMapping(
            value = "/{adId}/comments/{commentId}"
    )
    public ResponseEntity<Void> deleteComment(
            @Parameter(name = "adId",required = true) @PathVariable("adId") Integer adId,
            @Parameter(name = "commentId", required = true) @PathVariable("commentId") Integer commentId) {
        commentService.deleteComment(adId, commentId);
        return ResponseEntity.ok().build();
    }


    @GetMapping(
            value = "/{id}/comments",
            produces = {"application/json"}
    )
    public ResponseEntity<CommentsDto> getComments(
            @Parameter(name = "id",required = true) @PathVariable("id") Integer id) {
        return ResponseEntity.ok(commentService.getComments(id));
    }


    @PreAuthorize("@commentServiceImpl.getComment(#commentId).author.email.equals(authentication.name) or hasAuthority('ROLE_ADMIN')")
    @PatchMapping(
            value = "/{adId}/comments/{commentId}",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    public ResponseEntity<CommentDto> updateComment(
            @Parameter(name = "adId", required = true) @PathVariable("adId") Integer adId,
            @Parameter(name = "commentId",required = true) @PathVariable("commentId") Integer commentId,
            @Parameter(name = "CreateOrUpdateCommentDto") @Valid @RequestBody(required = false) CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        return ResponseEntity.ok(commentService.updateComment(adId, commentId, createOrUpdateCommentDto));
    }
}
