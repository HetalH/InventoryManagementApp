package com.rob.shop.inventory.api.exception;

import com.rob.shop.inventory.service.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
@RestController
public class CustomRestExceptionHandler {

    @ExceptionHandler(CustomerNotExist.class )
    public ResponseEntity<Object> handleCustomerNotExistException(
            CustomerNotExist ex, WebRequest request) {

        ApiError apiError =
                new ApiError(LocalDate.now(), ex.getLocalizedMessage(), "");
        return  new ResponseEntity(apiError,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientStock.class )
    public ResponseEntity<Object> handleInsufficientStockException(
            CustomerNotExist ex, WebRequest request) {

        ApiError apiError =
                new ApiError(LocalDate.now(), ex.getLocalizedMessage(), "");
        return  new ResponseEntity(apiError,HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(ProductNotExist.class )
    public ResponseEntity<Object> handleProductNotExistException(
            ProductNotExist ex, WebRequest request) {

        ApiError apiError =
                new ApiError(LocalDate.now(), ex.getLocalizedMessage(), "");
        return  new ResponseEntity(apiError,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(CustomerAlreadyExist.class )
    public ResponseEntity<Object> handleCustomerAlreadyExistException(
            CustomerAlreadyExist ex, WebRequest request) {

        ApiError apiError =
                new ApiError(LocalDate.now(), ex.getLocalizedMessage(), "");
        return  new ResponseEntity(apiError,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductAlreadyExist.class )
    public ResponseEntity<Object> handleProductAlreadyExistException(
            ProductAlreadyExist ex, WebRequest request) {

        ApiError apiError =
                new ApiError(LocalDate.now(), ex.getLocalizedMessage(), "");
        return  new ResponseEntity(apiError,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler( MethodArgumentNotValidException.class )
    public ResponseEntity<Object> handleMethodNotValidException(
            MethodArgumentNotValidException ex, WebRequest request) {

        BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        List<String> errors = new ArrayList<>();

        fieldErrors.forEach(fieldError -> {
            String error = fieldError.getDefaultMessage();
            errors.add(error);
        });

        ApiError apiError =
                new ApiError(LocalDate.now(), "Validation Failed", errors);
        return  new ResponseEntity(apiError,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler( Exception.class )
    public ResponseEntity<Object> handleOtherException(
            Exception ex, WebRequest request) {
        ex.printStackTrace();
        ApiError apiError =
                new ApiError(LocalDate.now(), "Internal Serval Error", "Internal Serval Error");
        return new ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
