package com.example.judesys.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        System.out.println("ERROR");
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BadRequestParamsException.class)
    public Map<String, String> handleBusinessException(BadRequestParamsException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());
        System.out.println("ERROR2 " +  ex.getMessage());
        return errorMap;
    }

//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
////        ErrorModel error = new ErrorModel(HttpStatus.BAD_REQUEST, "Validation Error", ex.getBindingResult().toString());
//        ErrorModel error = new ErrorModel(HttpStatus.BAD_REQUEST, "Validation Error",ex.get);
//
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(EntityNotFoundException.class)
//    private ResponseEntity<ErrorModel> handleEntityNotFound(EntityNotFoundException ex){
//        ErrorModel error = new ErrorModel(HttpStatus.NOT_FOUND, "Entity not found", ex.getMessage());
//
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//    }
}
