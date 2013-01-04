/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Jinterface;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * @author Paulo
 */
public interface ServerInterface extends ObserverInterface{
    public void addObserver(ObserverInterface obs) throws RemoteException;
    public ArrayList<String> getjogadores() throws RemoteException;
    public ArrayList<String> getjogos() throws RemoteException;
    
}
