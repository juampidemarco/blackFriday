package com.cbi.blackFriday.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ApiExceptionHandler {
   @ExceptionHandler(value = {ApiRequestException.class})
    private ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        ApiException apiException =
                new ApiException(new Date(System.currentTimeMillis()), HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage());
        return new ResponseEntity<>(apiException, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = {OfferNotFoundException.class})
    private ResponseEntity<Object> offerNotFoundHandle(OfferNotFoundException ex) {
        ApiException apiException =
                new ApiException(new Date(System.currentTimeMillis()), HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ProductException.class})
    private ResponseEntity<Object> handleProductException(ProductException ex) {
        ApiException apiException =
                new ApiException(new Date(System.currentTimeMillis()), HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {ProductNotFoundException.class})
    private ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex) {
        ApiException apiException =
                new ApiException(new Date(System.currentTimeMillis()), HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {OfferValidationException.class})
    private ResponseEntity<Object> handleOfferValidationException(OfferValidationException ex) {
        ApiException apiException =
                new ApiException(new Date(System.currentTimeMillis()), HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UserErrorException.class})
    private ResponseEntity<Object> handleUserErrorException(UserErrorException ex) {
        ApiException apiException =
                new ApiException(new Date(System.currentTimeMillis()), HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
