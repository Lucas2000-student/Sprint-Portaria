package br.com.fiap.Portaria.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TPL_APARTAMENTO")
public class Apartamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idApartamento;

    private Integer torre;
    private String bloco;
    private String numero;

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public Long getIdApartamento() {
        return idApartamento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getTorre() {
        return torre;
    }

    public void setTorre(Integer torre) {
        this.torre = torre;
    }
}
