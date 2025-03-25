package com.example.CadastroProdutos.services;

import com.example.CadastroProdutos.dtos.ProductDTO;
import com.example.CadastroProdutos.entities.RegisterProduct;
import com.example.CadastroProdutos.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

	//Todos os Produtos
	public List<RegisterProduct> getAllProducts() {
		return repository.findAll();
	}

	//Deletar produtor por id
	public String deleteProduct(Integer id) {

		if (!repository.existsById(id)) {
			throw new RuntimeException("Product not found");
		}

		return "Product deleted";

	}
}
