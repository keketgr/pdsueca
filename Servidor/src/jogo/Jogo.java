/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.util.ArrayList;
import servidor.User;

public class Jogo extends Thread {

    private String id;
    private ArrayList<User> jogadores;
    private int[] score;
    private int max;
    private int vale;
    private Partida partida;
    private int adar;

    public int getAdar() {
        return adar;
    }

    public void incrementaAdar() {
        this.adar++;
        if (adar == 4) {
            adar = 0;
        }
    }

    public Jogo(int m, String id) {
        this.id = id;
        max = m;
        jogadores = new ArrayList<>();
        vale = 0;
        score = new int[2];
        score[0] = 0;
        score[1] = 0;

    }

    public Partida getPartida() {
        return partida;
    }

    public void addJogador(User u) {
        jogadores.add(u);
    }

    public String getidjogo() {
        return id;
    }

    public void setIdjogo(String id) {
        this.id = id;
    }

    public boolean removeJogador(User u) {
        return jogadores.remove(u);
    }

    public int getposJogador(String username) {
        for (int i = 0; i < jogadores.size(); i++) {
            if (jogadores.get(i).getNickname().equals(username)) {
                return i;
            }
        }
        return -1;
    }

    public String[] getGameAndPlayers() {
        String[] Arrayjoga = new String[jogadores.size() + 1];
        int k = 0;
        Arrayjoga[k++] = this.id;
        for (int i = 0; i < jogadores.size(); i++) {
            Arrayjoga[k++] = jogadores.get(i).getNickname();
        }
        return Arrayjoga;
    }

    @Override
    public void run() {
        for (int i = 0; i < jogadores.size(); i++) {
            jogadores.get(i).setEstado(User.ESTADO_JOGO);
            jogadores.get(i).IniciaJogo(getStringjogadores());
        }
        max = 5;
        int vencedor = 0;
        for (int i = 0; i < max; i++) {//meter max mais a frente
            partida = new Partida(this);
            partida.NotificaLogJogo("\njogo " + i + " de " + max + "\n");
            vencedor = partida.joga();
            if (vencedor == 1) {
                partida.NotificaLogJogo("Vencedores da Partida:\n" + jogadores.get(0).getNickname() + "\n" + jogadores.get(2).getNickname());
                score[0]++;
                NotificaVencedorPartida(1);
            } else {
                partida.NotificaLogJogo("Vencedores da Partida:\n" + jogadores.get(1).getNickname() + "\n" + jogadores.get(3).getNickname());
                score[0]++;
                NotificaVencedorPartida(2);
            }
            partida.NotificaLogJogo("Pontuação:\n<" + jogadores.get(0).getNickname() + ">&<"
                    + jogadores.get(2).getNickname() + ">:" + score[0] + "\n<" + jogadores.get(1).getNickname()
                    + ">&<" + jogadores.get(3).getNickname() + ">:" + score[2]);
        }
        if (score[0] > score[1]) {
            partida.NotificaLogJogo("Vencedores:\n" + jogadores.get(0).getNickname() + "\n" + jogadores.get(2).getNickname());
        } else {
            partida.NotificaLogJogo("Vencedores:\n" + jogadores.get(1).getNickname() + "\n" + jogadores.get(3).getNickname());
        }
    }

    public ArrayList<User> getjogadores() {
        return jogadores;
    }

    public boolean haveVagas(int x) {
        if (4 - jogadores.size() >= x) {
            return true;
        }
        return false;
    }

    public ArrayList<String> getStringjogadores() {
        ArrayList<String> users = new ArrayList<>();
        for (int i = 0; i < jogadores.size(); i++) {
            users.add(jogadores.get(i).getNickname());
        }
        return users;
    }

    public boolean isJogador(User u) {
        return jogadores.contains(u);
    }

    private void NotificaVencedorPartida(int v) {
        int sf;
        if (v == 1) {
            sf = 8;
        } else {
            sf = 9;
        }
        for (int i = 0; i < jogadores.size(); i++) {
            jogadores.get(i).notificavencedor(sf);
        }
    }
}
