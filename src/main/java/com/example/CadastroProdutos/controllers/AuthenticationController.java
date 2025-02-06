package com.example.CadastroProdutos.controllers;


import com.example.CadastroProdutos.dtos.AuthenticationDTO;
import com.example.CadastroProdutos.dtos.RegisterDTO;
import com.example.CadastroProdutos.entities.User;
import com.example.CadastroProdutos.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
     UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        //FAZER UM TOKEN DO MEU LOGIN E SENHA
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        //RECEBE O TOKEN POR PARAMETRO(USUARIO E SENHA) PARA AUTENTICAR
        var auth = this.authenticationManager.authenticate(usernamePassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.userRepository.findByUsername(data.username()) != null) return ResponseEntity.badRequest().build();

        //REFERENTE AO BEAN DA CLASSE DE CONFIGURACAO DE SEGURANCA
        String encryptedPassword = passwordEncoder.encode(data.password());
        User newUser = new User(data.username(), encryptedPassword, data.roles());

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        this.userRepository.deleteById(id);
    }
}
