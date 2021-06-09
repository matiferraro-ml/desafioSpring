package com.desafioSpring.exception;

import com.desafioSpring.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;

@ControllerAdvice
public class UserNotFoundException extends RuntimeException {

    Response result;
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Response> runTimeException(RuntimeException rte) {
        result = new Response(timestamp, "[Exception Response] - Exception: " + rte.getMessage(),
                HttpStatus.BAD_REQUEST.value(), "Error");
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

}
