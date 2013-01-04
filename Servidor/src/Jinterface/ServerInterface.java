package Jinterface;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import sistema.ObserverInterface;

/**
 * @author Paulo
 */
public interface ServerInterface extends Remote{
    public void addObserver(ObserverInterface obs) throws RemoteException;
    public ArrayList<String> getjogadores() throws RemoteException;
    public ArrayList<String> getjogos() throws RemoteException;
}