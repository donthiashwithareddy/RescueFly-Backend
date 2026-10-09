package com.rescuefly.exception;
import org.springframework.http.*; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.annotation.*; import java.time.Instant; import java.util.*;
@RestControllerAdvice public class ApiExceptionHandler{
 @ExceptionHandler(IllegalArgumentException.class) ResponseEntity<?> bad(IllegalArgumentException e){return body(HttpStatus.BAD_REQUEST,e.getMessage());}
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<?> validation(MethodArgumentNotValidException e){String m=e.getBindingResult().getFieldErrors().stream().findFirst().map(x->x.getField()+": "+x.getDefaultMessage()).orElse("Invalid request");return body(HttpStatus.BAD_REQUEST,m);}
 @ExceptionHandler(Exception.class) ResponseEntity<?> all(Exception e){return body(HttpStatus.INTERNAL_SERVER_ERROR,"Something went wrong. Check the server logs.");}
 private ResponseEntity<?> body(HttpStatus s,String m){return ResponseEntity.status(s).body(Map.of("timestamp",Instant.now().toString(),"status",s.value(),"message",m==null?"Request failed":m));}
}
