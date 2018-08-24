package br.com.mojumob.etanolougasolina.ui.activity;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.blackcat.currencyedittext.CurrencyEditText;
import java.text.DecimalFormat;
import java.util.Locale;

import br.com.mojumob.etanolougasolina.R;
import br.com.mojumob.etanolougasolina.model.Combustivel;

public class MainActivity extends AppCompatActivity {

    //Atributos
    private Button btnCalcular, btnLimpar;
    private EditText edtMediaEtanol, edtMediaGasolina;
    private CurrencyEditText edtEtanol, edtGasolina;
    private Context context;
    private String msg;
    private Combustivel combustivel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializaComponentes();

        //Evento clique no botão Calcular
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validaCampos();
            }
        });

        //Evento clique no botão Limpar
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limparCampos();
            }
        });

    }

    private void inicializaComponentes() {
        //Inicializaçoes
        edtGasolina      = findViewById(R.id.edtGasolina);
        edtEtanol        = findViewById(R.id.edtEtanol);
        edtMediaGasolina = findViewById(R.id.edtMediaGasolina);
        edtMediaEtanol   = findViewById(R.id.edtMediaEtanol);
        btnCalcular      = findViewById(R.id.btnCalcular);
        btnLimpar        = findViewById(R.id.btnLimpar);
        context          = this;

        //Define o locale como pt-br para exibição do R$
        Locale locale = new Locale("pt", "BR");
        edtGasolina.setLocale(locale);
        edtEtanol.setLocale(locale);

    }

    private Combustivel getValoresCombustivel(){

        combustivel = new Combustivel();
        combustivel.setValorEtanol(edtEtanol.getText().toString());
        combustivel.setValorGasolina(edtGasolina.getText().toString());
        combustivel.setMediaEtanol(edtMediaEtanol.getText().toString());
        combustivel.setMediaGasolina(edtMediaGasolina.getText().toString());

        return combustivel;
    }

    private void validaCampos() {

        Combustivel combustivel = getValoresCombustivel();

        if(combustivel.getValorEtanol().equals("R$0,00") || combustivel.getValorEtanol().isEmpty()){
            exibeMensagem(getString(R.string.preenchacampoetanol));
        }else if(combustivel.getValorGasolina().equals("R$0,00") || combustivel.getValorGasolina().isEmpty()){
            exibeMensagem(getString(R.string.preenchacampogasolina));
        }else if(combustivel.getMediaEtanol().isEmpty() && combustivel.getMediaGasolina().isEmpty()){
            calculaMediaNormal();
        }else if(combustivel.getMediaEtanol().isEmpty() && !combustivel.getMediaGasolina().isEmpty()){
            exibeMensagem(getString(R.string.campopersonalizadoetanolvazio));
        }else if(!combustivel.getMediaEtanol().isEmpty() && combustivel.getMediaGasolina().isEmpty()){
            exibeMensagem(getString(R.string.campopersonalizadogasolinavazio));
        }else{
            calculaMediaPersonalizada();
        }

    }

    private void exibeMensagem(String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }



    private void calculaMediaNormal() {

        //Forma os valores do Edit Text Current
        String etanol = String.valueOf(edtEtanol.getRawValue());
        String gasolina = String.valueOf(edtGasolina.getRawValue());

        Double valorEtanol        = Double.parseDouble(etanol);
        Double valorGasolina      = Double.parseDouble(gasolina);



        Double resDiv = valorEtanol / valorGasolina; //Calcula o percentual de defirença
        String n = formataNumeroDeDoubleParaString(resDiv);
        String titulo = "";
        String msg = "";

        if(resDiv <= 0.7){
            titulo = getString(R.string.escolhaEtanol);
            msg = getString(R.string.abastecaEtanol) + n;
        }else{
            titulo = getString(R.string.escolhaGasolina);
            msg = getString(R.string.abastecaGasolina) + n;
        }

        exibirAlertDialog(titulo, msg);

    }

    private void calculaMediaPersonalizada() {

        //Forma os valores do Edit Text Current
        String etanol = String.valueOf(edtEtanol.getRawValue());
        String gasolina = String.valueOf(edtGasolina.getRawValue());

        Double valorEtanol        = Double.parseDouble(etanol);
        Double valorGasolina      = Double.parseDouble(gasolina);

        Double valorConsumoGasolina = Double.parseDouble(edtMediaGasolina.getText().toString());
        Double valorConsumoEtanol   = Double.parseDouble(edtMediaEtanol.getText().toString());
        Double valorPersonalizado   = valorConsumoEtanol / valorConsumoGasolina;

        Double resDiv = valorEtanol / valorGasolina; //Calcula o percentual de defirença
        String n = formataNumeroDeDoubleParaString(resDiv);
        String titulo = "";
        String msg = "";

        if(resDiv <= valorPersonalizado){
            titulo = getString(R.string.escolhaEtanol);
            msg = getString(R.string.abastecaEtanol) + n;
        }else{
            titulo = getString(R.string.escolhaGasolina);
            msg = getString(R.string.abastecaGasolina) + n;
        }

        exibirAlertDialog(titulo, msg);
    }

    private void exibirAlertDialog(String titulo, String msg) {
        //Exibindo AlertDialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

        dialog.setTitle(titulo);
        dialog.setMessage(msg);
        dialog.setPositiveButton("OK", null);
        dialog.show();
    }

    private void limparCampos() {

        edtGasolina.setText("");
        edtEtanol.setText("");
        edtMediaGasolina.setText("");
        edtMediaEtanol.setText("");
    }

    private String formataNumeroDeDoubleParaString(Double resDiv){

        //Formatando o resultado
        DecimalFormat df = new DecimalFormat("#.##%");
        String res = df.format(resDiv);

        return res;
    }

    private String formaNumeroDeStringParaDouble(String valor){

        DecimalFormat df = new DecimalFormat("#,##%");
        String res = df.format(valor);

        return res;
    }
}
