package kr.co.polycube.backendtest.er;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class, HttpMessageNotReadableException.class})
    public Object handleException(Exception e) {
        return new ResponseEntity<>(new ErrorRes(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NoResourceFoundException.class})
    public Object handleNoResourceFoundException(Exception e) {
        return new ResponseEntity<>(new ErrorRes(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        if (!e.getClass().equals(MethodArgumentNotValidException.class)) {
            log.info("e.getclass is {}", e.getClass());
            return handleMethodArgumentException();
        }

        String errorMessage = e.getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage();

        return new ResponseEntity<>(new ErrorRes(errorMessage), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, MissingServletRequestParameterException.class, MissingServletRequestPartException.class})
    public Object handleMethodArgumentException() {
        return new ResponseEntity<>(
                new ErrorRes("parameter 명이나 type, value, 개수가 올바른지 확인해주세요")
                , HttpStatus.BAD_REQUEST);
    }
}
