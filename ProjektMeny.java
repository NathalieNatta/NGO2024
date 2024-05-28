/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo2024;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import oru.inf.InfDB;
import oru.inf.InfException;

/**
 *
 * @author natha, JillWithJ, Israael
 */
public class ProjektMeny extends javax.swing.JFrame {

    private InfDB idb;
    private String anstalldID;
    private Projekt projekt;
    private Partner partner;
    private String minAvdelning;
    
    public ProjektMeny(InfDB idb, String aid) {
        
        this.idb = idb;
        anstalldID = aid;
        projekt = new Projekt(idb, anstalldID);
        partner = new Partner(idb, anstalldID);
        minAvdelning = projekt.getMinAvdelning(anstalldID);
        
        initComponents();
        //setSynlighet("alla", false);
        taProjekt.setEditable(false);
        cbStatus.setEditable(false);
        cbProjekt.setEditable(false);
        cbDatumFrom.setEditable(false);
        cbDatumTill.setEditable(false);
        
    }
    
    
    
    private void visaMinaProjekt(){
        
        ArrayList<HashMap<String, String>> minaProjekt = projekt.getMinaProjekt(anstalldID);
        
        StringBuilder sb = new StringBuilder();
        String header = String.format("%-10s %-20s %-35s %-15s %-15s %-10s %-20s %-15s %-15s %-15s\n", "ProjektID", "Namn", "Beskrivning", "Startdatum", "Slutdatum", "Kostnad", "Status", "Prioritet", "Projektchef", "Land");
        sb.append(header);
        sb.append("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        
        for (HashMap<String, String> projekt1 : minaProjekt) {
            String row = String.format("%-10s %-20s %-35s %-15s %-15s %-10s %-20s %-15s %-15s %-15s\n", projekt1.get("pid"), projekt1.get("projektnamn"), projekt1.get("beskrivning"), projekt1.get("startdatum"), projekt1.get("slutdatum"), projekt1.get("kostnad"), projekt1.get("status"), projekt1.get("prioritet"), projekt1.get("projektchef"), projekt1.get("land"));
            sb.append(row);
        }
        
        taProjekt.setText(sb.toString());
    }
    
    private void visaProjektAvdelning(){
        
        ArrayList<HashMap<String, String>> allaProjektAvd = projekt.getAllprojectsFromAvdelning(minAvdelning);
        
        StringBuilder sb = new StringBuilder();
        String header = String.format("%-10s %-20s %-35s %-15s %-15s %-10s %-20s %-15s %-15s %-15s\n", "ProjektID", "Namn", "Beskrivning", "Startdatum", "Slutdatum", "Kostnad", "Status", "Prioritet", "Projektchef", "Land");
        sb.append(header);
        sb.append("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        
        for (HashMap<String, String> projekt1 : allaProjektAvd) {
            String row = String.format("%-10s %-20s %-35s %-15s %-15s %-10s %-20s %-15s %-15s %-15s\n", projekt1.get("pid"), projekt1.get("projektnamn"), projekt1.get("beskrivning"), projekt1.get("startdatum"), projekt1.get("slutdatum"), projekt1.get("kostnad"), projekt1.get("status"), projekt1.get("prioritet"), projekt1.get("projektchef"), projekt1.get("land"));
            sb.append(row);
        }
        
        taProjekt.setText(sb.toString());
    }
    
    private void visaAllaProjekt(){
        ArrayList<HashMap<String, String>> projektlista = projekt.getAllaProjekt();
        
        StringBuilder sb = new StringBuilder();
        String header = String.format("%-10s %-20s %-35s %-15s %-15s %-10s %-20s %-15s %-15s %-15s\n", "ProjektID", "Namn", "Beskrivning", "Startdatum", "Slutdatum", "Kostnad", "Status", "Prioritet", "Projektchef", "Land");
        sb.append(header);
        sb.append("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        
        for (HashMap<String, String> projekt1 : projektlista) {
            String row = String.format("%-10s %-20s %-35s %-15s %-15s %-10s %-20s %-15s %-15s %-15s\n", projekt1.get("pid"), projekt1.get("projektnamn"), projekt1.get("beskrivning"), projekt1.get("startdatum"), projekt1.get("slutdatum"), projekt1.get("kostnad"), projekt1.get("status"), projekt1.get("prioritet"), projekt1.get("projektchef"), projekt1.get("land"));
            sb.append(row);
        }
        
        taProjekt.setText(sb.toString());
    }
    
    private void visaStatus(){
        
        ArrayList<HashMap<String, String>> projektlista = projekt.getAllprojectsFromAvdelning(minAvdelning);
        String status = (String) cbStatus.getSelectedItem();
        
        StringBuilder sb = new StringBuilder();
        String header = String.format("%-10s %-20s %-35s %-15s %-15s %-10s %-20s %-15s %-15s %-15s\n", "ProjektID", "Namn", "Beskrivning", "Startdatum", "Slutdatum", "Kostnad", "Status", "Prioritet", "Projektchef", "Land");
        sb.append(header);
        sb.append("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        
        for(HashMap<String, String> projekt1 : projektlista) {
             String statusP = projekt1.get("status");
            if(statusP.equalsIgnoreCase(status)){
                String row = String.format("%-10s %-20s %-35s %-15s %-15s %-10s %-20s %-15s %-15s %-15s\n", projekt1.get("pid"), projekt1.get("projektnamn"), projekt1.get("beskrivning"), projekt1.get("startdatum"), projekt1.get("slutdatum"), projekt1.get("kostnad"), projekt1.get("status"), projekt1.get("prioritet"), projekt1.get("projektchef"), projekt1.get("land"));
                sb.append(row);
            }
        }
        
        taProjekt.setText(sb.toString());
    }


    private void getPartnersMinaProjekt(){
        
        ArrayList<HashMap<String, String>> minaProjekt = projekt.getMinaProjekt(anstalldID);
                
        StringBuilder sb = new StringBuilder();
        String header = String.format("%-20s %-15s %-30s %-25s %-25s %-25s %-30s %-20s %-15s\n", "Projekt", "PartnerID", "Namn", "Kontaktperson", "Kontakt Epost", "Telefon", "Adress", "Branch", "Stad");
        sb.append(header);
        sb.append("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        
        for(HashMap<String, String> projekt1 : minaProjekt){
            //Ska jag ta bort att jag skriver ut namn i textArean?
            String namn = projekt1.get("projektnamn");
            String projektID = projekt1.get("pid");
            
            String partnerID = partner.getPartnerIdFromProjektId(projektID);
            HashMap<String, String> partner1 = partner.getPartner(partnerID);
             
            Stad stad = new Stad(idb, anstalldID);
            String staden = stad.getStad(partner1.get("stad"));
            String row = String.format("%-20s %-15s %-30s %-25s %-25s %-25s %-30s %-20s %-15s\n", namn, partner1.get("pid"), partner1.get("namn"), partner1.get("kontaktperson"), partner1.get("kontaktepost"), partner1.get("telefon"), partner1.get("adress"), partner1.get("branch"), staden);
            sb.append(row); 
            
        }
    }

    private void visaKostnadStatistik(){

        ArrayList<String> chefer = projekt.getProjektChefer();
        double kostnad = 0;

        for(String chef : chefer){
            if(chef.equals(anstalldID)){
                kostnad = projekt.getProjektKostnadStatistik(chef);
            }
        }
        int kostnadInt = (int) kostnad;
        String kostnadString = Integer.toString(kostnadInt);
        taProjekt.setText("Totala kostnaden för dina projekt är: " + kostnadString + "kr.");
    }


    /*private void visaDatum(){

        String avdelning = projekt.getMinAvdelning(anstalldID);
        ArrayList<HashMap<String, String>> projektDatum = projekt.getProjectsByDate(avdelning);
        
        String datum = 
        
        StringBuilder sb = new StringBuilder();
        String header = String.format("%-10s %-20s %-35s %-15s %-15s %-10s %-20s %-15s %-15s %-15s\n", "ProjektID", "Namn", "Beskrivning", "Startdatum", "Slutdatum", "Kostnad", "Status", "Prioritet", "Projektchef", "Land");
        sb.append(header);
        sb.append("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        
        for(HashMap<String, String> projekt1 : projektDatum){
            String startdatum = projekt1.get("startdatum");
            if(datum >= startdatum ){
                for (HashMap<String, String> projekt2 : projektDatum) {
                String row = String.format("%-10s %-20s %-35s %-15s %-15s %-10s %-20s %-15s %-15s %-15s\n", projekt2.get("pid"), projekt2.get("projektnamn"), projekt2.get("beskrivning"), projekt2.get("startdatum"), projekt2.get("slutdatum"), projekt2.get("kostnad"), projekt2.get("status"), projekt2.get("prioritet"), projekt2.get("projektchef"), projekt2.get("land"));
                sb.append(row);
                }
            }
        }
        taProjekt.setText(sb.toString());
    }*/
    
    private void fyllDatumC(){
        
        ArrayList<HashMap<String, String>> projektlista = projekt.getAllprojectsFromAvdelning(minAvdelning);
          
        for(HashMap<String, String> projektD : projektlista){
            String datum = projektD.get("startdatum");
            String status = projektD.get("status");
            
            if(status.equals("Pågående")){
                cbDatumFrom.addItem(datum);
                cbDatumTill.addItem(datum);
            }
        }
    }   
        
    private void sokDatum(){
        
        ArrayList<HashMap<String, String>> projektlista = projekt.getAllprojectsFromAvdelning(minAvdelning);
        String startDatum = (String) cbDatumFrom.getSelectedItem();
        String slutDatum = (String) cbDatumTill.getSelectedItem();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startdatum = LocalDate.parse(startDatum, formatter);
        LocalDate slutdatum = LocalDate.parse(slutDatum, formatter);
        
        StringBuilder sb = new StringBuilder();
        String header = String.format("%-10s %-20s %-35s %-15s %-15s %-10s %-20s %-15s %-15s %-15s\n", "ProjektID", "Namn", "Beskrivning", "Startdatum", "Slutdatum", "Kostnad", "Status", "Prioritet", "Projektchef", "Land");
        sb.append(header);
        sb.append("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        
        for(HashMap<String, String> projektD : projektlista){
            
            String status = projektD.get("status");
            String datumS = projektD.get("startdatum");
            LocalDate datum = LocalDate.parse(datumS, formatter);
            
            if(status.equals("Pågående")){
                if((datum.isEqual(startdatum) || datum.isAfter(startdatum)) && (datum.isEqual(slutdatum) || datum.isBefore(slutdatum))){
                    String row = String.format("%-10s %-20s %-35s %-15s %-15s %-10s %-20s %-15s %-15s %-15s\n", projektD.get("pid"), projektD.get("projektnamn"), projektD.get("beskrivning"), projektD.get("startdatum"), projektD.get("slutdatum"), projektD.get("kostnad"), projektD.get("status"), projektD.get("prioritet"), projektD.get("projektchef"), projektD.get("land"));
                    sb.append(row);
                }
            }
        }
        taProjekt.setText(sb.toString());
    }
    
    public java.util.Date setDatum(String datum){
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        try {
            date = formatter.parse(datum);
            System.out.println("Angivet datum: " + formatter.format(date));
        } catch (ParseException ex) {
            lblFelmeddelande.setText("Felaktigt datumformat. Använd formatet: yyyy-MM-dd");
        }
        return date;
    }
    
    private void redigeraProjekt(Date startdatum, Date slutdatum){
                
        String projektnamn = tfProjektNamn.getText();
        String pid = projekt.getProjektID(projektnamn);
        String beskrivning = tfProjektBeskrivning.getText();
        int kostnadInt = Integer.parseInt(tfProjektKostnad.getText());
        double kostnad = kostnadInt;
        String status = tfProjektStatus.getText();
        String prioritet = tfProjektPrioritet.getText();
        String projektchef = tfProjektProjektChef.getText();
        String land = tfProjektLand.getText();
        
        projekt.changeProjekt(pid, projektnamn, beskrivning, startdatum, slutdatum, kostnad, status, prioritet, projektchef, land);
        
    }
    
    private void addProjekt(Date startdatum, Date slutdatum){
        
        String projektnamn = tfProjektNamn.getText();
        String pid = projekt.getProjektID(projektnamn);
        String beskrivning = tfProjektBeskrivning.getText();
        int kostnadInt = Integer.parseInt(tfProjektKostnad.getText());
        double kostnad = kostnadInt;
        String status = tfProjektStatus.getText();
        String prioritet = tfProjektPrioritet.getText();
        String projektchef = tfProjektProjektChef.getText();
        String land = tfProjektLand.getText();
        
        projekt.addProjekt(pid, projektnamn, beskrivning, startdatum, slutdatum, kostnad, status, prioritet, projektchef, land);
    }
    
   
    
public void fyllCbAllaProjekt(){
        
        ArrayList<HashMap<String, String>> projektlista = projekt.getAllaProjekt();
        
        for(HashMap<String, String> projekt1 : projektlista){
        String namn = projekt1.get("namn");
        cbProjekt.addItem(namn);
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

        btnMinaProjekt = new javax.swing.JButton();
        btnProjektPaMinAvdelning = new javax.swing.JButton();
        btnAllaProjekt = new javax.swing.JButton();
        lblTaRubrik = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taProjekt = new javax.swing.JTextArea();
        btnPartnersIProjekt = new javax.swing.JButton();
        btnSokHandlaggareIProjekt = new javax.swing.JButton();
        btnKostnadsStatistik = new javax.swing.JButton();
        btnDatum = new javax.swing.JButton();
        btnStatusFiltrera = new javax.swing.JButton();
        lblDatum = new javax.swing.JLabel();
        btnSokDatum = new javax.swing.JButton();
        lblProjekt = new javax.swing.JLabel();
        btnAddProjekt = new javax.swing.JButton();
        btnDeleteProjekt = new javax.swing.JButton();
        btnAndraProjekt = new javax.swing.JButton();
        lblProjektNamn = new javax.swing.JLabel();
        tfProjektNamn = new javax.swing.JTextField();
        lblProjektBeskrivning = new javax.swing.JLabel();
        tfProjektBeskrivning = new javax.swing.JTextField();
        lblProjektStartDatum = new javax.swing.JLabel();
        tfProjektStartdatum = new javax.swing.JTextField();
        lblProjektSlutDatum = new javax.swing.JLabel();
        tfProjektSlutdatum = new javax.swing.JTextField();
        lblProjektKostnad = new javax.swing.JLabel();
        lblProjektStatus = new javax.swing.JLabel();
        tfProjektKostnad = new javax.swing.JTextField();
        tfProjektStatus = new javax.swing.JTextField();
        lblProjektPrioritet = new javax.swing.JLabel();
        tfProjektPrioritet = new javax.swing.JTextField();
        lblProjektProjektChef = new javax.swing.JLabel();
        tfProjektProjektChef = new javax.swing.JTextField();
        lblProjektLand = new javax.swing.JLabel();
        tfProjektLand = new javax.swing.JTextField();
        btnTillbaka = new javax.swing.JButton();
        cbProjekt = new javax.swing.JComboBox<>();
        btnRedigeraProjekt = new javax.swing.JButton();
        lblFelmeddelande = new javax.swing.JLabel();
        btnStatus = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();
        cbStatus = new javax.swing.JComboBox<>();
        lblDatumFrom = new javax.swing.JLabel();
        lblDatumTill = new javax.swing.JLabel();
        cbDatumFrom = new javax.swing.JComboBox<>();
        cbDatumTill = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnMinaProjekt.setText("Mina Projekt");
        btnMinaProjekt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinaProjektActionPerformed(evt);
            }
        });

        btnProjektPaMinAvdelning.setText("Projekt På Min Avdelning");
        btnProjektPaMinAvdelning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProjektPaMinAvdelningActionPerformed(evt);
            }
        });

        btnAllaProjekt.setText("Alla Projekt");
        btnAllaProjekt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllaProjektActionPerformed(evt);
            }
        });

        lblTaRubrik.setText("lblTaRubrik");

        taProjekt.setColumns(20);
        taProjekt.setRows(5);
        jScrollPane1.setViewportView(taProjekt);

        btnPartnersIProjekt.setText("Partners I Projekt");
        btnPartnersIProjekt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPartnersIProjektActionPerformed(evt);
            }
        });

        btnSokHandlaggareIProjekt.setText("Handläggare I Projekt");
        btnSokHandlaggareIProjekt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSokHandlaggareIProjektActionPerformed(evt);
            }
        });

        btnKostnadsStatistik.setText("Statistik Kostnad");
        btnKostnadsStatistik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKostnadsStatistikActionPerformed(evt);
            }
        });

        btnDatum.setText("Sök med Datum");
        btnDatum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatumActionPerformed(evt);
            }
        });

        btnStatusFiltrera.setText("Filtrera genom Status");
        btnStatusFiltrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatusFiltreraActionPerformed(evt);
            }
        });

        lblDatum.setText("Sök Datum");

        btnSokDatum.setText("Sök");
        btnSokDatum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSokDatumActionPerformed(evt);
            }
        });

        lblProjekt.setText("Projekt");

        btnAddProjekt.setText("Lägg Till");
        btnAddProjekt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProjektActionPerformed(evt);
            }
        });

        btnDeleteProjekt.setText("Ta Bort");
        btnDeleteProjekt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteProjektActionPerformed(evt);
            }
        });

        btnAndraProjekt.setText("Ändra");
        btnAndraProjekt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAndraProjektActionPerformed(evt);
            }
        });

        lblProjektNamn.setText("Namn");

        lblProjektBeskrivning.setText("Beskrivning");

        lblProjektStartDatum.setText("StartDatum");

        lblProjektSlutDatum.setText("SlutDatum");

        lblProjektKostnad.setText("Kostnad");

        lblProjektStatus.setText("Status");

        lblProjektPrioritet.setText("Prioritet");

        lblProjektProjektChef.setText("Projekt Chef");

        lblProjektLand.setText("Land");

        btnTillbaka.setText("Tillbaka");
        btnTillbaka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaActionPerformed(evt);
            }
        });

        cbProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj Projekt" }));

        btnRedigeraProjekt.setText("Ändra Projekt");
        btnRedigeraProjekt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedigeraProjektActionPerformed(evt);
            }
        });

        lblFelmeddelande.setText("Felmeddelande");

        btnStatus.setText("Filtrera");
        btnStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatusActionPerformed(evt);
            }
        });

        lblStatus.setText("Status");

        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj Status", "Planerat", "Pågående", "Avslutat" }));

        lblDatumFrom.setText("Från");

        lblDatumTill.setText("Till");

        cbDatumFrom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj ett Datum" }));

        cbDatumTill.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj ett Datum" }));

        jLabel1.setText("Fixa ändra");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTaRubrik)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTillbaka))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnMinaProjekt)
                            .addComponent(btnProjektPaMinAvdelning)
                            .addComponent(btnPartnersIProjekt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnStatusFiltrera)
                            .addComponent(btnDatum)
                            .addComponent(btnKostnadsStatistik))
                        .addGap(136, 136, 136)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSokHandlaggareIProjekt)
                            .addComponent(btnAllaProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRedigeraProjekt)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblDatum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnStatus)
                                .addGap(224, 224, 224))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDatumFrom)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbDatumFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblDatumTill)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(cbDatumTill, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnSokDatum)))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFelmeddelande)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addComponent(lblStatus)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblProjekt)
                                        .addGap(68, 68, 68)
                                        .addComponent(cbProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblProjektNamn)
                                        .addGap(72, 72, 72)
                                        .addComponent(tfProjektNamn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(80, 80, 80)
                                .addComponent(btnAddProjekt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDeleteProjekt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAndraProjekt))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblProjektSlutDatum)
                                    .addComponent(lblProjektKostnad)
                                    .addComponent(lblProjektStatus)
                                    .addComponent(lblProjektStartDatum)
                                    .addComponent(lblProjektBeskrivning)
                                    .addComponent(lblProjektPrioritet)
                                    .addComponent(lblProjektProjektChef)
                                    .addComponent(lblProjektLand))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfProjektBeskrivning, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                    .addComponent(tfProjektStartdatum)
                                    .addComponent(tfProjektLand)
                                    .addComponent(tfProjektSlutdatum)
                                    .addComponent(tfProjektKostnad)
                                    .addComponent(tfProjektStatus)
                                    .addComponent(tfProjektPrioritet)
                                    .addComponent(tfProjektProjektChef))
                                .addGap(65, 65, 65)
                                .addComponent(jLabel1)))
                        .addGap(0, 100, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMinaProjekt)
                    .addComponent(btnAllaProjekt)
                    .addComponent(btnKostnadsStatistik))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProjektPaMinAvdelning)
                    .addComponent(btnRedigeraProjekt)
                    .addComponent(btnDatum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStatusFiltrera)
                    .addComponent(btnPartnersIProjekt)
                    .addComponent(btnSokHandlaggareIProjekt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTaRubrik, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStatus)
                    .addComponent(lblStatus)
                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(183, 183, 183)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDatumFrom)
                    .addComponent(lblDatumTill))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDatum)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSokDatum)
                        .addComponent(cbDatumFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbDatumTill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(146, 146, 146)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProjekt)
                    .addComponent(cbProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddProjekt)
                    .addComponent(btnDeleteProjekt)
                    .addComponent(btnAndraProjekt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProjektNamn)
                    .addComponent(tfProjektNamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblProjektBeskrivning)
                            .addComponent(tfProjektBeskrivning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProjektStartDatum)
                    .addComponent(tfProjektStartdatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProjektSlutDatum)
                    .addComponent(tfProjektSlutdatum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProjektKostnad)
                    .addComponent(tfProjektKostnad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProjektStatus)
                    .addComponent(tfProjektStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfProjektPrioritet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProjektPrioritet))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfProjektProjektChef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProjektProjektChef))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfProjektLand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProjektLand))
                .addGap(32, 32, 32)
                .addComponent(lblFelmeddelande)
                .addGap(1, 1, 1)
                .addComponent(btnTillbaka)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMinaProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinaProjektActionPerformed
        visaMinaProjekt();
    }//GEN-LAST:event_btnMinaProjektActionPerformed

    private void btnProjektPaMinAvdelningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProjektPaMinAvdelningActionPerformed
        visaProjektAvdelning();
    }//GEN-LAST:event_btnProjektPaMinAvdelningActionPerformed

    private void btnRedigeraProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedigeraProjektActionPerformed
        //setSynlighet("",true);
    }//GEN-LAST:event_btnRedigeraProjektActionPerformed

    private void btnAndraProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAndraProjektActionPerformed
        
        String startdatumString = tfProjektStartdatum.getText();
        String slutdatumString = tfProjektSlutdatum.getText();
        
        java.util.Date startdatumUtil = setDatum(startdatumString);
        java.util.Date slutdatumUtil = setDatum(slutdatumString);
        
        java.sql.Date startdatum = new java.sql.Date(startdatumUtil.getTime());
        java.sql.Date slutdatum = new java.sql.Date(slutdatumUtil.getTime());
        
        redigeraProjekt(startdatum, slutdatum);
        
    }//GEN-LAST:event_btnAndraProjektActionPerformed

    private void btnDeleteProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteProjektActionPerformed
        
        String projektNamn = (String) cbProjekt.getSelectedItem();
        String projektID = projekt.getProjektID(projektNamn);
        projekt.removeProjekt(projektID);
        
    }//GEN-LAST:event_btnDeleteProjektActionPerformed

    private void btnAddProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProjektActionPerformed
        
        String startdatumString = tfProjektStartdatum.getText();
        String slutdatumString = tfProjektSlutdatum.getText();
        
        java.util.Date startdatumUtil = setDatum(startdatumString);
        java.util.Date slutdatumUtil = setDatum(slutdatumString);
        
        java.sql.Date startdatum = new java.sql.Date(startdatumUtil.getTime());
        java.sql.Date slutdatum = new java.sql.Date(slutdatumUtil.getTime());
        
        addProjekt(startdatum, slutdatum);
        
    }//GEN-LAST:event_btnAddProjektActionPerformed

    private void btnSokDatumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSokDatumActionPerformed
        
        sokDatum();
        
    }//GEN-LAST:event_btnSokDatumActionPerformed

    private void btnKostnadsStatistikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKostnadsStatistikActionPerformed
        
        visaKostnadStatistik();

    }//GEN-LAST:event_btnKostnadsStatistikActionPerformed

    private void btnAllaProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllaProjektActionPerformed
        
        visaAllaProjekt();

    }//GEN-LAST:event_btnAllaProjektActionPerformed

    private void btnStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatusActionPerformed
        
        if(cbStatus.getSelectedItem() == "Välj Status"){
            lblFelmeddelande.setVisible(true);
            lblFelmeddelande.setText("Välj en Status");
        }else{
            visaStatus();
        }
    
    }//GEN-LAST:event_btnStatusActionPerformed

    private void btnPartnersIProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPartnersIProjektActionPerformed
        
    }//GEN-LAST:event_btnPartnersIProjektActionPerformed

    private void btnStatusFiltreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatusFiltreraActionPerformed
        
    }//GEN-LAST:event_btnStatusFiltreraActionPerformed

    private void btnSokHandlaggareIProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSokHandlaggareIProjektActionPerformed
        
    }//GEN-LAST:event_btnSokHandlaggareIProjektActionPerformed

    private void btnDatumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatumActionPerformed
        
        fyllDatumC();
        
    }//GEN-LAST:event_btnDatumActionPerformed

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
            java.util.logging.Logger.getLogger(Projekt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Projekt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Projekt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Projekt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new Projekt().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddProjekt;
    private javax.swing.JButton btnAllaProjekt;
    private javax.swing.JButton btnAndraProjekt;
    private javax.swing.JButton btnDatum;
    private javax.swing.JButton btnDeleteProjekt;
    private javax.swing.JButton btnKostnadsStatistik;
    private javax.swing.JButton btnMinaProjekt;
    private javax.swing.JButton btnPartnersIProjekt;
    private javax.swing.JButton btnProjektPaMinAvdelning;
    private javax.swing.JButton btnRedigeraProjekt;
    private javax.swing.JButton btnSokDatum;
    private javax.swing.JButton btnSokHandlaggareIProjekt;
    private javax.swing.JButton btnStatus;
    private javax.swing.JButton btnStatusFiltrera;
    private javax.swing.JButton btnTillbaka;
    private javax.swing.JComboBox<String> cbDatumFrom;
    private javax.swing.JComboBox<String> cbDatumTill;
    private javax.swing.JComboBox<String> cbProjekt;
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDatum;
    private javax.swing.JLabel lblDatumFrom;
    private javax.swing.JLabel lblDatumTill;
    private javax.swing.JLabel lblFelmeddelande;
    private javax.swing.JLabel lblProjekt;
    private javax.swing.JLabel lblProjektBeskrivning;
    private javax.swing.JLabel lblProjektKostnad;
    private javax.swing.JLabel lblProjektLand;
    private javax.swing.JLabel lblProjektNamn;
    private javax.swing.JLabel lblProjektPrioritet;
    private javax.swing.JLabel lblProjektProjektChef;
    private javax.swing.JLabel lblProjektSlutDatum;
    private javax.swing.JLabel lblProjektStartDatum;
    private javax.swing.JLabel lblProjektStatus;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTaRubrik;
    private javax.swing.JTextArea taProjekt;
    private javax.swing.JTextField tfProjektBeskrivning;
    private javax.swing.JTextField tfProjektKostnad;
    private javax.swing.JTextField tfProjektLand;
    private javax.swing.JTextField tfProjektNamn;
    private javax.swing.JTextField tfProjektPrioritet;
    private javax.swing.JTextField tfProjektProjektChef;
    private javax.swing.JTextField tfProjektSlutdatum;
    private javax.swing.JTextField tfProjektStartdatum;
    private javax.swing.JTextField tfProjektStatus;
    // End of variables declaration//GEN-END:variables
}
