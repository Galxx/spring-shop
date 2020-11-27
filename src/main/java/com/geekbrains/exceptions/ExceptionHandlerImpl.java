package com.geekbrains.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerImpl {

    //TODO: Сделать красивую обработку ошибки.

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ManagerIsEarlierThanNeedException.class)
    public ResponseEntity<Object> methodArgumentNotValidException(ManagerIsEarlierThanNeedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Object> methodArgumentNotValidException(RoleNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleConverterErrors(HttpServletRequest req, MethodArgumentTypeMismatchException exception) {

        String URI = req.getRequestURI();
        if (URI.equals("/api/v1/user")){

            return new ResponseEntity<>(String.format("Тип пользователя с именем %s не найден.", req.getParameter("type")), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);

    }

}
