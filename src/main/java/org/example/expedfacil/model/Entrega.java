//package org.example.expedfacil.model;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//
//import java.util.Objects;
//
//@Entity
//public class Entrega {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String cliente;
//    private int OrdemCarregamento;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getCliente() {
//        return cliente;
//    }
//
//    public void setCliente(String cliente) {
//        this.cliente = cliente;
//    }
//
//    public int getOrdemCarregamento() {
//        return OrdemCarregamento;
//    }
//
//    public void setOrdemCarregamento(int ordemCarregamento) {
//        OrdemCarregamento = ordemCarregamento;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Entrega entrega = (Entrega) o;
//        return OrdemCarregamento == entrega.OrdemCarregamento && Objects.equals(id, entrega.id) && Objects.equals(cliente, entrega.cliente);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, cliente, OrdemCarregamento);
//    }
//
//    @Override
//    public String toString() {
//        return "Entrega{" +
//                "id=" + id +
//                ", cliente='" + cliente + '\'' +
//                ", OrdemCarregamento=" + OrdemCarregamento +
//                '}';
//    }
//
//    public void  calcularTotal(){
//        //colocar o metodo de calculo total
//    }
//}
