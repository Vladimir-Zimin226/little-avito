package ru.skypro.homework.service.impl;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.service.CommentService;

public class CommentServiceImpl implements CommentService {
    @Override
    public CommentDto addComment(Integer adId, CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        return null;
    }

    @Override
    public void deleteComment(Integer adId, Integer commentId) {

    }

    @Override
    public CommentsDto getComments(Integer adId) {
        return null;
    }

    @Override
    public CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        return null;
    }
}
