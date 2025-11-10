package br.com.fiap.Portaria.dto;

public class ApartamentoRequestDTO {

    private Integer torre;
    private String bloco;
    private String numero;

    public Integer getTorre() {
        return torre;
    }

    public void setTorre(Integer torre) {
        this.torre = torre;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
