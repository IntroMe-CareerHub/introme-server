package com.introme.interfaces.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ServiceErrorCode implements ErrorCode {
    COMPANY_NOT_FOUND(HttpStatus.NOT_FOUND, "Company not exists"),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "Request DTO is nUll");
    
    private final HttpStatus httpStatus;
    private final String message;
}
