package br.com.mojumob.etanolougasolina.model;

public class Combustivel {

    private String valorEtanol;
    private String valorGasolina;
    private String valorRawEtanol;
    private String valorRawGasolina;
    private String mediaEtanol;
    private String mediaGasolina;
    private Double precoEtanol;
    private Double precoGasolina;

    public String getValorRawEtanol() {
        return valorRawEtanol;
    }

    public void setValorRawEtanol(String valorRawEtanol) {
        this.valorRawEtanol = valorRawEtanol;
    }

    public String getValorRawGasolina() {
        return valorRawGasolina;
    }

    public void setValorRawGasolina(String valorRawGasolina) {
        this.valorRawGasolina = valorRawGasolina;
    }

    public Double getPrecoEtanol() {
        return precoEtanol;
    }

    public void setPrecoEtanol(Double precoEtanol) {
        this.precoEtanol = precoEtanol;
    }

    public Double getPrecoGasolina() {
        return precoGasolina;
    }

    public void setPrecoGasolina(Double precoGasolina) {
        this.precoGasolina = precoGasolina;
    }

    public String getValorEtanol() {
        return valorEtanol;
    }

    public void setValorEtanol(String valorEtanol) {
        this.valorEtanol = valorEtanol;
    }

    public String getValorGasolina() {
        return valorGasolina;
    }

    public void setValorGasolina(String valorGasolina) {
        this.valorGasolina = valorGasolina;
    }

    public String getMediaEtanol() {
        return mediaEtanol;
    }

    public void setMediaEtanol(String mediaEtanol) {
        this.mediaEtanol = mediaEtanol;
    }

    public String getMediaGasolina() {
        return mediaGasolina;
    }

    public void setMediaGasolina(String mediaGasolina) {
        this.mediaGasolina = mediaGasolina;
    }
}
