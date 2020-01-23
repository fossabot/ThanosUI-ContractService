package com.github.abigail830.ThanosContractService.exception;

import com.mongodb.MongoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public ResponseEntity<?> bizExceptionHandler(BizException e) {
        if (e.getCode()!=null)
            return new ResponseEntity<>(e.getCode().name(), HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler(value = MongoException.class)
    @ResponseBody
    public ResponseEntity<?> mongoExceptionHandler(MongoException ex) {
        log.warn("Mongo operation fail : "+ ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
