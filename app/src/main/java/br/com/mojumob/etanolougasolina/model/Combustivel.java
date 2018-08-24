package br.com.mojumob.etanolougasolina.model;

public class Combustivel {

    private String valorEtanol;
    private String valorGasolina;
    private String mediaEtanol;
    private String mediaGasolina;

    public Combustivel() {
    }

    public Combustivel(String valorEtanol, String valorGasolina) {
        this.valorEtanol = valorEtanol;
        this.valorGasolina = valorGasolina;
    }

    public Combustivel(String valorEtanol, String valorGasolina, String mediaEtanol, String mediaGasolina) {
        this.valorEtanol = valorEtanol;
        this.valorGasolina = valorGasolina;
        this.mediaEtanol = mediaEtanol;
        this.mediaGasolina = mediaGasolina;
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
