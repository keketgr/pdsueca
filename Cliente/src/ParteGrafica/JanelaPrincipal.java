package ParteGrafica;

import FormsPrincipais.FormChatRoom;
import FormsPrincipais.FormLogin;
import FormsPrincipais.FormMesa;
import FormsPrincipais.JanelaAcerca;
import FormsPrincipais.JanelaOpcoes;
import cliente.Comunicacao;
import cliente.Opcoes;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Rafael
 */
public class JanelaPrincipal extends javax.swing.JFrame {

    private Comunicacao comunica;
    private FormChatRoom chatRoom;
    private FormLogin fLogin;
    private FormMesa fMesa;
    private JanelaAcerca acerca;
    private JanelaOpcoes jopcoes;
    private static Opcoes op = null;

    /**
     * Creates new form Chat
     */
    public JanelaPrincipal() {
        initComponents();
        if (op == null) {
            op = new Opcoes();
        }
        comunica = new Comunicacao(this);
        chatRoom = new FormChatRoom(this);
        fLogin = new FormLogin(this);
        fMesa = new FormMesa(this);
        acerca = new JanelaAcerca();
        setIconImage(Toolkit.getDefaultToolkit().getImage("sueca.gif"));
        jopcoes = new JanelaOpcoes(this);
        showfLogin();

    }

    public Opcoes getOp() {
        return op;
    }

    public Comunicacao getComunicacao() {
        return comunica;
    }

    public void setJComunicacao(Comunicacao comunica) {
        this.comunica = comunica;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuLogOut = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuSair = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuAcerca = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sueca na Rede");
        setBackground(new java.awt.Color(0, 0, 0));
        setMaximumSize(new java.awt.Dimension(2000, 2000));
        setMinimumSize(new java.awt.Dimension(557, 427));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jMenu1.setText("Ficheiro");

        jMenuLogOut.setText("LogOut");
        jMenuLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuLogOutActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuLogOut);

        jMenu4.setText("Novo");

        jMenuItem4.setText("Jogo");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);

        jMenu1.add(jMenu4);
        jMenu1.add(jSeparator1);

        jMenuSair.setText("Sair");
        jMenuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuSairActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuSair);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ferramentas");

        jMenuItem1.setText("Opções");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        jMenuAcerca.setText("Ajuda");

        jMenuItem2.setText("Acerca de...");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenuAcerca.add(jMenuItem2);

        jMenuBar1.add(jMenuAcerca);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 557, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public FormChatRoom getchatRoom() {
        return chatRoom;
    }

    public void setchatRoom(FormChatRoom formChatRoom1) {
        this.chatRoom = formChatRoom1;
    }

    public FormLogin getfLogin() {
        return fLogin;
    }

    public void setfLogin(FormLogin formLogin1) {
        this.fLogin = formLogin1;
    }

    public FormMesa getfMesa() {
        return fMesa;
    }

    public void setfMesa(FormMesa formMesa1) {
        this.fMesa = formMesa1;
    }

    public void showfLogin() {
        setContentPane(fLogin);
        this.setSize(fLogin.getSize());
        centrarJanela();
        if (op.isSaveUser()) {
            fLogin.setUser(op.getUser(), op.getPass());
            guardaOpcoes();
        } else {
            fLogin.setUser("", "");
        }
        fLogin.setVisible(true);
    }

    public void showfChatRoom() {
        setContentPane(chatRoom);
        this.setSize(chatRoom.getSize());
        centrarJanela();
        chatRoom.setLbusername();
        chatRoom.show();
    }

    private void centrarJanela() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension scrnsize = toolkit.getScreenSize();
        int largura = (int) (((int) scrnsize.getWidth() - this.getSize().getWidth()) / 2);
        int altura = (int) (((int) scrnsize.getHeight() - 50 - this.getSize().getHeight()) / 2);;
        //this.setSize(largura, altura);
        setLocation(largura, altura);
    }

    public void showfMesa() {
        setContentPane(fMesa);
        setSize(fMesa.getSize());
        centrarJanela();
        fMesa.setVisible(true);
    }

    public FormChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(FormChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public void UpdateSala() {
        chatRoom.Update();
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            comunica.sair();
        } catch (Exception e) {
        }

    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    }//GEN-LAST:event_formWindowClosed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        chatRoom.showfPopup();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        acerca.show();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuLogOutActionPerformed
        try {
            comunica.sair();
        } catch (Exception e) {
        }
        showfLogin();
    }//GEN-LAST:event_jMenuLogOutActionPerformed

    private void jMenuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuSairActionPerformed
        try {
            comunica.sair();
        } catch (Exception e) {
        }
        System.exit(0);
    }//GEN-LAST:event_jMenuSairActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        jopcoes.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                op = loadOpcoes();
                new JanelaPrincipal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenuAcerca;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuLogOut;
    private javax.swing.JMenuItem jMenuSair;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    // End of variables declaration//GEN-END:variables

    public void guardaOpcoes() {
        try {
            FileOutputStream fo = new FileOutputStream("FileOpcoes.sav");
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            oo.writeObject(op);
            oo.close();
            System.out.println("Opcoes gravadas com Sucesso.");
        } catch (IOException e) {
            System.out.println("Error - " + e.toString());
        }
    }

    public static Opcoes loadOpcoes() {
        Opcoes op1;
        try {
            ObjectInputStream restore = new ObjectInputStream(new FileInputStream("FileOpcoes.sav"));
            op1 = (Opcoes) restore.readObject();
            restore.close();
            return op1;
        } catch (FileNotFoundException ex) {
        } catch (Exception e) {
        }
        return new Opcoes();
    }
}
