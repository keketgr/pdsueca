/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package FormsPrincipais;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author pedro
 */
public class FormMesa extends javax.swing.JPanel {

    private JPanel jPesq, jPdir, jPcima, jPbaixo, jPtrunfo, jPCimaouBaixo;
    private JLabel LdarCartas;
    private JButton btnCima, btnBaixo;
    private JanelaPrincipal grafico;
    private int indiceCarta;
    private static ImagePanel mao, ImgDir, ImgCima, ImgBaixo, ImgEsq, ImgTrunfo;
    private int m_interval;  // Milliseconds between updates.
    private static Timer m_timerCima, m_timerBaixo, m_timerEsq, m_timerDir;// Timer fires to anmimate one step
    private boolean vezJogar;
    private int LowplayCard;
    private int HighplayCard;
    private ArrayList<Cartas> CartasMao;
    private Cartas trunfo;
    private int posicaoNoJogo;
    private ArrayList<String> usersinGame;
    private Cartas PrimeiraCarta;
    private StyledDocument doc;
    private Style style;

    public FormMesa(JanelaPrincipal grafico) {
        this.grafico = grafico;
        initComponents();
        initComponents2();

        style = TextP_lgJogo.addStyle("I'm a Style", null);
        StyleConstants.setForeground(style, Color.black);
        doc = TextP_lgJogo.getStyledDocument();

        Dimension size = new Dimension(1000, 750);
        setSize(size);
        indiceCarta = -1;
        m_interval = 5;
        CartasMao = new ArrayList<>();
        trunfo = null;
        usersinGame = new ArrayList<>();
        PrimeiraCarta = null;
        vezJogar = false;
        LowplayCard = 0;
        HighplayCard = 10;
    }

    public Pontuacao getPontuacao1() {
        return pontuacao1;
    }

    public JanelaPrincipal getGrafico() {
        return grafico;
    }

    public void setGrafico(JanelaPrincipal grafico) {
        this.grafico = grafico;
    }

//    private void AlignComponents() {
//
//        jPesq.setBounds(0, (450 + jPesq.getHeight()) / 2, jPesq.getWidth(), jPesq.getHeight());
//        //jPesq.setBounds(0, (jPMesa.getSize().height + jPesq.getHeight()) / 2, jPesq.getWidth(), jPesq.getHeight());
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton10 = new javax.swing.JToggleButton();
        JPborda = new javax.swing.JPanel();
        jPmao = new javax.swing.JPanel();
        jPMesa = new javax.swing.JPanel();
        lbbaixo = new javax.swing.JLabel();
        lbesq = new javax.swing.JLabel();
        lbcima = new javax.swing.JLabel();
        lbdir = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TextP_lgJogo = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        pontuacao1 = new Pontuacao();

        jToggleButton1.setText("jToggleButton1");

        jToggleButton10.setText("jToggleButton2");

        setName(""); // NOI18N

        JPborda.setBackground(new java.awt.Color(51, 51, 0));

