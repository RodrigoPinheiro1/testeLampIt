package br.com.lamppit.teste.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@ControllerAdvice
public class EmpresaFechadaException extends RuntimeException {


    @ResponseBody
    @ExceptionHandler(EmpresaFechadaException.class)
    public ResponseEntity<MessageGlobalException> lojaFechada() {

        MessageGlobalException globalException = new MessageGlobalException(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Loja fechada para pedidos");

        return new ResponseEntity<>(globalException, HttpStatus.BAD_REQUEST);
    }
}
