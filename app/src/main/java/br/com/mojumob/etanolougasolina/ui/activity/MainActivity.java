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
import br.com.mojumob.etanolougasolina.presenter.PresenterValidacoes;
import br.com.mojumob.etanolougasolina.task.Calcula;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements Calcula.View {

    //Atributos do XML
    @BindView(R.id.btnCalcular)
    Button btnCalcular;

    @BindView(R.id.btnLimpar)
    Button btnLimpar;

    @BindView(R.id.edtMediaEtanol)
    EditText edtMediaEtanol;

    @BindView(R.id.edtMediaGasolina)
    EditText edtMediaGasolina;

    @BindView(R.id.edtEtanol)
    CurrencyEditText edtEtanol;

    @BindView(R.id.edtGasolina)
    CurrencyEditText edtGasolina;

    //Atributos da lógica
    private Combustivel combustivel;
    //private PresenterValidacoes presenterValidacoes; Refatorado para uma interface
    private Calcula.Presenter presenterValidacoes;
    private Context context;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;
        ButterKnife.bind(this);
        inicializaMoeda();
        presenterValidacoes = new PresenterValidacoes(MainActivity.this);
        combustivel = new Combustivel();
    }

    public Combustivel pegaCampos(){

        String etanol        = edtEtanol.getText().toString();
        String gasolina      = edtGasolina.getText().toString();
        String rawEtanol     = String.valueOf(edtEtanol.getRawValue());
        String rawGasolina   = String.valueOf(edtGasolina.getRawValue());
        String mediaEtanol   = edtMediaEtanol.getText().toString();
        String mediaGasolina = edtMediaGasolina.getText().toString();

        combustivel = new Combustivel();
        combustivel.setValorEtanol(etanol);
        combustivel.setValorRawEtanol(rawEtanol);
        combustivel.setValorRawGasolina(rawGasolina);
        combustivel.setValorGasolina(gasolina);
        combustivel.setMediaEtanol(mediaEtanol);
        combustivel.setMediaGasolina(mediaGasolina);

        return combustivel;
    }

    @OnClick(R.id.btnCalcular)
    public void validarCampos(){

        combustivel = pegaCampos();

        String valorEtanol   = combustivel.getValorEtanol();
        String valorGasolina = combustivel.getValorGasolina();
        String mediaEtanol   = combustivel.getMediaEtanol();
        String mediaGasolina = combustivel.getMediaGasolina();

        presenterValidacoes.validaCampos(valorEtanol, valorGasolina, mediaEtanol, mediaGasolina);

    }

    @OnClick(R.id.btnLimpar)
    public void limpaCampos(){
        edtGasolina.setText("");
        edtEtanol.setText("");
        edtMediaGasolina.setText("");
        edtMediaEtanol.setText("");
    }

    private void inicializaMoeda() {
        //Define o locale como pt-br para exibição do R$
        Locale locale = new Locale("pt", "BR");
        edtGasolina.setLocale(locale);
        edtEtanol.setLocale(locale);

    }


    private void exibeMensagem(String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
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

    @Override
    public void erroValorEtanolEmBranco() {
        exibeMensagem("Preencha o valor do Etanol");
    }

    @Override
    public void erroValorGasolinaEmBranco() {
        exibeMensagem("Preencha o valor da Gasolina");
    }

    @Override
    public void erroValorMediaEtanolVazio() {
        exibeMensagem(getString(R.string.campopersonalizadoetanolvazio));
    }
    @Override
    public void erroValorMediaGasolinaVazio() {
        exibeMensagem(getString(R.string.campopersonalizadogasolinavazio));
    }
    @Override
    public void calculoNormal() {

        combustivel = pegaCampos();

        //Forma os valores do EditText Current
        String etanol   = combustivel.getValorRawEtanol();
        String gasolina = combustivel.getValorRawGasolina();

        Double valorEtanol        = Double.parseDouble(etanol);
        Double valorGasolina      = Double.parseDouble(gasolina);

        presenterValidacoes.calculaNormal(valorEtanol, valorGasolina);

    }

    @Override
    public void escolhaEtanol(String percentualDiferenca) {
        String titulo = getString(R.string.escolhaEtanol);
        String msg = getString(R.string.abastecaEtanol) + " " + percentualDiferenca;

        exibirAlertDialog(titulo, msg);
    }

    @Override
    public void escolhaGasolina(String percentualDiferenca) {
        String titulo = getString(R.string.escolhaGasolina);
        String msg = getString(R.string.abastecaGasolina) + " " + percentualDiferenca;

        exibirAlertDialog(titulo, msg);
    }

    @Override
    public void calculoPersonalizado() {

        combustivel = pegaCampos();

        //Forma os valores do EditText Current
        String etanol = String.valueOf(combustivel.getValorRawEtanol());
        String gasolina = String.valueOf(combustivel.getValorRawGasolina());
        String mediaEtanol = String.valueOf(combustivel.getMediaEtanol());
        String mediaGasolina = String.valueOf(combustivel.getMediaGasolina());

        Double valorEtanol        = Double.parseDouble(etanol);
        Double valorGasolina      = Double.parseDouble(gasolina);
        Double valorMediaEtanol   = Double.parseDouble(mediaEtanol);
        Double valorMediaGasolina = Double.parseDouble(mediaGasolina);

        presenterValidacoes.calculaPersonalizado(valorEtanol, valorGasolina, valorMediaEtanol, valorMediaGasolina);

        Double valorPersonalizado   = valorMediaEtanol / valorMediaGasolina;

    }
}
