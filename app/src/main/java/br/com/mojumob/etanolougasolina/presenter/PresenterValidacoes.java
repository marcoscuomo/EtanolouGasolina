package br.com.mojumob.etanolougasolina.presenter;

import br.com.mojumob.etanolougasolina.model.Combustivel;
import br.com.mojumob.etanolougasolina.ui.activity.MainActivity;

public class PresenterValidacoes {

    MainActivity mainActivity;

    public PresenterValidacoes(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void validaCampos(Combustivel combustivel) {

        if(combustivel.getValorEtanol().isEmpty() || combustivel.getValorGasolina().isEmpty()){

            if(combustivel.getValorEtanol().isEmpty()){
                mainActivity.erroValorEtanolEmBranco();
            }

            if(combustivel.getValorGasolina().isEmpty()){
                mainActivity.erroValorGasolinaEmBranco();
            }

        }

    }


}
