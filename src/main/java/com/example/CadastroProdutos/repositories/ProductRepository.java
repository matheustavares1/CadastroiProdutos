package com.example.CadastroProdutos.repositories;

import java.util.List;
import java.util.Optional;

import com.example.CadastroProdutos.dtos.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.CadastroProdutos.entities.RegisterProduct;

public interface ProductRepository extends JpaRepository<RegisterProduct, Integer> {
	//CONSULTA PARA LISTAR OS NOMES E OS PREÇOS DOS PRODUTOS
	@Query("SELECT new com.example.CadastroProdutos.dtos.ProductDTO(p.name, p.price) from RegisterProduct p ORDER BY p.price ASC")
	List<ProductDTO> getListProducts();




}
