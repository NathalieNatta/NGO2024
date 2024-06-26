/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo2024;

import oru.inf.InfDB;
import oru.inf.InfException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 * @author JillWithJ, Israael, Natha
 */
public class RedigeraAnstallda extends javax.swing.JFrame {

    private InfDB idb;
    private String anstalldID;
    private Anstallning anstalld;
    private Avdelning avdelning;
    
    public RedigeraAnstallda(InfDB idb, String aid) {
        
        this.idb = idb;
        anstalldID = aid;
        anstalld = new Anstallning(idb, aid, "");
        avdelning = new Avdelning(idb, anstalldID);
        
        initComponents();
        
        setSynlighet("reset", false);
        lblFelmeddelande.setVisible(false);
        fyllCbAddAvdelning();
        
    }
    
    public String getDagensDatum(){
        
        LocalDate idag = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dagensDatum = idag.format(formatter);
        
        return dagensDatum;
    }
    
    public void setSynlighet(String vilken, boolean synlighet){
        
        if(vilken.equals("add")){
            
            lblAdd.setVisible(synlighet);
            lblAddAdress.setVisible(synlighet);
            lblAddAtEpost.setVisible(synlighet);
            lblAddAvdelning.setVisible(synlighet);
            lblAddEfternamn.setVisible(synlighet);
            lblAddEpost.setVisible(synlighet);
            lblAddFornamn.setVisible(synlighet);
            lblAddLosenord.setVisible(synlighet);
            lblAddTelefon.setVisible(synlighet);
            
            btnAdd.setVisible(synlighet);
            btnAddAvbryt.setVisible(synlighet);
            btnAddRandomEpost.setVisible(synlighet);
            btnAddRandomLosenord.setVisible(synlighet);
            
            tfAddAdress.setVisible(synlighet);
            cbAddAvdelning.setVisible(synlighet);
            tfAddEfternamn.setVisible(synlighet);
            tfAddEpost.setVisible(synlighet);
            tfAddFornamn.setVisible(synlighet);
            tfAddLosenord.setVisible(synlighet);
            tfAddTelefon.setVisible(synlighet);
        }
        if(vilken.equals("delete")){
            
            lblDelete.setVisible(synlighet);
            lblDeleteEpost.setVisible(synlighet);
            lblDeleteAtEpost.setVisible(synlighet);
            
            btnDelete.setVisible(synlighet);
            btnDeleteAvbryt.setVisible(synlighet);
            
            tfDeleteEpost.setVisible(synlighet);
        }
        if(vilken.equals("reset")){
            
            lblAdd.setVisible(synlighet);
            lblAddAdress.setVisible(synlighet);
            lblAddAtEpost.setVisible(synlighet);
            lblAddAvdelning.setVisible(synlighet);
            lblAddEfternamn.setVisible(synlighet);
            lblAddEpost.setVisible(synlighet);
            lblAddFornamn.setVisible(synlighet);
            lblAddLosenord.setVisible(synlighet);
            lblAddTelefon.setVisible(synlighet);
            
            btnAdd.setVisible(synlighet);
            btnAddAvbryt.setVisible(synlighet);
            btnAddRandomEpost.setVisible(synlighet);
            btnAddRandomLosenord.setVisible(synlighet);
            
            tfAddAdress.setVisible(synlighet);
            cbAddAvdelning.setVisible(synlighet);
            tfAddEfternamn.setVisible(synlighet);
            tfAddEpost.setVisible(synlighet);
            tfAddFornamn.setVisible(synlighet);
            tfAddLosenord.setVisible(synlighet);
            tfAddTelefon.setVisible(synlighet);
            
            lblDelete.setVisible(synlighet);
            lblDeleteEpost.setVisible(synlighet);
            lblDeleteAtEpost.setVisible(synlighet);
            btnDelete.setVisible(synlighet);
            btnDeleteAvbryt.setVisible(synlighet);
            tfDeleteEpost.setVisible(synlighet);
            
            lblFelmeddelande.setVisible(synlighet);
            
            btnValAdd.setVisible(true);
            btnValDelete.setVisible(true);
            lblRubrik.setVisible(true);
        }
        if(vilken.equals("valBtns")){
            
            btnValAdd.setVisible(synlighet);
            btnValDelete.setVisible(synlighet);
            lblRubrik.setVisible(synlighet);
        }
        
    }
    
