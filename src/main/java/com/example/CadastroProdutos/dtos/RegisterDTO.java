package com.example.CadastroProdutos.dtos;

import com.example.CadastroProdutos.entities.UserRoles;
//PEGAR DADOS APENAS DO REGISTRO
public record RegisterDTO(
        String username,
        String password,
        UserRoles roles
) {
}