        jPmao.setBackground(new java.awt.Color(0, 153, 0));
        jPmao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPmao.setPreferredSize(new java.awt.Dimension(752, 116));
        jPmao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPmaoMouseClicked(evt);
            }
        });
        jPmao.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPmaoMouseMoved(evt);
            }
        });

        javax.swing.GroupLayout jPmaoLayout = new javax.swing.GroupLayout(jPmao);
        jPmao.setLayout(jPmaoLayout);
        jPmaoLayout.setHorizontalGroup(
            jPmaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        jPmaoLayout.setVerticalGroup(
            jPmaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 114, Short.MAX_VALUE)
        );

        jPMesa.setBackground(new java.awt.Color(0, 153, 0));
        jPMesa.setToolTipText("");
        jPMesa.setMaximumSize(new java.awt.Dimension(750, 450));
        jPMesa.setMinimumSize(new java.awt.Dimension(750, 450));
        jPMesa.setPreferredSize(new java.awt.Dimension(750, 450));

        javax.swing.GroupLayout jPMesaLayout = new javax.swing.GroupLayout(jPMesa);
        jPMesa.setLayout(jPMesaLayout);
        jPMesaLayout.setHorizontalGroup(
            jPMesaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 752, Short.MAX_VALUE)
        );
        jPMesaLayout.setVerticalGroup(
            jPMesaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lbbaixo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbbaixo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbbaixo.setText("Baixo");
        lbbaixo.setName("lbbaixo"); // NOI18N

        lbesq.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbesq.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbesq.setText("Esquerda");
        lbesq.setName("lbesq"); // NOI18N

        lbcima.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbcima.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbcima.setText("Cima");
        lbcima.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbcima.setName("lbcima"); // NOI18N

        lbdir.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbdir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbdir.setText("Direita");
        lbdir.setName("lbadv1"); // NOI18N

        javax.swing.GroupLayout JPbordaLayout = new javax.swing.GroupLayout(JPborda);
        JPborda.setLayout(JPbordaLayout);
        JPbordaLayout.setHorizontalGroup(
            JPbordaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPbordaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(JPbordaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPmao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JPbordaLayout.createSequentialGroup()
                        .addComponent(lbesq, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JPbordaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbbaixo, javax.swing.GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
                            .addComponent(lbcima, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPMesa, javax.swing.GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbdir, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        JPbordaLayout.setVerticalGroup(
            JPbordaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPbordaLayout.createSequentialGroup()
                .addComponent(lbcima, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPbordaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbesq, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbdir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPMesa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbbaixo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPmao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jPMesa.getAccessibleContext().setAccessibleName("");
        lbesq.setUI(new VerticalLabelUI(true));
        lbesq.setUI(new VerticalLabelUI());
        lbdir.setUI(new VerticalLabelUI());

        TextP_lgJogo.setFocusCycleRoot(false);
        TextP_lgJogo.setFocusable(false);
        jScrollPane2.setViewportView(TextP_lgJogo);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Log Jogo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pontuacao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pontuacao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPborda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(JPborda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 190, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jPmaoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPmaoMouseMoved
        if (CartasMao.isEmpty()) {
            return;
        }
        if ((evt.getX() / 75) >= LowplayCard && (evt.getX() / 75) <= HighplayCard) {
            if (indiceCarta != evt.getX() / 75) {//previne refrescasr as imagens constantemente
                indiceCarta = evt.getX() / 75;
                updatecards();
            }
        }
    }//GEN-LAST:event_jPmaoMouseMoved

    public int getIndiceCarta() {
        return indiceCarta;
    }

    public void setIndiceCarta(int indiceCarta) {
        this.indiceCarta = indiceCarta;
    }

    private void jPmaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPmaoMouseClicked
        if ((evt.getX() / 75) >= LowplayCard && (evt.getX() / 75) <= HighplayCard && vezJogar) {

            Cartas carta = CartasMao.get((evt.getX() / 75));
            if (vezJogar) {
                indiceCarta = evt.getX() / 75;
                AnimacaoBaixo(carta);
                grafico.getComunicacao().enviaCarta(carta);
                vezJogar = false;
                CartasMao.remove(carta);
                DrawCards();
                LowplayCard = 0;
                HighplayCard = CartasMao.size() - 1;
            } else {
                indiceCarta = -1;
            }
        }
    }//GEN-LAST:event_jPmaoMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPborda;
    private javax.swing.JTextPane TextP_lgJogo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPMesa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPmao;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton10;
    private javax.swing.JLabel lbbaixo;
    private javax.swing.JLabel lbcima;
    private javax.swing.JLabel lbdir;
    private javax.swing.JLabel lbesq;
    private Pontuacao pontuacao1;
    // End of variables declaration//GEN-END:variables

    public void colocaJogadores() {
        usersinGame = grafico.getComunicacao().getUsersNoJogo();
        System.out.println("coloca jogadores");
        if (usersinGame == null) {
            return;
        }
        for (int i = 0; i < usersinGame.size(); i++) {
            if (usersinGame.get(i).compareTo(grafico.getComunicacao().getUser()) == 0) {
                posicaoNoJogo = i;
                break;
            }
        }
        switch (posicaoNoJogo) {
            case 0:
                lbbaixo.setText(usersinGame.get(0));
                lbdir.setText(usersinGame.get(1));
                lbcima.setText(usersinGame.get(2));
                lbesq.setText(usersinGame.get(3));
                break;
            case 1:
                lbbaixo.setText(usersinGame.get(1));
                lbdir.setText(usersinGame.get(2));
                lbcima.setText(usersinGame.get(3));
                lbesq.setText(usersinGame.get(0));
                break;
            case 2:
                lbbaixo.setText(usersinGame.get(2));
                lbdir.setText(usersinGame.get(3));
                lbcima.setText(usersinGame.get(0));
                lbesq.setText(usersinGame.get(1));
                break;
            case 3:
                lbbaixo.setText(usersinGame.get(3));
                lbdir.setText(usersinGame.get(0));
                lbcima.setText(usersinGame.get(1));
                lbesq.setText(usersinGame.get(2));
                break;
        }
    }

    public void updateJogo() {
        DrawCards();
    }

    public void recolheVaza() {
        initPanels();
        this.repaint();
        PrimeiraCarta = null;
    }

    public void mostraCimaBaixo() {
        jPCimaouBaixo.setVisible(true);
    }

    private void initPanels() {
        Color c = new Color(0, 153, 0);
        jPesq = new JPanel();
        jPdir = new JPanel();
        jPcima = new JPanel();
        jPbaixo = new JPanel();

        // lbadv1.setUI(new VerticalLabelUI());

        Dimension size;
        size = new Dimension((750 - 75) / 2, 75);
        //JPesq
        jPesq.setPreferredSize(size);
        jPesq.setMinimumSize(size);
        jPesq.setMaximumSize(size);
        jPesq.setBackground(c);
        jPesq.setSize(size);
        jPesq.setLayout(null);
        jPesq.setBounds(0, (450 - jPesq.getHeight()) / 2, jPesq.getWidth(), jPesq.getHeight());

        //jPdir
        jPdir.setPreferredSize(size);
        jPdir.setMinimumSize(size);
        jPdir.setMaximumSize(size);
        jPdir.setBackground(c);
        jPdir.setSize(size);
        jPdir.setLayout(null);
        jPdir.setBounds(jPesq.getWidth() + 75, (450 - jPdir.getHeight()) / 2, jPdir.getWidth(), jPdir.getHeight());

        size = new Dimension(75, (450 - 75) / 2);

        //jPcima
        jPcima.setPreferredSize(size);
        jPcima.setMinimumSize(size);
        jPcima.setMaximumSize(size);
        jPcima.setBackground(c);
        jPcima.setSize(size);
        jPcima.setLayout(null);
        jPcima.setBounds((750 - jPcima.getWidth()) / 2, 0, jPcima.getWidth(), jPcima.getHeight());

        //jPbaixo
        jPbaixo.setPreferredSize(size);
        jPbaixo.setMinimumSize(size);
        jPbaixo.setMaximumSize(size);
        jPbaixo.setBackground(c);
        jPbaixo.setSize(size);
        jPbaixo.setLayout(null);
        jPbaixo.setBounds((750 - jPbaixo.getWidth()) / 2, jPcima.getHeight() + 75, jPbaixo.getWidth(), jPbaixo.getHeight());


        jPMesa.removeAll();
        jPMesa.add(jPesq);
        jPMesa.add(jPdir);
        jPMesa.add(jPcima);
        jPMesa.add(jPbaixo);
        jPMesa.add(jPtrunfo);
        jPMesa.add(jPCimaouBaixo);
        jPCimaouBaixo.add(LdarCartas);
        jPCimaouBaixo.add(btnCima);
        jPCimaouBaixo.add(btnBaixo);
        jPCimaouBaixo.repaint();
    }

    private void initComponents2() {
        Color c = new Color(0, 153, 0);
        jPesq = new JPanel();
        jPdir = new JPanel();
        jPcima = new JPanel();
        jPbaixo = new JPanel();
        //jPcentro = new JPanel();
        jPtrunfo = new JPanel();
        jPCimaouBaixo = new JPanel();


        // lbadv1.setUI(new VerticalLabelUI());

        Dimension size;
        size = new Dimension((750 - 75) / 2, 75);
        //JPesq
        jPesq.setPreferredSize(size);
        jPesq.setMinimumSize(size);
        jPesq.setMaximumSize(size);
        jPesq.setBackground(c);
        jPesq.setSize(size);
        jPesq.setLayout(null);
        jPesq.setBounds(0, (450 - jPesq.getHeight()) / 2, jPesq.getWidth(), jPesq.getHeight());

        //jPdir
        jPdir.setPreferredSize(size);
        jPdir.setMinimumSize(size);
        jPdir.setMaximumSize(size);
        jPdir.setBackground(c);
        jPdir.setSize(size);
        jPdir.setLayout(null);
        jPdir.setBounds(jPesq.getWidth() + 75, (450 - jPdir.getHeight()) / 2, jPdir.getWidth(), jPdir.getHeight());

        size = new Dimension(75, (450 - 75) / 2);

        //jPcima
        jPcima.setPreferredSize(size);
        jPcima.setMinimumSize(size);
        jPcima.setMaximumSize(size);
        jPcima.setBackground(c);
        jPcima.setSize(size);
        jPcima.setLayout(null);
        jPcima.setBounds((750 - jPcima.getWidth()) / 2, 0, jPcima.getWidth(), jPcima.getHeight());

        //jPbaixo
        jPbaixo.setPreferredSize(size);
        jPbaixo.setMinimumSize(size);
        jPbaixo.setMaximumSize(size);
        jPbaixo.setBackground(c);
        jPbaixo.setSize(size);
        jPbaixo.setLayout(null);
        jPbaixo.setBounds((750 - jPbaixo.getWidth()) / 2, jPcima.getHeight() + 75, jPbaixo.getWidth(), jPbaixo.getHeight());

        size = new Dimension(75, 128);

        //jPtrunfo
        jPtrunfo.setPreferredSize(size);
        jPtrunfo.setMinimumSize(size);
        jPtrunfo.setMaximumSize(size);
        jPtrunfo.setBackground(c);
        jPtrunfo.setSize(size);
        jPtrunfo.setLayout(null);
        jPtrunfo.setBounds((750 - jPtrunfo.getWidth()), 0, jPtrunfo.getWidth(), jPtrunfo.getHeight());

        size = new Dimension(128, 75);

        //jPCimaouBaixo
        jPCimaouBaixo.setPreferredSize(size);
        jPCimaouBaixo.setMinimumSize(size);
        jPCimaouBaixo.setMaximumSize(size);
        //jPCimaouBaixo.setBackground(Color.gray);
        jPCimaouBaixo.setBackground(c);
        jPCimaouBaixo.setVisible(false);
        jPCimaouBaixo.setSize(size);
        //jPCimaouBaixo.setLayout(null);
        jPCimaouBaixo.setBounds((750 - jPCimaouBaixo.getWidth()), (450 - jPCimaouBaixo.getHeight()), jPCimaouBaixo.getWidth(), jPCimaouBaixo.getHeight());
        jPCimaouBaixo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        //BotaoCima
        btnCima = new JButton();
        btnCima.setText("Cima");
        btnCima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCimaONClick(evt);
            }
        });
        btnCima.setBounds(0, jPCimaouBaixo.getHeight() / 2, jPCimaouBaixo.getWidth() / 2, jPCimaouBaixo.getHeight() / 2);

        //Botao baixo
        btnBaixo = new JButton();
        btnBaixo.setText("Baixo");
        btnBaixo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaixoONClick(evt);
            }
        });
        btnBaixo.setBounds(jPCimaouBaixo.getWidth() / 2, jPCimaouBaixo.getHeight() / 2, jPCimaouBaixo.getWidth() / 2, jPCimaouBaixo.getHeight() / 2);


        //LdarCartas
        LdarCartas = new JLabel();
        LdarCartas.setBackground(new java.awt.Color(0, 153, 0));
        LdarCartas.setFont(new java.awt.Font("Tahoma", 0, 18));
        LdarCartas.setText("Dar Cartas");
        LdarCartas.setName("DarCartas");

        jPMesa.removeAll();
        jPMesa.add(jPesq);
        jPMesa.add(jPdir);
        jPMesa.add(jPcima);
        jPMesa.add(jPbaixo);
        jPMesa.add(jPtrunfo);
        jPMesa.add(jPCimaouBaixo);
        jPCimaouBaixo.add(LdarCartas);
        jPCimaouBaixo.add(btnCima);
        jPCimaouBaixo.add(btnBaixo);
        jPCimaouBaixo.repaint();


    }

    private void btnCimaONClick(java.awt.event.ActionEvent evt) {
        grafico.getComunicacao().CimaBaixo(1);
        jPCimaouBaixo.setVisible(false);
    }

    private void btnBaixoONClick(java.awt.event.ActionEvent evt) {
        grafico.getComunicacao().CimaBaixo(2);
        jPCimaouBaixo.setVisible(false);
    }
