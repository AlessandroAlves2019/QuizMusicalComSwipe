package com.example.quizmusicalcomswipe;

public class Pergunta {
    String pergunta;
    boolean resposta;

    public void setResultado(String pergunta, Boolean resposta) {
        this.pergunta = pergunta;
        this.resposta = resposta;

    }

    public String getPergunta() {
        return pergunta;
    }

    public boolean getResposta() {
        return resposta;
    }
}
