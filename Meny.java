/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo2024;

import oru.inf.InfDB;
import oru.inf.InfException;

/**
 *
 * @author JillWithJ
 */
public class Meny extends javax.swing.JFrame {

    private InfDB idb;
    private String inloggadAnv;
    private String aid;
    private String namn;
    
    /**
     * Creates new form Meny
     */
    public Meny(InfDB idb, String inloggadAnv) {
        this.idb = idb;
        this.inloggadAnv = inloggadAnv;
        
        Anstallning anstallning = new Anstallning(idb, inloggadAnv);
        //aid = "1";
        aid = "6";
        //aid = anstallning.getInloggadAID(inloggadAnv);
        
        String atkomst = anstallning.getAtkomst(aid);
        namn = anstallning.getNamn(aid);
        
        
        initComponents();
        lblInloggad.setText("Välkommen " + atkomst + " " + namn);
    }
    public Meny(InfDB idb, String aid, String inget) {
        this.idb = idb;
        this.aid = aid;
        
        Anstallning anstallning = new Anstallning(idb, aid, "");
        //aid = "1";
        aid = "6";
        //aid = anstallning.getInloggadAID(inloggadAnv);
        
        String atkomst = anstallning.getAtkomst(aid);
        namn = anstallning.getNamn(aid);
        
        
        initComponents();
        lblInloggad.setText("Välkommen " + atkomst + " " + namn);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblInloggad = new javax.swing.JLabel();
        btnKonto = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblInloggad.setText("jLabel1");

        btnKonto.setText("Mitt Konto");
        btnKonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKontoActionPerformed(evt);
            }
        });

        jButton1.setText("Anställda");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Avdelning");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInloggad)
                    .addComponent(btnKonto)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(307, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblInloggad)
                .addGap(40, 40, 40)
                .addComponent(btnKonto)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(133, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKontoActionPerformed
        
        Kontoinstallningar mittKonto = new Kontoinstallningar(idb, aid);
        mittKonto.setVisible(true);
        this.setVisible(false);
        
    }//GEN-LAST:event_btnKontoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        AnstalldaAdmin anstalld = new AnstalldaAdmin(idb, aid);
        anstalld.setVisible(true);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        AvdelningMeny avdelningM = new AvdelningMeny(idb, aid);
        avdelningM.setVisible(true);
        
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Meny.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Meny.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Meny.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Meny.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new Meny().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKonto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel lblInloggad;
    // End of variables declaration//GEN-END:variables
}
