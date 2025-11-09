package br.com.fiap.Portaria.dto;

import java.util.Date;

public class EncomendaRequestDTO {
    private String descricao;
    private Date dataRecebida;
    private String status;
    private Long idMorador;
    protected Long idRetirada;


    public Date getDataRecebida() {
        return dataRecebida;
    }

    public void setDataRecebida(Date dataRecebida) {
        this.dataRecebida = dataRecebida;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getIdMorador() {
        return idMorador;
    }

    public void setIdMorador(Long moradorId) {
        this.idMorador = idMorador;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIdRetirada() {
        return idRetirada;
    }

    public void setIdRetirada(Long idRetirada) {
        this.idRetirada = idRetirada;
    }
}