/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Jinterface;
import java.rmi.*;
/**
 *
 * @author Paulo
 */
public interface ObserverInterface extends Remote{
    public void printjogador(String jog) throws RemoteException;
    public void printjogo(String jgs) throws RemoteException;
}
