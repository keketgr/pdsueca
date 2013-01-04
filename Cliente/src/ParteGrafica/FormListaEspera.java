/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ParteGrafica;

import java.awt.Dimension;
import java.util.ArrayList;

/**
 *
 * @author Rafael
 */
public class FormListaEspera extends javax.swing.JPanel {

    /**
     * Creates new form FormListaEspera
     */
    JanelaNovoJogo popup;
    ArrayList<String> Equipa1, Equipa2;

    public FormListaEspera(JanelaNovoJogo popup) {
        this.popup = popup;
        Equipa1 = new ArrayList<>();
        Equipa2 = new ArrayList<>();
        Dimension size = new Dimension(325, 200);
        setSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
        initComponents();
        btnStart.setVisible(false);
    }

    public void updateJogadores(ArrayList<String> Equipa1, ArrayList<String> Equipa2) {
        this.Equipa1 = Equipa1;
        this.Equipa2 = Equipa2;
        LJoga1.setText("Esperar Jogador");
        LJoga2.setText("Esperar Jogador");
        LJoga3.setText("Esperar Jogador");
        LJoga4.setText("Esperar Jogador");
        switch (Equipa1.size()) {//preenche os jojadores equipa1
            case 2:
                LJoga3.setText(Equipa1.get(1));//equipa 1
            case 1:
                LJoga1.setText(Equipa1.get(0));//equipa 1
        }
        switch (Equipa2.size()) {//preenche os jojadores equipa2
            case 2:
                LJoga4.setText(Equipa2.get(1));//equipa 2            
            case 1:
                LJoga2.setText(Equipa2.get(0));//equipa 2
        }
    }

    @Override
    public void setVisible(boolean aFlag) {
        if (popup.getChatRoom().getGrafico().getComunicacao().getUser().compareTo(popup.getChatRoom().getGrafico().getComunicacao().getIdJogo()) == 0) {
            btnStart.setVisible(true);
        } else {
            btnStart.setVisible(false);
        }
        super.setVisible(aFlag);
    }

    public JanelaNovoJogo getPopup() {
        return popup;
    }

    public void setPopup(JanelaNovoJogo popup) {
        this.popup = popup;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        LJoga1 = new javax.swing.JLabel();
        LabTeam1 = new javax.swing.JLabel();
        LJoga3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        LJoga4 = new javax.swing.JLabel();
        LabTeam2 = new javax.swing.JLabel();
        LJoga2 = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(325, 183));
        setMinimumSize(new java.awt.Dimension(325, 183));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Lista de Jogadores");

        LJoga1.setText("Esperar Jogador");

        LabTeam1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LabTeam1.setText("Equipa 1");
        LabTeam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LabTeam1MouseClicked(evt);
            }
        });

        LJoga3.setText("Esperar Jogador");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LJoga3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LJoga1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LabTeam1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LabTeam1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LJoga1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LJoga3)
                .addContainerGap())
        );

        LJoga4.setText("Esperar Jogador");

        LabTeam2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LabTeam2.setText("Equipa 2");
        LabTeam2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LabTeam2MouseClicked(evt);
            }
        });

        LJoga2.setText("Esperar Jogador");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabTeam2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LJoga2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LJoga4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 40, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LabTeam2)
                .addGap(4, 4, 4)
                .addComponent(LJoga2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LJoga4)
                .addContainerGap())
        );

        btnStart.setText("Começar");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        btnExit.setText("Sair");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnStart)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnExit)
                            .addComponent(btnStart)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        popup.getChatRoom().getGrafico().getComunicacao().ComeçarJogo();
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        popup.getChatRoom().getGrafico().getComunicacao().SairJogo();
    }//GEN-LAST:event_btnExitActionPerformed

    private void LabTeam1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabTeam1MouseClicked
        if (Equipa2.contains(popup.getChatRoom().getGrafico().getComunicacao().getUser())) {
            popup.getChatRoom().getGrafico().getComunicacao().ChangeTeam();
        }
    }//GEN-LAST:event_LabTeam1MouseClicked

    private void LabTeam2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabTeam2MouseClicked
        if (Equipa1.contains(popup.getChatRoom().getGrafico().getComunicacao().getUser())) {
            popup.getChatRoom().getGrafico().getComunicacao().ChangeTeam();
        }
    }//GEN-LAST:event_LabTeam2MouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LJoga1;
    private javax.swing.JLabel LJoga2;
    private javax.swing.JLabel LJoga3;
    private javax.swing.JLabel LJoga4;
    private javax.swing.JLabel LabTeam1;
    private javax.swing.JLabel LabTeam2;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
