package com.baersolutions.mi_changarro_app.common.exception;

import com.baersolutions.mi_changarro_app.common.constants.LogMessages;
import com.baersolutions.mi_changarro_app.common.response.ApiResponse;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Manejo global de excepciones de la aplicación.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final String MODULE = "GLOBAL";
  private static final String OPERATION = "EXCEPTION_HANDLER";

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse<Void>> handleResourceNotFound(
      ResourceNotFoundException ex
  ) {

    log.warn(
        LogMessages.ERROR,
        MODULE,
        OPERATION,
        ex.getMessage()
    );

    return buildResponse(
        ex.getMessage(),
        HttpStatus.NOT_FOUND
    );
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ApiResponse<Void>> handleBusiness(
      BusinessException ex
  ) {

    log.warn(
        LogMessages.ERROR,
        MODULE,
        OPERATION,
        ex.getMessage()
    );

    return buildResponse(
        ex.getMessage(),
        HttpStatus.BAD_REQUEST
    );
  }

  @ExceptionHandler(InvalidOperationException.class)
  public ResponseEntity<ApiResponse<Void>> handleInvalidOperation(
      InvalidOperationException ex
  ) {

    log.warn(
        LogMessages.ERROR,
        MODULE,
        OPERATION,
        ex.getMessage()
    );

    return buildResponse(
        ex.getMessage(),
        HttpStatus.CONFLICT
    );
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<Void>> handleValidation(
      MethodArgumentNotValidException ex
  ) {

    String message = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(FieldError::getDefaultMessage)
        .collect(Collectors.joining(", "));

    log.warn(
        LogMessages.ERROR,
        MODULE,
        OPERATION,
        message
    );

    return buildResponse(
        message,
        HttpStatus.BAD_REQUEST
    );
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<Void>> handleGeneral(
      Exception ex
  ) {

    log.error(
        LogMessages.ERROR,
        MODULE,
        OPERATION,
        ex.getMessage(),
        ex
    );

    return buildResponse(
        LogMessages.INTERNAL_SERVER_ERROR,
        HttpStatus.INTERNAL_SERVER_ERROR
    );
  }

  private ResponseEntity<ApiResponse<Void>> buildResponse(
      String message,
      HttpStatus status
  ) {

    return ResponseEntity
        .status(status)
        .body(ApiResponse.error(message, status));
  }


}