package br.com.fiap.Portaria.dto;

import java.util.Date;

public class EncomendaResponseDTO {
    private Long id;
    private String descricao;
    private Date dataRecebida;
    private String status;
    private Long idMorador;
    private Long idRetirada;

    public EncomendaResponseDTO() {}

    public EncomendaResponseDTO(Long id, String descricao, Date dataRecebida, String status, Long idMorador, Long idRetirada) {
        this.id = id;
        this.descricao = descricao;
        this.dataRecebida = dataRecebida;
        this.status = status;
        this.idMorador = idMorador;
        this.idRetirada = idRetirada;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdMorador() {
        return idMorador;
    }

    public void setIdMorador(Long idMorador) {
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