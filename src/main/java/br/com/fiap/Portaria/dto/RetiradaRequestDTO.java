package br.com.fiap.Portaria.dto;

import java.util.Date;

public class RetiradaRequestDTO {

    private Date dataRetirada;
    private String tokenRetirada;
    private Long moradorId;
    private Long portariaId;

    public Date getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(Date dataRetirada) {
        this.dataRetirada = dataRetirada;
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
