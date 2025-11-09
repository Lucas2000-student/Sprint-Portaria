package br.com.fiap.Portaria.dto;

public class ApartamentoRequestDTO {

    private Integer Torre;
    private String bloco;
    private String numero;

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

    public Integer getTorre() {
        return Torre;
    }

    public void setTorre(Integer torre) {
        Torre = torre;
    }
}
