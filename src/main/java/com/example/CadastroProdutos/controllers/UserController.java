package com.example.CadastroProdutos.controllers;

import java.util.List;

import com.example.CadastroProdutos.dtos.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.CadastroProdutos.entities.RegisterProduct;
import com.example.CadastroProdutos.services.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:5500")
@RequestMapping("/products")
public class UserController {

	
	private final ProductService service;
	
	public UserController(ProductService service) {
		this.service = service;
	}

	//ADICIONAR UM NOVO PRODUTO NO BD
	@PostMapping("/add")
	public ResponseEntity<?> addProduct(@RequestBody RegisterProduct product){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.addProduct(product));
	}
	
	//BUSCA NOME E O PRECO DA TABELA E OS PRECOS ORDENADOS
	@GetMapping
	public List<ProductDTO> getProducts(){
		return service.getProducts();
	}
}
