package br.com.fiap.Portaria.dto;

public class MoradorRequestDTO {

    private String nome;
    private Integer bloco;
    private String contato;
    private Long idApartamento;

    public Integer getBloco() {
        return bloco;
    }

    public void setBloco(Integer bloco) {
        this.bloco = bloco;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdApartamento() {
        return idApartamento;
    }

    public void setIdApartamento(Long idApartamento) {
        this.idApartamento = idApartamento;
    }
}
