package br.com.fiap.Portaria.dto;

public class ApartamentoResponseDTO {

    private Integer idApartamento;
    private Integer Torre;
    private String bloco;
    private String numero;

    public ApartamentoResponseDTO(Integer idApartamento, Integer torre, String bloco, String numero) {
        this.idApartamento = idApartamento;
        this.Torre = torre;
        this.bloco = bloco;
        this.numero = numero;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public Integer getIdApartamento() {
        return idApartamento;
    }

    public void setIdApartamento(Integer idApartamento) {
        this.idApartamento = idApartamento;
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
