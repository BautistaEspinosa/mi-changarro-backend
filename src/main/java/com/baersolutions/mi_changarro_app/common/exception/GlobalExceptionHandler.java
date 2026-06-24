package com.baersolutions.mi_changarro_app.common.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Map<String,Object>> handlerNotFound(ResourceNotFoundException ex){
    return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<Map<String,Object>> handlerBussines(BusinessException ex){
    return buildResponse(ex.getMessage(),HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InvalidOperationException.class)
  public ResponseEntity<Map<String,Object>> handlerInvalid(InvalidOperationException ex){
    return buildResponse(ex.getMessage(),HttpStatus.CONFLICT);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String,Object>> handlerGeneral(Exception ex){
    return buildResponse("Error interno del sistema",HttpStatus.INTERNAL_SERVER_ERROR);
  }

  public ResponseEntity<Map<String,Object>> buildResponse(String message, HttpStatus status){
    Map<String,Object> body = new HashMap<>();

    body.put("message: ",message);
    body.put("status: ",status.value());
    body.put("timestamp: ", LocalDateTime.now());

    return new ResponseEntity<>(body,status);
  }
}
