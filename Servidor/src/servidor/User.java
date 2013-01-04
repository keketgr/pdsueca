package servidor;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jogo.Cartas;

public class User implements Serializable {

    public final static int ESTADO_SALA = 0;
    public final static int ESTADO_JOGO = 3;
    public final static int ESTADO_PRE_JOGO = 1;
    public final static int ESTADO_INVITE = 2;
    private String nickname;
    private String pass;
    private String JogoId;
    private static Socket s;
    private int estado;
    transient private ObjectOutputStream out;
    //ObjectInputStream in;
    //Variáveis Perfil
    private int vitorias;
    private int NJogos;

    public User(String nickname, String pass, Socket s, ObjectOutputStream out) {
        this.nickname = nickname;
        this.pass = pass;
        vitorias = 0;
        NJogos = 0;
        this.s = s;
        estado = ESTADO_SALA;
        this.out = out;
    }

    public String getJogoId() {
        return JogoId;
    }

    public void setJogoId(String JogoId) {
        this.JogoId = JogoId;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public void setS(Socket s) {
        this.s = s;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPass() {
        return pass;
    }

    public int getVitorias() {
        return vitorias;
    }

    public int getNJogos() {
        return NJogos;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void addvic() {
        vitorias++;
        addJogos();
    }

    public void addJogos() {
        NJogos++;
    }

    public void closeSock() {
        try {
            s.close();
            System.out.println("Jogador " + nickname + " saiu da sala");
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void UpdateSala(Buffer buf) {

        try {
            out.writeObject(buf);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /* 
     * ========================================================================
     * ============================ Decorrer do Jogo ==========================
     * ========================================================================
     */

    public void IniciaJogo(ArrayList<String> jogadores) throws IOException {
        if (out == null) {
            out = new ObjectOutputStream(s.getOutputStream());
        }
        Buffer buf = new Buffer();
        buf.setFlag(9);
        buf.setSubflag(1);
        buf.setUsersAceites(jogadores);
        this.estado = ESTADO_JOGO;
        out.writeObject(buf);
        out.flush();
    }

    public void RecebeCarta(Cartas carta, String proximoUser) throws IOException {
        Buffer buf = new Buffer();
        buf.setCartajogada(carta);
        buf.setUtilizador(proximoUser);
        buf.setFlag(9);
        buf.setSubflag(5);
        out.writeObject(buf);
        out.flush();

    }

    public void RecebeCartas(ArrayList<Cartas> mao, Cartas trunf) throws IOException {
        Buffer buf = new Buffer();
        buf.setCartasmao(mao);
        buf.setTrunfo(trunf);
        System.out.println("<" + nickname + ">mao: " + mao);
        buf.setFlag(9);
        buf.setSubflag(3);
        out.writeObject(buf);
        out.flush();

    }

    public void jogoQuemJoga(String nickname) throws IOException {
        Buffer buff = new Buffer();
        buff.setFlag(9);
        buff.setSubflag(4);
        buff.setUtilizador(nickname);
        out.writeObject(buff);
        out.flush();

    }

    public void CimaBaixo() throws IOException {
        Buffer buff = new Buffer();
        buff.setFlag(9);
        buff.setSubflag(2);
        buff.setUtilizador(nickname);
        out.writeObject(buff);
        out.flush();
    }

    public void RecolheCartas() throws IOException {
        Buffer buff = new Buffer();
        buff.setFlag(9);
        buff.setSubflag(6);
        out.writeObject(buff);
        out.flush();
    }

    public void MensagemSobreJogo(String msg) throws IOException {
        Buffer buff = new Buffer();
        buff.setFlag(9);
        buff.setSubflag(7);
        buff.setMensagem(msg);
        out.writeObject(buff);
        out.flush();

    }

    public void jogoFimJogo(String msgUsersVitoriosos) throws IOException {

        Buffer buff = new Buffer();
        buff.setFlag(9);
        buff.setSubflag(10);
        buff.setMensagem(msgUsersVitoriosos);

        out.writeObject(buff);
        out.flush();

    }

    public void notificavencedor(int sf,int pontos) throws IOException {
        Buffer buff = new Buffer();
        buff.setFlag(9);
        buff.setPontos(pontos);
        buff.setSubflag(sf);
        out.writeObject(buff);
        out.flush();
    }
    /* 
     * ========================================================================
     * ================================ MENSAGENS =============================
     * ========================================================================
     */

    public void EnviaMensagem(String msg, int flag) {
        Buffer buff = new Buffer();
        try {
            buff.setFlag(flag);
            buff.setMensagem(msg);
            out.writeObject(buff);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void EnviaMensagemprivada(String mensagem, String utilizador) {
        Buffer buff = new Buffer();
        try {
            buff.setFlag(10);
            buff.setMensagem(mensagem);
            buff.setUtilizador(utilizador);
            out.writeObject(buff);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* 
     * ========================================================================
     * ================================= INVITES ==============================
     * ========================================================================
     */
    void invite(String utilizador) {
        Buffer buff = new Buffer();
        buff.setSubflag(1);
        try {
            buff.setFlag(11);
            buff.setUtilizador(utilizador);
            out.writeObject(buff);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void trataInvite(String user, int i) {
        Buffer buff = new Buffer();
        try {
            buff.setFlag(11);
            buff.setSubflag(i);
            buff.setUtilizador(user);
            out.writeObject(buff);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void InviteCancelarInvite() {
        Buffer buff = new Buffer();
        try {
            buff.setFlag(11);
            buff.setSubflag(5);
            out.writeObject(buff);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void InviteEntrarJogo() {
        Buffer buff = new Buffer();
        try {
            buff.setFlag(11);
            buff.setSubflag(4);
            buff.setJogoId(JogoId);
            //buff.setUtilizador(user);
            out.writeObject(buff);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void InviteCancelarNotificacao(String nickname) {
        Buffer buff = new Buffer();
        try {
            buff.setFlag(11);
            buff.setSubflag(6);
            buff.setUtilizador(nickname);
            out.writeObject(buff);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* 
     * ========================================================================
     * ================================ PRÉ JOGO ==============================
     * ========================================================================
     */
    void createJoinGame() {
        Buffer buff = new Buffer();
        try {
            buff.setFlag(4);
            buff.setJogoId(JogoId);
            out.writeObject(buff);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void CancelarJogo() {
        Buffer buff = new Buffer();
        estado = ESTADO_SALA;
        try {
            buff.setFlag(14);
            out.writeObject(buff);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* 
     * ========================================================================
     * ================================ OVERRIDE ==============================
     * ========================================================================
     */
    @Override
    public String toString() {
        return nickname;
    }
}
