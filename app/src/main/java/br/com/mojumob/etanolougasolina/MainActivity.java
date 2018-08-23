package br.com.mojumob.etanolougasolina;

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

public class MainActivity extends AppCompatActivity {

    //Atributos
    private Button btnCalcular, btnLimpar;
    private EditText edtMediaEtanol, edtMediaGasolina;
    private CurrencyEditText edtEtanol, edtGasolina;
    private Context context;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializaComponentes();

        //Evento clique no botão Calcular
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcula();



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

    private void calcula() {

        //Inicialização de variaveis para teste de campo vazio

        String valorEtanol        = edtEtanol.getText().toString();
        String valorGasolina      = edtGasolina.getText().toString();
        String valorMedioEtanol   = edtMediaEtanol.getText().toString();
        String valorMedioGasolina = edtMediaGasolina.getText().toString();

        //Validação de campos vazios
        if(valorEtanol.equals("R$0,00") || valorEtanol.isEmpty()){
            exibeMensagem("Por favor, preencha o valor do etanol");
        }else if(valorGasolina.equals("R$0,00") || valorGasolina.isEmpty()){
            exibeMensagem("Por favor, preencha o valor da gasolina ");
        }else if(valorMedioEtanol.isEmpty() && valorMedioGasolina.isEmpty()){
            calculaMediaNormal();
        }else if(valorMedioEtanol.isEmpty() && !valorMedioGasolina.isEmpty()){
            exibeMensagem("Para saber o consumo personalizado, você deve preencher o consumo com Etanol");
        }else if(!valorMedioEtanol.isEmpty() && valorMedioGasolina.isEmpty()){
            exibeMensagem("Para saber o consumo personalizado, você deve preencher o consumo com Gasolina");
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
            titulo = "Escolha o Etanol";
            msg = "Abasteça Com Etanol. A relação de consumo Etanol/Gaslina é de: " + n;
        }else{
            titulo = "Escolha a Gasolina";
            msg = "Abasteça Com Gasolina. A relação de consumo Etanol/Gaslina é de: " + n;
        }

        exibirAlertDialog(titulo, msg);

    }

    private void calculaMediaPersonalizada() {

        Double valorGasolina        = Double.parseDouble(edtGasolina.getText().toString());
        Double valorEtanol          = Double.parseDouble(edtEtanol.getText().toString());
        Double valorConsumoGasolina = Double.parseDouble(edtMediaGasolina.getText().toString());
        Double valorConsumoEtanol   = Double.parseDouble(edtMediaEtanol.getText().toString());
        Double valorPersonalizado   = valorConsumoEtanol / valorConsumoGasolina;

        Double resDiv = valorEtanol / valorGasolina; //Calcula o percentual de defirença
        String n = formataNumeroDeDoubleParaString(resDiv);
        String titulo = "";
        String msg = "";

        if(resDiv <= valorPersonalizado){
            titulo = "Escolha o Etanol";
            msg = "Abasteça Com Etanol. A relação de consumo Etanol/Gaslina é de person: " + n;
        }else{
            titulo = "Escolha a Gasolina";
            msg = "Abasteça Com Gasolina. A relação de consumo Etanol/Gaslina é de person: " + n;
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
