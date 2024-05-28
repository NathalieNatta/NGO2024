package ngo2024;

import java.util.ArrayList;
import java.util.HashMap;
import oru.inf.InfDB;
import oru.inf.InfException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Israael, JillWithJ, Natha
 */
public class PartnersMeny extends javax.swing.JFrame {
private String anstalldID;
private InfDB idb;
private Projekt projekt;
private Anstallning anstalld;
private Partner partner;
private Stad stad;
    
    public PartnersMeny(InfDB idb, String aid) {
        this.anstalldID = aid;
        this.idb = idb;
        projekt = new Projekt(idb, anstalldID);
        anstalld = new Anstallning(idb, anstalldID);
        partner = new Partner(idb, anstalldID);
        stad = new Stad(idb, anstalldID);
        
        initComponents();
        taPartners.setEditable(false);
        cbProjekt.setEditable(false);
        cbPartner.setEditable(false);
        cbProjektPartner.setEditable(false);
        fyllCbProjekt();
        fyllCbPartner();
        setSynlighet("alla", false);
    }
    
    private void getPartnersMinaProjekt(){
        
        ArrayList<HashMap<String, String>> minaProjekt = projekt.getMinaProjekt(anstalldID);
        
        String cbText = (String) cbProjekt.getSelectedItem();
        
        StringBuilder sb = new StringBuilder();
        String header = String.format("%-20s %-15s %-30s %-25s %-25s %-25s %-30s %-20s %-15s\n", "Projekt", "PartnerID", "Namn", "Kontaktperson", "Kontakt Epost", "Telefon", "Adress", "Branch", "Stad");
        sb.append(header);
        sb.append("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        
        for(HashMap<String, String> projekt1 : minaProjekt){
            //Ska jag ta bort att jag skriver ut namn i textArean?
            String namn = projekt1.get("projektnamn");
            String projektID = projekt1.get("pid");
            
            if(namn.equals(cbText)){
                String partnerID = partner.getPartnerIdFromProjektId(projektID);
                HashMap<String, String> partner1 = partner.getPartner(partnerID);
                
                String staden = stad.getStad(partner1.get("stad"));
                String row = String.format("%-20s %-15s %-30s %-25s %-25s %-25s %-30s %-20s %-15s\n", namn, partner1.get("pid"), partner1.get("namn"), partner1.get("kontaktperson"), partner1.get("kontaktepost"), partner1.get("telefon"), partner1.get("adress"), partner1.get("branch"), staden);
                sb.append(row);
                
            }
        }
        
        taPartners.setText(sb.toString());
    }
    
    private void visaPartner(){
        
        String namn = (String) cbPartner.getSelectedItem();
        String partnerID = partner.getPartnerID(namn);
        HashMap<String, String> partner1 = partner.getPartner(partnerID);
        
        tfNamn.setText(partner1.get("namn"));
        tfKontaktPerson.setText(partner1.get("kontaktperson"));
        tfKontaktEpost.setText(partner1.get("kontaktepost"));
        tfTelefon.setText(partner1.get("telefon"));
        tfAdress.setText(partner1.get("adress"));
        tfBranch.setText(partner1.get("branch"));
        
        String staden = stad.getStad(partner1.get("stad"));
        tfStad.setText(staden);  
    }
    
    private void setKunnaSkriva(boolean skriva){
        
        tfNamn.setEditable(skriva);
        tfKontaktPerson.setEditable(skriva);
        tfKontaktEpost.setEditable(skriva);
        tfTelefon.setEditable(skriva);
        tfAdress.setEditable(skriva);
        tfBranch.setEditable(skriva);
        tfStad.setEditable(skriva);
    }
    
    private void addPartner(){
        
        String pid = partner.setPartnerID();
        
        String namn = tfNamn.getText();
        String kontaktperson = tfKontaktPerson.getText();
        String kontaktepost = tfKontaktEpost.getText();
        String telefon = tfTelefon.getText();
        String adress = tfAdress.getText();
        String branch = tfBranch.getText();
        
//Här behövs koll att stad finns
        
        String stadNamn = tfStad.getText();
        String sid = stad.getStadID(stadNamn);
        
        partner.addPartner(pid, namn, kontaktperson, kontaktepost, telefon, adress, branch, sid);
        
    }
    
    private void changePartner(){
        
        String namn = tfNyttNamn.getText();
        String kontaktperson = tfNyKontaktperson.getText();
        String kontaktepost = tfNyKontaktEpost.getText();
        String branch = tfNyBranch.getText();
        String telefon = tfNyTelefon.getText();
        
        String pid = partner.getPartnerID(namn);
        
        String adressNr = tfNyAdressNr.getText();
        String adressGata = tfNyAdressGata.getText();
        String adressStad = tfNyAdressStad.getText();
        String adress = adressNr + " " + adressGata + ", " + adressStad;
        
//Här behövs kollas om stad finns

        String stadNamn = tfNyStad.getText();
        String sid = stad.getStadID(stadNamn);
        
        partner.changePartner(pid, namn, kontaktperson, kontaktepost, telefon, adress, branch, sid);
    }
    
    private void deletePartner(){
        
//Här behövs "är du säker?"
        String namn = (String) cbPartner.getSelectedItem();
        String partnerID = partner.getPartnerID(namn);
        partner.removePartner(partnerID);
        
    }
    
    private void addPartneriProjekt(){
        
//Här behövs koll så att de inte redan sitter ihop
        
        String partnerNamn = (String) cbPartner.getSelectedItem();
        String projektNamn = (String) cbProjektPartner.getSelectedItem();
        
        String partnerID = partner.getPartnerID(partnerNamn);
        String projektID = projekt.getProjektID(projektNamn);
        
        partner.addPartneriProjekt(projektID, partnerID);
        
    }
    
    private void deletePartneriProjekt(){
        
//Här behövs koll så att de sitter ihop innan 
        
        String partnerNamn = (String) cbPartner.getSelectedItem();
        String projektNamn = (String) cbProjektPartner.getSelectedItem();
        
        String partnerID = partner.getPartnerID(partnerNamn);
        String projektID = projekt.getProjektID(projektNamn);
        
        partner.deletePartneriProjekt(projektID, partnerID);
        
    }
    
    public void fyllCbProjekt(){
        
        if(anstalld.getAdmin(anstalldID)){
            ArrayList<HashMap<String, String>> allaProjekt = projekt.getAllaProjekt();
            for(HashMap<String, String> projekt2 : allaProjekt){
                String namn = projekt2.get("projektnamn");
                cbProjekt.addItem(namn);  
                cbProjektPartner.addItem(namn);      
            }
        }else{
            ArrayList<HashMap<String, String>> minaProjekt = projekt.getMinaProjekt(anstalldID);
            for(HashMap<String, String> projekt1 : minaProjekt){
                String namn = projekt1.get("projektnamn");
                cbProjekt.addItem(namn);    
                cbProjektPartner.addItem(namn);
            }
        } 
    }
    
    public void fyllCbPartner(){
        
        ArrayList<HashMap<String, String>> partners = partner.getAllPartners();
        
        for(HashMap<String, String> partner1 : partners){
            String namn = partner1.get("namn");
            cbPartner.addItem(namn);
        }
    }
    
    private void setSynlighet(String vilken, boolean synlighet){
        
        if(vilken.equals("add")){
            btnAdd.setVisible(synlighet);
            
            lblAddPartner.setText("Lägg Till en Ny Partner");
            
            lblAddPartner.setVisible(synlighet);
            lblNyAdress.setVisible(synlighet);
            lblNyAdressNr.setVisible(synlighet);
            lblNyAdressGata.setVisible(synlighet);
            lblNyAdressStad.setVisible(synlighet);
            lblNyBranch.setVisible(synlighet);
            lblNyKontaktEpost.setVisible(synlighet);
            lblNyKontaktperson.setVisible(synlighet);
            lblNyStad.setVisible(synlighet);
            lblNyTelefon.setVisible(synlighet);
            lblNyttNamn.setVisible(synlighet);
            
            tfNyAdressNr.setVisible(synlighet);
            tfNyAdressGata.setVisible(synlighet);
            tfNyAdressStad.setVisible(synlighet);
            tfNyBranch.setVisible(synlighet);
            tfNyKontaktEpost.setVisible(synlighet);
            tfNyKontaktperson.setVisible(synlighet);
            tfNyStad.setVisible(synlighet);
            tfNyTelefon.setVisible(synlighet);
            tfNyttNamn.setVisible(synlighet);
            
        }
        if(vilken.equals("change")){
            btnChange.setVisible(synlighet);
            btnOK.setVisible(synlighet);
            
            cbPartner.setVisible(synlighet);
            
            lblAddPartner.setText("Ändra en partner");
            
            lblAddPartner.setVisible(synlighet);
            lblAdress.setVisible(synlighet);
            lblBranch.setVisible(synlighet);
            lblKontaktEpost.setVisible(synlighet);
            lblKontaktPerson.setVisible(synlighet);
            lblNamn.setVisible(synlighet);
            lblStad.setVisible(synlighet);
            lblTelefon.setVisible(synlighet);
            
            tfAdress.setVisible(synlighet);
            tfBranch.setVisible(synlighet);
            tfKontaktEpost.setVisible(synlighet);
            tfKontaktPerson.setVisible(synlighet);
            tfNamn.setVisible(synlighet);
            tfStad.setVisible(synlighet);
            tfTelefon.setVisible(synlighet);
            
        }
        if(vilken.equals("delete")){
            btnDelete.setVisible(synlighet);
            btnOK.setVisible(synlighet);
            
            cbPartner.setVisible(synlighet);
            
            lblAddPartner.setText("Ta bort en partner");
            
            lblAddPartner.setVisible(synlighet);
            lblAdress.setVisible(synlighet);
            lblBranch.setVisible(synlighet);
            lblKontaktEpost.setVisible(synlighet);
            lblKontaktPerson.setVisible(synlighet);
            lblNamn.setVisible(synlighet);
            lblStad.setVisible(synlighet);
            lblTelefon.setVisible(synlighet);
            
            tfAdress.setVisible(synlighet);
            tfBranch.setVisible(synlighet);
            tfKontaktEpost.setVisible(synlighet);
            tfKontaktPerson.setVisible(synlighet);
            tfNamn.setVisible(synlighet);
            tfStad.setVisible(synlighet);
            tfTelefon.setVisible(synlighet);            
        }
        if(vilken.equals("addDeletePiP")){
            btnAddPiP.setVisible(synlighet);
            btnDeletePiP.setVisible(synlighet);
            
            cbProjektPartner.setVisible(synlighet);
            cbPartner.setVisible(synlighet);
            
            lblAddPartner.setText("Lägg Till/Ta Bort en Partner i ett Projekt");
            lblAddPartner.setVisible(synlighet);
        }
        if(vilken.equals("alla")){
            btnAdd.setVisible(synlighet);
            btnChange.setVisible(synlighet);
            btnChange2.setVisible(synlighet);
            btnDelete.setVisible(synlighet);
            btnOK.setVisible(synlighet);
            btnAddPiP.setVisible(synlighet);
            btnDeletePiP.setVisible(synlighet);
            
            lblAddPartner.setVisible(synlighet);
            lblAdress.setVisible(synlighet);
            lblBranch.setVisible(synlighet);
            lblKontaktEpost.setVisible(synlighet);
            lblKontaktPerson.setVisible(synlighet);
            lblNamn.setVisible(synlighet);
            lblStad.setVisible(synlighet);
            lblTelefon.setVisible(synlighet);
            lblNyAdress.setVisible(synlighet);
            lblNyAdressNr.setVisible(synlighet);
            lblNyAdressGata.setVisible(synlighet);
            lblNyAdressStad.setVisible(synlighet);
            lblNyBranch.setVisible(synlighet);
            lblNyKontaktEpost.setVisible(synlighet);
            lblNyKontaktperson.setVisible(synlighet);
            lblNyStad.setVisible(synlighet);
            lblNyTelefon.setVisible(synlighet);
            lblNyttNamn.setVisible(synlighet);
            
            tfAdress.setVisible(synlighet);
            tfBranch.setVisible(synlighet);
            tfKontaktEpost.setVisible(synlighet);
            tfKontaktPerson.setVisible(synlighet);
            tfNamn.setVisible(synlighet);
            tfStad.setVisible(synlighet);
            tfTelefon.setVisible(synlighet);
            tfNyAdressNr.setVisible(synlighet);
            tfNyAdressGata.setVisible(synlighet);
            tfNyAdressStad.setVisible(synlighet);
            tfNyBranch.setVisible(synlighet);
            tfNyKontaktEpost.setVisible(synlighet);
            tfNyKontaktperson.setVisible(synlighet);
            tfNyStad.setVisible(synlighet);
            tfNyTelefon.setVisible(synlighet);
            tfNyttNamn.setVisible(synlighet);
            
            cbPartner.setVisible(synlighet);
            cbProjektPartner.setVisible(synlighet);
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

        lblPartners = new javax.swing.JLabel();
        cbProjekt = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        taPartners = new javax.swing.JTextArea();
        lblNamn = new javax.swing.JLabel();
        btnAddPartner = new javax.swing.JButton();
        lblKontaktPerson = new javax.swing.JLabel();
        tfKontaktPerson = new javax.swing.JTextField();
        btnChangePartner = new javax.swing.JButton();
        lblKontaktEpost = new javax.swing.JLabel();
        tfKontaktEpost = new javax.swing.JTextField();
        btnRemovePartner = new javax.swing.JButton();
        lblTelefon = new javax.swing.JLabel();
        tfTelefon = new javax.swing.JTextField();
        lblAdress = new javax.swing.JLabel();
        tfAdress = new javax.swing.JTextField();
        lblBranch = new javax.swing.JLabel();
        tfBranch = new javax.swing.JTextField();
        lblStad = new javax.swing.JLabel();
        tfStad = new javax.swing.JTextField();
        btnTillbaka = new javax.swing.JButton();
        btnVisaPartners = new javax.swing.JButton();
        lblFelmeddelande = new javax.swing.JLabel();
        cbPartner = new javax.swing.JComboBox<>();
        tfNamn = new javax.swing.JTextField();
        btnDelete = new javax.swing.JButton();
        btnChange = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        lblAddPartner = new javax.swing.JLabel();
        tfNyttNamn = new javax.swing.JTextField();
        tfNyKontaktperson = new javax.swing.JTextField();
        tfNyKontaktEpost = new javax.swing.JTextField();
        tfNyTelefon = new javax.swing.JTextField();
        tfNyAdressNr = new javax.swing.JTextField();
        tfNyBranch = new javax.swing.JTextField();
        tfNyStad = new javax.swing.JTextField();
        lblNyStad = new javax.swing.JLabel();
        lblNyBranch = new javax.swing.JLabel();
        lblNyAdress = new javax.swing.JLabel();
        lblNyTelefon = new javax.swing.JLabel();
        lblNyKontaktEpost = new javax.swing.JLabel();
        lblNyKontaktperson = new javax.swing.JLabel();
        lblNyttNamn = new javax.swing.JLabel();
        tfNyAdressGata = new javax.swing.JTextField();
        tfNyAdressStad = new javax.swing.JTextField();
        lblNyAdressNr = new javax.swing.JLabel();
        lblNyAdressGata = new javax.swing.JLabel();
        lblNyAdressStad = new javax.swing.JLabel();
        btnOK = new javax.swing.JButton();
        btnAddPartnerIProjekt = new javax.swing.JButton();
        cbProjektPartner = new javax.swing.JComboBox<>();
        btnAddPiP = new javax.swing.JButton();
        btnDeletePiP = new javax.swing.JButton();
        btnChange2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblPartners.setText("Partners");

        cbProjekt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj projekt" }));

        taPartners.setColumns(20);
        taPartners.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        taPartners.setRows(5);
        jScrollPane1.setViewportView(taPartners);

        lblNamn.setText("Namn");

        btnAddPartner.setText("Lägg till partner");
        btnAddPartner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPartnerActionPerformed(evt);
            }
        });

