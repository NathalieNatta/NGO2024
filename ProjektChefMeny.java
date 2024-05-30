/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo2024;

import oru.inf.InfDB;
import oru.inf.InfException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 * @author natha
 */
public class ProjektChefMeny extends javax.swing.JFrame {
    private String anstalldID;
    private InfDB idb;
    private Projekt projekt;

    /**
     * Creates new form ProjektChefMeny
     */
    public ProjektChefMeny(InfDB idb, String aid) {
       
        this.idb = idb;
        anstalldID = aid;
        projekt = new Projekt(idb, anstalldID);
        
        initComponents();
        setSynlighet(false);
        lblFelmeddelande.setVisible(false);
    }
    private void changeProjektNamn() {
    String nyttProjektNamn = tfProjektNamn.getText();
    String projektIDString = tfAngeProjektID.getText();
    
    if(nyttProjektNamn.isEmpty()|| projektIDString.isEmpty()){
        lblFelmeddelande.setText("Vänligen fyll i nytt projektnamn samt befintligt projektID");
        lblFelmeddelande.setVisible(true);
        return;
    }
    try{
        int pid = Integer.parseInt(projektIDString);
        int projektchef = Integer.parseInt(anstalldID);
        boolean success = projekt.changeProjektNamn(projektchef, pid, nyttProjektNamn);
        
        if(success){
            lblFelmeddelande.setText("Projektnamn ändrat till: " + nyttProjektNamn);
        } else {
            lblFelmeddelande.setText("Du är inte projektchef över detta projekt");
        }
    
    
    }catch (NumberFormatException e){
        lblFelmeddelande.setText("ProjektID måste vara ett nummer");
    }catch (InfException ex){
        lblFelmeddelande.setText("Fel vid ändring av projektnamn" + ex.getMessage());
    }
       lblFelmeddelande.setVisible(true);
}
    private void changeProjektBeskrivning(){
        String nyProjektBeskrivning = tfBeskrivning.getText();
        String projektIDString = tfAngeProjektID.getText();
        
        if(nyProjektBeskrivning.isEmpty()|| projektIDString.isEmpty()){
        lblFelmeddelande.setText("Vänligen fyll i ny beskrivning samt befintligt projektID");
        lblFelmeddelande.setVisible(true);
        return;
    }
    try{
        int pid = Integer.parseInt(projektIDString);
        int projektchef = Integer.parseInt(anstalldID);
        boolean success = projekt.changeProjektBeskrivning(projektchef, pid, nyProjektBeskrivning);
        
        if(success){
            lblFelmeddelande.setText("Projektbeskrivning ändrat till: " + nyProjektBeskrivning);
        } else {
            lblFelmeddelande.setText("Du är inte projektchef över detta projekt");
        }
    
    
    }catch (NumberFormatException e){
        lblFelmeddelande.setText("ProjektID måste vara ett nummer");
    }catch (InfException ex){
        lblFelmeddelande.setText("Fel vid ändring av projektbeskrivning" + ex.getMessage());
    }
       lblFelmeddelande.setVisible(true);
}
    private void changeProjektStartdatum(){
       String nyttStartDatumString = tfStartdatum.getText();
        String projektIDString = tfAngeProjektID.getText();
        
        if(nyttStartDatumString.isEmpty()|| projektIDString.isEmpty()){
        lblFelmeddelande.setText("Vänligen fyll i nytt startdatum samt befintligt projektID");
        lblFelmeddelande.setVisible(true);
        return;
    }
    try{
        int pid = Integer.parseInt(projektIDString);
        int projektchef = Integer.parseInt(anstalldID);
        Date nyttStartDatum = Date.valueOf(nyttStartDatumString);
        boolean success = projekt.changeProjektStartdatum(projektchef, pid, nyttStartDatum);
        
        if(success){
            lblFelmeddelande.setText("Projektstartdatum ändrat till: " + nyttStartDatum);
        } else {
            lblFelmeddelande.setText("Du är inte projektchef över detta projekt");
        }
    
    
    }catch (NumberFormatException e){
        lblFelmeddelande.setText("ProjektID måste vara ett nummer");
}catch (IllegalArgumentException e){
    lblFelmeddelande.setText("Datumet måste vara i formatet ÅÅÅÅ-MM-DD");
    }catch (InfException ex){
        lblFelmeddelande.setText("Fel vid ändring av startdatum" + ex.getMessage());
    }
       lblFelmeddelande.setVisible(true); 
    }
    
