package com.matei.customeraccountaggregation.exception;

public class ExternalServiceUnavailableException extends RuntimeException {
    public ExternalServiceUnavailableException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
