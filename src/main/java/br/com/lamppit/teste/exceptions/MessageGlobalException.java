package br.com.lamppit.teste.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
public class MessageGlobalException {

    private LocalDateTime data;

    private Integer status;

    private String message;

    private List<ValidacaoDto> validacaoDtos = new ArrayList<>();


    public MessageGlobalException(LocalDateTime data, Integer status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }
}
