package com.example.cadastropet.Exceptions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.example.cadastropet.Response.ExceptionMessage;

import java.util.Date;
import java.util.zip.DataFormatException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value= AgeHigherThan19Exception.class)
    public ResponseEntity<Object> AgeHigherThan19(Exception e, WebRequest request){
        String exception = e.getClass().getSimpleName();
        String exceptionDescription =e.getLocalizedMessage();
        ExceptionMessage exceptionMessage = new ExceptionMessage(new Date(), exceptionDescription, exception);
        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(value = WeightOutOfBoundsException.class)
    public ResponseEntity<Object> WeightOutOfBounds(Exception e, WebRequest request){
        String exception = e.getClass().getSimpleName();
        String exceptionDescription = e.getLocalizedMessage();
        ExceptionMessage exceptionMessage = new ExceptionMessage(new Date(), exceptionDescription, exception);
        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Object> IllegalArgumentException(Exception e, WebRequest request){
        String exception = e.getClass().getSimpleName();
        String exceptionDescription = e.getLocalizedMessage();
        ExceptionMessage exceptionMessage = new ExceptionMessage(new Date(), exceptionDescription, exception);
        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(value = InvalidPutMethodException.class)
    public ResponseEntity<Object> InvalidPutMethod(Exception e, WebRequest request){
        String exception = e.getClass().getSimpleName();
        String exceptionDescription = e.getLocalizedMessage();
        ExceptionMessage exceptionMessage = new ExceptionMessage(new Date(), exceptionDescription, exception);
        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(value = FilterMaxQuantityException.class)
    public ResponseEntity<Object> FilterMaxQuantity(Exception e, WebRequest request){
        String exception = e.getClass().getSimpleName();
        String exceptionDescription = e.getLocalizedMessage();
        ExceptionMessage exceptionMessage = new ExceptionMessage(new Date(), exceptionDescription, exception);
        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = InvalidPetIDException.class)
    public ResponseEntity<Object> InvalidPetID(Exception e, WebRequest request){
        String exception = e.getClass().getSimpleName();
        String exceptionDescription = e.getLocalizedMessage();
        ExceptionMessage exceptionMessage = new ExceptionMessage(new Date(), exceptionDescription, exception);
        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = PetIdModifiedException.class)
    public ResponseEntity<Object> PetIdModified(Exception e, WebRequest request){
        String exception = e.getClass().getSimpleName();
        String exceptionDescription = e.getLocalizedMessage();
        ExceptionMessage exceptionMessage = new ExceptionMessage(new Date(), exceptionDescription, exception);
        return new ResponseEntity<>(exceptionMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
