package com.home.freelancer.exception;

import lombok.Value;
import org.springframework.http.HttpStatus;

@Value
public class ErrorResponse {
    private HttpStatus status;
    private String message;
}
