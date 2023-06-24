package br.com.lamppit.teste.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalCustomException {


    @Autowired
    private MessageSource messageSource;


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public MessageGlobalException validacaoCampos(MethodArgumentNotValidException exception) {

        List<ValidacaoDto> validacaoDtos = new ArrayList<>();

        MessageGlobalException messageGlobalException = new MessageGlobalException
                (LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "erro de digitação", validacaoDtos);


        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors(); //pega o erro

        fieldErrors.forEach(fieldError -> {
            String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());  //localeContext serve para ver a posicção atual da list
            ValidacaoDto erro = new ValidacaoDto(fieldError.getField(), message);
            validacaoDtos.add(erro);

        });

        return messageGlobalException;

    }


}
