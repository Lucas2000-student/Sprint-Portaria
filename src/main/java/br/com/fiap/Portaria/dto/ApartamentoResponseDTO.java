package br.com.fiap.Portaria.dto;

public class ApartamentoResponseDTO {

    private Integer idApartamento;
    private Integer torre;
    private String bloco;
    private String numero;

    public ApartamentoResponseDTO(Integer idApartamento, Integer torre, String bloco, String numero) {
        this.idApartamento = idApartamento;
        this.torre = torre;
        this.bloco = bloco;
        this.numero = numero;
    }

    public Integer getIdApartamento() {
        return idApartamento;
    }

    public void setIdApartamento(Integer idApartamento) {
        this.idApartamento = idApartamento;
    }

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
