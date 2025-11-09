package br.com.fiap.Portaria.dto;

import java.util.Date;

public class PortariaResponseDTO {

    private Integer idPortaria;
    private String nomePorteiro;
    private String turno;
    private String contato;
    private Date dataRegistro;

    public PortariaResponseDTO() {
    }

    public PortariaResponseDTO(Integer idPortaria, String nomePorteiro, String turno, String contato, Date dataRegistro) {
        this.idPortaria = idPortaria;
        this.nomePorteiro = nomePorteiro;
        this.turno = turno;
        this.contato = contato;
        this.dataRegistro = dataRegistro;
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
}
