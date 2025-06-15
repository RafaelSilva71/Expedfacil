package org.example.expedfacil.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ConferenciaLotes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numeroEmbarqueOriginal;

    private LocalDateTime dataRegistro = LocalDateTime.now();

    @OneToMany(mappedBy = "conferencia", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<EntregaConferida> entregasConferidas = new ArrayList<>();



    public Long getId() {
        return id;
    }

    public String getNumeroEmbarqueOriginal() {
        return numeroEmbarqueOriginal;
    }

    public void setNumeroEmbarqueOriginal(String numeroEmbarqueOriginal) {
        this.numeroEmbarqueOriginal = numeroEmbarqueOriginal;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public List<EntregaConferida> getEntregasConferidas() {
        return entregasConferidas;
    }

    public void setEntregasConferidas(List<EntregaConferida> entregasConferidas) {
        this.entregasConferidas = entregasConferidas;
        this.entregasConferidas.forEach(e -> e.setConferencia(this)); // garante o vínculo
    }
}
