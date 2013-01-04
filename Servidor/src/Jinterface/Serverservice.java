package Jinterface;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import servidor.Servidor;
import sistema.ObserverInterface;

/**
 *
 * @author Paulo
 */
public class Serverservice extends UnicastRemoteObject implements ServerInterface {

    protected ObserverInterface observer = null;
    protected static Servidor server;

    public Serverservice(Servidor s) throws RemoteException {
        server = s;
    }

    @Override
    public void addObserver(ObserverInterface obs) throws RemoteException {
        observer = obs;
    }

    @Override
    public ArrayList<String> getjogadores() throws RemoteException {
        return server.getjogadores();
    }

    @Override
    public ArrayList<String> getjogos() throws RemoteException {
        return server.getJogos();
    }

    public void printjogador(String jog) throws RemoteException {
        if (observer != null) {
            observer.printjogador(jog);
        }
    }

    public void printjogo(String jgs) throws RemoteException {
        if (observer != null) {
            observer.printjogo(jgs);
        }
    }
}
