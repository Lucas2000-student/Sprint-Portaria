package br.com.fiap.Portaria.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "TPL_ENCOMENDA")
public class Encomenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEncomenda;

    private String descricao;
    private Date dataRecebida;
    private String status;

    @OneToOne
    @JoinColumn(name = "TPL_MORADOR_ID_MORADOR")
    private Morador morador;

    @OneToOne
    @JoinColumn(name = "TPL_RETIRADA_ID_RETIRADA")
    private Retirada retirada;

    public Long getIdEncomenda() {
        return idEncomenda;
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

    public Morador getMorador() {
        return morador;
    }

    public void setMorador(Morador morador) {
        this.morador = morador;
    }

    public Retirada getRetirada() {
        return retirada;
    }

    public void setRetirada(Retirada retirada) {
        this.retirada = retirada;
    }
}
