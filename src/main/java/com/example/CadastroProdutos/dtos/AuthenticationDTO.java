package com.example.CadastroProdutos.dtos;
//PEGAR DADOS APENAS NO LOGIN
public record AuthenticationDTO(
        String username,
        String password
) {
}
