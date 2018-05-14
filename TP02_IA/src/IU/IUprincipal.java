/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IU;

import codigos.Resolve;
import codigos.Tabuleiro;
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Gustavo
 */
public class IUprincipal extends javax.swing.JFrame {

    private ViewPanel view;
    private Resolve resolve;
    private boolean inicializado; // Informa se o usuário embaralhou o tabuleiro
    private boolean embaralhado;
    private boolean exibe;
    private int larguras;
    private int alturas;
    private Timer timer;

    /**
     * Creates new form IUprincipal
     */
    public IUprincipal() {
        this.view = new ViewPanel();
        this.inicializado = false;
        this.embaralhado = false;
        this.exibe = false;
        initComponents();
        this.setTitle("Projeto IA - 2018");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel = new javax.swing.JScrollPane(this.view);
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        NomeAlgLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        NumItLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        NumJogLabel = new javax.swing.JLabel();
        Menu = new javax.swing.JMenuBar();
        Tab_menu = new javax.swing.JMenu();
        Iniciar_tab_menu = new javax.swing.JMenuItem();
        Embaralhar_menu = new javax.swing.JMenuItem();
        Salva_tab_menu = new javax.swing.JMenuItem();
        Recupera_tab_menu = new javax.swing.JMenuItem();
        Resolver_menu = new javax.swing.JMenu();
        Cega_menu = new javax.swing.JMenuItem();
        Heuristica1_menu = new javax.swing.JMenuItem();
        Heuristica2_menu = new javax.swing.JMenuItem();
        HPessoal_menu = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        Exibe_Menu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 650));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PanelMousePressed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Ultimo Algoritmo Executado:");

        NomeAlgLabel.setText("---");

        jLabel2.setText("Número de iterações:");

        NumItLabel.setText("---");

        jLabel3.setText("Número de jogadas:");

        NumJogLabel.setText("---");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NomeAlgLabel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NumItLabel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NumJogLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(NomeAlgLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(NumJogLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(NumItLabel))
                .addContainerGap())
        );

        Tab_menu.setText("Tabuleiro");

        Iniciar_tab_menu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        Iniciar_tab_menu.setText("Iniciar Tabuleiro");
        Iniciar_tab_menu.setToolTipText("Cria um tabuleiro na dimensão informada");
        Iniciar_tab_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Iniciar_tab_menuActionPerformed(evt);
            }
        });
        Tab_menu.add(Iniciar_tab_menu);

        Embaralhar_menu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        Embaralhar_menu.setText("Embaralhar");
        Embaralhar_menu.setToolTipText("Realiza movimentos aleatórios");
        Embaralhar_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Embaralhar_menuActionPerformed(evt);
            }
        });
        Tab_menu.add(Embaralhar_menu);

        Salva_tab_menu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        Salva_tab_menu.setText("Salvar Tabuleiro");
        Salva_tab_menu.setToolTipText("Armazena o estado atual do tabuleiro");
        Salva_tab_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Salva_tab_menuActionPerformed(evt);
            }
        });
        Tab_menu.add(Salva_tab_menu);

        Recupera_tab_menu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        Recupera_tab_menu.setText("Recuperar Tabuleiro");
        Recupera_tab_menu.setToolTipText("Recupera estado do tabuleiro armazenado");
        Recupera_tab_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Recupera_tab_menuActionPerformed(evt);
            }
        });
        Tab_menu.add(Recupera_tab_menu);

        Menu.add(Tab_menu);

        Resolver_menu.setText("Resolver");

        Cega_menu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_MASK));
        Cega_menu.setText("Busca Cega");
        Cega_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cega_menuActionPerformed(evt);
            }
        });
        Resolver_menu.add(Cega_menu);

        Heuristica1_menu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_MASK));
        Heuristica1_menu.setText("Heurística 1");
        Heuristica1_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Heuristica1_menuActionPerformed(evt);
            }
        });
        Resolver_menu.add(Heuristica1_menu);

        Heuristica2_menu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_MASK));
        Heuristica2_menu.setText("Heurística 2");
        Heuristica2_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Heuristica2_menuActionPerformed(evt);
            }
        });
        Resolver_menu.add(Heuristica2_menu);

        HPessoal_menu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.CTRL_MASK));
        HPessoal_menu.setText("Heurística Pessoal");
        HPessoal_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HPessoal_menuActionPerformed(evt);
            }
        });
        Resolver_menu.add(HPessoal_menu);

        Menu.add(Resolver_menu);

        jMenu1.setText("Solução");

        Exibe_Menu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        Exibe_Menu.setText("Exibir solução");
        Exibe_Menu.setToolTipText("Mostra o passo a passo da solução de uma heurística");
        Exibe_Menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Exibe_MenuActionPerformed(evt);
            }
        });
        jMenu1.add(Exibe_Menu);

        Menu.add(jMenu1);

        setJMenuBar(Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Iniciar_tab_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Iniciar_tab_menuActionPerformed
        try {
            int dimensao = Integer.valueOf(JOptionPane.showInputDialog("Insira qual a dimensão do tabuleiro"));
            if (dimensao <= 1) {
                JOptionPane.showMessageDialog(this, "Dimensão deve ser MAIOR que 1!!");
                return;
            }
            this.resolve = new Resolve(dimensao, dimensao);
            this.view.setTab(this.resolve);
            this.inicializado = true;
            this.Panel.repaint();
        } catch (NumberFormatException e) {
        }
    }//GEN-LAST:event_Iniciar_tab_menuActionPerformed

    private void Embaralhar_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Embaralhar_menuActionPerformed
        if (this.inicializado) {
            try {
                int movimentos = Integer.valueOf(JOptionPane.showInputDialog("Insira o número de movimentos"));

                this.resolve.embaralha(movimentos);
                this.view.setTab(resolve);
                this.view.repaint();
                if (!this.resolve.verificaFim()) {
                    this.embaralhado = true;
                }
            } catch (NumberFormatException e) {
            }
        } else {
            JOptionPane.showMessageDialog(this, "Inicialize o tabuleiro!");
        }
    }//GEN-LAST:event_Embaralhar_menuActionPerformed

    private void Cega_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cega_menuActionPerformed
        if (this.inicializado) {
            this.resolve.buscaCega();
            this.NomeAlgLabel.setText("Busca Cega");
            this.NumItLabel.setText(String.valueOf(this.resolve.getNumJogadas()));
            this.NumJogLabel.setText(String.valueOf(this.resolve.getNumJogadas()));
            this.view.setTab(this.resolve);
            this.embaralhado = false;
            this.exibe = false;
            this.view.repaint();

        } else {
            JOptionPane.showMessageDialog(this, "Inicialize o tabuleiro!");
        }
    }//GEN-LAST:event_Cega_menuActionPerformed

    private void Heuristica1_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Heuristica1_menuActionPerformed
        if (this.inicializado) {
            this.resolve.buscaH1();
            this.NomeAlgLabel.setText("Heurística 1");
            this.NumItLabel.setText(String.valueOf(this.resolve.getNumJogadas()));
            this.NumJogLabel.setText(String.valueOf(this.resolve.getPassos()));
            this.view.setTab(this.resolve);
            this.embaralhado = false;
            this.exibe = true;
            this.resolve.setCam();
            this.view.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Inicialize o tabuleiro!");
        }
    }//GEN-LAST:event_Heuristica1_menuActionPerformed

    private void Heuristica2_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Heuristica2_menuActionPerformed
        if (this.inicializado) {
            this.resolve.buscaH2();
            this.NomeAlgLabel.setText("Heurística 2");
            this.NumItLabel.setText(String.valueOf(this.resolve.getNumJogadas()));
            this.NumJogLabel.setText(String.valueOf(this.resolve.getPassos()));
            this.view.setTab(this.resolve);
            this.embaralhado = false;
            this.exibe = true;
            this.resolve.setCam();
            this.view.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Inicialize o tabuleiro!");
        }
    }//GEN-LAST:event_Heuristica2_menuActionPerformed

    private void HPessoal_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HPessoal_menuActionPerformed
        if (this.inicializado) {
            this.resolve.buscaHPessoal();
            this.NomeAlgLabel.setText("Heurística Pessoal -  Número de peças no lugar certo");
            this.NumItLabel.setText(String.valueOf(this.resolve.getNumJogadas()));
            this.NumJogLabel.setText(String.valueOf(this.resolve.getPassos()));
            this.view.setTab(this.resolve);
            this.embaralhado = false;
            this.exibe = true;
            this.resolve.setCam();
            this.view.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Inicialize o tabuleiro!");
        }
    }//GEN-LAST:event_HPessoal_menuActionPerformed

    private void PanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelMousePressed
        if (!this.inicializado) {
            return;
        }
        Point p = this.Panel.getMousePosition();
        int i = p.y / alturas;
        int j = p.x / larguras;
        boolean fezJog = this.resolve.jogaClique(i, j);
        if (!this.embaralhado) {
            this.resolve.resetJogadas();
            this.embaralhado = true;
        }
        this.Panel.repaint();
        if (fezJog && this.resolve.verificaFim()) {
            JOptionPane.showMessageDialog(this, "Você resolveu em " + this.resolve.getNumJogadas() + " jogada(s)!");
            this.embaralhado = false;
        }
    }//GEN-LAST:event_PanelMousePressed

    private void Recupera_tab_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Recupera_tab_menuActionPerformed
        if (this.inicializado) {
            this.resolve.recuperaReserva();
            this.Panel.repaint();
        }
    }//GEN-LAST:event_Recupera_tab_menuActionPerformed

    private void Salva_tab_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Salva_tab_menuActionPerformed
        if (this.inicializado) {
            this.resolve.salvaReserva();
        }
    }//GEN-LAST:event_Salva_tab_menuActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (!this.inicializado) {
            return;
        }
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                this.resolve.fazJogada(3);
                break;
            case KeyEvent.VK_LEFT:
                this.resolve.fazJogada(2);
                break;
            case KeyEvent.VK_UP:
                this.resolve.fazJogada(1);
                break;
            case KeyEvent.VK_DOWN:
                this.resolve.fazJogada(0);
                break;
            default:
                return;
        }
        this.Panel.repaint();
        if (!this.embaralhado) {
            this.resolve.resetJogadas();
            this.embaralhado = true;
        } else if (this.resolve.verificaFim()) {
            JOptionPane.showMessageDialog(this, "Você resolveu em " + this.resolve.getNumJogadas() + " jogada(s)!");
            this.embaralhado = false;
        }
    }//GEN-LAST:event_formKeyPressed

    private void Exibe_MenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Exibe_MenuActionPerformed
        if (this.exibe) {
            this.pintaCam();
        }else{
            JOptionPane.showMessageDialog(this, "Execute uma busca com heurística para ver a solução!");
        }
    }//GEN-LAST:event_Exibe_MenuActionPerformed

    private synchronized void pintaCam() {

        Tabuleiro aux = this.resolve.getResult();
        TimerActionListener tal = new TimerActionListener();
        tal.setAux(aux);
        timer = new Timer(2000, tal);
        timer.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IUprincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IUprincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IUprincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IUprincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IUprincipal().setVisible(true);
            }
        });
    }

    public class ViewPanel extends JPanel {

        private Resolve tabuleiro;

        public void setTab(Resolve tab) {
            this.tabuleiro = tab;
        }

        @Override
        public void paintComponent(java.awt.Graphics g) {
            int alturaPanel = this.getHeight();
            int larguraPanel = this.getWidth();
            super.paintComponent(g);
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;
            if (this.tabuleiro == null) {
                BufferedImage imagem;
                try {
                    imagem = ImageIO.read(new File(System.getProperty("user.dir") + "\\src\\IU\\Img\\Icon1.png"));
                    g2.drawImage(imagem, larguraPanel / 2 - 105, 75, null);
                    g2.setFont(new Font("TimesRoman", Font.BOLD, alturaPanel / 20));
                    g2.drawString("Projeto IA", larguraPanel / 2 - 60, 50);
                    g2.drawString("Jogo Dos Quadradinhos", larguraPanel / 2 - 130, 300 + alturaPanel / 20);

                    g2.setFont(new Font("TimesRoman", Font.PLAIN, alturaPanel / 30));
                    g2.drawString("Alunos:", larguraPanel / 2 - 30, alturaPanel - 60);
                    g2.drawString("Fabio Vinicius Goes Amaral", larguraPanel / 2 - 115, alturaPanel - 35);
                    g2.drawString("Gustavo Gimenez De Deus", larguraPanel / 2 - 115, alturaPanel - 10);
                    return;
                } catch (IOException ex) {
                    Logger.getLogger(IUprincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                return;
            }

            int[][] aux = this.tabuleiro.getTab();

            int linhas = this.tabuleiro.getLin();
            int colunas = this.tabuleiro.getCol();

            this.setBackground(Color.lightGray);

            // configuração do rendering para obeter melhor qualidade
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);

            larguras = larguraPanel / linhas;
            alturas = alturaPanel / linhas;

            larguras = larguraPanel / linhas;
            alturas = alturaPanel / colunas;
            g2.setStroke(new java.awt.BasicStroke(5f));
            g2.setColor(Color.black);
            g2.setFont(new Font("TimesRoman", Font.PLAIN, larguras / 10));

            for (int i = 0; i < linhas; i++) {
                for (int j = 0; j < colunas; j++) {
                    // Desenha o quadrado branco que se movimenta
                    if (aux[i][j] == -1) {
                        g2.setColor(Color.white);
                        g2.fillRect(j * larguras, i * alturas,
                                larguras, alturas);
                        g2.setColor(Color.black);
                        continue;
                    }
                    g2.drawString(String.valueOf(aux[i][j] + 1), (j * 2 + 1)
                            * (larguras / 2), (i * 2 + 1) * (alturas / 2));
                }
            }
            g2.setStroke(new java.awt.BasicStroke(1f));
            g2.setColor(Color.black);
            for (int i = 1; i < linhas; i++) {
                // Desenha as colunas
                g2.drawLine(i * larguras, 0, i * larguras, alturaPanel);

                // Desenha as linhas
                g2.drawLine(0, i * alturas, larguraPanel, i * alturas);
            }

        }
    }

    public class TimerActionListener implements ActionListener {

        private Tabuleiro aux;

        public Tabuleiro getAux() {
            return aux;
        }

        public void setAux(Tabuleiro aux) {
            this.aux = aux;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            resolve.setTab(aux.getTab());
            Panel.updateUI();
            aux = aux.getPai();
            if (aux == null) {
                paraTimer();
            }
        }

    }

    public void paraTimer() {
        timer.stop();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Cega_menu;
    private javax.swing.JMenuItem Embaralhar_menu;
    private javax.swing.JMenuItem Exibe_Menu;
    private javax.swing.JMenuItem HPessoal_menu;
    private javax.swing.JMenuItem Heuristica1_menu;
    private javax.swing.JMenuItem Heuristica2_menu;
    private javax.swing.JMenuItem Iniciar_tab_menu;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JLabel NomeAlgLabel;
    private javax.swing.JLabel NumItLabel;
    private javax.swing.JLabel NumJogLabel;
    private javax.swing.JScrollPane Panel;
    private javax.swing.JMenuItem Recupera_tab_menu;
    private javax.swing.JMenu Resolver_menu;
    private javax.swing.JMenuItem Salva_tab_menu;
    private javax.swing.JMenu Tab_menu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
