package com.matei.customeraccountaggregation.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

@Slf4j
@ResponseBody
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Object handleIllegalArgumentException(HttpServletRequest req, HttpServletResponse res, Exception exception) {
        log.error("Exception caught: {}, user: {}", exception.getMessage(), req.getHeader("username"));
        Map<String, Object> response = new HashMap<>();
        response.put("user", req.getHeader("username"));
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
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleInternalServelError(HttpServletRequest req, HttpServletResponse res, Exception exception) {
        log.error("Exception caught: {}, user: {}", exception.getMessage(), req.getHeader("username"));
        Map<String, Object> response = new HashMap<>();
        response.put("user", req.getHeader("username"));
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        response.put("message", "Could not handle request");
        response.put("timestamp", new Timestamp(System.currentTimeMillis()));
        return response;
    }

    @ExceptionHandler({
            ExternalServiceUnavailableException.class
    })
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    public Object handleServiceUnavailable(HttpServletRequest req, HttpServletResponse res, Exception exception) {
        log.error("Exception caught: {}, user: {}", exception.getMessage(), req.getHeader("username"));
        Map<String, Object> response = new HashMap<>();
        response.put("user", req.getHeader("username"));
        response.put("status", HttpStatus.SERVICE_UNAVAILABLE);
        response.put("message", "External data service provider is temporary down");
        response.put("timestamp", new Timestamp(System.currentTimeMillis()));
        return response;
    }
}
