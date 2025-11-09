package br.com.fiap.Portaria.dto;

import java.util.Date;

public class EncomendaRequestDTO {
    private String descricao;
    private Date dataRecebida;
    private String status;
    private Integer moradorId;
    private Integer retiradaId;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataRecebida() {
        return dataRecebida;
    }

    public void setDataRecebida(Date dataRecebida) {
        this.dataRecebida = dataRecebida;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getMoradorId() {
        return moradorId;
    }

    public void setMoradorId(Integer moradorId) {
        this.moradorId = moradorId;
    }

    public Integer getRetiradaId() {
        return retiradaId;
    }

    public void setRetiradaId(Integer retiradaId) {
        this.retiradaId = retiradaId;
    }
}