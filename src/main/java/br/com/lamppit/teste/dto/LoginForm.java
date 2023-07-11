package br.com.lamppit.teste.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Name;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


@Data
@NoArgsConstructor
public class LoginForm {

    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private String senha;



}
