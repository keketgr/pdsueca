package sistema;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import Jinterface.ServerInterface;

/**
 *
 * @author Paulo
 */
public class Observer extends UnicastRemoteObject implements ObserverInterface {

    static Observer rmi;

    public Observer() throws RemoteException {
    }
    /**
     * @param args the command line arguments
     */
    static ServerInterface servico;

    public static void main(String[] args) {

        if (args.length > 0) {
            String url ="rmi://localhost/ServerInterface";
            System.out.println(args[0]);
            try {
                servico = (ServerInterface) Naming.lookup(url);

            } catch (Exception e) {
                System.out.println("erro: " + e);
            }

        } else {
            System.exit(-1);
        }
        try {
            servico.addObserver(new Observer());
        } catch (RemoteException ex) {
            Logger.getLogger(Observer.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scanner sc = new Scanner(System.in);
        String in = "";
        while (in != "sair") {
            System.out.print("\nCmd:>");
            in = sc.next();
            if (in.equals("jogadores")) {
                try {
                    getjogadores();
                } catch (RemoteException ex) {
                    Logger.getLogger(Observer.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (in.equals("jogos")) {
                try {
                    getjogos();
                } catch (RemoteException ex) {
                    Logger.getLogger(Observer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if(in.equals("help")){
                System.out.println("<jogadores> mostra todos os jogadores na sala\n"
                        + "<jogos> mostra todos os jogos criados com os as pontuações e useres no jogo");
            }else{
                 System.out.println("\nComando incorrecto... escreva help pra ver os comandos");
            }
        }
    }

    @Override
    public void printjogador(String jog) throws RemoteException {
        System.out.println("Jogador " + jog + " conectou-se..");
        System.out.print("\nCmd:>");
    }

    @Override
    public void printjogo(String jgs) throws RemoteException {
        System.out.println(jgs );
        System.out.print("\nCmd:>");
    }

    public static void getjogadores() throws RemoteException {
        ArrayList<String> a = servico.getjogadores();
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }
    }

    public static void getjogos() throws RemoteException {
        ArrayList<String> a = servico.getjogos();
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }
    }
}
