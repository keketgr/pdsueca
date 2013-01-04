/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.io.IOException;
import java.util.ArrayList;
import servidor.User;

public class Jogo extends Thread {

    private String id;
    private ArrayList<User> jogadores;
    private int[] score;
    private int max;
    private Partida partida;
    private int adar;
    private boolean disconect;

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
        score = new int[2];
        score[0] = 0;
        score[1] = 0;
        disconect = false;

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
        System.out.println(this);
        for (int i = 0; i < jogadores.size(); i++) {
            jogadores.get(i).setEstado(User.ESTADO_JOGO);
            try {
                jogadores.get(i).IniciaJogo(getStringjogadores());
            } catch (IOException ex) {
                //bvna tratar exceção...user foi se
                Disconect(jogadores.get(i), 0, i);
                System.out.println("user " + jogadores.get(i) + "as disconect");
                //Logger.getLogger(Jogo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        max = 5;
        int vencedor = 0;
        for (int i = 0; i < max; i++) {//meter max mais a frente
            if (disconect) {
                return;
            }
            partida = new Partida(this);
            partida.NotificaLogJogo("\njogo " + (i + 1) + " de " + max + "\n");
            vencedor = partida.joga();
            if (vencedor == 1) {
                partida.NotificaLogJogo("Vencedores da Partida: <" + jogadores.get(0) + "> & <" + jogadores.get(2) + ">\n");
                score[0]++;
                NotificaVencedorPartida(1);
            } else {
                partida.NotificaLogJogo("\nVencedores da Partida: <" + jogadores.get(1) + "> & <" + jogadores.get(3) + ">\n");
                score[1]++;
                NotificaVencedorPartida(2);
            }
            partida.NotificaLogJogo("Pontuação:\n<" + jogadores.get(0)+ ">&<"
                    + jogadores.get(2) + ">:" + score[0] + "\n<" + jogadores.get(1)
                    + "> & <" + jogadores.get(3) + ">:" + score[1]);
        }
        fimJogo(-1);
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
            try {
                jogadores.get(i).notificavencedor(sf);
            } catch (IOException ex) {
                //bvna tratar exceção...user foi se
                Disconect(jogadores.get(i), 0, i);
                System.out.println("user " + jogadores.get(i) + "as disconect");
                //Logger.getLogger(Jogo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void fimJogo(int eqvence) {
        String msg;

        if (score[0] > score[1] || eqvence == 1) {

            msg = "Vencedores: <" + jogadores.get(0) + "> & <" + jogadores.get(2) + ">";
        } else {

            msg = "Vencedores: <" + jogadores.get(1) + "> & <" + jogadores.get(3) + ">";
        }

        for (int i = 0; i < jogadores.size(); i++) {
            try {
                jogadores.get(i).jogoFimJogo(msg);

            } catch (IOException ex) {
                //Logger.getLogger(Jogo.class.getName()).log(Level.SEVERE, null, ex);
            }
            jogadores.get(i).setEstado(User.ESTADO_SALA);
        }

        jogadores.clear();
        this.stop();
    }

    @Override
    public String toString() {
        String out = "";
        out += "\nJogo criado por: " + id;
        out += "\n\tEquipa1: " + score[0] + " vitorias. Elementos:<" + jogadores.get(0) + "> & <" + jogadores.get(2)+">";
        out += "\n\tEquipa2: " + score[1] + " vitorias. Elementos:<" + jogadores.get(1) + "> & <" + jogadores.get(3)+">";
        return out;
    }

    public void Disconect(User u, int f, int index) {
        int eq_vencedora;
        if (disconect) {
            return;
        }
        if (index == -1) {
            for (int i = 0; i < jogadores.size(); i++) {
                if (jogadores.get(i).equals(u)) {
                    index = i;
                    break;
                }
            }
        }
        disconect = true;
        if (index == 0 || index == 2) {
            eq_vencedora = 2;
        } else {
            eq_vencedora = 1;
        }
        //jogadores.remove(index);
        fimJogo(eq_vencedora);
    }
}
