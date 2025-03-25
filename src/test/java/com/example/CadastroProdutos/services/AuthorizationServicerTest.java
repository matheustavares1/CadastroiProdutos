package com.example.CadastroProdutos.services;

import com.example.CadastroProdutos.entities.User;
import com.example.CadastroProdutos.entities.UserRoles;
import com.example.CadastroProdutos.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorizationServicerTest {

    @InjectMocks
   private AuthorizationServicer service;

    @Mock
    private UserRepository repository;

    @Test
    void deveBuscarUsername(){

        //Arrange
        User user = new User("12","Matheus","senha", UserRoles.ADMIN);
        //Simulando que retorne o usuario Matheus
        doReturn(user).when(repository).findByUsername("Matheus");

        var output = service.loadUserByUsername("Matheus");
        //Verifica se nao retorna nulo
        assertNotNull(output);
        //Verifica se o retorno esta como esperado (retorne o usuario Matheus)
        assertEquals("Matheus", output.getUsername());

    }

    @Test
    void casoNaoEncontrado(){

        String username = "Matheus";
        //Simulando  o usuario nao encontrado e lancando excecao simulada
        when(repository.findByUsername(username)).thenThrow(new UsernameNotFoundException("User not found"));

        //Verifica se a excecao sera lancada corretamente
       UsernameNotFoundException exception =  assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername(username));

        //Verifica se o retorno vai esta como esperado
       assertEquals("User not found", exception.getMessage());

    }
}