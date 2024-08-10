package ru.skypro.homework.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.skypro.homework.exception.CommentNotFoundException;


@ControllerAdvice
public class CommentExceptionHandler {

    @ExceptionHandler(value = {CommentNotFoundException.class})
    public ResponseEntity<?> handleCommentNotFound(CommentNotFoundException exception) {
        String message = "Комментарий не найден";
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
