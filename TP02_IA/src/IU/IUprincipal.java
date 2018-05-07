/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IU;

import codigos.Resolve;
import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Gustavo
 */
public class IUprincipal extends javax.swing.JFrame {

    private ViewPanel view;
    private Resolve resolve;

    /**
     * Creates new form IUprincipal
     */
    public IUprincipal() {
        this.view = new ViewPanel();
        initComponents();
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
        Menu = new javax.swing.JMenuBar();
        Tab_menu = new javax.swing.JMenu();
        Iniciar_tab_menu = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Tab_menu.setText("Tabuleiro");

        Iniciar_tab_menu.setText("Iniciar Tabuleiro");
        Iniciar_tab_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Iniciar_tab_menuActionPerformed(evt);
            }
        });
        Tab_menu.add(Iniciar_tab_menu);

        Menu.add(Tab_menu);

        jMenu2.setText("Edit");
        Menu.add(jMenu2);

        setJMenuBar(Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Iniciar_tab_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Iniciar_tab_menuActionPerformed
        int dimensao = Integer.valueOf(JOptionPane.showInputDialog("Insira qual a dimensão do tabuleiro"));
        this.resolve = new Resolve(dimensao, dimensao);
        this.view.setTab(this.resolve);
        this.view.setBackground(Color.lightGray);
        this.Panel.repaint();
    }//GEN-LAST:event_Iniciar_tab_menuActionPerformed

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
            if (this.tabuleiro == null) {
                return;
            }

            int alturaPanel = this.getHeight();
            int larguraPanel = this.getWidth();
            int[][] aux = this.tabuleiro.getTab();
            int linhas = 5;
            int colunas = 5;
//            int linhas = this.tabuleiro.getlin();
//            int colunas = this.tabuleiro.getlin();

            super.paintComponent(g);
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g;

            // configuração do rendering para obeter melhor qualidade
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);

            int larguras = larguraPanel / linhas;
            int alturas = alturaPanel / linhas;

            for (int i = 1; i < linhas; i++) {
                g2.setColor(Color.black);
                // Desenha as colunas
                g2.drawLine(i * larguras, 0, i * larguras, alturaPanel);

                // Desenha as linhas
                g2.drawLine(0, i * alturas, larguraPanel, i * alturas);
            }

            larguras = larguraPanel / linhas;
            alturas = alturaPanel / colunas;
            g2.setStroke(new java.awt.BasicStroke(5f));
            g2.setColor(Color.black);
            g2.setFont(new Font("TimesRoman", Font.PLAIN, larguras / 10));

            for (int i = 0; i < linhas; i++) {
                for (int j = 0; j < colunas; j++) {
                    if(aux[i][j] == -1){
                        
                    }
                    
                    g2.drawString(String.valueOf(aux[i][j]), (j * 2 + 1) * (larguras / 2),
                            (i * 2 + 1) * (alturas / 2));
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Iniciar_tab_menu;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JScrollPane Panel;
    private javax.swing.JMenu Tab_menu;
    private javax.swing.JMenu jMenu2;
    // End of variables declaration//GEN-END:variables
}
