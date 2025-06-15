package org.example.expedfacil;

import org.example.expedfacil.controller.ProdutoController;
import org.example.expedfacil.controller.dto.CreateProdutoDTO;
import org.example.expedfacil.controller.dto.UpdateProdutoDTO;
import org.example.expedfacil.model.Produto;
import org.example.expedfacil.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ProdutoControllerTest {

    @Mock
    private ProdutoService produtoService;

    @InjectMocks
    private ProdutoController produtoController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProdutoTest() {
        CreateProdutoDTO dto = new CreateProdutoDTO("123456.78", "Arroz", 10, 5);
        Produto produto = new Produto("123456.78", "Arroz", 10, 5);

        when(produtoService.createProduto(dto)).thenReturn(produto);

        ResponseEntity<String> response = produtoController.createProduto(dto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("123456.78", response.getBody());
    }

    @Test
    void buscarProdutoTest() {
        Produto produto = new Produto("123456.78", "Arroz", 10, 5);

        when(produtoService.buscarPorId("123456.78")).thenReturn(produto);

        ResponseEntity<Produto> response = produtoController.getProdutoById("123456.78");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Arroz", response.getBody().getNome());
    }

    @Test
    void listarProdutoId() {
        List<Produto> lista = Arrays.asList(
                new Produto("123456.78", "Arroz", 10, 5),
                new Produto("654321.00", "Feijão", 12, 8)
        );

        when(produtoService.ListarProdutos()).thenReturn(lista);

        ResponseEntity<List<Produto>> response = produtoController.getAllProdutos();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void atualizarProdutoTest() {
        UpdateProdutoDTO dto = new UpdateProdutoDTO("Arroz Integral", "Arroz", 12, 6);

        ResponseEntity<Void> response = produtoController.updateProdutoById("123456.78", dto);

        verify(produtoService).updateProduto("123456.78", dto);
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void deletarProdutoTest() {
        ResponseEntity<Void> response = produtoController.deleteById("123456.78");

        verify(produtoService).deleteById("123456.78");
        assertEquals(204, response.getStatusCodeValue());
    }
}
