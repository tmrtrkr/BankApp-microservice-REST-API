package com.projects.app.bankApplication.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java.util.UUID;



@ControllerAdvice
public class GlobalExceptionHandler {

   /* @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        // Return custom error message and HTTP status code
        return new ResponseEntity<>(new ErrorDetails("An error occurred: " + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    */
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Object> handleNumberFormatException(NumberFormatException ex) {
        // Return custom error message for invalid number formats
        return new ResponseEntity<>(new ErrorDetails("Number format exception: " + ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

   /*  @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        // General error catching
        return new ResponseEntity<>(new ErrorDetails("General error: " + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
 */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleUUIDFormatException(MethodArgumentTypeMismatchException ex) {
        if (ex.getRequiredType() == UUID.class) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Invalid UUID format: " + ex.getValue());
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid parameter: " + ex.getValue());
    }

    // Inner class containing error details
    public static class ErrorDetails {
        private String message;

        public ErrorDetails(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
