/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package ParteGrafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Rafael
 */
public class Notificacao extends JInternalFrame {

    String jogador;
    FormChatRoom fChatRoom;

    public Notificacao(String jogador, FormChatRoom fChatRoom) {
        super("Convite de " + jogador, false, true, false, true);
        this.jogador = jogador;
        this.setSize(232, 71);
        this.fChatRoom = fChatRoom;
        setLocation(800, 400);
        initComponents();
    }

    public String getJogador() {
        return jogador;
    }

    private void initComponents() {
        JPanel content = new JPanel();
        content.setBackground(new java.awt.Color(153, 153, 153));
        content.setLayout(new BorderLayout());
        Dimension size = new Dimension(232, 71);
        content.setPreferredSize(size);
        content.setMaximumSize(size);
        content.setMinimumSize(size);
        //this.pack();
        JPanel jPbutoes = new JPanel();
        jLmensagem = new JLabel();
        BtnAceitar = new JButton();
        Btnrecusar = new JButton();

        jLmensagem.setFont(new java.awt.Font("Tahoma", 0, 10));
        jLmensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLmensagem.setText("Jogador " + jogador + " convidou-te para um jogo");

        BtnAceitar.setText("Aceitar");
        BtnAceitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAceitarOnClick(evt);
            }
        });

        Btnrecusar.setText("Recusar");
        Btnrecusar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnrecusarOnClick(evt);
            }
        });
        jPbutoes.setLayout(new GridLayout(1, 2));
        jPbutoes.add(BtnAceitar);
        jPbutoes.add(Btnrecusar);
        jPbutoes.setOpaque(false);

        content.add(jLmensagem, BorderLayout.NORTH);
        content.add(jPbutoes, BorderLayout.SOUTH);
        this.add(content);

    }

    private void BtnrecusarOnClick(java.awt.event.ActionEvent evt) {
        fChatRoom.RecusaInvite(jogador);
    }

    private void BtnAceitarOnClick(java.awt.event.ActionEvent evt) {
        fChatRoom.AceitaInvite(jogador);
    }
    JLabel jLmensagem;
    JButton BtnAceitar, Btnrecusar;
}
