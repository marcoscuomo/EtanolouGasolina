package br.com.mojumob.etanolougasolina.presenter;

import java.text.DecimalFormat;
import br.com.mojumob.etanolougasolina.R;
import br.com.mojumob.etanolougasolina.task.Calcula;
import br.com.mojumob.etanolougasolina.ui.activity.MainActivity;

public class PresenterValidacoes implements Calcula.Presenter{

    MainActivity mainActivity;

    public PresenterValidacoes(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void validaCampos(String etanol, String gasolina, String mediaEtanol, String mediaGasolina) {

        if(etanol.isEmpty() || etanol.equals("R$0,00")){
            mainActivity.erroValorEtanolEmBranco();
        }else if(gasolina.isEmpty() || gasolina.equals("R$0,00")){
            mainActivity.erroValorGasolinaEmBranco();
        }else if (mediaEtanol.isEmpty() && mediaGasolina.isEmpty()){
            //Calculo realizado com a media padrao, pois o usuario não colocou sua media de consumo
            mainActivity.calculoNormal();
        }else if(mediaEtanol.isEmpty()){
            mainActivity.erroValorMediaEtanolVazio();
        }else if(mediaGasolina.isEmpty()){
            mainActivity.erroValorMediaGasolinaVazio();
        }else{
            // Calculo realizado com a media personalizada
            mainActivity.calculoPersonalizado();
        }
    }

    @Override
    public void calculaNormal(Double valorEtanol, Double valorGasolina) {

        Double resultadoDiv = valorEtanol / valorGasolina; //Calcula o percentual de defirença
        String percentualDiferenca = formataNumeroDeDoubleParaString(resultadoDiv);
        String titulo = "";
        String msg = "";

        if(resultadoDiv <= 0.7){
            mainActivity.escolhaEtanol(percentualDiferenca);
        }else{
            mainActivity.escolhaGasolina(percentualDiferenca);
        }
    }

    private String formataNumeroDeDoubleParaString(Double resDiv){

        //Formatando o resultado
        DecimalFormat df = new DecimalFormat("#.##%");
        String res = df.format(resDiv);

        return res;
    }

    @Override
    public void calculaPersonalizado(Double valorEtanol,
                                     Double valorGasolina,
                                     Double valorMediaEtanol,
                                     Double valorMediaGasolina) {

        Double resultadoDiv = valorEtanol / valorGasolina; //Calcula o percentual de defirença
        String percentualDiferenca = formataNumeroDeDoubleParaString(resultadoDiv);

        Double valorPersonalizado   = valorMediaEtanol / valorMediaGasolina;

        if(resultadoDiv <= valorPersonalizado){
            mainActivity.escolhaEtanol(percentualDiferenca);
        }else{
            mainActivity.escolhaGasolina(percentualDiferenca);
        }

    }

}
