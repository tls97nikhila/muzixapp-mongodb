package com.stackroute.muzixapp.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceContoller {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e) {
        return new ResponseEntity<>("Error thrown globally : " + e.getMessage(), HttpStatus.CONFLICT);
    }
}
