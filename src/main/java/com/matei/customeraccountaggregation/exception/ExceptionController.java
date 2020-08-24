package com.matei.customeraccountaggregation.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@ResponseBody
@ControllerAdvice
public class ExceptionController {

    private static final String SYSTEM_USER = "SYSTEM";
    private static final String USERNAME_HEADER = "username";

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object handleIllegalArgumentException(HttpServletRequest req, HttpServletResponse res, Exception exception) {
        log.error("Exception caught: {}, user: {}", exception.getMessage(), req.getHeader(USERNAME_HEADER));
        Map<String, Object> response = new HashMap<>();
        response.put("user", Objects.nonNull(req.getHeader(USERNAME_HEADER)) ? req.getHeader(USERNAME_HEADER) : SYSTEM_USER);
        response.put("status", HttpStatus.BAD_REQUEST);
        response.put("message", "Invalid username.");
        response.put("timestamp", new Timestamp(System.currentTimeMillis()));
        return response;
    }

    @ExceptionHandler({
            RuntimeException.class,
            IOException.class,
            SQLException.class
    })
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public Object handleInternalServelError(HttpServletRequest req, Exception exception, Exception e) {
        log.error("Exception caught: {}, user: {}", exception.getMessage(), req.getHeader(USERNAME_HEADER));
        Map<String, Object> response = new HashMap<>();
        response.put("user", Objects.nonNull(req.getHeader(USERNAME_HEADER)) ? req.getHeader(USERNAME_HEADER) : SYSTEM_USER);
        response.put("status", HttpStatus.UNPROCESSABLE_ENTITY);
        response.put("message", Objects.nonNull(e.getMessage()) ? e.getMessage() : "Could not handle request");
        response.put("timestamp", new Timestamp(System.currentTimeMillis()));
        return response;
    }

    @ExceptionHandler({
            ExternalServiceUnavailableException.class
    })
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    public Object handleServiceUnavailable(HttpServletRequest req, Exception exception) {
        log.error("Exception caught: {}, user: {}", exception.getMessage(), req.getHeader(USERNAME_HEADER));
        Map<String, Object> response = new HashMap<>();
        response.put("user", Objects.nonNull(req.getHeader(USERNAME_HEADER)) ? req.getHeader(USERNAME_HEADER) : SYSTEM_USER);
        response.put("status", HttpStatus.SERVICE_UNAVAILABLE);
        response.put("message", "External data service provider is temporary down");
        response.put("timestamp", new Timestamp(System.currentTimeMillis()));
        return response;
    }

    @ExceptionHandler({
            UserNotFoundException.class
    })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object handleUserNotFound(Exception exception) {
        log.error("Exception caught: {}", exception.getMessage());
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST);
        response.put("message", "User not found");
        response.put("timestamp", new Timestamp(System.currentTimeMillis()));
        return response;
    }


    @ExceptionHandler({
            MethodArgumentNotValidException.class
    })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object handleMethodArgumentNotValidException(Exception exception) {
        log.error("Exception caught: {}", exception.getMessage());
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST);
        response.put("message", "Request body input is not valid");
        response.put("timestamp", new Timestamp(System.currentTimeMillis()));
        return response;
    }
}
