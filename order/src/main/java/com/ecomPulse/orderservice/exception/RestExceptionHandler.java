package com.ecomPulse.orderservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({ ServiceException.class,RestTemplateException.class })
    public ResponseEntity<CustomErrorResponse> handleServiceException(Exception exc){
        CustomErrorResponse err = new CustomErrorResponse();
        err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        err.setTimestamp(System.currentTimeMillis());
        err.setError(exc.getMessage());
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {ProductQuantityNotEnoughException.class, IllegalArgumentException.class})
    public ResponseEntity<CustomErrorResponse> handleNotFoundException(Exception exc){
        CustomErrorResponse err = new CustomErrorResponse();
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setTimestamp(System.currentTimeMillis());
        err.setError(exc.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CustomErrorResponse> handleException(Exception exc){
        CustomErrorResponse err = new CustomErrorResponse();
        err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        err.setTimestamp(System.currentTimeMillis());
        err.setError(exc.getMessage());
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
