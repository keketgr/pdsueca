/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ParteGrafica;

import FormsPrincipais.FormChatRoom;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

/**
 *
 * @author Rafael
 */
public class JanelaNovoJogo extends JInternalFrame {

    /**
     * Creates new form FormInicio
     */
    //Comunicacao comunica;
    FormChatRoom chatRoom;
    FormInicio fInicio;
    FormInvite fInvite;
    FormListaEspera fListaEspera;

    public JanelaNovoJogo(FormChatRoom chatRoom) {
        super("Novo Jogo", false, true, false, true);
        initComponents();

        this.chatRoom = chatRoom;
        fInvite = new FormInvite(this);
        fInicio = new FormInicio(this);
        fListaEspera = new FormListaEspera(this);
        this.showfInicio();
    }

    private int BotaoFechar() {
        return JFrame.DO_NOTHING_ON_CLOSE;
    }

    public FormChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(FormChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public void showfInicio() {

        setContentPane(fInicio);
        this.setSize(fInicio.getPreferredSize());
        centrarJanela();
        this.setTitle("Lista de Jogos");
        fInicio.setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    public void showfListaEspera() {
        this.setSize(fListaEspera.getPreferredSize());
        centrarJanela();
        this.setTitle("Novo Jogo");
        setContentPane(fListaEspera);
        fInicio.setVisible(true);
        this.show();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public void showfInvite() {
        this.setSize(fInvite.getPreferredSize());
        centrarJanela();
        this.setTitle("Jogadores Convidados");
        setContentPane(fInvite);
        fInicio.setVisible(true);
        this.show();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    }

    public void centrarJanela() {
        Dimension size = chatRoom.getSize();
        int largura = (int) ((size.getWidth() - this.getSize().getWidth()) / 2);
        int altura = (int) ((size.getHeight() - this.getSize().getHeight()) / 2);
        setLocation(largura, altura);
    }

    public void Update() {
        //this.update(this.getGraphics());
        //formInicio1.update(formInicio1.getGraphics());
        //formListaEspera1.update(formListaEspera1.getGraphics());
        //formInvite1.update(formInvite1.getGraphics());
    }

    public FormInicio getfInicio() {
        return fInicio;
    }

    public void setfInicio(FormInicio fInicio) {
        this.fInicio = fInicio;
    }

    public FormInvite getfInvite() {
        return fInvite;
    }

    public void setfInvite(FormInvite fInvite) {
        this.fInvite = fInvite;
    }

    public FormListaEspera getfListaEspera() {
        return fListaEspera;
    }

    public void setfListaEspera(FormListaEspera fListaEspera) {
        this.fListaEspera = fListaEspera;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(BotaoFechar());
        setMaximumSize(new java.awt.Dimension(600, 500));
        setMinimumSize(new java.awt.Dimension(600, 500));
        setPreferredSize(new java.awt.Dimension(600, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 231, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
