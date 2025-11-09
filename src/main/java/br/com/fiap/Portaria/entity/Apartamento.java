package br.com.fiap.Portaria.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TPL_APARTAMENTO")
public class Apartamento {

    @Id
    private Integer idApartamento;

    private Integer torre;
    private String bloco;
    private String numero;

    // NOVO: Relacionamento com Morador
    @OneToMany(mappedBy = "apartamento")
    private List<Morador> moradores;

    // Construtores
    public Apartamento() {
        this.moradores = new ArrayList<>();
    }

    public Integer getIdApartamento() {
        return idApartamento;
    }

    public void setIdApartamento(Integer idApartamento) {
        this.idApartamento = idApartamento;
    }

    public Integer getTorre() {
        return torre;
    }

    public void setTorre(Integer torre) {
        this.torre = torre;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public List<Morador> getMoradores() {
        return moradores;
    }

    public void setMoradores(List<Morador> moradores) {
        this.moradores = moradores;
    }
}
