package com.dbserver.dbalmoco;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HttpErrorExceptionHandler {

  @ResponseStatus(NOT_FOUND)
  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseBody
  public ResponseEntity<String> tratarErroRecursoNaoEncontrado(RuntimeException exception) {
    return ResponseEntity.status(NOT_FOUND).body(exception.getMessage());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<String>> tratarErroParametrosInvalidos(MethodArgumentNotValidException exception) {
    List<String> ListErrors = exception.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
        .collect(Collectors.toList());
    return ResponseEntity.status(BAD_REQUEST).body(ListErrors);
  }
}