    private void changeProjektSlutDatum() {
        String nyttSlutDatumString = tfSlutdatum.getText();
        String projektIDString = tfAngeProjektID.getText();
        
        if(nyttSlutDatumString.isEmpty()|| projektIDString.isEmpty()){
        lblFelmeddelande.setText("Vänligen fyll i nytt slutdatum samt befintligt projektID");
        lblFelmeddelande.setVisible(true);
        return;
    }
    try{
        int pid = Integer.parseInt(projektIDString);
        int projektchef = Integer.parseInt(anstalldID);
        Date nyttSlutDatum = Date.valueOf(nyttSlutDatumString);
        boolean success = projekt.changeProjektSlutDatum(projektchef, pid, nyttSlutDatum);
        
        if(success){
            lblFelmeddelande.setText("Projektslutdatum ändrat till: " + nyttSlutDatum);
        } else {
            lblFelmeddelande.setText("Du är inte projektchef över detta projekt");
        }
    
    
    }catch (NumberFormatException e){
        lblFelmeddelande.setText("ProjektID måste vara ett nummer");
}catch (IllegalArgumentException e){
    lblFelmeddelande.setText("Datumet måste vara i formatet ÅÅÅÅ-MM-DD");
    }catch (InfException ex){
        lblFelmeddelande.setText("Fel vid ändring av slutdatum" + ex.getMessage());
    }
       lblFelmeddelande.setVisible(true); 
    }
    
    private void changeProjektKostnad() {
        String nyKostnadString = tfKostnad.getText();
        String projektIDString = tfAngeProjektID.getText();
        
        if(nyKostnadString.isEmpty()|| projektIDString.isEmpty()){
        lblFelmeddelande.setText("Vänligen fyll i ny kostnad samt befintligt projektID");
        lblFelmeddelande.setVisible(true);
        return;
    }
    try{
        int pid = Integer.parseInt(projektIDString);
        int projektchef = Integer.parseInt(anstalldID);
        double nyKostnad = Double.parseDouble(nyKostnadString);
        boolean success = projekt.changeProjektKostnad(projektchef, pid, nyKostnad);
        
        if(success){
            lblFelmeddelande.setText("Projektkostnad ändrat till: " + nyKostnad);
        } else {
            lblFelmeddelande.setText("Du är inte projektchef över detta projekt");
        }
    
    
    }catch (NumberFormatException e){
        lblFelmeddelande.setText("ProjektID och kostnad måste vara ett nummer");
    }catch (InfException ex){
        lblFelmeddelande.setText("Fel vid ändring av kostnad" + ex.getMessage());
    }
       lblFelmeddelande.setVisible(true);

}
    private void changeProjektStatus() {
        String nyStatus = tfStatus.getText();
        String projektIDString = tfAngeProjektID.getText();
        
        if(nyStatus.isEmpty()|| projektIDString.isEmpty()){
        lblFelmeddelande.setText("Vänligen fyll i ny status samt befintligt projektID");
        lblFelmeddelande.setVisible(true);
        return;
    }
    try{
        int pid = Integer.parseInt(projektIDString);
        int projektchef = Integer.parseInt(anstalldID);
        boolean success = projekt.changeProjektStatus(projektchef, pid, nyStatus);
        
        if(success){
            lblFelmeddelande.setText("Projektstatus ändrat till: " + nyStatus);
        } else {
            lblFelmeddelande.setText("Du är inte projektchef över detta projekt");
        }
    
    
    }catch (NumberFormatException e){
        lblFelmeddelande.setText("ProjektID  måste vara ett nummer");
    }catch (InfException ex){
        lblFelmeddelande.setText("Fel vid ändring av status" + ex.getMessage());
    }
       lblFelmeddelande.setVisible(true);

}
    private void changeProjektPrioritet() {
        String nyPrioritering = tfPrioritering.getText();
        String projektIDString = tfAngeProjektID.getText();
        
        if(nyPrioritering.isEmpty()|| projektIDString.isEmpty()){
        lblFelmeddelande.setText("Vänligen fyll i ny prioritering samt befintligt projektID");
        lblFelmeddelande.setVisible(true);
        return;
    }
    try{
        int pid = Integer.parseInt(projektIDString);
        int projektchef = Integer.parseInt(anstalldID);
        boolean success = projekt.changeProjektPrioritet(projektchef, pid, nyPrioritering);
        
        if(success){
            lblFelmeddelande.setText("Projektprioritet ändrat till: " + nyPrioritering);
        } else {
            lblFelmeddelande.setText("Du är inte projektchef över detta projekt");
        }
    
    
    }catch (NumberFormatException e){
        lblFelmeddelande.setText("ProjektID  måste vara ett nummer");
    }catch (InfException ex){
        lblFelmeddelande.setText("Fel vid ändring av prioritet" + ex.getMessage());
    }
       lblFelmeddelande.setVisible(true);

    }
    private void changeProjektLand(){
        String nyttLandString = tfLand.getText();
        String projektIDString = tfAngeProjektID.getText();
        
        if(nyttLandString.isEmpty()|| projektIDString.isEmpty()){
        lblFelmeddelande.setText("Vänligen fyll i nytt land samt befintligt projektID");
        lblFelmeddelande.setVisible(true);
        return;
    }
    try{
        int pid = Integer.parseInt(projektIDString);
        int nyttLand = Integer.parseInt(nyttLandString);
        int projektchef = Integer.parseInt(anstalldID);
        boolean success = projekt.changeProjektLand(projektchef, pid, nyttLand);
        
        if(success){
            lblFelmeddelande.setText("Projektland ändrat till: " + nyttLand);
        } else {
            lblFelmeddelande.setText("Du är inte projektchef över detta projekt");
        }
    
    
    }catch (NumberFormatException e){
        lblFelmeddelande.setText("ProjektID och land  måste vara ett nummer");
    }catch (InfException ex){
        lblFelmeddelande.setText("Fel vid ändring av land" + ex.getMessage());
    }
       lblFelmeddelande.setVisible(true);

    }
    
