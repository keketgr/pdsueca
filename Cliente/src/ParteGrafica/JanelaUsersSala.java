/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ParteGrafica;

import FormsPrincipais.FormChatRoom;
import cliente.Comunicacao;
import javax.swing.JInternalFrame;

/**
 *
 * @author Rafael
 */
public class JanelaUsersSala extends JInternalFrame {

    /**
     * Creates new form JanelaUsersSala
     */
    String[] Userssala;
    PopUpMenu Pmenu;
    Comunicacao comunica;
    FormChatRoom fChatRoom;

    public JanelaUsersSala(Comunicacao comunica, FormChatRoom fChatRoom) {
        super("Utilizadores na sala", false, false, false, true);
        this.comunica = comunica;
        this.fChatRoom = fChatRoom;
        this.setSize(232, 71);
        setLocation(20, 60);
        initComponents();
        Userssala = new String[0];
        Pmenu = new PopUpMenu(this);

    }

    public String[] getUserssala() {
        return Userssala;
    }

    public void PrivateChat(String user) {
        fChatRoom.addChatPrivado(user);
    }
    public void Invite(String user){
        comunica.Invite(user);
        fChatRoom.invite(user);
        //System.out.println("Invite player "+user);
    }

    public void setUserssala(String[] Userssala) {
        this.Userssala = Userssala;
        update();
    }

    public void update() {
        ListUsersSala.removeAll();
        ListUsersSala.setListData(Userssala);
        ListUsersSala.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ListUsersSala = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        ListUsersSala.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        ListUsersSala.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListUsersSalaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ListUsersSalaMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ListUsersSala, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ListUsersSala, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
public String getSelectedUser(){
    return ListUsersSala.getSelectedValue().toString();
}
    private void ListUsersSalaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListUsersSalaMouseClicked
        if (evt.getButton() == evt.BUTTON3) {//evento do mause 2
            int index = ListUsersSala.locationToIndex(evt.getPoint());
            ListUsersSala.setSelectedIndex(index);
            Pmenu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_ListUsersSalaMouseClicked

    private void ListUsersSalaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListUsersSalaMousePressed
        if (evt.getButton() == evt.BUTTON3) {
            //Pmenu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_ListUsersSalaMousePressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList ListUsersSala;
    // End of variables declaration//GEN-END:variables
}
