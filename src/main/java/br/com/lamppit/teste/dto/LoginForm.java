package br.com.lamppit.teste.dto;

import jdk.jfr.Name;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class LoginForm {

    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private String senha;


    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }
}
