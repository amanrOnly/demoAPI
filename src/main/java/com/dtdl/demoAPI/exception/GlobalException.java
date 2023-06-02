package com.dtdl.demoAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Boolean>> resourceNotFoundException(ResourceNotFoundException ex){
        Map<String, Boolean> response = new HashMap<>();
        response.put(ex.getMessage(), false);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMeMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> response = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            response.put(fieldName, message);
        });
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Map<String,String>> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex){
        Map<String, String> response = new HashMap<>();
        String message =  ex.getMessage();
        MediaType mediaType =  ex.getContentType();
        String supportedTypes = ex.getSupportedMediaTypes().toString();
        String media = mediaType.toString();
        response.put("message", message);
        response.put("mediaType", media);
        response.put("suppoertedMediaTypes", supportedTypes);
        return new ResponseEntity<>(response, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String,String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){
        Map<String, String> response = new HashMap<>();
        String message =  ex.getMessage();
        String error = ex.toString();
        response.put("message", message);
        response.put("error", error);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
