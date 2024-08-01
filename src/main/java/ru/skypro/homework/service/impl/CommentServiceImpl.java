package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    @Override
    public CommentDto addComment(Integer adId, CreateOrUpdateCommentDto createOrUpdateCommentDto, Authentication authentication) {
        log.info("Adding the comment");
        Ad ad = adRepository.findById(Long.valueOf(adId))
                .orElseThrow(() -> new IllegalArgumentException("Ad not found"));
        Comment comment = new Comment();
        comment.setAd(ad);
        comment.setText(createOrUpdateCommentDto.getText());
        comment.setCreatedAt(LocalDateTime.now());

        User currentUser = userRepository.findUserByEmailIgnoreCase(authentication.getName())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        comment.setAuthor(currentUser);

        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toCommentDto(savedComment);
    }

    @Override
    public void deleteComment(Integer adId, Integer commentId) {
        log.info("Deleting the comment in ad by its id: {}", commentId);
        Comment comment = commentRepository.findById(Long.valueOf(commentId)).orElseThrow(CommentNotFoundException::new);
        Ad ad = adRepository.findById(Long.valueOf(adId)).orElseThrow(AdNotFoundException::new);
        boolean remove = ad.getComments().remove(commentId);
        commentRepository.delete(comment);

    }

    @Override
    public void deleteAllByAdId(Integer adId) {
        log.info("Deleting all comments in ad: {}", adId);
        commentRepository.deleteAllByAdId(adId);
    }

    @Override
    public CommentsDto getComments(Long adId) {
        log.info("Getting all comments in ad {}", adId);
        Ad ad = adRepository.findById(Long.valueOf(adId)).orElseThrow(AdNotFoundException::new);

        List<Comment> comments = ad.getComments();

        CommentsDto commentsDto = new CommentsDto();
        commentsDto.setCount(comments.size());
        commentsDto.setResults(comments.stream()
                .map(commentMapper::toCommentDto)
                .collect(Collectors.toList()));
        return commentsDto;
    }


    @Override
    public CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        log.info("Updating comment in ad by its id: {}", commentId);
        Comment comment = commentRepository.findById(Long.valueOf(commentId)).orElseThrow(CommentNotFoundException::new);
        comment.setText(createOrUpdateCommentDto.getText());
        Ad ad = adRepository.findById(Long.valueOf(adId)).orElseThrow(AdNotFoundException::new);
        ad.getComments().add(commentId, comment);
        Comment updatedComment = commentRepository.save(comment);
        return commentMapper.toCommentDto(updatedComment);
    }

    @Override
    public Comment getComment(Long commentId) {
        log.info("Getting comment by its id: {}", commentId);
        return commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
    }
}
