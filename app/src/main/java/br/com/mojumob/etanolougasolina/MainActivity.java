package br.com.mojumob.etanolougasolina;

import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //Atributos
    private EditText edtEtanol, edtGasolina, edtMediaEtanol, edtMediaGasolina;
    private Button btnCalcular, btnLimpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializaçoes
        edtGasolina      = findViewById(R.id.edtGasolina);
        edtEtanol        = findViewById(R.id.edtEtanol);
        edtMediaGasolina = findViewById(R.id.edtMediaGasolina);
        edtMediaEtanol   = findViewById(R.id.edtMediaEtanol);
        btnCalcular      = findViewById(R.id.btnCalcular);
        btnLimpar        = findViewById(R.id.btnLimpar);


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

    private void calcula() {

        //Inicialização de variaveis para teste de campo vazio
        String valorEtanol        = edtEtanol.getText().toString();
        String valorGasolina      = edtGasolina.getText().toString();
        String valorMedioEtanol   = edtMediaEtanol.getText().toString();
        String valorMedioGasolina = edtMediaGasolina.getText().toString();

        //Validação de campos vazios
        if(valorEtanol.isEmpty()){
            Toast.makeText(this,
                    "Por favor, preencha o valor do etanol", Toast.LENGTH_LONG).show();
        }else if(valorGasolina.isEmpty()){
            Toast.makeText(this,
                    "Por favor, preencha o valor da gasolina ", Toast.LENGTH_LONG).show();
        }else if(valorMedioEtanol.isEmpty() && valorMedioGasolina.isEmpty()){
            calculaMediaNormal();
        }else if(valorMedioEtanol.isEmpty() && !valorMedioGasolina.isEmpty()){
            Toast.makeText(this,
                    "Para saber o consumo personalizado, você deve preencher o consumo com Etanol", Toast.LENGTH_LONG).show();
        }else if(!valorMedioEtanol.isEmpty() && valorMedioGasolina.isEmpty()){
            Toast.makeText(this,
                    "Para saber o consumo personalizado, você deve preencher o consumo com Gasolina", Toast.LENGTH_LONG).show();
        }else{
            calculaMediaPersonalizada();
        }


    }

    private void calculaMediaPersonalizada() {

        Double valorGasolina        = Double.parseDouble(edtGasolina.getText().toString());
        Double valorEtanol          = Double.parseDouble(edtEtanol.getText().toString());
        Double valorConsumoGasolina = Double.parseDouble(edtGasolina.getText().toString());
        Double valorConsumoEtanol   = Double.parseDouble(edtEtanol.getText().toString());
        Double valorPersonalizado   = valorConsumoEtanol / valorConsumoGasolina;

        Double resDiv = valorEtanol / valorGasolina; //Calcula o percentual de defirença
        String n = formataNumeros(resDiv);
        String titulo = "";
        String msg = "";

        if(resDiv <= valorPersonalizado){
            titulo = "Escolha o Etanol";
            msg = "Abasteça Com Etanol. A relação de consumo Etanol/Gaslina é de: " + n;
        }else{
            titulo = "Escolha a Gasolina";
            msg = "Abasteça Com Gasolina. A relação de consumo Etanol/Gaslina é de: " + n;
        }

        //Exibindo AlertDialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

        dialog.setTitle(titulo);
        dialog.setMessage(msg);
        dialog.setPositiveButton("OK", null);
        dialog.show();


    }

    private void calculaMediaNormal() {

        Double valorGasolina      = Double.parseDouble(edtGasolina.getText().toString());
        Double valorEtanol        = Double.parseDouble(edtEtanol.getText().toString());

        Double resDiv = valorEtanol / valorGasolina; //Calcula o percentual de defirença
        String n = formataNumeros(resDiv);
        String titulo = "";
        String msg = "";

        if(resDiv <= 0.7){
            titulo = "Escolha o Etanol";
            msg = "Abasteça Com Etanol. A relação de consumo Etanol/Gaslina é de: " + n;
        }else{
            titulo = "Escolha a Gasolina";
            msg = "Abasteça Com Gasolina. A relação de consumo Etanol/Gaslina é de: " + n;
        }

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

    private String formataNumeros(Double resDiv){

        //Formatando o resultado
        DecimalFormat df = new DecimalFormat("#.##%");
        String res = df.format(resDiv);

        return res;
    }
}