        lblKontaktPerson.setText("KontaktPerson");

        btnChangePartner.setText("Ändra partner");
        btnChangePartner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePartnerActionPerformed(evt);
            }
        });

        lblKontaktEpost.setText("KontaktEpost");

        btnRemovePartner.setText("Ta bort partner");
        btnRemovePartner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemovePartnerActionPerformed(evt);
            }
        });

        lblTelefon.setText("Telefon");

        lblAdress.setText("Adress");

        lblBranch.setText("Branch");

        lblStad.setText("Stad");

        btnTillbaka.setText("Tillbaka");
        btnTillbaka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaActionPerformed(evt);
            }
        });

        btnVisaPartners.setText("Visa Partners");
        btnVisaPartners.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisaPartnersActionPerformed(evt);
            }
        });

        lblFelmeddelande.setText("Felmeddelande");

        cbPartner.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj Partner" }));

        btnDelete.setText("Ta Bort");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnChange.setText("Ändra");
        btnChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeActionPerformed(evt);
            }
        });

        btnAdd.setText("Lägg Till");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        lblAddPartner.setText("jLabel1");

        lblNyStad.setText("Ny Stad");

        lblNyBranch.setText("Ny Branch");

        lblNyAdress.setText("Ny Adress");

        lblNyTelefon.setText("Nytt Telefonnummer");

        lblNyKontaktEpost.setText("Ny Kontakt-Epost");

        lblNyKontaktperson.setText("Ny Kontaktperson");

        lblNyttNamn.setText("Nytt Namn");

        lblNyAdressNr.setText("Nummer");

        lblNyAdressGata.setText("Gata");

        lblNyAdressStad.setText("Stad");

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        btnAddPartnerIProjekt.setText("Lägg Till/Ta Bort Partner i Projekt");
        btnAddPartnerIProjekt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPartnerIProjektActionPerformed(evt);
            }
        });

        cbProjektPartner.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj Projekt" }));

        btnAddPiP.setText("Lägg Till Partner i Projekt");
        btnAddPiP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPiPActionPerformed(evt);
            }
        });

        btnDeletePiP.setText("Ta Bort Partner i Projekt");
        btnDeletePiP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletePiPActionPerformed(evt);
            }
        });

        btnChange2.setText("Ändra");
        btnChange2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChange2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNamn)
                                .addGap(70, 70, 70)
                                .addComponent(tfNamn, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblKontaktPerson)
                                    .addComponent(lblKontaktEpost)
                                    .addComponent(lblTelefon)
                                    .addComponent(lblAdress)
                                    .addComponent(lblBranch))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbPartner, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cbProjektPartner, 0, 300, Short.MAX_VALUE)
                                        .addComponent(tfKontaktPerson)
                                        .addComponent(tfKontaktEpost)
                                        .addComponent(tfTelefon)
                                        .addComponent(tfAdress)
                                        .addComponent(tfBranch)
                                        .addComponent(tfStad)))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblNyTelefon)
                                            .addComponent(lblNyBranch)
                                            .addComponent(lblNyStad)
                                            .addComponent(lblNyAdress))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(tfNyAdressNr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblNyAdressNr))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(tfNyAdressGata, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblNyAdressGata))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblNyAdressStad)
                                                    .addComponent(tfNyAdressStad, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(tfNyTelefon)
                                            .addComponent(tfNyBranch)
                                            .addComponent(tfNyStad)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblNyKontaktperson)
                                            .addComponent(lblNyKontaktEpost)
                                            .addComponent(lblNyttNamn))
                                        .addGap(34, 34, 34)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfNyttNamn)
                                            .addComponent(tfNyKontaktEpost)
                                            .addComponent(tfNyKontaktperson)))))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnOK)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnDelete))
                                    .addComponent(btnAddPiP))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnDeletePiP)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnChange)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnChange2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnAdd)))))
                        .addGap(0, 264, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPartners)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVisaPartners)
                        .addGap(28, 28, 28)
                        .addComponent(btnAddPartner)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnChangePartner)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemovePartner)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddPartnerIProjekt))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblStad)
                            .addComponent(lblAddPartner)
                            .addComponent(lblFelmeddelande))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTillbaka)
                .addGap(43, 43, 43))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPartners)
                    .addComponent(cbProjekt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVisaPartners)
                    .addComponent(btnChangePartner)
                    .addComponent(btnRemovePartner)
                    .addComponent(btnAddPartnerIProjekt)
                    .addComponent(btnAddPartner))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAddPartner)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbPartner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOK)
                    .addComponent(btnDelete)
                    .addComponent(btnChange)
                    .addComponent(btnAdd)
                    .addComponent(btnChange2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbProjektPartner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddPiP)
                    .addComponent(btnDeletePiP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNamn)
                    .addComponent(tfNamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNyttNamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNyttNamn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKontaktPerson)
                    .addComponent(tfKontaktPerson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNyKontaktperson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNyKontaktperson))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKontaktEpost)
                    .addComponent(tfKontaktEpost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNyKontaktEpost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNyKontaktEpost))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTelefon)
                    .addComponent(tfNyTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNyTelefon))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNyAdressNr)
                    .addComponent(lblNyAdressGata)
                    .addComponent(lblNyAdressStad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNyAdressNr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNyAdressGata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNyAdressStad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNyAdress)
                    .addComponent(tfAdress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAdress))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfBranch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBranch)
                    .addComponent(lblNyBranch)
                    .addComponent(tfNyBranch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStad)
                    .addComponent(tfStad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNyStad)
                    .addComponent(tfNyStad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(lblFelmeddelande)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTillbaka)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddPartnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPartnerActionPerformed
        
        setSynlighet("alla", false);
        setSynlighet("add", true);
        setKunnaSkriva(true);
        cbPartner.removeAllItems();
        cbPartner.addItem("Välj Projekt");
        fyllCbPartner();
        
    }//GEN-LAST:event_btnAddPartnerActionPerformed

    private void btnVisaPartnersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisaPartnersActionPerformed
        getPartnersMinaProjekt();
    }//GEN-LAST:event_btnVisaPartnersActionPerformed

    private void btnChangePartnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePartnerActionPerformed
        
        setSynlighet("alla", false);
        setSynlighet("change", true);
        setKunnaSkriva(false);
        changePartner();
        
    }//GEN-LAST:event_btnChangePartnerActionPerformed

    private void btnRemovePartnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemovePartnerActionPerformed
        
        setSynlighet("alla", false);
        setSynlighet("delete", true);
        setKunnaSkriva(false);
        
    }//GEN-LAST:event_btnRemovePartnerActionPerformed

    private void btnTillbakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaActionPerformed
        Meny meny = new Meny(idb, anstalldID, "");
        meny.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnTillbakaActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        
        visaPartner();
        
    }//GEN-LAST:event_btnOKActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        
        setSynlighet("alla", false);
        addPartner();
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAddPiPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPiPActionPerformed
        