    private void fyllCbAddAvdelning(){
        
        ArrayList<HashMap<String, String>> avdelningar = avdelning.getAvdelningar();
        
        for(HashMap<String, String> avdelning1 : avdelningar){
            String namn = avdelning1.get("namn");
            cbAddAvdelning.addItem(namn);
        }
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jfArDuSaker = new javax.swing.JFrame();
        lblArDuSaker = new javax.swing.JLabel();
        btnArDuSakerDelete = new javax.swing.JButton();
        btnArDuSakerAvbryt = new javax.swing.JButton();
        lblRubrik = new javax.swing.JLabel();
        lblDelete = new javax.swing.JLabel();
        btnValAdd = new javax.swing.JButton();
        btnValDelete = new javax.swing.JButton();
        lblDeleteEpost = new javax.swing.JLabel();
        tfDeleteEpost = new javax.swing.JTextField();
        lblAdd = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        btnDeleteAvbryt = new javax.swing.JButton();
        lblAddFornamn = new javax.swing.JLabel();
        tfAddFornamn = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        tfAddEfternamn = new javax.swing.JTextField();
        tfAddAdress = new javax.swing.JTextField();
        tfAddEpost = new javax.swing.JTextField();
        tfAddLosenord = new javax.swing.JTextField();
        lblAddEfternamn = new javax.swing.JLabel();
        lblAddAdress = new javax.swing.JLabel();
        lblAddEpost = new javax.swing.JLabel();
        lblAddLosenord = new javax.swing.JLabel();
        btnAddAvbryt = new javax.swing.JButton();
        lblFelmeddelande = new javax.swing.JLabel();
        lblAddAtEpost = new javax.swing.JLabel();
        btnAddRandomEpost = new javax.swing.JButton();
        btnAddRandomLosenord = new javax.swing.JButton();
        lblAddTelefon = new javax.swing.JLabel();
        tfAddTelefon = new javax.swing.JTextField();
        lblAddAvdelning = new javax.swing.JLabel();
        lblDeleteAtEpost = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        cbAddAvdelning = new javax.swing.JComboBox<>();

        lblArDuSaker.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblArDuSaker.setText("jLabel1");

        btnArDuSakerDelete.setText("Ta Bort");
        btnArDuSakerDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArDuSakerDeleteActionPerformed(evt);
            }
        });

        btnArDuSakerAvbryt.setText("Avbryt");
        btnArDuSakerAvbryt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArDuSakerAvbrytActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jfArDuSakerLayout = new javax.swing.GroupLayout(jfArDuSaker.getContentPane());
        jfArDuSaker.getContentPane().setLayout(jfArDuSakerLayout);
        jfArDuSakerLayout.setHorizontalGroup(
            jfArDuSakerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jfArDuSakerLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jfArDuSakerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jfArDuSakerLayout.createSequentialGroup()
                        .addComponent(btnArDuSakerDelete)
                        .addGap(29, 29, 29)
                        .addComponent(btnArDuSakerAvbryt))
                    .addComponent(lblArDuSaker))
                .addContainerGap(198, Short.MAX_VALUE))
        );
        jfArDuSakerLayout.setVerticalGroup(
            jfArDuSakerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jfArDuSakerLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lblArDuSaker)
                .addGap(112, 112, 112)
                .addGroup(jfArDuSakerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnArDuSakerDelete)
                    .addComponent(btnArDuSakerAvbryt))
                .addContainerGap(109, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblRubrik.setText("Lägg till eller Ta bort en anställd");

        lblDelete.setText("Ta bort en anställd");

        btnValAdd.setText("Lägg Till");
        btnValAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValAddActionPerformed(evt);
            }
        });

        btnValDelete.setText("Ta Bort");
        btnValDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValDeleteActionPerformed(evt);
            }
        });

        lblDeleteEpost.setText("Anställdes epost");

        lblAdd.setText("Lägg till en anställd");

        btnDelete.setText("Ta Bort");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnDeleteAvbryt.setText("Avbryt");
        btnDeleteAvbryt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteAvbrytActionPerformed(evt);
            }
        });

        lblAddFornamn.setText("Förnamn");

        btnAdd.setText("Lägg Till");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        lblAddEfternamn.setText("Efternamn");

        lblAddAdress.setText("Adress");

        lblAddEpost.setText("Epost");

        lblAddLosenord.setText("Lösenord");

        btnAddAvbryt.setText("Avbryt");
        btnAddAvbryt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddAvbrytActionPerformed(evt);
            }
        });

        lblFelmeddelande.setForeground(new java.awt.Color(255, 0, 51));
        lblFelmeddelande.setText("Jag är ett felmeddelande");

        lblAddAtEpost.setText("@example.com");

        btnAddRandomEpost.setText("Generera en Epost");
        btnAddRandomEpost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRandomEpostActionPerformed(evt);
            }
        });

        btnAddRandomLosenord.setText("Generera ett Lösenord");
        btnAddRandomLosenord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRandomLosenordActionPerformed(evt);
            }
        });

        lblAddTelefon.setText("Telefon");

        lblAddAvdelning.setText("Avdelning");

        lblDeleteAtEpost.setText("@example.com");

        jButton1.setText("Tillbaka");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cbAddAvdelning.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj Avdelning" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAdd)
                    .addComponent(lblDelete)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblRubrik, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnValAdd)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnValDelete))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblAddFornamn)
                                        .addComponent(lblAddEfternamn)
                                        .addComponent(lblAddEpost)
                                        .addComponent(lblAddLosenord)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblAddAdress)
                                            .addComponent(lblAddTelefon))
                                        .addComponent(lblAddAvdelning))
                                    .addGap(34, 34, 34)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tfAddFornamn, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                        .addComponent(tfAddEfternamn)
                                        .addComponent(tfAddEpost)
                                        .addComponent(tfAddLosenord)
                                        .addComponent(tfAddAdress)
                                        .addComponent(tfAddTelefon)
                                        .addComponent(cbAddAvdelning, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(lblDeleteEpost)
                                    .addGap(3, 3, 3)
                                    .addComponent(tfDeleteEpost, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAddAtEpost)
                            .addComponent(lblDeleteAtEpost))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 69, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnAddAvbryt, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(btnDelete)
                    .addComponent(btnDeleteAvbryt)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddRandomLosenord)
                            .addComponent(btnAddRandomEpost))))
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFelmeddelande)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblRubrik)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnValAdd)
                    .addComponent(btnValDelete))
                .addGap(35, 35, 35)
                .addComponent(lblDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDeleteEpost)
                    .addComponent(tfDeleteEpost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete)
                    .addComponent(lblDeleteAtEpost))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeleteAvbryt)
                .addGap(12, 12, 12)
                .addComponent(lblAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfAddFornamn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblAddFornamn)
                        .addComponent(btnAdd)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfAddEfternamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAddEfternamn)
                    .addComponent(btnAddAvbryt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfAddEpost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAddEpost)
                    .addComponent(lblAddAtEpost)
                    .addComponent(btnAddRandomEpost))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddRandomLosenord)
                    .addComponent(tfAddLosenord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAddLosenord))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAddAdress)
                            .addComponent(tfAddAdress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfAddTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAddTelefon))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAddAvdelning)
                            .addComponent(cbAddAvdelning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addComponent(lblFelmeddelande)
                        .addContainerGap(25, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnValAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValAddActionPerformed
        
        setSynlighet("add",true);
        setSynlighet("valBtns", false);
        
    }//GEN-LAST:event_btnValAddActionPerformed

    private void btnValDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValDeleteActionPerformed
        
        setSynlighet("delete",true);
        setSynlighet("valBtns", false);
        
    }//GEN-LAST:event_btnValDeleteActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        
        jfArDuSaker.setVisible(true);
        String epost = tfDeleteEpost.getText() + "@example.com";
        String aid = anstalld.getInloggadAID(epost);
        String namn = anstalld.getNamn(aid);
        lblArDuSaker.setText("Är du säker på att du vill ta bort " + namn + " som anställd");
        
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnArDuSakerDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArDuSakerDeleteActionPerformed
        
        String epost = tfDeleteEpost.getText() + "@example.com";
        String aid = anstalld.getInloggadAID(epost);
        anstalld.deleteAnstalld(aid);
        setSynlighet("reset",false);
        
    }//GEN-LAST:event_btnArDuSakerDeleteActionPerformed

    private void btnArDuSakerAvbrytActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArDuSakerAvbrytActionPerformed
        
        jfArDuSaker.setVisible(false);
        tfDeleteEpost.setText("");
        
    }//GEN-LAST:event_btnArDuSakerAvbrytActionPerformed

    private void btnAddRandomEpostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRandomEpostActionPerformed
        
        String fornamn = tfAddFornamn.getText();
        String efternamn = tfAddEfternamn.getText();
        
        if(fornamn.isBlank() || efternamn.isBlank()){
            lblFelmeddelande.setText("Skriv i för- och efternamn Tack!");
            lblFelmeddelande.setVisible(true);
        }else{
           String epost = anstalld.genereraEpost(fornamn, efternamn);
           tfAddEpost.setText(epost);
        }
        
    }//GEN-LAST:event_btnAddRandomEpostActionPerformed

    private void btnAddRandomLosenordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRandomLosenordActionPerformed
        
        String losenord = anstalld.genereraLosenord();
        tfAddLosenord.setText(losenord);
        
    }//GEN-LAST:event_btnAddRandomLosenordActionPerformed

    private void btnAddAvbrytActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAvbrytActionPerformed
        
        setSynlighet("reset", false);
        
    }//GEN-LAST:event_btnAddAvbrytActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        
        String fornamn = tfAddFornamn.getText();
        String efternamn = tfAddEfternamn.getText();
        String epost = tfAddEpost.getText() + "@example.com";
        String losenord = tfAddLosenord.getText();
        String adress = tfAddAdress.getText();
        String telefon = tfAddTelefon.getText();
        String avdelning1 = (String) cbAddAvdelning.getSelectedItem();
        String aid = anstalld.setAID();
        String dagensDatum = getDagensDatum();
        
        anstalld.addAnstalld(aid, fornamn, efternamn, adress, epost, telefon, dagensDatum, losenord, avdelning1);
        
        setSynlighet("reset", false);
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteAvbrytActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAvbrytActionPerformed
        
        setSynlighet("reset", false);
        
    }//GEN-LAST:event_btnDeleteAvbrytActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Meny meny = new Meny(idb,anstalldID, "");
        meny.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(RedigeraAnstallda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RedigeraAnstallda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RedigeraAnstallda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RedigeraAnstallda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new RedigeraAnstallda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddAvbryt;
    private javax.swing.JButton btnAddRandomEpost;
    private javax.swing.JButton btnAddRandomLosenord;
    private javax.swing.JButton btnArDuSakerAvbryt;
    private javax.swing.JButton btnArDuSakerDelete;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeleteAvbryt;
    private javax.swing.JButton btnValAdd;
    private javax.swing.JButton btnValDelete;
    private javax.swing.JComboBox<String> cbAddAvdelning;
    private javax.swing.JButton jButton1;
    private javax.swing.JFrame jfArDuSaker;
    private javax.swing.JLabel lblAdd;
    private javax.swing.JLabel lblAddAdress;
    private javax.swing.JLabel lblAddAtEpost;
    private javax.swing.JLabel lblAddAvdelning;
    private javax.swing.JLabel lblAddEfternamn;
    private javax.swing.JLabel lblAddEpost;
    private javax.swing.JLabel lblAddFornamn;
    private javax.swing.JLabel lblAddLosenord;
    private javax.swing.JLabel lblAddTelefon;
    private javax.swing.JLabel lblArDuSaker;
    private javax.swing.JLabel lblDelete;
    private javax.swing.JLabel lblDeleteAtEpost;
    private javax.swing.JLabel lblDeleteEpost;
    private javax.swing.JLabel lblFelmeddelande;
    private javax.swing.JLabel lblRubrik;
    private javax.swing.JTextField tfAddAdress;
    private javax.swing.JTextField tfAddEfternamn;
    private javax.swing.JTextField tfAddEpost;
    private javax.swing.JTextField tfAddFornamn;
    private javax.swing.JTextField tfAddLosenord;
    private javax.swing.JTextField tfAddTelefon;
    private javax.swing.JTextField tfDeleteEpost;
    // End of variables declaration//GEN-END:variables
}
