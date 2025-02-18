package com.example.CadastroProdutos.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.CadastroProdutos.dtos.ProductDTO;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.CadastroProdutos.entities.RegisterProduct;
import com.example.CadastroProdutos.repositories.ProductRepository;

@Service
public class ProductService {
	
	//REPOSITORIO QUE ACESSO O BD
	private final ProductRepository repository;
	
	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}

	//ADICIONAR UM PRODUTO
	public ResponseEntity<?> addProduct(RegisterProduct product) {
	return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(product));	
	}
	
	//LISTAR OS PRODUTOS NOME E PRECO
	public List<ProductDTO> getProducts() {
		return repository.getListProducts();
	}

	//Deletar produtor por id
	public String deleteProduct(Integer id) {

		if (repository.existsById(id)) {
			repository.deleteById(id);
			return "Product deleted";
		}

		return "Product not found";

	}
}
