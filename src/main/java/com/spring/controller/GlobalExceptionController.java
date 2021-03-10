package com.spring.controller;

import com.spring.dataprovider.PropertyReader;
import com.spring.logging.Console;
import com.spring.model.response.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleArgumentException(HttpServletRequest request, Exception exception) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, request.getRequestURI(), exception);
        return loadResponse(apiError, exception);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationException(HttpServletRequest request, MethodArgumentNotValidException exception) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, request.getRequestURI(), exception, exception.getBindingResult().getAllErrors());
        return loadResponse(apiError, exception);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleUncaught(HttpServletRequest request, Exception exception) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, request.getRequestURI(), exception);
        return loadResponse(apiError, exception);
    }

    //==================================================================================================================

    private ResponseEntity<ApiError> loadResponse(ApiError apiError, Exception exception) {
        Console.logError(apiError.toString());
        if (PropertyReader.getInstance().getConsole_ShowLog()) {
            exception.printStackTrace();
        }
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }
}
