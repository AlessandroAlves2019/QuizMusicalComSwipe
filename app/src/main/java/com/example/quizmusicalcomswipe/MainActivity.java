package com.example.quizmusicalcomswipe;

import androidx.appcompat.app.AppCompatActivity;
import com.example.quizmusicalcomswipe.OnSwipeTouchListener;
import android.os.Bundle;
import java.util.ArrayList;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    ConstraintLayout tela;
    TextView tvSwipe;
    TextView tvSim;
    TextView tvNao;
    Button button;ArrayList<Pergunta> perguntas = new ArrayList<Pergunta>();
    ArrayList<Boolean> respostas = new ArrayList<Boolean>();
    int contador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tela = findViewById(R.id.tela);
        tvSim = findViewById(R.id.tvSim);
        tvNao = findViewById(R.id.tvNao);
        tvSwipe = findViewById(R.id.tvSwipe);

        button = findViewById(R.id.button);

        perguntas.add(PerguntaResultado("A música Aquarela é Brasileira?", true));
        perguntas.add(PerguntaResultado("Imagine é um Rap?", false));
        perguntas.add(PerguntaResultado("Raimundos é uma banda de pagode?", false));
        setGabaritoResposta(perguntas.size());

        contador = -1;

        tela.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeBottom() {
                if (contador >= 0) {
                    super.onSwipeBottom();
                    respostas.set(contador, false);
                    tvNao.setText("NÃO");
                    tvSim.setText("");


                }
                verificarRespostas(respostas);
            }

            @Override
            public void onSwipeTop() {
                if (contador >= 0) {
                    super.onSwipeTop();
                    respostas.set(contador, true);
                    tvSim.setText("SIM");
                    tvNao.setText("");
                }
                verificarRespostas(respostas);

            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                tvNao.setText("");
                tvSim.setText("");

                if (contador <= 0) {
                    contador = perguntas.size() - 1;
                    tvSwipe.setText(perguntas.get(contador).getPergunta());
                } else {
                    contador--;
                    tvSwipe.setText(perguntas.get(contador).getPergunta());
                }
            }

            @Override
            public void onSwipeRight() {
                tvNao.setText("");
                tvSim.setText("");
                super.onSwipeRight();
                if (contador == perguntas.size() - 1 || contador < 0) {
                    contador = 0;
                    tvSwipe.setText(perguntas.get(contador).getPergunta());
                } else {
                    contador++;
                    tvSwipe.setText(perguntas.get(contador).getPergunta());
                }
            }
        });


        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(v.getContext());
                alerta.setTitle("PONTUAÇÃO");
                alerta.setMessage("Respostas assertivas " + Acertos(respostas, perguntas) + " de " + perguntas.size());
                alerta.setNeutralButton("OK", null);
                alerta.show();
            }
        });


    }



    Pergunta PerguntaResultado (String pergunta, Boolean resposta) {
        Pergunta p = new Pergunta();
        p.setResultado(pergunta, resposta);
        return p;
    }

    void setGabaritoResposta(int tamanho) {
        for (int i = 0; i < tamanho; i++) {
            respostas.add(null);
        }
    }


    int Acertos (ArrayList<Boolean> respostas, ArrayList<Pergunta> perguntas) {
        int total = 0;
        for (int i = 0; i < respostas.size(); i++) {
            if (respostas.get(i) == perguntas.get(i).getResposta()) {
                total++;
            }
        }
        return total;
    }

    void verificarRespostas(ArrayList<Boolean> respostas) {
        boolean todasRespondidas = true;
        for (int i = 0; i < respostas.size(); i++) {
            if (respostas.get(i) == null) {
                todasRespondidas = false;
            }
        }
        if (todasRespondidas) button.setVisibility(View.VISIBLE);
    }

    public void sair_click(View v){
        finish();
    }
}