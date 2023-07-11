package br.com.lamppit.teste.security;

import br.com.lamppit.teste.dto.LoginForm;
import br.com.lamppit.teste.model.Perfil;
import br.com.lamppit.teste.model.Usuario;
import br.com.lamppit.teste.repository.PerfilRepository;
import br.com.lamppit.teste.repository.UsuarioRepository;
import br.com.lamppit.teste.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationDetails implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username);
    }
}

