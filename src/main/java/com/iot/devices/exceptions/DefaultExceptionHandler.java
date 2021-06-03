package com.iot.devices.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class DefaultExceptionHandler {

    @ExceptionHandler(ObjectAlreadyExistException.class)
    protected ResponseEntity<ExceptionResponse> handleObjectAlreadyExistException(ObjectAlreadyExistException exception) {
        //handling ObjectAlreadyExistException
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(
                HttpStatus.NOT_FOUND.value(), exception.getMessage()));
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    protected ResponseEntity<ExceptionResponse> handleObjectNotFoundException(ObjectNotFoundException exception) {
        //handling ObjectNotFoundException
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                HttpStatus.NOT_FOUND.value(), exception.getMessage()));
    }

    @ExceptionHandler(ObjectSavingException.class)
    protected ResponseEntity<ExceptionResponse> handleObjectSavingException(ObjectSavingException exception) {
        //handling ObjectSavingException
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()));
    }

    @ExceptionHandler(RequiredDataNotFoundException.class)
    protected ResponseEntity<ExceptionResponse> handleRequiredDataNotFoundException(RequiredDataNotFoundException exception) {
        //handling RequiredDataNotFoundException
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(
                HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    protected ResponseEntity<ExceptionResponse> handleUnauthorizedAccessException(UnauthorizedAccessException exception) {
        //handling UnauthorizedAccessException
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionResponse(
                HttpStatus.UNAUTHORIZED.value(), exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionResponse> handleAllException(Exception exception) {
        //handling all other unknown exceptions
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()));
    }
}
