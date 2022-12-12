package learn.capstone.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        return new ResponseEntity<>(
                new ErrorResponse("Sorry, something unexpected went wrong. :("),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArgumentException(IllegalArgumentException exception) {
        return new ResponseEntity<>(
                new ErrorResponse("Sorry, something unexpected went wrong. :("),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException exception) {
        return new ResponseEntity<>(
                new ErrorResponse("Sorry, something unexpected went wrong. :("),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponse> handleDataAccessException(DataAccessException exception) {
        return new ResponseEntity<>(
                new ErrorResponse("Sorry, something unexpected went wrong. :("),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //data integrity violation exception
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityException(DataIntegrityViolationException exception) {
        return new ResponseEntity<>(
                new ErrorResponse("Data Integrity Violation! (Data is being used)"),
                HttpStatus.BAD_REQUEST);
    }

}