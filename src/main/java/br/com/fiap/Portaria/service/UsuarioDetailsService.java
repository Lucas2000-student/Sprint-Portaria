package br.com.fiap.Portaria.service;

import br.com.fiap.Portaria.repository.MoradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private MoradorRepository moradorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var morador = moradorRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Morador não encontrado: " + username));

        return User.builder()
                .username(morador.getEmail())
                .password("") // senha gerenciada pelo Firebase
                .roles(morador.getRole().name())
                .build();
    }
}