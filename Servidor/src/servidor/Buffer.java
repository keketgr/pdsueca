package servidor;

import java.io.Serializable;
import java.util.ArrayList;
import jogo.Cartas;

public class Buffer implements Serializable {

    int flag;//comando
    int subflag;//sub comando
    /**
     * ***Informaçao sobre jogadores ****
     */
    String[] usersSala;
    String[][] gameIdJogadores;
    ArrayList<String> usersAceites;
    ArrayList<String> usersConvidados;
    ArrayList<Cartas> Cartasmao;
    String proxjogar;//nick do proximo jogador a jogar
    String jogadorcarta;//jogador que jogou a carta
    Cartas trunfo;
    String jogoId;
    Cartas cartajogada;
    String mensagem;
    String pass;
    String utilizador;//para whisp , invite,login
    int pontos;

    public String[][] getGameIdJogadores() {
        return gameIdJogadores;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public void setGameIdJogadores(String[][] gameIdJogadores) {
        this.gameIdJogadores = gameIdJogadores;
    }

    public Cartas getCartajogada() {
        return cartajogada;
    }

    public void setCartajogada(Cartas cartajogada) {
        this.cartajogada = cartajogada;
    }

    public Cartas getTrunfo() {
        return trunfo;
    }

    public void setTrunfo(Cartas trunfo) {
        this.trunfo = trunfo;
    }

    public String getProxjogar() {
        return proxjogar;
    }

    public void setProxjogar(String proxjogar) {
        this.proxjogar = proxjogar;
    }

    public String getJogadorcarta() {
        return jogadorcarta;
    }

    public void setJogadorcarta(String jogadorcarta) {
        this.jogadorcarta = jogadorcarta;
    }

    public String getJogoId() {
        return jogoId;
    }

    public void setJogoId(String jogoId) {
        this.jogoId = jogoId;
    }

    public ArrayList<Cartas> getCartasmao() {
        return Cartasmao;
    }

    public void setCartasmao(ArrayList<Cartas> Cartasmao) {
        ArrayList<Cartas> mao = new ArrayList<>(Cartasmao);
        this.Cartasmao = mao;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Buffer() {
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String[] getUsersSala() {
        return usersSala;
    }

    public void setUsersSala(String[] usersSala) {
        this.usersSala = usersSala;
    }

    public ArrayList<String> getUsersAceites() {
        return usersAceites;
    }

    public void setUsersAceites(ArrayList<String> usersAceites) {
        this.usersAceites = usersAceites;
    }

    public ArrayList<String> getUsersConvidados() {
        return usersConvidados;
    }

    public void setUsersConvidados(ArrayList<String> usersConvidados) {
        this.usersConvidados = usersConvidados;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(String utilizador) {
        this.utilizador = utilizador;
    }

    public int getSubflag() {
        return subflag;
    }

    public void setSubflag(int subflag) {
        this.subflag = subflag;
    }
}
