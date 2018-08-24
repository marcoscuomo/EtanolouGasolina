package br.com.mojumob.etanolougasolina.model;

public class Combustivel {

    String tipoCombistivel;
    String preco;

    public Combustivel() {
    }

    public Combustivel(String tipoCombistivel, String preco) {
        this.tipoCombistivel = tipoCombistivel;
        this.preco = preco;
    }

    public String getTipoCombistivel() {
        return tipoCombistivel;
    }

    public void setTipoCombistivel(String tipoCombistivel) {
        this.tipoCombistivel = tipoCombistivel;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
}
