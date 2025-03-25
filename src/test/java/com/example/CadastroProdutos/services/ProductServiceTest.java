package com.example.CadastroProdutos.services;

import com.example.CadastroProdutos.entities.RegisterProduct;
import com.example.CadastroProdutos.repositories.ProductRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//Inicializa todos o mocks
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    //Arrange
    //Act
    //Assert

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Captor
    private ArgumentCaptor<RegisterProduct> registerProductArgumentCaptor;



    @Nested
    class addProductTest {
        @Test
        void deverCriarProduto() {
            //Criando produto pra teste
            RegisterProduct input2 = new RegisterProduct(2, "Teste", "Testestestes", 12.4, true);

            //Capturo as info do input2 e retorno o input2
            when(productRepository.save(any(RegisterProduct.class))).thenReturn(input2);;

            //Chamada o metodo que ta sendo testado
            var output = productService.addProduct(input2);

            //Verifico se nao é nulo
            assertNotNull(output);

            //Verifica se o metodo save foi chamado 1 vez e caputra os valores
            verify(productRepository, times(1)).save(registerProductArgumentCaptor.capture());

            //Armazeno o valor capturado no .save()
            var captured = registerProductArgumentCaptor.getValue();

            //Verifico se os valores capturados batem
            assertEquals(captured.getId(), input2.getId());
            assertEquals(captured.getName(), input2.getName());
            assertEquals(captured.getDescription(), input2.getDescription());
            assertEquals(captured.getPrice(), input2.getPrice());

        }
    }
    @Nested
    class getAllProductsTest {
        @Test
        void deveListarTodosProdutos() {

            //Arrange
            List<RegisterProduct> products = List.of(
                    new RegisterProduct(2, "Teste", "Testestestes", 12.4, true),
                    new RegisterProduct(3, "Teste2", "Testestestes", 12.5, false)
            );

            //Simulando que o findAll Retorne a Lista
            when(productRepository.findAll()).thenReturn(products);

            //Chamada o metodo que ta sendo testado
            var output = productService.getAllProducts();

            //Verifica se a lista retornada nbao é nula
            assertNotNull(output);

            //Verificacando se a quantidade de produtos retornados é esperado
            assertEquals(2, output.size());

            //Verifica se os produtos sao os mesmo da lista simulada
            assertEquals("Teste", output.get(0).getName());
            assertEquals("Teste2", output.get(1).getName());

        }
    }

    @Nested
    class deletarPorIdTest {

        @Test
        void deveDeletarPorId() {

            //Arrange
            var input = new RegisterProduct(2, "Teste", "Testestestes", 12.4, true);

            /*
            Como eu tenho a verificacao de ExistById no metodo, aqui eu verifico se bate. Case o o existById for true ele pode ser deletado
            BASICAMENTE EU TO SIMULANDO UMA BUSCA NO BANCO DE DADOS DO ID PASSADO, CASO EXISTA TRUE( E PODE SER APAGADO)
             */
            when(productRepository.existsById(input.getId())).thenReturn(true);

            //Como o deleteById é void a gente usa o doNothing para nao da error
            doNothing().when(productRepository).deleteById(input.getId());

            //ACT

            //Metodo que ta sendo testado de fato
            String output = productService.deleteProduct(2);

            //Asserts

            //Verificacao se a resposta do metodo esta correta
            assertEquals("Product deleted", output);
        }

        @Test
        void casoNaoAcheIdTest(){

            //Arrange
            var input = new RegisterProduct(2, "Teste", "Testestestes", 12.4, true);

            //Caso o ID nao exista no BD ele retorna FALSE  etnao pode ser deletado pois nao existe
            //Como o existById é um boolean retorna false
            when(productRepository.existsById(input.getId())).thenReturn(false);

            //Verificando se a excecao correta sera lancada
            RuntimeException exception = assertThrows(RuntimeException.class, () -> productService.deleteProduct(2));

            //Asserts

            //Verificando a resposta do metodo esta como esperado
            assertEquals("Product not found", exception.getMessage());

            //Garantindo que o metodo nunca seja chamado quando o id nao for encontrado
            verify(productRepository, never()).deleteById(input.getId());
        }
    }
}