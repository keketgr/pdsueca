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
public class Invites {

    User host;
    ArrayList<User> UsersConvidados;
    ArrayList<User> UsersAceites;

    public Invites(User host) {
        this.host = host;
        UsersConvidados = new ArrayList<>();
        UsersAceites = new ArrayList<>();
        UsersAceites.add(host);
    }

    public boolean isHost(User u) {
        if (host.equals(u)) {
            return true;
        }
        return false;
    }

    public void addUserConvidados(User u) {
        UsersConvidados.add(u);
    }

    public void removeUserAceites(User u) {
        UsersAceites.remove(u);
    }

    public void Aceitou(User user) {
        if (UsersConvidados.remove(user)) {
            UsersAceites.add(user);
        }
    }

    public void recusou(User user) {
        UsersConvidados.remove(user);
    }

    public boolean isJogador(User user) {
        for (int i = 0; i < UsersConvidados.size(); i++) {
            if (UsersConvidados.get(i).equals(user)) {
                return true;
            }
        }
        for (int i = 0; i < UsersAceites.size(); i++) {
            if (UsersAceites.get(i).equals(user)) {
                return true;
            }
        }
        return false;
    }

    public int getNumUsersAceites() {
        return UsersAceites.size();
    }

    public void Start(PreJogo pre) {
        pre.addJodagores(UsersAceites);
        for (int i = 0; i < UsersAceites.size(); i++) {            
            UsersAceites.get(i).setEstado(User.ESTADO_PRE_JOGO);
            UsersAceites.get(i).setJogoId(pre.getHost());
            UsersAceites.get(i).InviteEntrarJogo();
        }
        //remover users convidados
    }

    public ArrayList<String> getUsersConvidados() {
        ArrayList<String> u = new ArrayList<>();
        for (int i = 0; i < UsersConvidados.size(); i++) {
            u.add(UsersConvidados.get(i).getNickname());
        }
        return u;
    }

    public ArrayList<String> getUsersAceites() {
        ArrayList<String> u = new ArrayList<>();
        for (int i = 0; i < UsersAceites.size(); i++) {
            u.add(UsersAceites.get(i).getNickname());
        }
        return u;
    }

    public boolean isUser(User u) {
        return false;
    }

    public void Cancelar() {
        for (int i = 0; i < UsersAceites.size(); i++) {
            UsersAceites.get(i).InviteCancelarInvite();
        }
        for (int i = 0; i < UsersConvidados.size(); i++) {
            UsersConvidados.get(i).InviteCancelarNotificacao(host.getNickname());
        }
    }
}
