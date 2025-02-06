package com.example.CadastroProdutos.entities;

public enum UserRoles {

    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String role;

    UserRoles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