//Metodos de desenho

    public JPanel getjPmao() {
        return jPmao;
    }

    public JLabel getLbbaixo() {
        return lbbaixo;
    }

    public JLabel getLbcima() {
        return lbcima;
    }

    public JLabel getLbdir() {
        return lbdir;
    }

    public JLabel getLbesq() {
        return lbesq;
    }

    public void setjPmao(JPanel jPmao) {
        this.jPmao = jPmao;
    }

    public void DrawCards() {

//        if (CartasMao.isEmpty()) {
//            return;
//        }
        Getlink gl = new Getlink();
        BufferedImage[] imagensmao;
        imagensmao = new BufferedImage[CartasMao.size()];
        for (int i = 0; i < CartasMao.size(); i++) {
            imagensmao[i] = gl.getcarta(CartasMao.get(i));
        }
        mao = new ImagePanel(imagensmao, 0, 0, 0, CartasMao.size() * 75, 130, 75);
        jPmao.removeAll();
        jPmao.add(mao);
    }

    public void updatecards() {
        mao.setIndiceCarta(indiceCarta);
        mao.repaint();
    }

    public static void stoptimer(char c) {
        switch (c) {
            case 'c':
                m_timerCima.stop();
                break;
            case 'b':
                m_timerBaixo.stop();
                break;
            case 'e':
                m_timerEsq.stop();
                break;
            case 'd':
                m_timerDir.stop();
                break;
        }
    }

    public void Drawtrunfo(Cartas c) {
        Getlink gl = new Getlink();
        int ang = 0;
        int cordXinicial = 0;
        int CordYinicial = 0;
        int largurapainel = jPtrunfo.getWidth();
        int alturapainel = jPtrunfo.getHeight();
        ImgTrunfo = new ImagePanel(gl.getcarta(c), ang, cordXinicial, CordYinicial, largurapainel, alturapainel);
        jPtrunfo.add(ImgTrunfo);
        jPtrunfo.repaint();
    }

    public void AnimacaoDireita(Cartas c) {
        Getlink gl = new Getlink();
        int ang = 90;
        int cordXinicial = jPdir.getWidth();
        int CordYinicial = 0;
        int largurapainel = jPdir.getWidth();
        int alturapainel = jPdir.getHeight();
        ImgDir = new ImagePanel(gl.getcarta(c), ang, cordXinicial, CordYinicial, largurapainel, alturapainel);
        jPdir.add(ImgDir);
        jPdir.repaint();
        m_timerDir = new Timer(m_interval, new TimerAnimation(1, jPdir.getWidth()));
        m_timerDir.start();//inicia animação
    }

    public void AnimacaoCima(Cartas c) {
        Getlink gl = new Getlink();
        BufferedImage imgcarta = gl.getcarta(c);
        int ang = 0;
        int cordXinicial = 0;
        int CordYinicial = -imgcarta.getHeight();
        int largurapainel = jPcima.getWidth();
        int alturapainel = jPcima.getHeight();
        ImgCima = new ImagePanel(imgcarta, ang, cordXinicial, CordYinicial, largurapainel, alturapainel);
        jPcima.add(ImgCima);
        jPcima.repaint();
        m_timerCima = new Timer(m_interval, new TimerAnimation(2, jPcima.getHeight() - imgcarta.getHeight()));
        m_timerCima.start();//inicia animação
    }

    public void AnimacaoEsquerda(Cartas c) {
        Getlink gl = new Getlink();
        BufferedImage imgcarta = gl.getcarta(c);
        int ang = 90;
        int cordXinicial = -imgcarta.getHeight();
        int CordYinicial = 0;
        int largurapainel = jPesq.getWidth();
        int alturapainel = jPesq.getHeight();
        ImgEsq = new ImagePanel(imgcarta, ang, cordXinicial, CordYinicial, largurapainel, alturapainel);
        jPesq.add(ImgEsq);
        jPesq.repaint();

        m_timerEsq = new Timer(m_interval, new TimerAnimation(3, jPesq.getWidth() - imgcarta.getHeight()));
        m_timerEsq.start();//inicia animação
    }

    public void putLog(String log) {
        //StyleConstants.setForeground(style, Color.red);
        try {
            doc.insertString(doc.getLength(), log, style);
        } catch (BadLocationException ex) {
            Logger.getLogger(JanelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        //StyleConstants.setForeground(style, Color.black);
    }

    public void AnimacaoBaixo(Cartas c) {
        Getlink gl = new Getlink();
        int ang = 0;
        int cordXinicial = 0;
        int CordYinicial = jPbaixo.getHeight();
        int largurapainel = jPbaixo.getWidth();
        int alturapainel = jPbaixo.getHeight();
        ImgBaixo = new ImagePanel(gl.getcarta(c), ang, cordXinicial, CordYinicial, largurapainel, alturapainel);
        jPbaixo.add(ImgBaixo);
        jPbaixo.repaint();
        m_timerBaixo = new Timer(m_interval, new TimerAnimation(4, jPbaixo.getHeight()));
        m_timerBaixo.start();//inicia animação
    }
//eliminar

    public void iniciaCartas(ArrayList cartas, Cartas trunfo) {
        CartasMao = cartas;
        this.trunfo = trunfo;
        PrimeiraCarta = null;
        DrawCards();
        Drawtrunfo(trunfo);
        this.repaint();
        LowplayCard = 0;
        HighplayCard = 10;
    }

    public void vezJogar(String utilizador) {
        lbcima.setForeground(Color.black);
        lbbaixo.setForeground(Color.black);
        lbesq.setForeground(Color.black);
        lbdir.setForeground(Color.black);
        jPmao.setEnabled(false);
        vezJogar = false;
        int aux = 0;
        if (grafico.getComunicacao().getUser().compareTo(utilizador) == 0) {
            lbbaixo.setForeground(Color.blue);
            jPmao.setEnabled(true);
            vezJogar = true;
            if (PrimeiraCarta != null) {
                setHighandLow();
            }
            return;
        }
        for (int i = 0; i < usersinGame.size(); i++) {
            if (usersinGame.get(i).compareTo(utilizador) == 0) {
                aux = i;
                break;
            }
        }
        aux = aux - posicaoNoJogo;
        if (aux < 0) {
            aux = aux + 4;
        }
        switch (aux) {
            case 0:
                lbbaixo.setForeground(Color.blue);
                break;
            case 1:
                lbdir.setForeground(Color.blue);
                break;
            case 2:
                lbcima.setForeground(Color.blue);
                break;
            case 3:
                lbesq.setForeground(Color.blue);
                break;
        }
    }

    public void CartaJogada(String utilizador, Cartas c) {
        int aux = 0;
        if (PrimeiraCarta == null) {
            PrimeiraCarta = c;
        }
        for (int i = 0; i < usersinGame.size(); i++) {
            if (usersinGame.get(i).compareTo(utilizador) == 0) {
                aux = i;
                break;
            }
        }
        aux = aux - posicaoNoJogo;
        if (aux < 0) {
            aux = aux + 4;
        }
        switch (aux) {
            case 0:
                //AnimacaoBaixo(c);
                break;
            case 1:
                AnimacaoDireita(c);
                break;
            case 2:
                AnimacaoCima(c);
                break;
            case 3:
                AnimacaoEsquerda(c);
                break;
        }
    }

    private void setHighandLow() {
        LowplayCard = 0;
        HighplayCard = CartasMao.size() - 1;
        for (int i = 0; i < CartasMao.size(); i++) {
            if (CartasMao.get(i).getNaipe() == PrimeiraCarta.getNaipe()) {
                LowplayCard = i;
                break;
            }
        }
        for (int i = CartasMao.size() - 1; i >= 0; i--) {
            if (CartasMao.get(i).getNaipe() == PrimeiraCarta.getNaipe()) {
                HighplayCard = i;
                break;
            }
        }

    }

    private static class TimerAnimation implements ActionListener {

        int flag;
        int medida;

        public TimerAnimation() {
        }

        public TimerAnimation(int flag, int medida) {
            this.flag = flag;
            this.medida = medida;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (flag) {
                case 1://animação da direita
                    ImgDir.setCordx(medida);
                    medida -= 1;
                    if (medida < 0) {
                        medida = 0;
                        stoptimer('d');
                    }
                    ImgDir.repaint();
                    break;
                case 2://cima
                    ImgCima.setCordy(ImgCima.getCordy() + 1);
                    if (medida <= ImgCima.getCordy()) {
                        stoptimer('c');
                    }
                    ImgCima.repaint();
                    break;
                case 3://esq
                    ImgEsq.setCordx(ImgEsq.getCordx() + 1);

                    if (ImgEsq.getCordx() >= medida) {
                        stoptimer('e');
                    }
                    ImgEsq.repaint();
                    break;
                case 4://baixo
                    ImgBaixo.setCordy(medida);
                    medida -= 1;
                    if (medida < 0) {
                        medida = 0;
                        stoptimer('b');
                    }
                    ImgBaixo.repaint();
                    break;
            }
        }
    }
}
