package br.com.mojumob.etanolougasolina.presenter;

import br.com.mojumob.etanolougasolina.ui.activity.MainActivity;

public class PresenterValidacoes {

    MainActivity mainActivity;

    public PresenterValidacoes(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void validaCampos(String etanol, String gasolina, String mediaEtanol, String mediaGasolina) {

        if(etanol.isEmpty() || etanol.equals("R$0,00")){
            mainActivity.erroValorEtanolEmBranco();
        }else if(gasolina.isEmpty() || gasolina.equals("R$0,00")){
            mainActivity.erroValorGasolinaEmBranco();
        }else if (mediaEtanol.isEmpty() && mediaGasolina.isEmpty()){
            //Calculo realizado com a media padrao, pois o usuario não colocou sua media de consumo

        }else if(mediaEtanol.isEmpty()){
            mainActivity.erroValorMediaEtanolVazio();
        }else if(mediaGasolina.isEmpty()){
            mainActivity.erroValorMediaGasolinaVazio();
        }else{
            // Calculo realizado com a media personalizada

        }
    }



}
