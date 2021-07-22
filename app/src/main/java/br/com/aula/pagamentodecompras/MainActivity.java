package br.com.aula.pagamentodecompras;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView vercheck; // onde vai sair o valor da compra
    EditText pagamento; // onde vai colocar o valor do pagamento
    CheckBox arroz, carne, pao, leite, ovos;
    Button total, btpagar; // do total da compras e o botao de efetuar as compras
    RadioGroup opcoes; // caixinha de selaçao dos descontos
    double soma = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            vercheck = findViewById(R.id.vercheck); // link entre interface e código java
            arroz = findViewById(R.id.ck_arroz); // link entre interface e código java
            carne = findViewById(R.id.ck_carne); // link entre interface e código java
            pao = findViewById(R.id.ck_pao);
            leite = findViewById(R.id.ck_leite);
            ovos = findViewById(R.id.ck_ovos);
            total = findViewById(R.id.total);
            btpagar = findViewById(R.id.btpagar);
            pagamento = findViewById(R.id.pagamento);
            opcoes = findViewById(R.id.opçao);

            pagamento.setOnClickListener(new View.OnClickListener() { // evento do botao de pagamento
                @Override
                public void onClick(View v) { pagamento.setText("");} // ele vai apagar automaticamente o valor após clicar onde vai digitar o valor para pagar a compra
            });

            total.setOnClickListener(new View.OnClickListener() { // evento do botao de total
                @Override
                public void onClick(View v) {
                    double total = 0.0;

                    if (arroz.isChecked()) { // is.Ckecked é para ver se esta selacionado a caixinha
                        total += 3.50;
                    }

                    if (carne.isChecked()) {
                        total += 12.30;
                    }

                    if (pao.isChecked()) {
                        total += 2.20;
                    }

                    if (leite.isChecked()) {
                        total += 5.50;
                    }

                    if (ovos.isChecked()) {
                        total += 7.50;
                    }
                    soma = total;
                    vercheck.setText("Valor: " + total);  //para sair o valor das compras
                }
            });

        btpagar.setOnClickListener(new View.OnClickListener() { // evento do botao efetuar as compras
                                        @Override
        public void onClick(View v) {

             double troco = 0;
             double total = soma;
             double pagto = 0;

             if(!pagamento.getText().toString().isEmpty()){
                 pagto = Double.parseDouble(pagamento.getText().toString());   // conversão para verificar se esta vazio
             }

            int op = opcoes.getCheckedRadioButtonId(); // para verificar quais das olternativas estao marcada
            double desconto = 0;

             if(op == R.id.ck_sem){
              total = total - (total * 0);
            } else if(op == R.id.ck_5){
              total = total - (total * 0.05);
              desconto = 5;
            } else if(op == R.id.ck_10) {
              total = total - (total * 0.10);
              desconto = 10;
            } else {
              total = total - (total * 0.15);
              desconto = 15;
            }
            troco = pagto - total;

            AlertDialog.Builder janela = new AlertDialog.Builder(MainActivity.this);
            if(pagto < total || pagto <= 0) { // para verificar se esta pagando menos que o valor desejavel
                janela.setTitle("Aviso"); // o titilo da janela
                janela.setMessage("Valor incompatível com a compra!");  // resultado da mensagem se nao estiver com acordo com que pedi
                janela.setNeutralButton("Ok", null); // depois do resultado aparecer apertar o ok para fechar
                janela.show(); // para aparecer a tela
               } else {
                janela.setTitle("Aviso"); // o titilo da janela
                janela.setMessage("Valor total da compra: " + String.valueOf(total)  // resultado da mensagem
                        + "\nDesconto: " + desconto + "%"
                        + "\nValor pago: " + String.valueOf(pagto)
                        + "\nTroco: " + String.valueOf(troco));
                janela.setNeutralButton("Ok", null); // depois da resultado aparecer apertar o ok para fechar
                janela.show(); // para aparecer a tela
                }
            }
        });
    }
}





