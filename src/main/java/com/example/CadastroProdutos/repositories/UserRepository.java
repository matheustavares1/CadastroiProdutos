package com.example.CadastroProdutos.repositories;

import com.example.CadastroProdutos.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {

    UserDetails findByUsername (String username);
}
