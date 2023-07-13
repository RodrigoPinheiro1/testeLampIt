package br.com.lamppit.teste.exceptions.Status;

import br.com.lamppit.teste.exceptions.MessageGlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@ControllerAdvice
public class AguardarFinalizarEntregaException extends RuntimeException {




    @ResponseBody

    @ExceptionHandler(AguardarFinalizarEntregaException.class)
    public ResponseEntity<MessageGlobalException> aguardarEntrega() {

        MessageGlobalException messageGlobalException = new MessageGlobalException(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(), "Aguardar Entregador Finalizar Entrega");

        return new ResponseEntity<>(messageGlobalException, HttpStatus.NOT_FOUND);
    }


}
