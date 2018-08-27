package br.com.mojumob.etanolougasolina.presenter;

import java.text.DecimalFormat;
import br.com.mojumob.etanolougasolina.task.Calcula;

public class PresenterValidacoes implements Calcula.Presenter{

    private Calcula.View view;

    public PresenterValidacoes(Calcula.View mainActivity) {
        this.view = mainActivity;
    }

    @Override
    public void validaCampos(String etanol, String gasolina, String mediaEtanol, String mediaGasolina) {

        if(etanol.isEmpty() || etanol.equals("R$0,00")){
            view.erroValorEtanolEmBranco();
        }else if(gasolina.isEmpty() || gasolina.equals("R$0,00")){
            view.erroValorGasolinaEmBranco();
        }else if (mediaEtanol.isEmpty() && mediaGasolina.isEmpty()){
            //Calculo realizado com a media padrao, pois o usuario não colocou sua media de consumo
            view.calculoNormal();
        }else if(mediaEtanol.isEmpty()){
            view.erroValorMediaEtanolVazio();
        }else if(mediaGasolina.isEmpty()){
            view.erroValorMediaGasolinaVazio();
        }else{
            // Calculo realizado com a media personalizada
            view.calculoPersonalizado();
        }
    }

    @Override
    public void calculaNormal(Double valorEtanol, Double valorGasolina) {

        Double resultadoDiv = valorEtanol / valorGasolina; //Calcula o percentual de defirença
        String percentualDiferenca = formataNumeroDeDoubleParaString(resultadoDiv);
        String titulo = "";
        String msg = "";

        if(resultadoDiv <= 0.7){
            view.escolhaEtanol(percentualDiferenca);
        }else{
            view.escolhaGasolina(percentualDiferenca);
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
            view.escolhaEtanol(percentualDiferenca);
        }else{
            view.escolhaGasolina(percentualDiferenca);
        }

    }

}
