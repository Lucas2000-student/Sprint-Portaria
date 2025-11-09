package br.com.fiap.Portaria.dto;

import java.util.Date;

public class EncomendaResponseDTO {
    private Integer id;
    private String descricao;
    private Date dataRecebida;
    private String status;
    private Integer moradorId;
    private Integer retiradaId;

    public EncomendaResponseDTO() {}

    public EncomendaResponseDTO(Integer id, String descricao, Date dataRecebida, String status, Integer moradorId, Integer retiradaId) {
        this.id = id;
        this.descricao = descricao;
        this.dataRecebida = dataRecebida;
        this.status = status;
        this.moradorId = moradorId;
        this.retiradaId = retiradaId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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