    private void addProjektPartner() {
        String partnerIDString = tfPartnerID.getText();
        String projektIDString = tfAngeProjektPartnerID.getText();
        
        if(partnerIDString.isEmpty()|| projektIDString.isEmpty()){
        lblFelmeddelande.setText("Vänligen fyll i nytt partnerID samt befintligt projektID");
        lblFelmeddelande.setVisible(true);
        return;
    }
    try{
        int pid = Integer.parseInt(projektIDString);
        int partner_pid = Integer.parseInt(partnerIDString);
        int projektchef = Integer.parseInt(anstalldID);
        boolean success = projekt.addProjektPartner(projektchef, pid, partner_pid);
        
        if(success){
            lblFelmeddelande.setText("Partner tillagd för projekt: " + pid);
        } else {
            lblFelmeddelande.setText("Du är inte projektchef över detta projekt");
        }
    
    
    }catch (NumberFormatException e){
        lblFelmeddelande.setText("ProjektID och partnerID  måste vara ett nummer");
    }catch (InfException ex){
        lblFelmeddelande.setText("Fel vid tillägg av partner" + ex.getMessage());
    }
       lblFelmeddelande.setVisible(true);

        
    }
    private void removeProjektPartner(){
        String partnerIDString = tfPartnerID.getText();
        String projektIDString = tfAngeProjektPartnerID.getText();
        
        if(partnerIDString.isEmpty()|| projektIDString.isEmpty()){
        lblFelmeddelande.setText("Vänligen fyll i både partnerID och projektID");
        lblFelmeddelande.setVisible(true);
        return;
    }
    try{
        int pid = Integer.parseInt(projektIDString);
        int partner_pid = Integer.parseInt(partnerIDString);
        int projektchef = Integer.parseInt(anstalldID);
        boolean success = projekt.removeProjektPartner(projektchef, pid, partner_pid);
        
        if(success){
            lblFelmeddelande.setText("Partner borttagen för projekt: " + pid);
        } else {
            lblFelmeddelande.setText("Du är inte projektchef över detta projekt");
        }
    
    
    }catch (NumberFormatException e){
        lblFelmeddelande.setText("ProjektID och partnerID  måste vara ett nummer");
    }catch (InfException ex){
        lblFelmeddelande.setText("Fel vid borttagning av partner" + ex.getMessage());
    }
       lblFelmeddelande.setVisible(true);
}

