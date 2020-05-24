package io.github.sidaoswat.rest.controller;

import io.github.sidaoswat.exception.RegraNegocioException;
import io.github.sidaoswat.rest.ApiErros;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleRegraNegocioException(RegraNegocioException exception){
        String msgErro = exception.getMessage();
        return  new ApiErros(msgErro);
    }

}
