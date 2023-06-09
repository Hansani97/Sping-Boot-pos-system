package com.devstack.posapp.adviser;

import com.devstack.posapp.exception.EntryNotFoundException;
import com.devstack.posapp.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWiderExceptionHandler {
    @ExceptionHandler(EntryNotFoundException.class)
    public ResponseEntity<StandardResponse> handleEntryNotFoundException(EntryNotFoundException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404, e.getMessage(), e),
                HttpStatus.NOT_FOUND
        );
    }
}
