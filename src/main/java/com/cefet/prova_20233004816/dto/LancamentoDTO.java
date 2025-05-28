package com.cefet.prova_20233004816.dto;

public class LancamentoDTO {

    private Long id;
    private Double valor;
    private Double idConta;
    private String numeroConta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getIdConta() {
        return idConta;
    }

    public void setIdConta(Double idConta) {
        this.idConta = idConta;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }
}
