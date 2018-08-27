package br.com.mojumob.etanolougasolina.task;

public interface Calcula {

    interface View{
        void erroValorEtanolEmBranco();
        void erroValorGasolinaEmBranco();
        void calculoNormal();
        void erroValorMediaEtanolVazio();
        void erroValorMediaGasolinaVazio();
        void calculoPersonalizado();
        void escolhaEtanol(String percentualDiferenca);
        void escolhaGasolina(String percentualDiferenca);

    }

    interface Presenter{
        void validaCampos(String etanol, String gasolina, String mediaEtanol, String mediaGasolina);
        void calculaNormal(Double valorEtanol, Double valorGasolina);
        void calculaPersonalizado(Double valorEtanol,
                                  Double valorGasolina,
                                  Double valorMediaEtanol,
                                  Double valorMediaGasolina);
    }

}
