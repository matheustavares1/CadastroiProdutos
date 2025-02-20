package com.example.CadastroProdutos.services;

import com.example.CadastroProdutos.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



//QUANDO ALGUEM TENTAR SE AUTENTICAR NA APLICACAO O SPRING VAI FAZER PRECISAr FAZER UM CONSULTA ENTAO UTILZIA ESTA CLASSE PARA FAZER A CONSULTA PARA ELE
@Service
public class AuthorizationServicer implements UserDetailsService {



    private final UserRepository userRepository;

    public AuthorizationServicer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
