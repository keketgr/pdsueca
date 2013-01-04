/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            String url = "rmi://" + args[0] + "/serviç";
            try {
                servico= (ServerInterface) Naming.lookup(url);
                
            } catch (Exception e) {
                System.out.println("erro: " + e);
            }
            
        } else {
            System.exit(-1);
        }
        
        Scanner sc=new Scanner(System.in);
        String in="";
        while(in!="sair"){
            in=sc.next();
            if(in.equals("jogadores")){
                try {
                    getjogadores();
                } catch (RemoteException ex) {
                    Logger.getLogger(Observer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if(in.equals("jogadores")){
                try {
                    getjogos();
                } catch (RemoteException ex) {
                    Logger.getLogger(Observer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                
        }
    }

    @Override
    public void printjogador(String jog) throws RemoteException {
        System.out.println("Jogador " + jog + " conectou-se..");
    }

    @Override
    public void printjogo(String jgs) throws RemoteException {
        System.out.println("Novo jogo " + jgs + " jogadores: ");
        System.out.println(jgs);
        
    }

    
    public static void getjogadores() throws RemoteException {
        ArrayList<String> a=servico.getjogadores();
        for(int i=0;i<a.size();i++){
            System.out.println(a.get(i));
        }
    }

    public static void getjogos() throws RemoteException {
         ArrayList<String> a=servico.getjogos();
        for(int i=0;i<a.size();i++){
            System.out.println(a.get(i));
        }
    }
}
