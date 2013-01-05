//package servidor;

//import Jinterface.Serverservice;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
//import jogo.*;

public class Servidor implements Runnable {

    private Socket socketaux;
    static ArrayList<User> usersRegist;
    static ArrayList<User> usersLogad;
    static ArrayList<Jogo> jogos;
    Buffer buffer;
    static int njogos;
    static ArrayList<Invites> invites;
    static ArrayList<PreJogo> prejogos;
    static Serverservice servico = null;
    static Registry r;

    /**
     * @param args the command line arguments
     */
    public Servidor(Socket s) {
        this.socketaux = s;
        buffer = new Buffer();


    }

    public ArrayList<String> getJogos() {
        ArrayList<String> jog = new ArrayList<>();
        for (int i = 0; i < jogos.size(); i++) {
            jog.add(jogos.get(i).getidjogo());
            jog.addAll(jogos.get(i).getStringjogadores());
        }
        return jog;
    }

    public ArrayList<String> getjogadores() {
        ArrayList<String> jog = new ArrayList<>();
        for (int i = 0; i < usersLogad.size(); i++) {
            if (usersLogad.get(i).getEstado() != User.ESTADO_JOGO) {
                jog.add(usersLogad.get(i).getNickname());
            }
        }
        return jog;
    }

    public boolean verificaUser(String utilizador, String pass) {
        for (int i = 0; i < usersRegist.size(); i++) {
            if (usersRegist.get(i).getNickname().compareTo(utilizador) == 0 && usersRegist.get(i).getPass().compareTo(pass) == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean verificaUser(String utilizador) {
        for (int i = 0; i < usersRegist.size(); i++) {
            if (usersRegist.get(i).getNickname().compareTo(utilizador) == 0) {
                return true;
            }
        }
        return false;
    }

    public void EnviaMensagemSala(String msg, String user) {
        for (int i = 0; i < usersLogad.size(); i++) {
            if (usersLogad.get(i).getEstado() < User.ESTADO_JOGO) {
                if (usersLogad.get(i).getNickname().compareTo(user) != 0) {
                    usersLogad.get(i).EnviaMensagem(msg, 1);
                }
            }
        }
    }

    public int getNumUsersSala() {
        int count = 0;
        for (int i = 0; i < usersLogad.size(); i++) {
            if (usersLogad.get(i).getEstado() < User.ESTADO_JOGO) {
                count++;
            }
        }
        return count;
    }

    public String[] GetUsersSala() {

        String[] usersala = new String[getNumUsersSala()];
        int k = 0;
        for (int i = 0; i < usersLogad.size(); i++) {
            if (usersLogad.get(i).getEstado() < User.ESTADO_JOGO) {
                usersala[k++] = usersLogad.get(i).getNickname().toString();
            }
        }
        return usersala;
    }

    public void MensagemSystema(String msg, int flag, String user) {
        msg = "Sistema: " + msg;
        for (int i = 0; i < usersLogad.size(); i++) {
            if (usersLogad.get(i).getEstado() < User.ESTADO_JOGO) {
                //if (user == null) {
                usersLogad.get(i).EnviaMensagem(msg, 2);
//                } else if (usersLogad.get(i).getNickname().compareTo(user) != 0) {
//                    usersLogad.get(i).EnviaMensagem(msg, 2);
//                }
            }
        }
    }

    public User GetUserbyNick(String user) {
        for (int i = 0; i < usersRegist.size(); i++) {
            if (usersRegist.get(i).getNickname().compareTo(user) == 0) {
                return usersRegist.get(i);
            }
        }
        return null;
    }

    public void UpdateSala() {

        Buffer buf = new Buffer();
        buf.setFlag(3);
        buf.setUsersSala(GetUsersSala());
        verificaJogos();
        String[][] idgamejoga;


        for (int i = 0; i < usersLogad.size(); i++) {//faz o update dos users

            buf.setUsersAceites(new ArrayList<String>());
            buf.setUsersConvidados(new ArrayList<String>());
            switch (usersLogad.get(i).getEstado()) {
                case User.ESTADO_SALA://faz update da lista de jogos

                    idgamejoga = new String[prejogos.size()][];

                    for (int j = 0; j < prejogos.size(); j++) {
                        //idgamejoga[i] = jogos.get(1231).getGameAndPlayers();                        
                        idgamejoga[j] = prejogos.get(j).getJogadores();
                    }
                    buf.setGameIdJogadores(idgamejoga);
                    break;
                case User.ESTADO_INVITE://faz  update dos jogadores no invite

                    Invites invites = getInviteof(usersLogad.get(i));
                    if (invites != null) {
                        buf.setUsersAceites(invites.getUsersAceites());
                        buf.setUsersConvidados(invites.getUsersConvidados());
                    }
                    break;
                case User.ESTADO_PRE_JOGO://faz update dos jogadores no seu jogo
                    PreJogo pre = getPreJogoOf(usersLogad.get(i));
                    if (pre != null) {
                        buf.setUsersAceites(pre.getEquipa1());
                        buf.setUsersConvidados(pre.getEquipa2());
                    }
                    break;
                case User.ESTADO_JOGO:
                    Jogo j = getJogo(usersLogad.get(i).getJogoId());
                    buf.setUsersAceites(j.getStringjogadores());
                    break;
            }
            usersLogad.get(i).UpdateSala(buf);
        }
    }

    public int UserRegistAt(String user) {
        for (int i = 0; i < usersRegist.size(); i++) {
            if (usersRegist.get(i).getNickname().compareTo(user) == 0) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void run() {
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        Socket aux = socketaux;
        String user = "";
        try {
            in = new ObjectInputStream(aux.getInputStream());
            out = new ObjectOutputStream(aux.getOutputStream());
            try {
                do {
                    Object ob = in.readObject();

                    buffer = (Buffer) ob;
                    switch (buffer.getFlag()) {
                        case 0://user logOut
                            UserLogOut(buffer.getUtilizador());
                            UpdateSala();
                            return;
                        case 1://login
                            if (verificaUser(buffer.getUtilizador(), buffer.getPass())) {
                                buffer.setFlag(1);
                                user = buffer.getUtilizador();
                                //GetUserbyNick(user).setIn(in);
                                GetUserbyNick(user).setOut(out);
                                usersLogad.add(GetUserbyNick(user));
                                MensagemSystema((user + " entrou na sala\n"), 2, user);
                                servico.printjogador(user);

                            } else {
                                buffer.setFlag(-1);
                            }
                            out.writeObject(buffer);
                            out.flush();
                            UpdateSala();
                            break;
                        case 2://regist
                            if (!verificaUser(buffer.getUtilizador())) {
                                user = buffer.getUtilizador();
                                usersRegist.add(new User(buffer.getUtilizador(), buffer.getPass(), aux, out));
                                usersLogad.add(GetUserbyNick(user));
                                buffer.setFlag(1);
                                MensagemSystema((user + " entrou na sala\n"), 2, user);
                                SaveUsers();
                                servico.printjogador(user);
                            } else {
                                buffer.setFlag(-1);
                            }
                            out.writeObject(buffer);
                            out.flush();
                            UpdateSala();
                            break;
                        case 3://

                            break;
                        case 4://reencaminha mensagens
                            EnviaMensagemSala(buffer.getMensagem(), user);
                            break;
                        case 5://Jogo
                            trataJogo(buffer);
                            break;
                        case 6://whisp
                            EnviaMensagemWhisp(buffer.getMensagem(), user, buffer.getUtilizador());
                            break;
                        case 7://Invite
                            trataInvite(user, buffer.getUtilizador(), buffer.getSubflag());

                            break;
                        case 8://Pre-Jogo
                            trataPreJogo(GetUserbyNick(buffer.getUtilizador()), buffer.getSubflag(), buffer.getJogoId());
                            break;
                        default:
                            System.out.println("Default catch: " + buffer.getFlag() + "\n");
                    }
                    //}suposto IF
                } while (true);
            } catch (ClassNotFoundException e) {
                System.err.println("<estoirou aki A" + Thread.currentThread().getName()
                        + ">  Pedido recebido de tipo inesperado");
                UserLogOut(user);
                UpdateSala();
                return;
            }
        } catch (IOException e) {
            System.out.println("<estoirou aki B " + Thread.currentThread().getName()
                    + "> Erro ao aceder ao socket:\n\t" + e);
            UserLogOut(user);
            UpdateSala();
        } finally {
            try {
                if (socketaux != null) {
                    socketaux.close();
                }
            } catch (IOException e) {
            }
        }
    }

    public static void criaObserver() {
        try {
            servico = new Serverservice(new Servidor(null));
            r = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            System.out.println(InetAddress.getLocalHost().getHostAddress());
            Naming.bind("rmi://localhost/ServerInterface", servico);
            //r.bind("servico", servico);
        } catch (RemoteException | UnknownHostException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AlreadyBoundException e) {
        } catch (MalformedURLException e) {
        }

    }

    public static void main(String[] args) throws SocketException, InterruptedException {
        ServerSocket ss = null;
        njogos = 100;
        Socket toClient;
        jogos = new ArrayList<>();
        int PortoEscuta = 6000;
        Thread t, m = null;
        int nCreatedThreads = 0;
        usersRegist = new ArrayList<>();
        usersLogad = new ArrayList<>();
        invites = new ArrayList<>();
        prejogos = new ArrayList<>();
        usersRegist = LoadUsers();

        criaObserver();
        try {

            ss = new ServerSocket(PortoEscuta);
            System.out.println("Iniciou server em " + InetAddress.getLocalHost().toString() + " porto: " + ss.getLocalPort());

            m = new Thread(new MulticastServer(ss.getInetAddress(), ss.getLocalPort()));
            m.start();
            // m.join();


            System.out.println("Iniciou Mulitcast");
            while (true) {
                System.out.println("a espera");
                toClient = ss.accept();
                nCreatedThreads++;
                t = new Thread(new Servidor(toClient));
                t.start();
            }

        } catch (IOException e) {
            System.out.println("<SRV> Erro ao aceder ao socket:\n\t" + e);
        } finally {
            if (ss != null) {
                try {
                    ss.close();

                } catch (IOException e) {
                }
            }
        }
    }

    private void SaveUsers() {

        try {
            FileOutputStream fo = new FileOutputStream("FileUsers.sav");
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            for (int i = 0; i < usersRegist.size(); i++) {
                oo.writeObject(usersRegist.get(i));
            }
            oo.close();
            System.out.println("Object created successfully.");
        } catch (IOException e) {
            System.out.println("Error - " + e.toString());
        }
    }

    private static ArrayList<User> LoadUsers() {
        ArrayList<User> lista = new ArrayList<>();
        try {
            ObjectInputStream restore = new ObjectInputStream(new FileInputStream("FileUsers.sav"));
            while (lista.add((User) restore.readObject())) {
            }

            restore.close();
            return lista;

        } catch (FileNotFoundException ex) {
            return new ArrayList<>();
        } catch (Exception e) {
        }
        return lista;
    }

    private void UserLogOut(String utilizador) {
        for (int i = 0; i < usersLogad.size(); i++) {
            if (usersLogad.get(i).getNickname().compareTo(utilizador) == 0) {
                Jogo j = getJogoOf(utilizador);
                if (j != null) {
                    j.Disconect(GetUserbyNick(utilizador), 1, -1);
                }
                PreJogo pj = getPreJogoOf(GetUserbyNick(utilizador));
                if (pj != null) {
                    if (pj.sair(GetUserbyNick(utilizador))) {
                        prejogos.remove(pj);
                    }
                }
                Invites inv = getInviteof(GetUserbyNick(utilizador));
                if (inv != null) {
                    if (inv.isHost(GetUserbyNick(utilizador))) {
                        inv.Cancelar();
                        invites.remove(inv);
                    } else {
                        inv.removeUserAceites(GetUserbyNick(utilizador));
                        inv.recusou(GetUserbyNick(utilizador));
                    }
                }
                //usersLogad.get(i).closeSock();
                usersLogad.remove(i);
                MensagemSystema(("User " + utilizador + " Saiu da sala\n"), 2, utilizador);
                return;
            }
        }
    }

    private Jogo getJogo(String jogoId) {
        for (int i = 0; i < jogos.size(); i++) {
            if (jogos.get(i).getidjogo().compareTo(jogoId) == 0) {
                return jogos.get(i);
            }
        }
        return null;
    }

    public void EnviaMensagemWhisp(String mensagem, String emissor, String receptor) {
        for (int i = 0; i < usersLogad.size(); i++) {
            if (usersLogad.get(i).getEstado() < User.ESTADO_JOGO) {
                if (usersLogad.get(i).getNickname().compareTo(receptor) == 0) {
                    usersLogad.get(i).EnviaMensagemprivada(mensagem, emissor);
                }
            }
        }
    }

    private Invites getInviteofHost(User u) {
        for (int i = 0; i < invites.size(); i++) {
            if (invites.get(i).isHost(u)) {
                return invites.get(i);
            }
        }
        Invites i = new Invites(u);
        invites.add(i);
        return i;
    }

    public Invites getInviteof(User u) {
        for (int i = 0; i < invites.size(); i++) {
            if (invites.get(i).isJogador(u)) {
                return invites.get(i);
            }
        }
        return null;
    }

    public PreJogo getJogoXvagas(int x) {
        for (int i = 0; i < prejogos.size(); i++) {
            if (prejogos.get(i).isVagas(x)) {
                return prejogos.get(i);
            }
        }
        PreJogo pre = new PreJogo();//5 vitorias
        prejogos.add(pre);
        return pre;
    }

    private void trataInvite(String user1, String user2, int flag) {
        Invites invit;
        switch (flag) {

            case 1://user1 convida user2
                if (GetUserbyNick(user2).getEstado() != User.ESTADO_JOGO) {
                    GetUserbyNick(user1).setEstado(User.ESTADO_INVITE);
                    GetUserbyNick(user2).invite(user1);
                    getInviteofHost(GetUserbyNick(user1)).addUserConvidados(GetUserbyNick(user2));
                    //System.out.println("User" + user1 + " convidou user " + user2);
                    UpdateSala();
                }
                break;
            case 2://user1 Aceita convite de user2
                //System.out.println("User" + user1 + " Aceitou convite de " + user2);
                GetUserbyNick(user2).trataInvite(user1, 2);
                GetUserbyNick(user1).setEstado(User.ESTADO_INVITE);
                getInviteofHost(GetUserbyNick(user2)).Aceitou(GetUserbyNick(user1));
                UpdateSala();
                break;
            case 3://user1 Regeita convite de user2
                // System.out.println("User" + user1 + " Recusou convite de " + user2);
                GetUserbyNick(user2).trataInvite(user1, 3);
                getInviteofHost(GetUserbyNick(user2)).recusou(GetUserbyNick(user1));
                UpdateSala();
                break;
            case 4://Start procura Jogos com vagas disponiveis ou cria novo jogo

                invit = getInviteofHost(GetUserbyNick(user2));
                PreJogo pre = getJogoXvagas(invit.getNumUsersAceites());
                invit.Start(pre);
                //System.out.println("Começa jogo " + j.getId());
                UpdateSala();
                invites.remove(invit);
                break;
            case 5://Sair ou Cancelar Invited Game... Host saiu ou cancelou
                invit = getInviteof(GetUserbyNick(user1));
                if (invit.isHost(GetUserbyNick(user1))) {
                    invit.Cancelar();
                    invites.remove(invit);
                } else {
                    invit.removeUserAceites(GetUserbyNick(user1));
                }
                UpdateSala();
                break;

        }

    }

    private void trataPreJogo(User u, int flag, String idJogo) {
        PreJogo pre = null;
        switch (flag) {
            case 1://cria Jogo
                prejogos.add(new PreJogo(u));
                u.setEstado(User.ESTADO_PRE_JOGO);
                MensagemSystema((u.getNickname() + " Criou um jogo \n"), 2, null);
                u.setJogoId(u.getNickname());
                u.createJoinGame();
                UpdateSala();
                break;
            case 2:// Join pre-jogo
                pre = getPreJogo(GetUserbyNick(idJogo));
                if (pre == null) {
                    return;
                }
                pre.addJogador(u);
                u.setJogoId(idJogo);
                u.setEstado(User.ESTADO_PRE_JOGO);
                u.createJoinGame();
                UpdateSala();
                break;
            case 3://sair
                pre = getPreJogoOf(u);
                if (pre == null) {
                    return;
                }
                if (pre.sair(u)) {
                    prejogos.remove(pre);
                }
                u.setEstado(User.ESTADO_SALA);
                u.CancelarJogo();
                UpdateSala();
                break;
            case 4://iniciar jogo
                pre = getPreJogoOf(u);
                if (pre == null) {
                    return;
                }
                Jogo j = pre.startGame();

                if (j != null) {
                    jogos.add(j);
                    UpdateSala();
                    j.start();
                    prejogos.remove(pre);
                    try {
                        servico.printjogo(j.toString());
                    } catch (RemoteException ex) {
                        Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case 5://mudar de equipa
                pre = getPreJogoOf(u);
                if (pre == null) {
                    return;
                }
                pre.changeTeam(u);
                UpdateSala();
                break;
        }
    }

    private PreJogo getPreJogoOf(User u) {
        for (int i = 0; i < prejogos.size(); i++) {
            if (prejogos.get(i).isJogador(u)) {
                return prejogos.get(i);
            }
        }
        return null;
    }

    private PreJogo getPreJogo(User u) {
        for (int i = 0; i < prejogos.size(); i++) {
            if (prejogos.get(i).isHost(u)) {
                return prejogos.get(i);
            }
        }
        return null;
    }

    private void trataJogo(Buffer buff) {//flag 5
        Jogo j = getJogoOf(buff.getUtilizador());
        switch (buff.getSubflag()) {
            case 1://cima
                j.getPartida().setCharcimabaixo('c');
                break;
            case 2://baixo
                j.getPartida().setCharcimabaixo('b');
                break;
            case 3://recebe carta
                j.getPartida().setUltimaCarta(buff.getCartajogada());
                break;
        }
    }

    private Jogo getJogoOf(String utilizador) {
        for (int i = 0; i < jogos.size(); i++) {
            if (jogos.get(i).isJogador(GetUserbyNick(utilizador))) {
                return jogos.get(i);
            }
        }
        return null;
    }

    private void verificaJogos() {
        for (int i = 0; i < jogos.size(); i++) {
            if (!jogos.get(i).isAlive() && jogos.get(i).getjogadores().isEmpty()) {
                jogos.remove(i);
            }
        }
    }
}