        private void addHandlaggareTillProjekt() {
        String anstalldIDString = tfAngeAnstalldIDHandlaggare.getText();
        String projektIDString = tfAngeProjektIDHandLaggare.getText();
        
        if(anstalldIDString.isEmpty()|| projektIDString.isEmpty()){
        lblFelmeddelande.setText("Vänligen fyll i både anställdID och projektID");
        lblFelmeddelande.setVisible(true);
        return;
    }
    try{
        int pid = Integer.parseInt(projektIDString);
        int aid = Integer.parseInt(anstalldIDString);
        int projektchef = Integer.parseInt(anstalldID);
        
        if (Validering.isValidNummer(projektIDString) && Validering.isValidNummer(anstalldIDString)
                && Validering.isValidProjektchef(projektchef, pid, idb)) {
        
        boolean success = projekt.addHandlaggareTillProjekt(projektchef, pid, aid);
        
        if(success){
            lblFelmeddelande.setText("Handläggare tillagd för projekt: " + pid);
        } else {
            lblFelmeddelande.setText("Du är inte projektchef över detta projekt");
        }
        } else {
        lblFelmeddelande.setText("Ogiltigt projektID, anställdID eller otillåten åtgärd");
        
        }
    
    
    }catch (NumberFormatException e){
        lblFelmeddelande.setText("ProjektID och anställdID  måste vara ett nummer");
    }catch (InfException ex){
        lblFelmeddelande.setText("Fel vid tillägg av handläggare" + ex.getMessage());
    }
       lblFelmeddelande.setVisible(true);

}
        private void removeHandlaggareFromProjekt(){
        String anstalldIDString = tfAngeAnstalldIDHandlaggare.getText();
        String projektIDString = tfAngeProjektIDHandLaggare.getText();
        
        if(anstalldIDString.isEmpty()|| projektIDString.isEmpty()){
        lblFelmeddelande.setText("Vänligen fyll i både anställdID och projektID");
        lblFelmeddelande.setVisible(true);
        return;
    }
    try{
        int pid = Integer.parseInt(projektIDString);
        int aid = Integer.parseInt(anstalldIDString);
        int projektchef = Integer.parseInt(anstalldID);
        
        if (Validering.isValidNummer(projektIDString) && Validering.isValidNummer(anstalldIDString)
                && Validering.isValidProjektchef(projektchef, pid, idb)) {
        
        boolean success = projekt.removeHandlaggareFromProjekt(projektchef, pid, aid);
        
        if(success){
            lblFelmeddelande.setText("Handläggare borttagen för projekt: " + pid);
        } else {
            lblFelmeddelande.setText("Du är inte projektchef över detta projekt");
        }
    } else {
            lblFelmeddelande.setText("Ogiltigt projektID, anställdID eller otillåten åtgärd");
        }
    
    }catch (NumberFormatException e){
        lblFelmeddelande.setText("ProjektID och anställdID  måste vara ett nummer");
    }catch (InfException ex){
        lblFelmeddelande.setText("Fel vid borttagning av handläggare" + ex.getMessage());
    }
       lblFelmeddelande.setVisible(true);

        }
        
