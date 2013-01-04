/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.Serializable;

/**
 *
 * @author Rafael
 */
public class Opcoes implements Serializable {

    private String user;
    private String pass;
    private String serv;
    private int porto;
    private boolean saveUser;
    private boolean procuraServidor;

    public int getPorto() {
        return porto;
    }

    public void setPorto(int porto) {
        this.porto = porto;
    }

    public Opcoes() {
        user = "";
        pass = "";
        serv = "127.0.0.1";
        porto = 6000;
        saveUser = false;
        procuraServidor = true;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getServ() {
        return serv;
    }

    public boolean isSaveUser() {
        return saveUser;
    }

    public boolean isProcuraServidor() {
        return procuraServidor;
    }

    public void addUser(String user, String pass) {
        this.user = user;
        this.pass = pass;
        this.saveUser = true;
    }

    public void setServ(String serv) {
        this.serv = serv;
    }

    public void setSaveUser(boolean saveUser) {
        this.saveUser = saveUser;
    }

    public void setProcuraServidor(boolean procuraServidor) {
        this.procuraServidor = procuraServidor;
    }
}