//Tänk på validering att både ett projekt och en partner är vald, annars meddelande om att skapa partner eller projekt om admin och "om inte finns..." till handläggare och om de redan är valda
        setSynlighet("alla", false);
        addPartneriProjekt();
        
    }//GEN-LAST:event_btnAddPiPActionPerformed

    private void btnDeletePiPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletePiPActionPerformed
        
//Tänk på koll att både partner och projekt är valda
        setSynlighet("alla", false);
        deletePartneriProjekt();
        
    }//GEN-LAST:event_btnDeletePiPActionPerformed

    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeActionPerformed
        
        setSynlighet("add", true);
        lblAddPartner.setText("Ändra en partner");
        btnChange.setVisible(false);
        btnChange2.setVisible(true);
        btnAdd.setVisible(false);
        
    }//GEN-LAST:event_btnChangeActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        
        setSynlighet("alla", false);
        deletePartner();
        
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnChange2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChange2ActionPerformed
        
        setSynlighet("alla", false);
        changePartner();
        
    }//GEN-LAST:event_btnChange2ActionPerformed

    private void btnAddPartnerIProjektActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPartnerIProjektActionPerformed
        
        setSynlighet("alla", false);
        setSynlighet("addDeletePiP", true);
        
    }//GEN-LAST:event_btnAddPartnerIProjektActionPerformed

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
            java.util.logging.Logger.getLogger(PartnersMeny.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PartnersMeny.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PartnersMeny.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PartnersMeny.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new PartnersMeny().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddPartner;
    private javax.swing.JButton btnAddPartnerIProjekt;
    private javax.swing.JButton btnAddPiP;
    private javax.swing.JButton btnChange;
    private javax.swing.JButton btnChange2;
    private javax.swing.JButton btnChangePartner;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeletePiP;
    private javax.swing.JButton btnOK;
    private javax.swing.JButton btnRemovePartner;
    private javax.swing.JButton btnTillbaka;
    private javax.swing.JButton btnVisaPartners;
    private javax.swing.JComboBox<String> cbPartner;
    private javax.swing.JComboBox<String> cbProjekt;
    private javax.swing.JComboBox<String> cbProjektPartner;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAddPartner;
    private javax.swing.JLabel lblAdress;
    private javax.swing.JLabel lblBranch;
    private javax.swing.JLabel lblFelmeddelande;
    private javax.swing.JLabel lblKontaktEpost;
    private javax.swing.JLabel lblKontaktPerson;
    private javax.swing.JLabel lblNamn;
    private javax.swing.JLabel lblNyAdress;
    private javax.swing.JLabel lblNyAdressGata;
    private javax.swing.JLabel lblNyAdressNr;
    private javax.swing.JLabel lblNyAdressStad;
    private javax.swing.JLabel lblNyBranch;
    private javax.swing.JLabel lblNyKontaktEpost;
    private javax.swing.JLabel lblNyKontaktperson;
    private javax.swing.JLabel lblNyStad;
    private javax.swing.JLabel lblNyTelefon;
    private javax.swing.JLabel lblNyttNamn;
    private javax.swing.JLabel lblPartners;
    private javax.swing.JLabel lblStad;
    private javax.swing.JLabel lblTelefon;
    private javax.swing.JTextArea taPartners;
    private javax.swing.JTextField tfAdress;
    private javax.swing.JTextField tfBranch;
    private javax.swing.JTextField tfKontaktEpost;
    private javax.swing.JTextField tfKontaktPerson;
    private javax.swing.JTextField tfNamn;
    private javax.swing.JTextField tfNyAdressGata;
    private javax.swing.JTextField tfNyAdressNr;
    private javax.swing.JTextField tfNyAdressStad;
    private javax.swing.JTextField tfNyBranch;
    private javax.swing.JTextField tfNyKontaktEpost;
    private javax.swing.JTextField tfNyKontaktperson;
    private javax.swing.JTextField tfNyStad;
    private javax.swing.JTextField tfNyTelefon;
    private javax.swing.JTextField tfNyttNamn;
    private javax.swing.JTextField tfStad;
    private javax.swing.JTextField tfTelefon;
    // End of variables declaration//GEN-END:variables
}
