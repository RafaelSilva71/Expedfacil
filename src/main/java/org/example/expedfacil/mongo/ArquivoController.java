package org.example.expedfacil.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/arquivo")
public class ArquivoController {

    @Autowired
    private ArquivoService arquivoService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file,
                                         @RequestParam(value = "descricao", required = false) String descricao) {
        try {
            String id = arquivoService.salvarArquivo(file, descricao != null ? descricao : "sem descrição");
            return ResponseEntity.ok("Arquivo salvo com ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao salvar: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getArquivo(@PathVariable String id) {
        try {
            GridFsResource arquivo = arquivoService.buscarArquivoPorId(id);

            if (arquivo == null || !arquivo.exists()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + arquivo.getFilename() + "\"")
                    .contentType(MediaType.parseMediaType(arquivo.getContentType()))
                    .body(new InputStreamResource(arquivo.getInputStream()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao buscar arquivo: " + e.getMessage());
        }
    }
}
