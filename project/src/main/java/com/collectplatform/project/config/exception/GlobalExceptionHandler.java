package com.collectplatform.project.config.exception;

import com.alibaba.fastjson.JSONObject;
import com.collectplatform.core.common.R;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException  e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(o -> o.getField() + o.getDefaultMessage())
                .collect(Collectors.toList());
        return new R<String>(400, String.join(" ", collect));
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public R<String> missingServletRequestParameterExceptionHandler(ConstraintViolationException e) {
        return new R<>(400, e.getMessage());
    }


    @ExceptionHandler(value = Exception.class)
    public R<String> exceptionHandler(Exception e) {
//        return new R<>(500, "服务器内部错误");
        return new R<>(500, e.toString());
    }
}
