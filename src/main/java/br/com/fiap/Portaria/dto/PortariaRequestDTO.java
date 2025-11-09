package br.com.fiap.Portaria.dto;

import java.util.Date;

public class PortariaRequestDTO {

    private String nomePorteiro;
    private String turno;
    private String contato;
    private Date dataRegistro;

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
}
