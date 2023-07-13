package br.com.lamppit.teste.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageGlobalException {

    private LocalDateTime data;

    private Integer status;

    private String message;

    private List<ErrorValidation> errorValidation = new ArrayList<>();


    public MessageGlobalException(LocalDateTime data, Integer status) {
        this.data = data;
        this.status = status;
    }

    public MessageGlobalException(LocalDateTime data, Integer status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }


}
