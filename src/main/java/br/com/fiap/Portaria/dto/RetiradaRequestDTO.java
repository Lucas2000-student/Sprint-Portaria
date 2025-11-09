package br.com.fiap.Portaria.dto;

import java.util.Date;

public class RetiradaRequestDTO {
    private Date dataRetirada;
    private String tokenRetirada;
    private Integer moradorId;
    private Integer portariaId;

    public Date getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(Date dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public String getTokenRetirada() {
        return tokenRetirada;
    }

    public void setTokenRetirada(String tokenRetirada) {
        this.tokenRetirada = tokenRetirada;
    }

    public Integer getMoradorId() {
        return moradorId;
    }

    public void setMoradorId(Integer moradorId) {
        this.moradorId = moradorId;
    }

    public Integer getPortariaId() {
        return portariaId;
    }

    public void setPortariaId(Integer portariaId) {
        this.portariaId = portariaId;
    }
}
