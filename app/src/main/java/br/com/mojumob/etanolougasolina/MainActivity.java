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

        Double valorGasolina      = Double.parseDouble(edtGasolina.getText().toString());
        Double valorEtanol        = Double.parseDouble(edtEtanol.getText().toString());
        //Double valorMediaGasolina = Double.parseDouble(edtMediaGasolina.getText().toString());
        //Double valorMediaEdtanol  = Double.parseDouble(edtMediaEtanol.getText().toString());

        //Calculando o melhor combustivel com a media padrao (70%)

        Double resDiv = valorEtanol / valorGasolina;
        if(resDiv <= 0.7){

            //Formatando o resultado
            DecimalFormat df = new DecimalFormat("#.##");
            String n = df.format(resDiv);

            //Exibindo um AlertDialog
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

            dialog.setTitle("Sua melhor escolha");
            dialog.setMessage("Abasteça Com Etanol. A relação de consumo Etanol/Gaslina é de: " + n + "%");
            dialog.setPositiveButton("OK", null);
            dialog.show();


        }else{

        }

        //Calculando o melhor combustivel com a media personalizada


    }

    private void limparCampos() {

        edtGasolina.setText("");
        edtEtanol.setText("");
        edtMediaGasolina.setText("");
        edtMediaEtanol.setText("");

    }
}
