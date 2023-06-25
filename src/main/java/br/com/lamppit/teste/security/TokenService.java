package br.com.lamppit.teste.security;

import br.com.lamppit.teste.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    @Value("864000") //milisegundos
    private String expiration;
    @Value("${forum.jwt.secret}")
    private String secret;


    public String gerarToken(Authentication authentication) {
        Usuario logado = (Usuario) authentication.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
        return Jwts.builder()
                .setIssuer("VarejoOnline") //qual aplicação está gerando o token
                .setSubject(logado.getId().toString())  //dono de quem pertence a sessao
                .setIssuedAt(hoje) //data quando foi gerado
                .setExpiration(dataExpiracao) //tempo de expiração
                .signWith(SignatureAlgorithm.HS256, secret) // HS256,ger;a o algoritmo de cripografia   cripocrafia do token obs "buscar um programa que faça uma senha aleatoria grande de numeros"
                .compact(); //compacta tudo,

    }

    public boolean isTokenValido(String token) {
        try {

            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);//converte o token,  claimns passa o token
        } catch (Exception e) {
            return false;
        }
        return true;

    }

    public Long getId(String token) {

        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
