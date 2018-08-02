package com.rob.shop.inventory.api.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ApiError {

    private LocalDate timeStamp;
    private String message;
    private List<String> errors;

    public ApiError(LocalDate timeStamp, String message, List<String> errors) {
        super();
        this.timeStamp = timeStamp;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(LocalDate timeStamp, String message, String error) {
        super();
        this.timeStamp = timeStamp;
        this.message = message;
        errors = Arrays.asList(error);
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }
}