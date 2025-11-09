package br.com.fiap.Portaria.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TPL_MORADOR")
public class Morador {

    @Id
    private Integer idMorador;

    private String nome;
    private String contato;

    @ManyToOne
    @JoinColumn(name = "ID_APARTAMENTO")
    private Apartamento apartamento;

    @OneToMany(mappedBy = "morador")
    private List<Encomenda> encomendas;

    @OneToMany(mappedBy = "morador")
    private List<Retirada> retiradas;

    public Morador() {
        this.encomendas = new ArrayList<>();
        this.retiradas = new ArrayList<>();
    }

    public Integer getIdMorador() {
        return idMorador;
    }

    public void setIdMorador(Integer idMorador) {
        this.idMorador = idMorador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Apartamento getApartamento() {
        return apartamento;
    }

    public void setApartamento(Apartamento apartamento) {
        this.apartamento = apartamento;
    }

    public List<Encomenda> getEncomendas() {
        return encomendas;
    }

    public void setEncomendas(List<Encomenda> encomendas) {
        this.encomendas = encomendas;
    }

    public List<Retirada> getRetiradas() {
        return retiradas;
    }

    public void setRetiradas(List<Retirada> retiradas) {
        this.retiradas = retiradas;
    }
}
