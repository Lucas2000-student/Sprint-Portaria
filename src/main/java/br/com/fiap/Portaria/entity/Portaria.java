package br.com.fiap.Portaria.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TPL_PORTARIA")
public class Portaria {

    @Id
    private Integer idPortaria;

    private String nomePorteiro;
    private String turno;
    private String contato;
    private Date dataRegistro;

    @OneToMany(mappedBy = "portaria")
    private List<Retirada> retiradas;

    public Portaria() {
        this.retiradas = new ArrayList<>();
    }

    public Integer getIdPortaria() {
        return idPortaria;
    }

    public void setIdPortaria(Integer idPortaria) {
        this.idPortaria = idPortaria;
    }

    public String getNomePorteiro() {
        return nomePorteiro;
    }

    public void setNomePorteiro(String nomePorteiro) {
        this.nomePorteiro = nomePorteiro;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public List<Retirada> getRetiradas() {
        return retiradas;
    }

    public void setRetiradas(List<Retirada> retiradas) {
        this.retiradas = retiradas;
    }
}