        private void getProjektKostnadStatistik() {
    try {
        int projektchefID = Integer.parseInt(anstalldID);
        double totalKostnad = projekt.getProjektKostnadStatistik(projektchefID);
        
        tfKostnadStatistik.setText("Total kostnad för dina projekt: " + totalKostnad);
    } catch (NumberFormatException e) {
        lblFelmeddelande.setText("Ogiltigt projektchef ID");
    } catch (InfException ex) {
        lblFelmeddelande.setText("Fel vid hämtning av kostnadsstatistik: " + ex.getMessage());
    }
    
    lblFelmeddelande.setVisible(true);
}
    
     
    private void setSynlighet(boolean synlighet){

        lblNyBeskrivning.setVisible(true);
        lblNyttStartDatum.setVisible(true);
        lblNyttSlutDatum.setVisible(true);
        lblNyttProjektNamn.setVisible(true) ;
        lblNyKostnad.setVisible(true);
        lblNyStatus.setVisible(true);
        lblNyPrioritering.setVisible(true);
        lblNyttLand.setVisible(true);
        lblAngeProjektID.setVisible(true);
        lblAngeAnstalldID.setVisible(true);
        lblAngeProjektIDHandLaggare.setVisible(true);
        lblTotalKostnadProjekt.setVisible(true);
        lblAngePartnerID.setVisible(true);
        lblAngeProjektIDPartner.setVisible(true);
        lblRubrikAndraMinaProjekt.setVisible(true);
        lblRubrikProjektChef.setVisible(true);
        lblRubrikLaggTillPartner.setVisible(true);
        lblRubrikLaggTillHandLaggare.setVisible(true);
        lblRubrikStatistik.setVisible(true);

        tfProjektNamn.setVisible(true);
        tfStartdatum.setVisible(true);
        tfSlutdatum.setVisible(true);
        tfKostnad.setVisible(true);
        tfStatus.setVisible(true);
        tfPrioritering.setVisible(true);
        tfLand.setVisible(true);
        tfAngeProjektID.setVisible(true);
        tfBeskrivning.setVisible(true);
        tfPartnerID.setVisible(true);
        tfAngeProjektPartnerID.setVisible(true);
        tfAngeAnstalldIDHandlaggare.setVisible(true);
        tfAngeProjektIDHandLaggare.setVisible(true);
        tfKostnadStatistik.setVisible(true);

        btnLaggTillPartner.setVisible(true);
        btnLaggTillHandLaggare.setVisible(true);
        btnSeStatistik.setVisible(true);
        btnChangeProjektNamn.setVisible(true);
        btnChangeBeskrivning.setVisible(true);
        btnChangeStartdatum.setVisible(true);
        btnChangeSlutdatum.setVisible(true);
        btnChangeKostnad.setVisible(true);
        btnChangeStatus.setVisible(true);
        btnChangePrioritering.setVisible(true);
        btnChangeLand.setVisible(true);
        btnTillbaka.setVisible(true);
        btnTabortPartner.setVisible(true);
        btnTaBortHandlaggare.setVisible(true);
}
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblRubrikProjektChef = new javax.swing.JLabel();
        btnLaggTillPartner = new javax.swing.JButton();
        btnLaggTillHandLaggare = new javax.swing.JButton();
        btnSeStatistik = new javax.swing.JButton();
        btnTillbaka = new javax.swing.JButton();
        lblFelmeddelande = new javax.swing.JLabel();
        lblRubrikAndraMinaProjekt = new javax.swing.JLabel();
        tfProjektNamn = new javax.swing.JTextField();
        tfStartdatum = new javax.swing.JTextField();
        tfSlutdatum = new javax.swing.JTextField();
        tfKostnad = new javax.swing.JTextField();
        tfStatus = new javax.swing.JTextField();
        tfPrioritering = new javax.swing.JTextField();
        tfLand = new javax.swing.JTextField();
        tfAngeProjektID = new javax.swing.JTextField();
        tfBeskrivning = new javax.swing.JTextField();
        lblNyBeskrivning = new javax.swing.JLabel();
        lblNyttStartDatum = new javax.swing.JLabel();
        lblNyttSlutDatum = new javax.swing.JLabel();
        lblNyttProjektNamn = new javax.swing.JLabel();
        lblNyKostnad = new javax.swing.JLabel();
        lblNyStatus = new javax.swing.JLabel();
        lblNyPrioritering = new javax.swing.JLabel();
        lblNyttLand = new javax.swing.JLabel();
        lblAngeProjektID = new javax.swing.JLabel();
        btnChangeProjektNamn = new javax.swing.JButton();
        btnChangeBeskrivning = new javax.swing.JButton();
        btnChangeStartdatum = new javax.swing.JButton();
        btnChangeSlutdatum = new javax.swing.JButton();
        btnChangeKostnad = new javax.swing.JButton();
        btnChangeStatus = new javax.swing.JButton();
        btnChangePrioritering = new javax.swing.JButton();
        btnChangeLand = new javax.swing.JButton();
        lblRubrikLaggTillPartner = new javax.swing.JLabel();
        tfPartnerID = new javax.swing.JTextField();
        tfAngeProjektPartnerID = new javax.swing.JTextField();
        lblAngePartnerID = new javax.swing.JLabel();
        lblAngeProjektIDPartner = new javax.swing.JLabel();
        lblRubrikLaggTillHandLaggare = new javax.swing.JLabel();
        tfAngeAnstalldIDHandlaggare = new javax.swing.JTextField();
        tfAngeProjektIDHandLaggare = new javax.swing.JTextField();
        lblAngeAnstalldID = new javax.swing.JLabel();
        lblAngeProjektIDHandLaggare = new javax.swing.JLabel();
        lblRubrikStatistik = new javax.swing.JLabel();
        tfKostnadStatistik = new javax.swing.JTextField();
        lblTotalKostnadProjekt = new javax.swing.JLabel();
        btnTabortPartner = new javax.swing.JButton();
        btnTaBortHandlaggare = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblRubrikProjektChef.setText("Projektchef");

