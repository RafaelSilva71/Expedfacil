package org.example.expedfacil;

import org.example.expedfacil.controller.dto.CreateProdutoDTO;
import org.example.expedfacil.controller.dto.UpdateProdutoDTO;
import org.example.expedfacil.exception.ProdutoJaExisteException;
import org.example.expedfacil.model.Produto;
import org.example.expedfacil.repository.ProdutoRepository;
import org.example.expedfacil.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProdutoServiceTest {

    private ProdutoRepository produtoRepository;
    private ProdutoService produtoService;

    @BeforeEach
    void setup() {
        produtoRepository = mock(ProdutoRepository.class);
        produtoService = new ProdutoService(produtoRepository);
    }
    @Test
    void createProdutoTest() {
        CreateProdutoDTO dto = new CreateProdutoDTO("123456.78", "Arroz", 10, 5);
        Produto produtoSalvo = new Produto("123456.78", "Arroz", 10, 5);

        when(produtoRepository.existsById("123456.78")).thenReturn(false);
        when(produtoRepository.save(produtoSalvo)).thenReturn(produtoSalvo);

        Produto resultado = produtoService.createProduto(dto);

        assertEquals("123456.78", resultado.getId());
        assertEquals("Arroz", resultado.getNome());

    }

    @Test
    void buscarProdutoPorIdTest() {
        Produto produto = new Produto("123456.78", "Arroz", 10, 5);
        when(produtoRepository.findById("123456.78")).thenReturn(Optional.of(produto));

        Produto resultado = produtoService.buscarPorId("123456.78");

        assertNotNull(resultado);
        assertEquals("Arroz", resultado.getNome());
        assertEquals(10, resultado.getQuantPorCaixa());
    }

    @Test
    void updateProdutoTest() {
        Produto produtoExistente = new Produto("123456.78", "Arroz", 10, 5);
        UpdateProdutoDTO updateDto = new UpdateProdutoDTO("Arroz Integral", "Arroz" , 7, 12);

        when(produtoRepository.findById("123456.78")).thenReturn(Optional.of(produtoExistente));
        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoExistente);

        produtoService.updateProduto("123456.78", updateDto);

        assertEquals("Arroz Integral", produtoExistente.getNome());
        assertEquals(7, produtoExistente.getQuantPorCaixa());
        assertEquals(12, produtoExistente.getQuantCxFd());

        verify(produtoRepository).save(produtoExistente);
    }

    @Test
    void deleteProdutoTest() {
        doNothing().when(produtoRepository).deleteById("123456.78");
        produtoService.deleteById("123456.78");
        verify(produtoRepository, times(1)).deleteById("123456.78");
    }

    @Test
    void produtoComMesmoId() {
        CreateProdutoDTO dto = new CreateProdutoDTO("123456.78", "Feijão", 10, 5);
        when(produtoRepository.existsById("123456.78")).thenReturn(true);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            produtoService.createProduto(dto);
        });

        assertTrue(ex.getMessage().contains("Já existe um produto"));
    }
}
