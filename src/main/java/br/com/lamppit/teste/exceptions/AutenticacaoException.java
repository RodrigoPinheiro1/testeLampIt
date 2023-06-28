package br.com.lamppit.teste.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.AuthenticationException;
import java.time.LocalDateTime;
@ControllerAdvice
public class AutenticacaoException extends RuntimeException {
    

    @ResponseBody
    @ExceptionHandler(AutenticacaoException.class)
    public ResponseEntity<MessageGlobalException> exceptionAuth() {

        MessageGlobalException messageGlobalException = new MessageGlobalException(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),"Usuario inexistente ou senha invalida");

        return new ResponseEntity<>(messageGlobalException, HttpStatus.NOT_FOUND);
    }

}
