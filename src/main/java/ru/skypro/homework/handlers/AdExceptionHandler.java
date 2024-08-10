package ru.skypro.homework.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.skypro.homework.exception.AdNotFoundException;

@ControllerAdvice
public class AdExceptionHandler {
    @ExceptionHandler(value = {AdNotFoundException.class})
    public ResponseEntity<?> handleAdNotFound(AdNotFoundException exception) {
        String message = "Объявление не найдено";
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
