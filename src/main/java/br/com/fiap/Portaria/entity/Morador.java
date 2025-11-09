package br.com.fiap.Portaria.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TPL_MORADOR")
public class Morador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMorador;

    private String nome;
    private Integer bloco;
    private String contato;

    @OneToOne
    @JoinColumn(name = "MORADOR_APARTAMENTO_FK")
    private Apartamento idApartamento;

    public Long getIdMorador() {
        return idMorador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public Integer getBloco() {
        return bloco;
    }

    public void setBloco(Integer bloco) {
        this.bloco = bloco;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Apartamento getIdApartamento() {
        return idApartamento;
    }

    public void setIdApartamento(Apartamento idApartamento) {
        this.idApartamento = idApartamento;
    }
}
