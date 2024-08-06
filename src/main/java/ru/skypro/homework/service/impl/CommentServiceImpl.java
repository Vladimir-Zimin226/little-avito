package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;

import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
        Ad ad = adRepository.findById(adId)
                .orElseThrow(() -> new IllegalArgumentException("Ad not found"));
        Comment comment = new Comment();
        comment.setAd(ad);
        comment.setText(createOrUpdateCommentDto.getText());
        comment.setCreatedAt(LocalDateTime.now());

        User currentUser = userRepository.findByEmail(authentication.getName())

                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        comment.setAuthor(currentUser);

        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toCommentDto(savedComment);
    }

    @Transactional
    @Override
    public void deleteComment(Integer adId, Integer commentId) {
        log.info("Deleting the comment in ad by its id: {}", commentId);
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        ad.getComments().remove(comment);
        commentRepository.delete(comment);

    }

    @Override
    public void deleteAllByAdId(Integer adId) {
        log.info("Deleting all comments in ad: {}", adId);
        commentRepository.deleteAllByAdId(adId);
    }

    @Override
    public CommentsDto getComments(Integer adId) {
        log.info("Getting all comments in ad {}", adId);
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);

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
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        comment.setText(createOrUpdateCommentDto.getText());
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        ad.getComments().add(commentId, comment);
        Comment updatedComment = commentRepository.save(comment);
        return commentMapper.toCommentDto(updatedComment);
    }

    @Override
    public Comment getComment(Integer commentId) {
        log.info("Getting comment by its id: {}", commentId);
        return commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
    }

    @Override
    public byte[] getUserImage(Integer commentId) throws IOException {
        log.info("Request to getting image");
        Comment comment = commentRepository.findById(commentId).orElseThrow(UserNotFoundException::new);
        if (comment.getAuthor() != null) {
            return comment.getAuthor().getUserPhoto().getData();
        } else {
            File emptyAvatar = new File("src/main/resources/defaultPhoto/6700.jpg");
            return Files.readAllBytes(emptyAvatar.toPath());
        }
    }
}

