package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.Comment;

import java.io.IOException;

public interface CommentService {

    CommentDto addComment(Integer adId, CreateOrUpdateCommentDto createOrUpdateCommentDto, Authentication authentication);

    void deleteComment(Integer adId, Integer commentId);

    void deleteAllByAdId(Integer adId);

    CommentsDto getComments(Integer adId);

    CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto createOrUpdateCommentDto);

    Comment getComment(Integer commentId);

    byte[] getUserImage(Integer commentId) throws IOException;
}