        btnLaggTillPartner.setText("Lägg Till Partner");
        btnLaggTillPartner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillPartnerActionPerformed(evt);
            }
        });

        btnLaggTillHandLaggare.setText("Lägg Till Handläggare");
        btnLaggTillHandLaggare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaggTillHandLaggareActionPerformed(evt);
            }
        });

        btnSeStatistik.setText("se statistik");
        btnSeStatistik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeStatistikActionPerformed(evt);
            }
        });

        btnTillbaka.setText("Tillbaka");
        btnTillbaka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaActionPerformed(evt);
            }
        });

        lblRubrikAndraMinaProjekt.setText("Ändra Mina Projekt");

        tfStartdatum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfStartdatumActionPerformed(evt);
            }
        });

        tfPrioritering.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPrioriteringActionPerformed(evt);
            }
        });

        tfAngeProjektID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfAngeProjektIDActionPerformed(evt);
            }
        });

        lblNyBeskrivning.setText("Ny beskrivning:");

        lblNyttStartDatum.setText("Nytt startdatum:");

        lblNyttSlutDatum.setText("Nytt slutdatum:");

        lblNyttProjektNamn.setText("Nytt projektnamn:");

        lblNyKostnad.setText("Ny kostnad:");

        lblNyStatus.setText("Ny status:");

        lblNyPrioritering.setText("Ny prioritering:");

        lblNyttLand.setText("Nytt land:");

        lblAngeProjektID.setText("Ange projektID:");

        btnChangeProjektNamn.setText("Ändra");
        btnChangeProjektNamn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeProjektNamnActionPerformed(evt);
            }
        });

        btnChangeBeskrivning.setText("Ändra");
        btnChangeBeskrivning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeBeskrivningActionPerformed(evt);
            }
        });

        btnChangeStartdatum.setText("Ändra");
        btnChangeStartdatum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeStartdatumActionPerformed(evt);
            }
        });

        btnChangeSlutdatum.setText("Ändra");
        btnChangeSlutdatum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeSlutdatumActionPerformed(evt);
            }
        });

        btnChangeKostnad.setText("Ändra");
        btnChangeKostnad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeKostnadActionPerformed(evt);
            }
        });

        btnChangeStatus.setText("Ändra");
        btnChangeStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeStatusActionPerformed(evt);
            }
        });

        btnChangePrioritering.setText("Ändra");
        btnChangePrioritering.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePrioriteringActionPerformed(evt);
            }
        });

        btnChangeLand.setText("Ändra");
        btnChangeLand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeLandActionPerformed(evt);
            }
        });

        lblRubrikLaggTillPartner.setText("Lägg till Partner ");

        lblAngePartnerID.setText("Ange PartnerID:");

        lblAngeProjektIDPartner.setText("Ange ProjektID:");

        lblRubrikLaggTillHandLaggare.setText("Lägg till Handläggare");

        lblAngeAnstalldID.setText("Ange AnställdID:");

        lblAngeProjektIDHandLaggare.setText("Ange ProjektID:");

        lblRubrikStatistik.setText("Få ut kostnadsstatistik för projekt");

        lblTotalKostnadProjekt.setText("Total Kostnad:");

        btnTabortPartner.setText("Ta bort Partner");
        btnTabortPartner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTabortPartnerActionPerformed(evt);
            }
        });

        btnTaBortHandlaggare.setText("Ta bort Handläggare");
        btnTaBortHandlaggare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaBortHandlaggareActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(369, 369, 369)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblAngeProjektIDPartner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfAngeProjektPartnerID, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAngePartnerID, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPartnerID, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnTillbaka)
                .addGap(60, 60, 60))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblNyttProjektNamn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tfProjektNamn, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblNyBeskrivning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfStartdatum, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfBeskrivning, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfSlutdatum, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfKostnad, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnChangeBeskrivning)
                            .addComponent(btnChangeProjektNamn))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(119, 119, 119)
                                .addComponent(lblRubrikLaggTillPartner, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90)
                                .addComponent(lblRubrikLaggTillHandLaggare))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(334, 334, 334)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAngeAnstalldID)
                                    .addComponent(lblAngeProjektIDHandLaggare, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfAngeAnstalldIDHandlaggare, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfAngeProjektIDHandLaggare, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addComponent(btnLaggTillPartner)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTabortPartner)
                                .addGap(28, 28, 28)
                                .addComponent(btnLaggTillHandLaggare))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblRubrikAndraMinaProjekt)
                                .addGap(93, 93, 93)
                                .addComponent(lblRubrikProjektChef, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblNyttLand, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAngeProjektID, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblNyttSlutDatum)
                                        .addComponent(lblNyKostnad, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNyPrioritering, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(btnChangeKostnad, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(btnChangeSlutdatum, javax.swing.GroupLayout.Alignment.TRAILING)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(24, 24, 24)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(tfLand, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(tfAngeProjektID, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(12, 12, 12)
                                                    .addComponent(tfPrioritering, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(btnChangeStatus)
                                                .addComponent(btnChangePrioritering)
                                                .addComponent(btnChangeLand)))))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(lblNyttStartDatum)
                                    .addGap(113, 113, 113)
                                    .addComponent(btnChangeStartdatum)))
                            .addComponent(lblNyStatus))
                        .addGap(132, 132, 132)
                        .addComponent(lblFelmeddelande, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(lblRubrikStatistik, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnTaBortHandlaggare)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSeStatistik)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(lblTotalKostnadProjekt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(tfKostnadStatistik, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblRubrikProjektChef)
                                .addComponent(lblRubrikAndraMinaProjekt))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblRubrikLaggTillHandLaggare)
                                .addComponent(lblRubrikLaggTillPartner))))
                    .addComponent(lblRubrikStatistik))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAngePartnerID)
                            .addComponent(tfPartnerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAngeAnstalldID)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfAngeAnstalldIDHandlaggare, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTotalKostnadProjekt)
                            .addComponent(tfKostnadStatistik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfAngeProjektIDHandLaggare, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblAngeProjektIDHandLaggare)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfAngeProjektPartnerID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblAngeProjektIDPartner))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLaggTillPartner)
                            .addComponent(btnTabortPartner))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblNyBeskrivning)
                                    .addComponent(tfBeskrivning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnChangeBeskrivning))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfStartdatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNyttStartDatum)
                                    .addComponent(btnChangeStartdatum))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfSlutdatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNyttSlutDatum)
                                    .addComponent(btnChangeSlutdatum)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfProjektNamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNyttProjektNamn)
                                    .addComponent(btnChangeProjektNamn))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnLaggTillHandLaggare)
                                    .addComponent(btnSeStatistik)
                                    .addComponent(btnTaBortHandlaggare))
                                .addGap(79, 79, 79)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNyKostnad)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnChangeKostnad)
                            .addComponent(tfKostnad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNyStatus)
                    .addComponent(btnChangeStatus)
                    .addComponent(tfStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFelmeddelande, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNyPrioritering)
                    .addComponent(tfPrioritering, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChangePrioritering))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNyttLand)
                    .addComponent(tfLand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChangeLand))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAngeProjektID)
                            .addComponent(tfAngeProjektID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(btnTillbaka)))
                .addGap(334, 334, 334))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfStartdatumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfStartdatumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfStartdatumActionPerformed

    private void tfPrioriteringActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPrioriteringActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPrioriteringActionPerformed

    private void tfAngeProjektIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfAngeProjektIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfAngeProjektIDActionPerformed

    private void btnChangeProjektNamnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeProjektNamnActionPerformed
        changeProjektNamn();
    }//GEN-LAST:event_btnChangeProjektNamnActionPerformed

    private void btnChangeBeskrivningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeBeskrivningActionPerformed
        changeProjektBeskrivning();
    }//GEN-LAST:event_btnChangeBeskrivningActionPerformed

    private void btnChangeStartdatumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeStartdatumActionPerformed
        changeProjektStartdatum();
    }//GEN-LAST:event_btnChangeStartdatumActionPerformed

    private void btnChangeSlutdatumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeSlutdatumActionPerformed
        changeProjektSlutDatum();
    }//GEN-LAST:event_btnChangeSlutdatumActionPerformed

    private void btnChangeKostnadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeKostnadActionPerformed
         changeProjektKostnad();
    }//GEN-LAST:event_btnChangeKostnadActionPerformed

    private void btnChangeStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeStatusActionPerformed
        changeProjektStatus();
    }//GEN-LAST:event_btnChangeStatusActionPerformed

    private void btnChangePrioriteringActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePrioriteringActionPerformed
        changeProjektPrioritet();
    }//GEN-LAST:event_btnChangePrioriteringActionPerformed

    private void btnChangeLandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeLandActionPerformed
        changeProjektLand();
    }//GEN-LAST:event_btnChangeLandActionPerformed

    private void btnLaggTillPartnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillPartnerActionPerformed
        addProjektPartner();
    }//GEN-LAST:event_btnLaggTillPartnerActionPerformed

    private void btnTabortPartnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTabortPartnerActionPerformed
        removeProjektPartner();
    }//GEN-LAST:event_btnTabortPartnerActionPerformed

    private void btnLaggTillHandLaggareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaggTillHandLaggareActionPerformed
        addHandlaggareTillProjekt();
    }//GEN-LAST:event_btnLaggTillHandLaggareActionPerformed

    private void btnTaBortHandlaggareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaBortHandlaggareActionPerformed
        removeHandlaggareFromProjekt();
    }//GEN-LAST:event_btnTaBortHandlaggareActionPerformed

    private void btnSeStatistikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeStatistikActionPerformed
        getProjektKostnadStatistik();
    }//GEN-LAST:event_btnSeStatistikActionPerformed

    private void btnTillbakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaActionPerformed
        Meny meny = new Meny(idb,anstalldID, "");
        meny.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnTillbakaActionPerformed

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
            java.util.logging.Logger.getLogger(ProjektChefMeny.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProjektChefMeny.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProjektChefMeny.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProjektChefMeny.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new ProjektChefMeny().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChangeBeskrivning;
    private javax.swing.JButton btnChangeKostnad;
    private javax.swing.JButton btnChangeLand;
    private javax.swing.JButton btnChangePrioritering;
    private javax.swing.JButton btnChangeProjektNamn;
    private javax.swing.JButton btnChangeSlutdatum;
    private javax.swing.JButton btnChangeStartdatum;
    private javax.swing.JButton btnChangeStatus;
    private javax.swing.JButton btnLaggTillHandLaggare;
    private javax.swing.JButton btnLaggTillPartner;
    private javax.swing.JButton btnSeStatistik;
    private javax.swing.JButton btnTaBortHandlaggare;
    private javax.swing.JButton btnTabortPartner;
    private javax.swing.JButton btnTillbaka;
    private javax.swing.JLabel lblAngeAnstalldID;
    private javax.swing.JLabel lblAngePartnerID;
    private javax.swing.JLabel lblAngeProjektID;
    private javax.swing.JLabel lblAngeProjektIDHandLaggare;
    private javax.swing.JLabel lblAngeProjektIDPartner;
    private javax.swing.JLabel lblFelmeddelande;
    private javax.swing.JLabel lblNyBeskrivning;
    private javax.swing.JLabel lblNyKostnad;
    private javax.swing.JLabel lblNyPrioritering;
    private javax.swing.JLabel lblNyStatus;
    private javax.swing.JLabel lblNyttLand;
    private javax.swing.JLabel lblNyttProjektNamn;
    private javax.swing.JLabel lblNyttSlutDatum;
    private javax.swing.JLabel lblNyttStartDatum;
    private javax.swing.JLabel lblRubrikAndraMinaProjekt;
    private javax.swing.JLabel lblRubrikLaggTillHandLaggare;
    private javax.swing.JLabel lblRubrikLaggTillPartner;
    private javax.swing.JLabel lblRubrikProjektChef;
    private javax.swing.JLabel lblRubrikStatistik;
    private javax.swing.JLabel lblTotalKostnadProjekt;
    private javax.swing.JTextField tfAngeAnstalldIDHandlaggare;
    private javax.swing.JTextField tfAngeProjektID;
    private javax.swing.JTextField tfAngeProjektIDHandLaggare;
    private javax.swing.JTextField tfAngeProjektPartnerID;
    private javax.swing.JTextField tfBeskrivning;
    private javax.swing.JTextField tfKostnad;
    private javax.swing.JTextField tfKostnadStatistik;
    private javax.swing.JTextField tfLand;
    private javax.swing.JTextField tfPartnerID;
    private javax.swing.JTextField tfPrioritering;
    private javax.swing.JTextField tfProjektNamn;
    private javax.swing.JTextField tfSlutdatum;
    private javax.swing.JTextField tfStartdatum;
    private javax.swing.JTextField tfStatus;
    // End of variables declaration//GEN-END:variables

}