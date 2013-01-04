/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidor.User;

/**
 *
 * @author Paulo
 */
public class Partida {

    private static ArrayList<User> jogadores;
    private Baralho baralho;
    private Jogada jogada;
    private Cartas trunfo;
    private Cartas UltimaCarta;
    private int first;
    private int eq1pontos;
    private int eq2pontos;
    private static Jogo jogo;
    private char Charcimabaixo;

    synchronized public void setUltimaCarta(Cartas UltimaCarta) {
        this.UltimaCarta = UltimaCarta;
        notify();
    }

    public Partida(Jogo j) {
        jogo = j;
        first = 1;
        Charcimabaixo = 'c';
        jogadores = jogo.getjogadores();
    }

    public Jogada getJogada() {
        return jogada;
    }

    synchronized public void setCharcimabaixo(char Charcimabaixo) {
        this.Charcimabaixo = Charcimabaixo;
        notify();
    }

    public void darcartas(char cimabiaxo) {//c- da por cima,b-daporbaixo
        baralho = new Baralho();
        baralho.baralha();
        ArrayList<Cartas> mao;
        // int next = jogo.getAdar();
        if (cimabiaxo == 'c') {

            trunfo = baralho.getcarta(0);
            for (int i = 0; i <= 3; i++) {
                mao = baralho.get10cartas();
                try {
                    jogadores.get(i).RecebeCartas(mao, trunfo);
                } catch (IOException ex) {
                    //bvna tratar exceção...user foi se
                    jogo.Disconect(jogadores.get(i), 1, i);
                    System.out.println("user " + jogadores.get(i) + "as disconect");
                    //Logger.getLogger(Partida.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {

            trunfo = baralho.getcarta(39);
            for (int i = 3; i >= 0; i--) {
                mao = baralho.get10cartas();
                try {
                    jogadores.get(i).RecebeCartas(mao, trunfo);
                } catch (IOException ex) {
                    //bvna tratar exceção...user foi se
                    jogo.Disconect(jogadores.get(i), 1, i);
                    System.out.println("user " + jogadores.get(i) + "as disconect");
                    //Logger.getLogger(Partida.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        jogo.incrementaAdar();

        first = jogo.getAdar();
    }

    public void NotificaJogadoresCartaJogada(String QuemJogou) {
        for (int i = 0; i < jogadores.size(); i++) {
            try {
                jogadores.get(i).RecebeCarta(UltimaCarta, QuemJogou);
            } catch (IOException ex) {
                //bvna tratar exceção...user foi se
                jogo.Disconect(jogadores.get(i), 1, i);
                System.out.println("user " + jogadores.get(i) + "as disconect");
                //Logger.getLogger(Partida.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int getEq1pontos() {
        return eq1pontos;
    }

    public void setEq1pontos(int eq1pontos) {
        this.eq1pontos = eq1pontos;
    }

    public int getEq2pontos() {
        return eq2pontos;
    }

    public void setEq2pontos(int eq2pontos) {
        this.eq2pontos = eq2pontos;
    }

    synchronized public int joga() {
        int vencedor = -1;
        int actual;
        eq1pontos = -1;
        eq2pontos = -1;

        //1º cima ou baixo
        System.out.println("Cima ou baixo Implementar Baixo");
        darCartasCimaBaixo();//pergunta ao user "adar"
        //2º dar cartas

        String msg = "<" + jogadores.get(jogo.getAdar()).getNickname() + "> Deu cartas por ";
        if (Charcimabaixo == 'c') {
            msg += "cima";
        } else {
            msg += "baixo";
        }
        NotificaLogJogo(msg);
        darcartas(Charcimabaixo);
        //3º jogar 
        for (int i = 0; i < 10; i++) {
            actual = first;
            jogada = new Jogada();
            for (int j = 0; j < 4; j++) {
                System.out.println("Notifica quem joga\nAjoogar: " + jogadores.get(actual).getNickname());
                NotificaQuemJoga(jogadores.get(actual).getNickname());
                System.out.println("Jogador: " + jogadores.get(actual).getNickname() + "Jogou: " + UltimaCarta);
                NotificaJogadoresCartaJogada(jogadores.get(actual++).getNickname());
                jogada.addjogada(UltimaCarta);
                if (actual == 4) {
                    actual = 0;
                }
            }
            int v = jogada.recolhebaza(trunfo, first);

            v += first;//deslocamento para o vencedor
            if (v >= 4) //regularizacao
            {
                v -= 4;
            }

            int p = jogada.contapontos();

            System.out.println("|||||Primeiro a jogar:" + first + "||indice: " + v + " pontos: " + p);
            if (v == 0 || v == 2) {
                if (eq1pontos == -1) {
                    eq1pontos++;
                }
                eq1pontos += p;
            }
            if (v == 1 || v == 3) {
                if (eq2pontos == -1) {
                    eq2pontos++;
                }
                eq2pontos += p;
            }
            first = v;

            System.out.println("espera 3 sec");
            try {
                wait(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Partida.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("3 sec mais tarde");
            NotificaLogJogo("<" + jogadores.get(first).getNickname() + "> ganhou a vaza");

            mandaRecolherCartas();
        }//acaba a partida

        if (eq1pontos > eq2pontos) {
            vencedor = 1;
        } else if (eq1pontos < eq2pontos) {
            vencedor = 2;
        }
        return vencedor;
    }

    public int valorvitoria() {
        if (eq1pontos == -1 || eq2pontos == -1) {
            return 4;
        }
        if (eq1pontos < 30 || eq2pontos < 30) {
            return 2;
        }
        return 1;
    }

    synchronized public void addCarta(Cartas c) {
        jogada.addjogada(c);
        notify();
    }

    synchronized public void darCartasCimaBaixo() {
        try {
            jogadores.get(jogo.getAdar()).CimaBaixo();
        } catch (IOException ex) {
            //bvna tratar exceção...user foi se
            jogo.Disconect(jogadores.get(jogo.getAdar()), 1, jogo.getAdar());
            System.out.println("user " + jogadores.get(jogo.getAdar()) + "as disconect");
            //Logger.getLogger(Partida.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            wait();
        } catch (InterruptedException ex) {
            Charcimabaixo = 'c';
            //Logger.getLogger(Partida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    synchronized void NotificaQuemJoga(String nick) {
        for (int i = 0; i < jogadores.size(); i++) {
            try {
                jogadores.get(i).jogoQuemJoga(nick);
            } catch (IOException ex) {
                //bvna tratar exceção...user foi se
                jogo.Disconect(jogadores.get(i), 1, i);
                System.out.println("user " + jogadores.get(i) + "as disconect");
                //Logger.getLogger(Partida.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(Partida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void NotificaLogJogo(String msg) {
        for (int i = 0; i < jogadores.size(); i++) {
            try {
                jogadores.get(i).MensagemSobreJogo(msg);
            } catch (IOException ex) {
                //bvna tratar exceção...user foi se
                jogo.Disconect(jogadores.get(i), 1, i);
                System.out.println("user " + jogadores.get(i) + "as disconect");
                //Logger.getLogger(Partida.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void mandaRecolherCartas() {
        for (int i = 0; i < jogadores.size(); i++) {
            try {
                jogadores.get(i).RecolheCartas();
            } catch (IOException ex) {
                //bvna tratar exceção...user foi se
                jogo.Disconect(jogadores.get(i), 1, i);
                System.out.println("user " + jogadores.get(i) + "as disconect");
                //Logger.getLogger(Partida.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
