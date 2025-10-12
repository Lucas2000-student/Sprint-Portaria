package br.com.fiap.Portaria.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "TPL_PORTARIA")
public class Portaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPortaria;

    private String nomePorteiro;
    private String turno;
    private String contato;
    private Date dataRegistro;

    public Long getIdPortaria() {
        return idPortaria;
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
}
