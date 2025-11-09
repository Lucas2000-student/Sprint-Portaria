package br.com.fiap.Portaria.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "TPL_RETIRADA")
public class Retirada {

    @Id
    private Integer idRetirada;

    private Date dataRetirada;
    private String tokenRetirada;

    @ManyToOne
    @JoinColumn(name = "ID_MORADOR")
    private Morador morador;

    @ManyToOne
    @JoinColumn(name = "ID_PORTARIA")
    private Portaria portaria;

    public Integer getIdRetirada() {
        return idRetirada;
    }

    public void setIdRetirada(Integer idRetirada) {
        this.idRetirada = idRetirada;
    }

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

    public Morador getMorador() {
        return morador;
    }

    public void setMorador(Morador morador) {
        this.morador = morador;
    }

    public Portaria getPortaria() {
        return portaria;
    }

    public void setPortaria(Portaria portaria) {
        this.portaria = portaria;
    }
}
