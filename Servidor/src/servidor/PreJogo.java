/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package servidor;

import java.util.ArrayList;
//import jogo.Jogo;

/**
 *
 * @author Rafael
 */
public class PreJogo {

    User host;
    ArrayList<User> Equipa1, Equipa2;

    public PreJogo(User host) {
        this.host = host;
        Equipa1 = new ArrayList<>();
        Equipa1.add(host);
        Equipa2 = new ArrayList<>();
    }

    public PreJogo() {
        host = null;
        Equipa1 = new ArrayList<>();
        Equipa2 = new ArrayList<>();
    }

    public String getHost() {
        return host.getNickname();
    }

    public ArrayList<String> getEquipa1() {
        ArrayList<String> jogadores = new ArrayList<>();
        for (int i = 0; i < Equipa1.size(); i++) {
            jogadores.add(Equipa1.get(i).getNickname());
        }
        return jogadores;
    }

    public ArrayList<String> getEquipa2() {
        ArrayList<String> jogadores = new ArrayList<>();
        for (int i = 0; i < Equipa2.size(); i++) {
            jogadores.add(Equipa2.get(i).getNickname());
        }
        return jogadores;
    }

    public boolean isHost(User u) {
        if (host.equals(u)) {
            return true;
        }
        return false;
    }

    public void addJogador(User u) {
        if (Equipa1.size() == 2 && Equipa2.size() == 2) {
            return;
        }
//        if (Equipa1.size() <= Equipa2.size()) {
//            Equipa1.add(u);
//        } else {
//            Equipa2.add(u);
//        }
        if (Equipa1.size() < 2) {
            Equipa1.add(u);
        } else {
            Equipa2.add(u);
        }
    }

    public boolean sair(User u) {
        if (u.equals(host)) {
            for (int i = 0; i < Equipa1.size(); i++) {
                Equipa1.get(i).CancelarJogo();
            }
            for (int i = 0; i < Equipa2.size(); i++) {
                Equipa2.get(i).CancelarJogo();
            }
            return true;
        }
        if (Equipa1.contains(u)) {
            Equipa1.remove(u);
            return false;
        }
        if (Equipa2.contains(u)) {
            Equipa2.remove(u);
        }
        return false;
    }

    public void addJodagores(ArrayList<User> users) {
        if (host == null) {
            host = users.get(0);
        }
        if (Equipa1.isEmpty() && users.size() == 2) {//2 jogadores para a equipa 1
            Equipa1.add(users.get(0));
            Equipa1.add(users.get(1));
            return;
        }
        if (Equipa2.isEmpty() && users.size() == 2) {//2 jogadores para a equipa 2
            Equipa2.add(users.get(0));
            Equipa2.add(users.get(1));
            return;
        }
        for (int i = 0; i < users.size(); i++) {//adiciona os jogadores a um jogo
            //enche a equipa 1 e depois a 2 se forem 1, 3 ou 4 jogadores
            addJogador(users.get(i));
        }

    }

    public boolean isVagas(int nvagas) {
        if (nvagas == 2) {
            return (Equipa1.isEmpty() || Equipa2.isEmpty());
        }
        if (4 - (Equipa1.size() + Equipa2.size()) >= nvagas) {
            return true;
        }
        return false;
    }

    public void changeTeam(User u) {
        if (Equipa1.contains(u) && Equipa2.size() < 2) {
            Equipa1.remove(u);
            Equipa2.add(u);
            return;
        }
        if (Equipa2.contains(u) && Equipa1.size() < 2) {
            Equipa2.remove(u);
            Equipa1.add(u);
        }
    }

    public Jogo startGame() {
        if (Equipa2.size() != 2 || Equipa1.size() != 2) {
            return null;
        }
        Jogo j = new Jogo(5, host.getNickname());//5 vitorias
        for (int i = 0; i < 2; i++) {
            j.addJogador(Equipa1.get(i));
            Equipa1.get(i).setEstado(User.ESTADO_JOGO);
            j.addJogador(Equipa2.get(i));
            Equipa2.get(i).setEstado(User.ESTADO_JOGO);
        }
        return j;
    }

    public boolean isJogador(User u) {
        return (Equipa1.contains(u) || Equipa2.contains(u));
    }

    public String[] getJogadores() {
        String[] jogadores = new String[Equipa1.size() + Equipa2.size() + 1];
        jogadores[0] = (host.getNickname());//id
        int k = 1;
        for (int i = 0; i < Equipa1.size(); i++) {
            jogadores[k++] = (Equipa1.get(i).getNickname());
        }
        for (int i = 0; i < Equipa2.size(); i++) {
            jogadores[k++] = (Equipa2.get(i).getNickname());
        }
        return jogadores;
    }
}
