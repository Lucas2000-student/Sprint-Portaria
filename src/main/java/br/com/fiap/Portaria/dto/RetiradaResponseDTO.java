package br.com.fiap.Portaria.dto;

import java.util.Date;

public class RetiradaResponseDTO {

    private Long idRetirada;
    private Date dataRetirada;
    private String tokenRetirada;
    private Long moradorId;
    private Long portariaId;

    public RetiradaResponseDTO() {
    }

    public RetiradaResponseDTO(Long idRetirada, Date dataRetirada, String tokenRetirada, Long moradorId, Long portariaId) {
        this.idRetirada = idRetirada;
        this.dataRetirada = dataRetirada;
        this.tokenRetirada = tokenRetirada;
        this.moradorId = moradorId;
        this.portariaId = portariaId;
    }

    public Date getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(Date dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public Long getIdRetirada() {
        return idRetirada;
    }

    public void setIdRetirada(Long idRetirada) {
        this.idRetirada = idRetirada;
    }

    public Long getMoradorId() {
        return moradorId;
    }

    public void setMoradorId(Long moradorId) {
        this.moradorId = moradorId;
    }

    public Long getPortariaId() {
        return portariaId;
    }

    public void setPortariaId(Long portariaId) {
        this.portariaId = portariaId;
    }

    public String getTokenRetirada() {
        return tokenRetirada;
    }

    public void setTokenRetirada(String tokenRetirada) {
        this.tokenRetirada = tokenRetirada;
    }
}
