package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;

public interface CommentService {

    CommentDto addComment(Integer adId, CreateOrUpdateCommentDto createOrUpdateCommentDto);

    void deleteComment(Integer adId, Integer commentId);

    CommentsDto getComments(Integer adId);

    CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto createOrUpdateCommentDto);
}
