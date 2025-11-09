package br.com.fiap.Portaria.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "TPL_RETIRADA")
public class Retirada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRetirada;

    private Date dataRetirada;
    private String tokenRetirada;

    @OneToOne
    @JoinColumn(name = "RETIRADA_MORDADOR_FK")
    private Morador morador;

    @OneToOne
    @JoinColumn(name = "RETIRADA_PORTARIA_FK")
    private Portaria portaria;

    public Long getIdRetirada() {
        return idRetirada;
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
