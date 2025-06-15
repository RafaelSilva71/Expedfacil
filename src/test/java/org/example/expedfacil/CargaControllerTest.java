package org.example.expedfacil;

import org.example.expedfacil.controller.CargaController;
import org.example.expedfacil.controller.dto.CreateCargaDTO;
import org.example.expedfacil.model.Carga;
import org.example.expedfacil.repository.CargaRepository;
import org.example.expedfacil.service.CargaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class CargaControllerTest {

    @Mock
    private CargaService cargaService;

    @Mock
    private CargaRepository cargaRepository;

    @InjectMocks
    private CargaController cargaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarCargaComSucesso() {
        CreateCargaDTO dto = mock(CreateCargaDTO.class);
        Carga cargaMock = new Carga();
        cargaMock.setNumeroEmbarque("EMB001");

        when(cargaService.create(dto)).thenReturn(cargaMock);

        ResponseEntity<Carga> response = cargaController.criarCarga(dto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("EMB001", response.getBody().getNumeroEmbarque());
    }

    @Test
    void deveListarTodasAsCargas() {
        Carga c1 = new Carga(); c1.setNumeroEmbarque("EMB001");
        Carga c2 = new Carga(); c2.setNumeroEmbarque("EMB002");

        when(cargaRepository.findAll()).thenReturn(List.of(c1, c2));

        ResponseEntity<List<Carga>> response = cargaController.listarTodas();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void deveBuscarCargaPorId_Existente() {
        Carga carga = new Carga(); carga.setNumeroEmbarque("EMB001");

        when(cargaRepository.findById("EMB001")).thenReturn(Optional.of(carga));

        ResponseEntity<Carga> response = cargaController.buscarPorId("EMB001");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("EMB001", response.getBody().getNumeroEmbarque());
    }

    @Test
    void deveRetornar404AoBuscarCargaInexistente() {
        when(cargaRepository.findById("NOT_FOUND")).thenReturn(Optional.empty());

        ResponseEntity<Carga> response = cargaController.buscarPorId("NOT_FOUND");



        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void deveDeletarCargaQuandoExiste() {
        when(cargaRepository.existsById("EMB001")).thenReturn(true);

        ResponseEntity<Void> response = cargaController.deletar("EMB001");

        assertEquals(204, response.getStatusCodeValue());
        verify(cargaRepository).deleteById("EMB001");
    }

    @Test
    void deveRetornar404AoDeletarCargaInexistente() {
        when(cargaRepository.existsById("EMB999")).thenReturn(false);

        ResponseEntity<Void> response = cargaController.deletar("EMB999");

        assertEquals(404, response.getStatusCodeValue());
        verify(cargaRepository, never()).deleteById(any());
    }
}
