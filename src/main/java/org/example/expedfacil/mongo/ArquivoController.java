package org.example.expedfacil.mongo;

import org.example.expedfacil.model.Carga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/arquivo")
public class ArquivoController {

    @Autowired
    private ArquivoService arquivoService;

    @PostMapping("/upload/{numeroEmbarque}")
    public ResponseEntity<String> uploadNotaFiscal(@RequestParam("file") MultipartFile file,
                                                   @PathVariable String numeroEmbarque) {
        try {
            String id = arquivoService.salvarNotaFiscalComNumeroEmbarque(file, numeroEmbarque);
            return ResponseEntity.ok("Nota fiscal salva com ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao salvar nota fiscal: " + e.getMessage());
        }
    }

    @GetMapping("/nota-fiscal/{numeroEmbarque}")
    public ResponseEntity<?> getNotaFiscalPorCarga(@PathVariable String numeroEmbarque) {
        try {
            GridFsResource arquivo = arquivoService.buscarNotaFiscalPorNumeroEmbarque(numeroEmbarque);

            if (arquivo == null || !arquivo.exists()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + arquivo.getFilename() + "\"")
                    .contentType(MediaType.parseMediaType(arquivo.getContentType()))
                    .body(new InputStreamResource(arquivo.getInputStream()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao buscar nota fiscal: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getArquivoPorId(@PathVariable String id) {
        try {
            GridFsResource arquivo = arquivoService.buscarArquivoPorId(id);

            if (arquivo == null || !arquivo.exists()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + arquivo.getFilename() + "\"")
                    .contentType(MediaType.parseMediaType(arquivo.getContentType()))
                    .body(new InputStreamResource(arquivo.getInputStream()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao buscar arquivo: " + e.getMessage());
        }
    }

    @DeleteMapping("/nota-fiscal/{numeroEmbarque}")
    public ResponseEntity<String> deletarNotaFiscalPorNumeroEmbarque(@PathVariable String numeroEmbarque) {
        try {
            boolean removido = arquivoService.deletarNotaFiscalPorNumeroEmbarque(numeroEmbarque);

            if (removido) {
                return ResponseEntity.ok("Nota fiscal removida com sucesso para a carga: " + numeroEmbarque);
            } else {
                return ResponseEntity.status(404).body("Nenhuma nota fiscal encontrada para o número de embarque: " + numeroEmbarque);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao deletar a nota fiscal: " + e.getMessage());
        }
    }


}
