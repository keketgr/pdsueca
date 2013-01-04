package cliente;

import ParteGrafica.JanelaPrincipal;
import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jogo.Cartas;
import servidor.Buffer;

public class Comunicacao extends Thread {

    public final static int ESTADO_SALA = 0;
    public final static int ESTADO_LISTA_DE_ESPERA = 1;
    public final static int ESTADO_NO_JOGO = 2;
    public final static int ESTADO_INVITE = 3;
    String user;
    Socket server;
    Buffer buffer;
    JanelaPrincipal grafico;
    ObjectOutputStream out;
    ObjectInputStream input;
    //Variaveis Update Sala
    String[] UsersSala;
    String[][] idJogoUser;
    ArrayList<String> UsersNoJogo;
    String idJogo;
    int estado;//estado onde se encontra 0. sala 1. lista de espera 2.jogo... 

    public Comunicacao(JanelaPrincipal grafico) {
        user = "";
        server = null;
        buffer = new Buffer();
        this.grafico = grafico;

        if (grafico.getOp().isProcuraServidor()) {
            createSocket();
        } else {
            try {
                setServer(InetAddress.getByName(grafico.getOp().getServ()), grafico.getOp().getPorto());
            } catch (UnknownHostException ex) {
                Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        out = null;
        //input = null;
        UsersSala = new String[0];
        idJogoUser = new String[0][0];
        estado = ESTADO_SALA;
    }

    public Comunicacao(Comunicacao jogo, JanelaPrincipal grafico) {
        user = jogo.getUser();
        buffer = new Buffer();
        server = jogo.getServer();
        this.grafico = grafico;
        this.out = jogo.getOut();
        this.input = jogo.getInput();
        UsersSala = null;
        idJogoUser = new String[0][0];
        estado = ESTADO_SALA;
        UsersNoJogo = new ArrayList<>();
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(String idJogo) {
        this.idJogo = idJogo;
    }

    public ObjectInputStream getInput() {
        return input;
    }

    public void setInput(ObjectInputStream input) {
        this.input = input;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Socket getServer() {
        return server;
    }

    public String[] getUsersSala() {
        return UsersSala;
    }

    public void setUsersSala(String[] UsersSala) {
        this.UsersSala = UsersSala;
    }

    public void setServer(InetAddress addr, int port) {
        try {
            this.server = new Socket(addr, port);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
            this.server = null;
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
            this.server = null;
        }
    }

    public String[][] getIdJogoUser() {
        return idJogoUser;
    }

    public void setIdJogoUser(String[][] idJogoUser) {
        this.idJogoUser = idJogoUser;
    }

    public JanelaPrincipal getGrafico() {
        return grafico;
    }

    public void createSocket() {
        try {
            InetAddress serveraddr = null;
            int port = 0;

            MulticastSocket soc = new MulticastSocket(6001);
            soc.setSoTimeout(1000);
            InetAddress group = InetAddress.getByName("225.0.0.1");
            soc.joinGroup(group);
            byte[] buf = new byte[256];
            String msg = "IP";

            DatagramPacket pkt = new DatagramPacket(buf, buf.length);
            DatagramPacket env = new DatagramPacket(msg.getBytes(), msg.length(), group, 6001);
            soc.send(env);
            String rec = null;
            soc.receive(pkt);
            rec = new String(pkt.getData());
            if (rec.contains("IP")) {
                soc.receive(pkt);
            }

            rec = new String(pkt.getData());
            StringTokenizer tok = new StringTokenizer(rec);
            String t = tok.nextToken();
            //t=t.substring(1,t.length());      
            serveraddr = InetAddress.getByName(t);
            port = Integer.parseInt(tok.nextToken());
            System.out.print("Address " + serveraddr + " port: " + port);
            setServer(serveraddr, port);
            soc.leaveGroup(group);
            soc.close();
        } catch (Exception ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void enviaMensagem(String msg, int flag, String userw) {

        buffer = new Buffer();
        msg = user + ": " + msg + "\n";
        buffer.setMensagem(msg);
        buffer.setFlag(flag);
        buffer.setUtilizador(userw);

        try {
            out.writeObject(buffer);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isSucesso() {

        try {
            if (input == null) {
                input = new ObjectInputStream(server.getInputStream());
            }

            buffer = (Buffer) input.readObject();
            if (buffer.getFlag() != -1) {
                return true;
            }

        } catch (IOException e) {
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }

    public void enviaLogin(String user, String pass) {

        buffer.setFlag(Constantes.FLAG_S_LOGIN);
        buffer.setUtilizador(user);
        buffer.setPass(pass);
        try {
            if (out == null) {
                out = new ObjectOutputStream(server.getOutputStream());
            }
            out.writeObject(buffer);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void CriarUser(String user, String pass) {
        buffer.setFlag(Constantes.FLAG_S_REGISTAR);
        buffer.setUtilizador(user);
        buffer.setPass(pass);
        try {
            if (out == null) {
                out = new ObjectOutputStream(server.getOutputStream());
            }
            out.writeObject(buffer);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Login(String user, String pass) {
        buffer.setFlag(Constantes.FLAG_S_LOGIN);
        buffer.setUtilizador(user);
        buffer.setPass(pass);
        try {
            if (out == null) {
                out = new ObjectOutputStream(server.getOutputStream());
            }
            out.writeObject(buffer);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<String> getUsersNoJogo() {
        return UsersNoJogo;
    }

    @Override
    @SuppressWarnings("empty-statement")
    public void run() {

        try {
            try {
                while (true) {
                    buffer = (Buffer) input.readObject();

                    /**
                     * **************Trata flags*******************
                     */
                    switch (buffer.getFlag()) {
                        case -1:
                            System.out.println("ERRO no login");
                            grafico.showfLogin();
                            break;
                        case 1://recebe mensagem
                            grafico.getchatRoom().colocaMensagem(buffer.getMensagem());
                            break;
                        case 2://Mensagem sistema
                            grafico.getchatRoom().colocaMensagemSistema(buffer.getMensagem());
                            break;
                        case 3://Update
                            Update(buffer);
                            break;
                        case 4://criar/entrar em pre_jogo recebe jogo_id
                            idJogo = buffer.getJogoId();
                            estado = ESTADO_LISTA_DE_ESPERA;
                            //Update(buffer);
                            break;
                        case 5:
                            break;
                        case 10://recebe Whisp
                            grafico.getchatRoom().ChatPrivado(buffer.getMensagem(), buffer.getUtilizador());
                            break;
                        case 11://recebe invite
                            trataInvite(buffer.getUtilizador(), buffer.getSubflag());
                            break;
                        case 13://recebe vencedor
//                            grafico.getfMesa().recolheVaza();
//                            System.out.println("o vencedor da vazada  foi " + buffer.getProxjogar());
//                            grafico.getfMesa().getLbdir().setForeground(Color.black);
//                            grafico.getfMesa().getLbesq().setForeground(Color.black);
//                            grafico.getfMesa().getLbcima().setForeground(Color.black);
//                            grafico.getfMesa().getLbbaixo().setForeground(Color.black);
//
//                            if (grafico.getfMesa().getLbesq().getText() == null ? buffer.getProxjogar() == null : grafico.getfMesa().getLbesq().getText().equals(buffer.getProxjogar())) {
//                                grafico.getfMesa().getLbesq().setForeground(Color.blue);
//                            }
//                            if (grafico.getfMesa().getLbcima().getText() == null ? buffer.getProxjogar() == null : grafico.getfMesa().getLbcima().getText().equals(buffer.getProxjogar())) {
//                                grafico.getfMesa().getLbcima().setForeground(Color.blue);
//                            }
//                            if (grafico.getfMesa().getLbdir().getText().equals(buffer.getProxjogar())) {
//                                grafico.getfMesa().getLbdir().setForeground(Color.blue);
//                            }
                            break;
                        case 14://jogo cancelado ou acabado
                            estado = ESTADO_SALA;
                            grafico.getChatRoom().getfPopup().showfInicio();
                            break;
                        case 9:
                            trataJogo(buffer);
                            break;
                        default:
                            System.out.println("Dafault catch: " + buffer.getFlag() + "\n");
                    }
                }
            } catch (ClassNotFoundException e) {
                System.err.println("<" + Thread.currentThread().getName()
                        + ">  Pedido recebido de tipo inesperado");
                return;
            }
        } catch (IOException e) {
            System.out.println("<" + Thread.currentThread().getName()
                    + "> Erro ao aceder ao socket:\n\t" + e);
        } finally {
            try {
                if (server != null) {
                    server.close();
                }
            } catch (IOException e) {
            }
        }
    }

    private void trataInvite(String user, int flag) {
        switch (flag) {
            case 1://recebe invite
                grafico.getChatRoom().AddNotificacao(user);
                break;
            case 2://User user Aceita invite
                System.out.println("User " + user + " Aceitou invite");
                //grafico.getChatRoom().AceitaInvite(user);
                break;
            case 3: //User user Recusa invit
                System.out.println("User " + user + "Recusou invite");
                break;
            case 4://Comeca jogo
                idJogo = buffer.getJogoId();
                estado = ESTADO_LISTA_DE_ESPERA;
                //System.out.println("comecar jogo" + idJogo);
                //grafico.getChatRoom().getfPopup().showfListaEspera();
                break;
            case 5://cancelar Invite...Host saiu
                estado = ESTADO_SALA;
                grafico.getChatRoom().getfPopup().showfInicio();
                break;
            case 6://cancelar notificacao... host saiu ou jogo começou
                grafico.getChatRoom().removeNotificacao(buffer.getUtilizador());
                grafico.getChatRoom().getfPopup().showfInicio();
        }
    }

    public void sair() {
        buffer = new Buffer();
        buffer.setUtilizador(user);
        buffer.setFlag(Constantes.FLAG_S_LOGOUT);
        // buffer.setUtilizador(userw);

        try {
            if (out != null) {
                out.writeObject(buffer);
                out.flush();
                out.close();

            }

        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            server.close();
            input.close();
            input = null;
            out = null;
            server = null;
            this.stop();
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Update(Buffer buff) {

        switch (estado) {
            case ESTADO_LISTA_DE_ESPERA:

                grafico.getChatRoom().getfPopup().getfListaEspera().updateJogadores(
                        buff.getUsersAceites(), buff.getUsersConvidados());
                grafico.getChatRoom().getfPopup().showfListaEspera();
                break;

            case ESTADO_INVITE:
                grafico.getChatRoom().getfPopup().showfInvite();
                grafico.getChatRoom().getfPopup().getfInvite().setJogadoresAceites(buff.getUsersAceites());
                grafico.getChatRoom().getfPopup().getfInvite().setJogadoresConvidados(buff.getUsersConvidados());
                grafico.getChatRoom().getfPopup().getfInvite().update();
                break;
            case ESTADO_SALA://update sala

                idJogoUser = buff.getGameIdJogadores();
                grafico.getChatRoom().getfPopup().getfInicio().UpdateListaJogos(grafico.getComunicacao().getIdJogoUser());

                break;
            case ESTADO_NO_JOGO:
                //UsersNoJogo = buff.getUsersAceites();
                //grafico.getfMesa().updateJogo();
                return;
        }
        UsersSala = buff.getUsersSala();
        grafico.UpdateSala();
    }

    public void CriarJogo(String nomeJogo) {
        buffer = new Buffer();
        buffer.setUtilizador(user);
        if (nomeJogo != null) {
            buffer.setJogoId(nomeJogo);
        }
        buffer.setFlag(8);
        buffer.setSubflag(1);
        try {
            out.writeObject(buffer);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void JoinJogo(String id) {
        buffer = new Buffer();
        buffer.setUtilizador(user);
        //buffer.setFlag(Constantes.FLAG_S_JOIN_GAME);
        buffer.setFlag(8);
        buffer.setSubflag(2);
        buffer.setJogoId(id);
        try {
            out.writeObject(buffer);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Invite(String user) {
        estado = ESTADO_INVITE;
        buffer = new Buffer();
        buffer.setFlag(Constantes.FLAG_S_INVITE);
        buffer.setSubflag(Constantes.SUBFLAG_S_INVITE_CONVIDAR);
        buffer.setUtilizador(user);

        try {
            out.writeObject(buffer);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AceitaInvite(String user) {
        buffer = new Buffer();
        buffer.setFlag(Constantes.FLAG_S_INVITE);
        buffer.setSubflag(Constantes.SUBFLAG_S_INVITE_ACEITRAR_CONVITE);
        buffer.setUtilizador(user);

        try {
            out.writeObject(buffer);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void RecusaInvite(String user) {
        buffer = new Buffer();
        buffer.setFlag(Constantes.FLAG_S_INVITE);
        buffer.setSubflag(Constantes.SUBFLAG_S_INVITE_RECUSAR_CONVITE);
        buffer.setUtilizador(user);

        try {
            out.writeObject(buffer);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void enviaCarta(Cartas c) {
        buffer.setCartajogada(c);
        buffer.setUtilizador(user);
        buffer.setFlag(5);
        buffer.setSubflag(3);//A corrigir
        buffer.setJogoId(idJogo);
        try {
            out.writeObject(buffer);
            out.flush();
        } catch (Exception ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void inviteStartJogo() {
        buffer = new Buffer();
        buffer.setFlag(Constantes.FLAG_S_INVITE);
        buffer.setSubflag(Constantes.SUBFLAG_S_INVITE_PROCURAR_JOGO);
        buffer.setUtilizador(user);

        try {
            out.writeObject(buffer);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void inviteCancelar() {
        buffer = new Buffer();
        buffer.setFlag(Constantes.FLAG_S_INVITE);
        buffer.setSubflag(Constantes.SUBFLAG_S_INVITE_SAIR_CANCELAR);
        buffer.setUtilizador(user);

        try {
            out.writeObject(buffer);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ComeçarJogo() {
        buffer = new Buffer();
        buffer.setFlag(8);
        buffer.setSubflag(4);
        buffer.setUtilizador(user);
        buffer.setJogoId(idJogo);
        try {
            out.writeObject(buffer);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SairJogo() {
        buffer = new Buffer();
        buffer.setFlag(8);
        buffer.setSubflag(3);
        buffer.setUtilizador(user);
        buffer.setJogoId(idJogo);
        estado = ESTADO_SALA;

        try {
            out.writeObject(buffer);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ChangeTeam() {
        buffer = new Buffer();
        buffer.setFlag(8);
        buffer.setSubflag(5);
        buffer.setUtilizador(user);
        buffer.setJogoId(idJogo);

        try {
            out.writeObject(buffer);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void CimaBaixo(int cb) {
        buffer = new Buffer();
        buffer.setFlag(5);
        buffer.setSubflag(cb);
        buffer.setUtilizador(user);
        buffer.setJogoId(idJogo);

        try {
            out.writeObject(buffer);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void trataJogo(Buffer buff) {//flag 9
        switch (buff.getSubflag()) {
            case 1:
                idJogo = buff.getUtilizador();
                UsersNoJogo = buffer.getUsersAceites();
                grafico.getfMesa().colocaJogadores();
                grafico.showfMesa();
                grafico.getfMesa().updateJogo();
                estado = ESTADO_NO_JOGO;
                break;
            case 2://Cima ou baixo
                grafico.getfMesa().mostraCimaBaixo();
                break;
            case 3://RecebeMao
                grafico.getfMesa().iniciaCartas(buff.getCartasmao(), buff.getTrunfo());
                break;
            case 4://VezDeJogar
                grafico.getfMesa().vezJogar(buff.getUtilizador());
                break;
            case 5://Recebe carta Jogada
                grafico.getfMesa().CartaJogada(buff.getUtilizador(), buff.getCartajogada());
                break;
            case 6://Recolhe cartas
                grafico.getfMesa().recolheVaza();
                break;
            case 7://recebe notificacao sobre jogo
                grafico.getfMesa().putLog(buff.getMensagem() + "\n");
                System.out.println("<Jogo>: " + buff.getMensagem());
                break;
            case 8://recebe vitoria equipa 1
                grafico.getfMesa().getPontuacao1().Pontua1();
                break;
            case 9://recebe vitoria equipa 2
                grafico.getfMesa().getPontuacao1().Pontua2();
                break;
            case 10://Fim de jogo
                //mostra mensagem com equipa vencedora
                grafico.showfChatRoom();
                this.estado = ESTADO_SALA;
                JOptionPane.showMessageDialog(grafico, buff.getMensagem(), "Fim de Jogo", JOptionPane.OK_OPTION, null);
                break;

        }
    }
}
