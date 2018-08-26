package br.com.mojumob.etanolougasolina.task;

public interface Calcula {

    interface View{
        void erroValorEtanolEmBranco();
        void erroValorGasolinaEmBranco();
        void calculoNormal();
        void erroValorMediaEtanolVazio();
        void erroValorMediaGasolinaVazio();
        void calculoPersonalizado();
        void escolhaEtanol();
        void escolhaGasolina();

    }

    interface Presenter{
        void validaCampos();
        void calculaNormal();
        void formataNumeroDeDoubleParaString();
        void calculaPersonalizado();
    }

}
