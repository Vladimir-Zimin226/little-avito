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
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса для управления комментариями.
 * <p>
 * Предоставляет методы для добавления, удаления, обновления и получения комментариев,
 * а также получения изображения пользователя, оставившего комментарий.
 * </p>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    /**
     * Добавляет новый комментарий к объявлению.
     *
     * @param adId идентификатор объявления, к которому добавляется комментарий.
     * @param createOrUpdateCommentDto данные комментария.
     * @param authentication информация о текущем пользователе.
     * @return DTO добавленного комментария.
     * @throws IllegalArgumentException если объявление или пользователь не найдены.
     */
    @Override
    public CommentDto addComment(Integer adId, CreateOrUpdateCommentDto createOrUpdateCommentDto, Authentication authentication) {
        log.info("Adding the comment");
        Ad ad = adRepository.findById(adId)
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

    /**
     * Удаляет комментарий из объявления.
     *
     * @param adId идентификатор объявления, к которому принадлежит комментарий.
     * @param commentId идентификатор комментария для удаления.
     * @throws CommentNotFoundException если комментарий не найден.
     * @throws AdNotFoundException если объявление не найдено.
     */
    @Override
    public void deleteComment(Integer adId, Integer commentId) {
        log.info("Deleting the comment in ad by its id: {}", commentId);
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        ad.getComments().remove(comment);
        commentRepository.delete(comment);
    }

    /**
     * Удаляет все комментарии из объявления.
     *
     * @param adId идентификатор объявления, из которого удаляются все комментарии.
     */
    @Override
    public void deleteAllByAdId(Integer adId) {
        log.info("Deleting all comments in ad: {}", adId);
        commentRepository.deleteAllByAdId(adId);
    }

    /**
     * Получает все комментарии к объявлению.
     *
     * @param adId идентификатор объявления.
     * @return DTO всех комментариев к объявлению.
     * @throws AdNotFoundException если объявление не найдено.
     */
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

    /**
     * Обновляет текст комментария.
     *
     * @param adId идентификатор объявления, к которому принадлежит комментарий.
     * @param commentId идентификатор комментария для обновления.
     * @param createOrUpdateCommentDto новые данные комментария.
     * @return DTO обновленного комментария.
     * @throws CommentNotFoundException если комментарий не найден.
     * @throws AdNotFoundException если объявление не найдено.
     */
    @Override
    public CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        log.info("Updating comment in ad by its id: {}", commentId);
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        comment.setText(createOrUpdateCommentDto.getText());
        Comment updatedComment = commentRepository.save(comment);
        return commentMapper.toCommentDto(updatedComment);
    }

    /**
     * Получает комментарий по его идентификатору.
     *
     * @param commentId идентификатор комментария.
     * @return комментарий.
     * @throws CommentNotFoundException если комментарий не найден.
     */
    @Override
    public Comment getComment(Integer commentId) {
        log.info("Getting comment by its id: {}", commentId);
        return commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
    }

    /**
     * Получает изображение пользователя, оставившего комментарий.
     *
     * @param commentId идентификатор комментария.
     * @return изображение пользователя в виде массива байтов. Если пользователь не найден, возвращается изображение по умолчанию.
     * @throws IOException если не удалось прочитать изображение.
     * @throws UserNotFoundException если пользователь, оставивший комментарий, не найден.
     */
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
