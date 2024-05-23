/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ngo2024;

import java.util.ArrayList;
import java.util.HashMap;
import oru.inf.InfDB;
import oru.inf.InfException;
/**
 *
 * @author israael-ainain
 */
public class AvdelningMeny extends javax.swing.JFrame {

    private InfDB idb;
    private String anstalldID;
    private Anstallning anstalld;
    private Validering validering;
    private Avdelning avdelning;
    
    public AvdelningMeny(InfDB idb, String aid) {
        
        this.idb = idb;
        anstalldID = aid;
        
        anstalld = new Anstallning(idb, anstalldID, "");
        validering = new Validering(idb, anstalldID);
        avdelning = new Avdelning(idb, anstalldID);
        
        
        initComponents();
        setSynlighet("alla", false);
        taPersonal.setEditable(false);
        cbFiltreraAvdelning.setEditable(false);
        btnsAdminVisible();
        fyllCbAvdelningar();
        
        
    }
    
    private void btnsAdminVisible(){
        
        Boolean admin = anstalld.getAdmin(anstalldID);
        
        if(admin){
            btnAddAvdelning.setVisible(true);
            btnRedigeraAvdelning.setVisible(true);
        }else{
            btnAddAvdelning.setVisible(false);
            btnRedigeraAvdelning.setVisible(false);
        }
        
    }
    
    private void visaAllPersonal(){
        
        ArrayList<HashMap<String, String>> allPersonal = avdelning.getPersonal();
        
        StringBuilder sb = new StringBuilder();
        String header = String.format("%-10s %-25s %-25s %-40s %-30s %-20s %-20s %-20s %-10s\n", "AnställdID", "Förnamnamn", "Efternamn", "Adress", "E-post", "Telefon", "Anställningsdatum", "Lösenord", "Avdelning");
        sb.append(header);
        sb.append("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        
        for (HashMap<String, String> person : allPersonal) {
            String row = String.format("%-10s %-25s %-25s %-40s %-30s %-20s %-20s %-20s %-10s\n", person.get("aid"), person.get("fornamn"), person.get("efternamn"), person.get("adress"), person.get("epost"), person.get("telefon"), person.get("anstallningsdatum"), person.get("losenord"), person.get("avdelning"));
            sb.append(row);
        }
        
        taPersonal.setText(sb.toString());
    }
    
    private void visaPersonalAvd(){
        
        String minAvdelning = anstalld.getAvdelning(anstalldID); 
        ArrayList<HashMap<String, String>> personal = avdelning.getPersonalFromAvdelning(minAvdelning);
        
        StringBuilder sb = new StringBuilder();
        String header = String.format("%-10s %-25s %-25s %-40s %-30s %-20s %-20s %-20s %-10s\n", "AnställdID", "Förnamnamn", "Efternamn", "Adress", "E-post", "Telefon", "Anställningsdatum", "Lösenord", "Avdelning");
        sb.append(header);
        sb.append("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        
        for (HashMap<String, String> person : personal) {
            String row = String.format("%-10s %-25s %-25s %-40s %-30s %-20s %-20s %-20s %-10s\n", person.get("aid"), person.get("fornamn"), person.get("efternamn"), person.get("adress"), person.get("epost"), person.get("telefon"), person.get("anstallningsdatum"), person.get("losenord"), person.get("avdelning"));
            sb.append(row);
        }
        
        taPersonal.setText(sb.toString());
    }
    
    private void searchAnstalldAvd(){
        
        String minAvdelning = anstalld.getAvdelning(anstalldID); 
        ArrayList<HashMap<String, String>> personal = avdelning.getPersonalFromAvdelning(minAvdelning);
        
        String fornamn = tfSokFornamn.getText();
        String efternamn = tfSokEfternamn.getText();
        String namn = fornamn + " " + efternamn;
        String epost = tfSokEpost.getText() + "@example.com";
        
        StringBuilder sb = new StringBuilder();
        String header = String.format("%-10s %-25s %-25s %-40s %-30s %-20s %-20s %-20s %-10s\n", "AnställdID", "Förnamnamn", "Efternamn", "Adress", "E-post", "Telefon", "Anställningsdatum", "Lösenord", "Avdelning");
        sb.append(header);
        sb.append("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        
        boolean personFinns = false;
        for (HashMap<String, String> person : personal) {
            if (person.get("fornamn").equalsIgnoreCase(fornamn) && person.get("efternamn").equalsIgnoreCase(efternamn)) {
                String row = String.format("%-10s %-25s %-25s %-40s %-30s %-20s %-20s %-20s %-10s\n", person.get("aid"), person.get("fornamn"), person.get("efternamn"), person.get("adress"), person.get("epost"), person.get("telefon"), person.get("anstallningsdatum"), person.get("losenord"), person.get("avdelning"));
                sb.append(row);
                personFinns = true;
            }
        }
        
        for (HashMap<String, String> person : personal) {
            if (person.get("epost").equals(epost)) {
                String row = String.format("%-10s %-25s %-25s %-40s %-30s %-20s %-20s %-20s %-10s\n", person.get("aid"), person.get("fornamn"), person.get("efternamn"), person.get("adress"), person.get("epost"), person.get("telefon"), person.get("anstallningsdatum"), person.get("losenord"), person.get("avdelning"));
                sb.append(row);
                personFinns = true;
            }
        }

        if (!personFinns) {
            sb.append("Ingen anställd hittades. Kontrollera stavningen.\n");
        }

        taPersonal.setText(sb.toString());
    }
    
    private void searchAnstalld(){
        
        ArrayList<HashMap<String, String>> allPersonal = avdelning.getPersonal();
        
        String fornamn = tfSokFornamn.getText();
        String efternamn = tfSokEfternamn.getText();
        String epost = tfSokEpost.getText() + "@example.com";
        
        StringBuilder sb = new StringBuilder();
        String header = String.format("%-10s %-25s %-25s %-40s %-30s %-20s %-20s %-20s %-10s\n", "AnställdID", "Förnamnamn", "Efternamn", "Adress", "E-post", "Telefon", "Anställningsdatum", "Lösenord", "Avdelning");
        sb.append(header);
        sb.append("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        
        boolean personFinns = false;
        for (HashMap<String, String> person : allPersonal) {
            if (person.get("fornamn").equalsIgnoreCase(fornamn) && person.get("efternamn").equalsIgnoreCase(efternamn)) {
                String row = String.format("%-10s %-25s %-25s %-40s %-30s %-20s %-20s %-20s %-10s\n", person.get("aid"), person.get("fornamn"), person.get("efternamn"), person.get("adress"), person.get("epost"), person.get("telefon"), person.get("anstallningsdatum"), person.get("losenord"), person.get("avdelning"));
                sb.append(row);
                personFinns = true;
            }
        }
        
        for (HashMap<String, String> person : allPersonal) {
            if (person.get("epost").equals(epost)) {
                String row = String.format("%-10s %-25s %-25s %-40s %-30s %-20s %-20s %-20s %-10s\n", person.get("aid"), person.get("fornamn"), person.get("efternamn"), person.get("adress"), person.get("epost"), person.get("telefon"), person.get("anstallningsdatum"), person.get("losenord"), person.get("avdelning"));
                sb.append(row);
                personFinns = true;
            }
        }

        if (!personFinns) {
            sb.append("Ingen anställd hittades. Kontrollera stavningen\n");
        }

        taPersonal.setText(sb.toString());
    }
    
    private void filtreraAvd(String namnAvd){
        
        String avdelningNr = avdelning.getAvdelningIdFromAvdelning(namnAvd);
        ArrayList<HashMap<String, String>> personal = avdelning.getPersonalFromAvdelning(avdelningNr);
        
        
        StringBuilder sb = new StringBuilder();
        String header = String.format("%-10s %-25s %-25s %-40s %-30s %-20s %-20s %-20s %-10s\n", "AnställdID", "Förnamnamn", "Efternamn", "Adress", "E-post", "Telefon", "Anställningsdatum", "Lösenord", "Avdelning");
        sb.append(header);
        sb.append("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        
        for (HashMap<String, String> person : personal) {
            String row = String.format("%-10s %-25s %-25s %-40s %-30s %-20s %-20s %-20s %-10s\n", person.get("aid"), person.get("fornamn"), person.get("efternamn"), person.get("adress"), person.get("epost"), person.get("telefon"), person.get("anstallningsdatum"), person.get("losenord"), person.get("avdelning"));
            sb.append(row);
        }

        taPersonal.setText(sb.toString());
    }
    
    
    
    private void visaAvdelning(String avdID){
        
        HashMap<String, String> avdelningMap = avdelning.getHelAvdelning(avdID);
        
        tfAddNamn.setText(avdelningMap.get("namn"));
        tfAddBeskrivning.setText(avdelningMap.get("beskrivning"));
        tfAddAdress.setText(avdelningMap.get("adress"));
        tfAddEpost.setText(avdelningMap.get("epost"));
        tfAddTelefon.setText(avdelningMap.get("telefon"));
        tfAddStad.setText(avdelningMap.get("stad"));
        tfAddChef.setText(avdelningMap.get("chef"));
    }
    
    private void addAvdelningToDB(){
        
        String namn = tfAddNamn.getText();
        String beskrivning = tfAddBeskrivning.getText();
        String adress  = tfAddAdress.getText();
        String epost = tfAddEpost.getText();
        String telefon = tfAddTelefon.getText();
        String stad = tfAddStad.getText();
        String chef = tfAddChef.getText();
        String avdID = avdelning.setAvdID();
        
        avdelning.addAvdelning(avdID, namn, beskrivning, adress, epost, telefon, stad, chef);
    }
    
    private void redigeraAvdelning(){
        
    }
    
    private void setSynlighet(String vilken, boolean synlighet){
        
        if(vilken.equals("sePersonal")){
            
            lblPersonal.setVisible(synlighet);
            taPersonal.setVisible(synlighet);
        }
        if(vilken.equals("filtreraAvd")){
            
            lblFiltreraAvdelning.setVisible(synlighet);
            cbFiltreraAvdelning.setVisible(synlighet);
            btnFiltreraAvdelning.setVisible(synlighet);
        }
        if(vilken.equals("addAvd")){
            lblAddAvdelning.setText("Lägg Till Avdelning");
            btnAdd.setText("Lägg Till");
            
            lblAddAvdelning.setVisible(synlighet);
            lblAddNamn.setVisible(synlighet);
            lblAddAtEpost.setVisible(synlighet);
            lblAddChef.setVisible(synlighet);
            lblAddStad.setVisible(synlighet);
            lblAddTelefon.setVisible(synlighet);
            lblAddAdress.setVisible(synlighet);
            lblAddBeskrivning.setVisible(synlighet);
            lblAddEpost.setVisible(synlighet);
            
            tfAddAdress.setVisible(synlighet);
            tfAddBeskrivning.setVisible(synlighet);
            tfAddChef.setVisible(synlighet);
            tfAddEpost.setVisible(synlighet);
            tfAddNamn.setVisible(synlighet);
            tfAddStad.setVisible(synlighet);
            tfAddTelefon.setVisible(synlighet);
            
            btnAdd.setVisible(synlighet);
            btnAddAvbryt.setVisible(synlighet);
        }
        if(vilken.equals("sokPersonal")){
            
            lblPersonal.setVisible(synlighet);
            taPersonal.setVisible(synlighet);
            
            lblSokPersonal.setVisible(synlighet);
            lblSokNamn.setVisible(synlighet);
            lblSokEpost.setVisible(synlighet);
            lblSokAtEpost.setVisible(synlighet);
            lblSokFornamn.setVisible(synlighet);
            lblSokEfternamn.setVisible(synlighet);
            tfSokFornamn.setVisible(synlighet);
            tfSokEfternamn.setVisible(synlighet);
            tfSokEpost.setVisible(synlighet);
            btnSok.setVisible(synlighet);
        }
        if(vilken.equals("redigeraAvd")){
            
            btnAddAdress.setVisible(synlighet);
            btnAddBeskrivning.setVisible(synlighet);
            btnAddChef.setVisible(synlighet);
            btnAddEpost.setVisible(synlighet);
            btnAddNamn.setVisible(synlighet);
            btnAddStad.setVisible(synlighet);
            btnAddTelefon.setVisible(synlighet); 
            cbAddAvdelning.setVisible(synlighet);
        }
        if(vilken.equals("redigeraNamn")){
            lblRedigeraNamn.setVisible(synlighet);
            tfRedigeraNamn.setVisible(synlighet);
            btnRedigeraNamn.setVisible(synlighet);
            btnRedigeraNamnAvbryt.setVisible(synlighet);
        }
        if(vilken.equals("redigeraBeskrivning")){
           lblRedigeraBeskrivning.setVisible(synlighet); 
           tfRedigeraBeskrivning.setVisible(synlighet);
           btnRedigeraBeskrivning.setVisible(synlighet);
           btnRedigeraBeskrivningAvbryt.setVisible(synlighet);
        }
        if(vilken.equals("redigeraAdress")){
            lblRedigeraAdress.setVisible(synlighet);
            lblRedigeraAdressNr.setVisible(synlighet);
            lblRedigeraAdressGata.setVisible(synlighet);
            lblRedigeraAdressStad.setVisible(synlighet);
            
            tfRedigeraAdressNr.setVisible(synlighet);
            tfRedigeraAdressGata.setVisible(synlighet);
            tfRedigeraAdressStad.setVisible(synlighet);
            
            btnRedigeraAdress.setVisible(synlighet);
            btnRedigeraAdressAvbryt.setVisible(synlighet);
        }
        if(vilken.equals("redigeraEpost")){
            lblRedigeraEpost.setVisible(synlighet);
            lblRedigeraAtEpost.setVisible(synlighet);
            tfRedigeraEpost.setVisible(synlighet);
            btnRedigeraEpost.setVisible(synlighet);
            btnRedigeraEpostAvbryt.setVisible(synlighet);
        }
        if(vilken.equals("redigeraTelefon")){
            lblRedigeraTelefon.setVisible(synlighet);
            tfRedigeraTelefon.setVisible(synlighet);
            btnRedigeraTelefon.setVisible(synlighet);
            btnRedigeraTelefonAvbryt.setVisible(synlighet);
        }
        if(vilken.equals("redigeraChef")){
            lblRedigeraChef.setVisible(synlighet);
            tfRedigeraChef.setVisible(synlighet);
            btnRedigeraChef.setVisible(synlighet);
            btnRedigeraChefAvbryt.setVisible(synlighet);
        }
        if(vilken.equals("redigeraStad")){
            lblRedigeraStad.setVisible(synlighet);
            tfRedigeraStad.setVisible(synlighet);
            btnRedigeraStad.setVisible(synlighet);
            btnRedigeraStadAvbryt.setVisible(synlighet);
        }
        if(vilken.equals("alla")){
            lblPersonal.setVisible(synlighet);
            taPersonal.setVisible(synlighet);
            
            lblFiltreraAvdelning.setVisible(synlighet);
            cbFiltreraAvdelning.setVisible(synlighet);
            btnFiltreraAvdelning.setVisible(synlighet);
            
            lblAddAvdelning.setVisible(synlighet);
            lblAddNamn.setVisible(synlighet);
            lblAddAtEpost.setVisible(synlighet);
            lblAddChef.setVisible(synlighet);
            lblAddStad.setVisible(synlighet);
            lblAddTelefon.setVisible(synlighet);
            lblAddAdress.setVisible(synlighet);
            lblAddBeskrivning.setVisible(synlighet);
            lblAddEpost.setVisible(synlighet);
            
            tfAddAdress.setVisible(synlighet);
            tfAddBeskrivning.setVisible(synlighet);
            tfAddChef.setVisible(synlighet);
            tfAddEpost.setVisible(synlighet);
            tfAddNamn.setVisible(synlighet);
            tfAddStad.setVisible(synlighet);
            tfAddTelefon.setVisible(synlighet);
            
            btnAdd.setVisible(synlighet);
            btnAddAdress.setVisible(synlighet);
            btnAddAvbryt.setVisible(synlighet);
            btnAddBeskrivning.setVisible(synlighet);
            btnAddChef.setVisible(synlighet);
            btnAddEpost.setVisible(synlighet);
            btnAddNamn.setVisible(synlighet);
            btnAddStad.setVisible(synlighet);
            btnAddTelefon.setVisible(synlighet);  
            cbAddAvdelning.setVisible(synlighet);
            
            lblSokPersonal.setVisible(synlighet);
            lblSokNamn.setVisible(synlighet);
            lblSokEpost.setVisible(synlighet);
            lblSokAtEpost.setVisible(synlighet);
            lblSokFornamn.setVisible(synlighet);
            lblSokEfternamn.setVisible(synlighet);
            tfSokFornamn.setVisible(synlighet);
            tfSokEfternamn.setVisible(synlighet);
            tfSokEpost.setVisible(synlighet);
            btnSok.setVisible(synlighet);
            
            lblRedigeraNamn.setVisible(synlighet);
            lblRedigeraBeskrivning.setVisible(synlighet);
            lblRedigeraAdress.setVisible(synlighet);
            lblRedigeraAdressNr.setVisible(synlighet);
            lblRedigeraAdressGata.setVisible(synlighet);
            lblRedigeraAdressStad.setVisible(synlighet);
            lblRedigeraEpost.setVisible(synlighet);
            lblRedigeraAtEpost.setVisible(synlighet);
            lblRedigeraTelefon.setVisible(synlighet);
            lblRedigeraChef.setVisible(synlighet);
            lblRedigeraStad.setVisible(synlighet);
            
            tfRedigeraNamn.setVisible(synlighet);
            tfRedigeraBeskrivning.setVisible(synlighet);
            tfRedigeraAdressNr.setVisible(synlighet);
            tfRedigeraAdressGata.setVisible(synlighet);
            tfRedigeraAdressStad.setVisible(synlighet);
            tfRedigeraEpost.setVisible(synlighet);
            tfRedigeraTelefon.setVisible(synlighet);
            tfRedigeraChef.setVisible(synlighet);
            tfRedigeraStad.setVisible(synlighet);
            
            btnRedigeraNamn.setVisible(synlighet);
            btnRedigeraBeskrivning.setVisible(synlighet);
            btnRedigeraAdress.setVisible(synlighet);
            btnRedigeraEpost.setVisible(synlighet);
            btnRedigeraTelefon.setVisible(synlighet);
            btnRedigeraChef.setVisible(synlighet);
            btnRedigeraStad.setVisible(synlighet);
            
            btnRedigeraNamnAvbryt.setVisible(synlighet);
            btnRedigeraBeskrivningAvbryt.setVisible(synlighet);
            btnRedigeraAdressAvbryt.setVisible(synlighet);
            btnRedigeraEpostAvbryt.setVisible(synlighet);
            btnRedigeraTelefonAvbryt.setVisible(synlighet);
            btnRedigeraChefAvbryt.setVisible(synlighet);
            btnRedigeraStadAvbryt.setVisible(synlighet);
            
            lblFelmeddelande.setVisible(synlighet);
        }
    }
    
    public void fyllCbAvdelningar(){
        
        ArrayList<HashMap<String, String>> avdelningLista = avdelning.getAvdelningar();
        
        for(HashMap<String, String> avdelningen : avdelningLista){
        String namn = avdelningen.get("namn");
        cbFiltreraAvdelning.addItem(namn);
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

        lblAvdelning = new javax.swing.JLabel();
        btnSePersonal = new javax.swing.JButton();
        btnSokPersonalPaMinAvdelning = new javax.swing.JButton();
        btnAddAvdelning = new javax.swing.JButton();
        btnRedigeraAvdelning = new javax.swing.JButton();
        lblPersonal = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taPersonal = new javax.swing.JTextArea();
        lblSokPersonal = new javax.swing.JLabel();
        lblSokNamn = new javax.swing.JLabel();
        tfSokEpost = new javax.swing.JTextField();
        btnSok = new javax.swing.JButton();
        lblSokEpost = new javax.swing.JLabel();
        tfSokFornamn = new javax.swing.JTextField();
        lblSokAtEpost = new javax.swing.JLabel();
        lblAddAvdelning = new javax.swing.JLabel();
        lblAddNamn = new javax.swing.JLabel();
        tfAddNamn = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnAddNamn = new javax.swing.JButton();
        lblAddBeskrivning = new javax.swing.JLabel();
        btnAddBeskrivning = new javax.swing.JButton();
        lblAddAdress = new javax.swing.JLabel();
        tfAddAdress = new javax.swing.JTextField();
        btnAddAdress = new javax.swing.JButton();
        lblAddEpost = new javax.swing.JLabel();
        tfAddTelefon = new javax.swing.JTextField();
        lblAddAtEpost = new javax.swing.JLabel();
        btnAddEpost = new javax.swing.JButton();
        lblAddTelefon = new javax.swing.JLabel();
        tfAddEpost = new javax.swing.JTextField();
        btnAddTelefon = new javax.swing.JButton();
        lblAddStad = new javax.swing.JLabel();
        tfAddStad = new javax.swing.JTextField();
        btnAddStad = new javax.swing.JButton();
        lblAddChef = new javax.swing.JLabel();
        tfAddChef = new javax.swing.JTextField();
        btnAddChef = new javax.swing.JButton();
        tfAddBeskrivning = new javax.swing.JTextField();
        btnTillbaka = new javax.swing.JButton();
        lblFelmeddelande = new javax.swing.JLabel();
        btnFiltreraAvdelning = new javax.swing.JButton();
        lblFiltreraAvdelning = new javax.swing.JLabel();
        btnAddAvbryt = new javax.swing.JButton();
        lblSokFornamn = new javax.swing.JLabel();
        tfSokEfternamn = new javax.swing.JTextField();
        lblSokEfternamn = new javax.swing.JLabel();
        cbFiltreraAvdelning = new javax.swing.JComboBox<>();
        cbAddAvdelning = new javax.swing.JComboBox<>();
        lblRedigeraNamn = new javax.swing.JLabel();
        tfRedigeraNamn = new javax.swing.JTextField();
        btnRedigeraNamn = new javax.swing.JButton();
        btnRedigeraNamnAvbryt = new javax.swing.JButton();
        lblRedigeraBeskrivning = new javax.swing.JLabel();
        tfRedigeraBeskrivning = new javax.swing.JTextField();
        lblRedigeraAdress = new javax.swing.JLabel();
        tfRedigeraAdressNr = new javax.swing.JTextField();
        lblRedigeraEpost = new javax.swing.JLabel();
        tfRedigeraEpost = new javax.swing.JTextField();
        tfRedigeraAdressGata = new javax.swing.JTextField();
        tfRedigeraAdressStad = new javax.swing.JTextField();
        lblRedigeraTelefon = new javax.swing.JLabel();
        lblRedigeraChef = new javax.swing.JLabel();
        lblRedigeraStad = new javax.swing.JLabel();
        tfRedigeraTelefon = new javax.swing.JTextField();
        tfRedigeraChef = new javax.swing.JTextField();
        tfRedigeraStad = new javax.swing.JTextField();
        lblRedigeraAtEpost = new javax.swing.JLabel();
        btnRedigeraBeskrivning = new javax.swing.JButton();
        btnRedigeraBeskrivningAvbryt = new javax.swing.JButton();
        btnRedigeraAdress = new javax.swing.JButton();
        btnRedigeraAdressAvbryt = new javax.swing.JButton();
        btnRedigeraEpost = new javax.swing.JButton();
        btnRedigeraEpostAvbryt = new javax.swing.JButton();
        btnRedigeraTelefon = new javax.swing.JButton();
        btnRedigeraTelefonAvbryt = new javax.swing.JButton();
        btnRedigeraChef = new javax.swing.JButton();
        btnRedigeraChefAvbryt = new javax.swing.JButton();
        btnRedigeraStad = new javax.swing.JButton();
        btnRedigeraStadAvbryt = new javax.swing.JButton();
        lblRedigeraAdressNr = new javax.swing.JLabel();
        lblRedigeraAdressGata = new javax.swing.JLabel();
        lblRedigeraAdressStad = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblAvdelning.setText("Avdelning");

        btnSePersonal.setText("Se Personal");
        btnSePersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSePersonalActionPerformed(evt);
            }
        });

        btnSokPersonalPaMinAvdelning.setText("Sök Personal");
        btnSokPersonalPaMinAvdelning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSokPersonalPaMinAvdelningActionPerformed(evt);
            }
        });

        btnAddAvdelning.setText("Lägg Till Avdelning");
        btnAddAvdelning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddAvdelningActionPerformed(evt);
            }
        });

        btnRedigeraAvdelning.setText("Ändra i Avdelning");
        btnRedigeraAvdelning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedigeraAvdelningActionPerformed(evt);
            }
        });

        lblPersonal.setText("Personal");

        taPersonal.setColumns(20);
        taPersonal.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        taPersonal.setRows(5);
        jScrollPane1.setViewportView(taPersonal);

        lblSokPersonal.setText("Sök Personal");

        lblSokNamn.setText("Sök med Namn");

        btnSok.setText("Sök");
        btnSok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSokActionPerformed(evt);
            }
        });

        lblSokEpost.setText("Sök med Epost");

        lblSokAtEpost.setText("@example.com");

        lblAddAvdelning.setText("Lägg Till Avdelning");

        lblAddNamn.setText("Avdelningsnamn");

        btnAdd.setText("Lagg Till");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnAddNamn.setText("Ändra");
        btnAddNamn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNamnActionPerformed(evt);
            }
        });

        lblAddBeskrivning.setText("Beskrivning");

        btnAddBeskrivning.setText("Ändra");
        btnAddBeskrivning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBeskrivningActionPerformed(evt);
            }
        });

        lblAddAdress.setText("Adress");

        btnAddAdress.setText("Ändra");
        btnAddAdress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddAdressActionPerformed(evt);
            }
        });

        lblAddEpost.setText("Epost");

        lblAddAtEpost.setText("@example.com");

        btnAddEpost.setText("Ändra");
        btnAddEpost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEpostActionPerformed(evt);
            }
        });

        lblAddTelefon.setText("Telefon");

        btnAddTelefon.setText("Ändra");
        btnAddTelefon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTelefonActionPerformed(evt);
            }
        });

        lblAddStad.setText("Stad");

        btnAddStad.setText("Ändra");
        btnAddStad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStadActionPerformed(evt);
            }
        });

        lblAddChef.setText("Chef");

        btnAddChef.setText("Ändra");
        btnAddChef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddChefActionPerformed(evt);
            }
        });

        btnTillbaka.setText("Tillbaka");
        btnTillbaka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTillbakaActionPerformed(evt);
            }
        });

        lblFelmeddelande.setText("Felmeddelande");

        btnFiltreraAvdelning.setText("Filtrera");
        btnFiltreraAvdelning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltreraAvdelningActionPerformed(evt);
            }
        });

        lblFiltreraAvdelning.setText("Avdelning");

        btnAddAvbryt.setText("Avbryt");
        btnAddAvbryt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddAvbrytActionPerformed(evt);
            }
        });

        lblSokFornamn.setText("Förnamn");

        lblSokEfternamn.setText("Efternamn");

        cbFiltreraAvdelning.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj Avdelning" }));

        cbAddAvdelning.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Välj en Avdelning" }));

        lblRedigeraNamn.setText("Nytt Avdelningsnamn");

        btnRedigeraNamn.setText("Ändra");
        btnRedigeraNamn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedigeraNamnActionPerformed(evt);
            }
        });

        btnRedigeraNamnAvbryt.setText("Avbryt");
        btnRedigeraNamnAvbryt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedigeraNamnAvbrytActionPerformed(evt);
            }
        });

        lblRedigeraBeskrivning.setText("Ny Beskrivning");

        lblRedigeraAdress.setText("Ny Adress");

        lblRedigeraEpost.setText("Ny Epost");

        lblRedigeraTelefon.setText("Nytt Telefonnummer");

        lblRedigeraChef.setText("Ny Chef");

        lblRedigeraStad.setText("Ny Stad");

        lblRedigeraAtEpost.setText("@example.com");

        btnRedigeraBeskrivning.setText("Ändra");
        btnRedigeraBeskrivning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedigeraBeskrivningActionPerformed(evt);
            }
        });

        btnRedigeraBeskrivningAvbryt.setText("Avbryt");
        btnRedigeraBeskrivningAvbryt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedigeraBeskrivningAvbrytActionPerformed(evt);
            }
        });

        btnRedigeraAdress.setText("Ändra");
        btnRedigeraAdress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedigeraAdressActionPerformed(evt);
            }
        });

        btnRedigeraAdressAvbryt.setText("Avbryt");
        btnRedigeraAdressAvbryt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedigeraAdressAvbrytActionPerformed(evt);
            }
        });

        btnRedigeraEpost.setText("Ändra");
        btnRedigeraEpost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedigeraEpostActionPerformed(evt);
            }
        });

        btnRedigeraEpostAvbryt.setText("Avbryt");
        btnRedigeraEpostAvbryt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedigeraEpostAvbrytActionPerformed(evt);
            }
        });

        btnRedigeraTelefon.setText("Ändra");
        btnRedigeraTelefon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedigeraTelefonActionPerformed(evt);
            }
        });

        btnRedigeraTelefonAvbryt.setText("Avbryt");
        btnRedigeraTelefonAvbryt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedigeraTelefonAvbrytActionPerformed(evt);
            }
        });

        btnRedigeraChef.setText("Ändra");
        btnRedigeraChef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedigeraChefActionPerformed(evt);
            }
        });

        btnRedigeraChefAvbryt.setText("Avbryt");
        btnRedigeraChefAvbryt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedigeraChefAvbrytActionPerformed(evt);
            }
        });

        btnRedigeraStad.setText("Ändra");
        btnRedigeraStad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedigeraStadActionPerformed(evt);
            }
        });

        btnRedigeraStadAvbryt.setText("Avbryt");
        btnRedigeraStadAvbryt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedigeraStadAvbrytActionPerformed(evt);
            }
        });

        lblRedigeraAdressNr.setText("Nummer");

        lblRedigeraAdressGata.setText("Gata");

        lblRedigeraAdressStad.setText("Stad");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPersonal)
                            .addComponent(lblAvdelning)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblAddAvdelning)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbAddAvdelning, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(677, 677, 677))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(lblSokPersonal)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(btnSok))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addComponent(lblSokEpost, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(tfSokEpost, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(lblSokAtEpost)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblSokNamn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(tfSokFornamn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblSokFornamn))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblSokEfternamn)
                                                    .addComponent(tfSokEfternamn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(113, 113, 113)
                                        .addComponent(lblFiltreraAvdelning)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbFiltreraAvdelning, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(btnFiltreraAvdelning))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnRedigeraAvdelning, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAddAvdelning, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnSePersonal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSokPersonalPaMinAvdelning, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAddTelefon)
                                    .addComponent(lblAddAdress)
                                    .addComponent(lblAddBeskrivning)
                                    .addComponent(lblAddNamn)
                                    .addComponent(lblAddEpost)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblAddStad)
                                        .addComponent(lblAddChef)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tfAddChef, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                        .addComponent(tfAddBeskrivning, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(tfAddNamn, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(tfAddAdress, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(tfAddEpost)
                                        .addComponent(tfAddTelefon))
                                    .addComponent(tfAddStad, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblAddAtEpost)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAddEpost)
                                    .addComponent(btnAddNamn)
                                    .addComponent(btnAddBeskrivning)
                                    .addComponent(btnAddAdress)
                                    .addComponent(btnAddStad)
                                    .addComponent(btnAddChef)
                                    .addComponent(btnAddTelefon)
                                    .addComponent(btnAdd))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblRedigeraBeskrivning)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                    .addComponent(lblRedigeraEpost)
                                                                    .addGap(74, 74, 74))
                                                                .addGroup(layout.createSequentialGroup()
                                                                    .addComponent(lblRedigeraAdress)
                                                                    .addGap(68, 68, 68)))
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(layout.createSequentialGroup()
                                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(tfRedigeraAdressNr, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(lblRedigeraAdressNr))
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(tfRedigeraAdressGata, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(lblRedigeraAdressGata))
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(lblRedigeraAdressStad)
                                                                        .addComponent(tfRedigeraAdressStad, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGroup(layout.createSequentialGroup()
                                                                    .addComponent(tfRedigeraEpost, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(lblRedigeraAtEpost))))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(lblRedigeraTelefon)
                                                                .addComponent(lblRedigeraChef)
                                                                .addComponent(lblRedigeraStad))
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(tfRedigeraStad, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(tfRedigeraChef, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(tfRedigeraTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblRedigeraNamn)
                                                .addGap(5, 5, 5)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                        .addComponent(btnTillbaka))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(btnRedigeraEpost)
                                                            .addComponent(btnRedigeraTelefon)
                                                            .addComponent(btnRedigeraChef)
                                                            .addComponent(btnRedigeraStad)
                                                            .addComponent(btnRedigeraAdress)
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                    .addComponent(tfRedigeraBeskrivning, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(tfRedigeraNamn, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(122, 122, 122)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(btnRedigeraNamn, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                    .addComponent(btnRedigeraBeskrivning, javax.swing.GroupLayout.Alignment.TRAILING))))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(btnRedigeraNamnAvbryt)
                                                            .addComponent(btnRedigeraBeskrivningAvbryt)
                                                            .addComponent(btnRedigeraAdressAvbryt)
                                                            .addComponent(btnRedigeraEpostAvbryt)
                                                            .addComponent(btnRedigeraTelefonAvbryt)
                                                            .addComponent(btnRedigeraChefAvbryt)
                                                            .addComponent(btnRedigeraStadAvbryt))
                                                        .addGap(0, 0, Short.MAX_VALUE)))))
                                        .addGap(43, 43, 43))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnAddAvbryt)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(lblFelmeddelande)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAvdelning)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSePersonal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSokPersonalPaMinAvdelning)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddAvdelning)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRedigeraAvdelning)
                .addGap(21, 21, 21)
                .addComponent(lblPersonal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFiltreraAvdelning)
                            .addComponent(lblFiltreraAvdelning)
                            .addComponent(cbFiltreraAvdelning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSokPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSok))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSokEpost, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfSokEpost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSokAtEpost))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSokNamn)
                    .addComponent(lblSokFornamn)
                    .addComponent(lblSokEfternamn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSokFornamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfSokEfternamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddAvdelning)
                    .addComponent(cbAddAvdelning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd)
                    .addComponent(btnAddAvbryt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRedigeraNamn, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfAddNamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblAddNamn)
                        .addComponent(btnAddNamn)
                        .addComponent(tfRedigeraNamn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRedigeraNamn)
                        .addComponent(btnRedigeraNamnAvbryt)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddBeskrivning)
                    .addComponent(tfAddBeskrivning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRedigeraBeskrivning)
                    .addComponent(btnAddBeskrivning)
                    .addComponent(tfRedigeraBeskrivning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRedigeraBeskrivning)
                    .addComponent(btnRedigeraBeskrivningAvbryt))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRedigeraAdressNr)
                    .addComponent(lblRedigeraAdressGata)
                    .addComponent(lblRedigeraAdressStad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddAdress)
                    .addComponent(tfAddAdress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddAdress)
                    .addComponent(lblRedigeraAdress)
                    .addComponent(tfRedigeraAdressNr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfRedigeraAdressGata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfRedigeraAdressStad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRedigeraAdress)
                    .addComponent(btnRedigeraAdressAvbryt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfAddEpost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAddEpost)
                    .addComponent(lblAddAtEpost)
                    .addComponent(btnAddEpost)
                    .addComponent(lblRedigeraEpost)
                    .addComponent(tfRedigeraEpost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRedigeraAtEpost)
                    .addComponent(btnRedigeraEpost)
                    .addComponent(btnRedigeraEpostAvbryt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddTelefon)
                    .addComponent(tfAddTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRedigeraTelefon)
                    .addComponent(tfRedigeraTelefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddTelefon)
                    .addComponent(btnRedigeraTelefon)
                    .addComponent(btnRedigeraTelefonAvbryt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddChef)
                    .addComponent(tfAddChef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRedigeraChef)
                    .addComponent(tfRedigeraChef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddChef)
                    .addComponent(btnRedigeraChef)
                    .addComponent(btnRedigeraChefAvbryt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddStad)
                    .addComponent(tfAddStad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRedigeraStad)
                    .addComponent(tfRedigeraStad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddStad)
                    .addComponent(btnRedigeraStad)
                    .addComponent(btnRedigeraStadAvbryt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(lblFelmeddelande)
                .addGap(40, 40, 40)
                .addComponent(btnTillbaka))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSePersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSePersonalActionPerformed
        
        if(anstalld.getAdmin(anstalldID)){
            visaAllPersonal();
            cbFiltreraAvdelning.setEnabled(true);
            setSynlighet("filtreraAvd", true);
        }else{
            visaPersonalAvd();
        }
        setSynlighet("sePersonal", true);
        
    
    }//GEN-LAST:event_btnSePersonalActionPerformed

    private void btnSokPersonalPaMinAvdelningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSokPersonalPaMinAvdelningActionPerformed
        
        setSynlighet("sokPersonal", true);
        
    }//GEN-LAST:event_btnSokPersonalPaMinAvdelningActionPerformed

    private void btnFiltreraAvdelningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltreraAvdelningActionPerformed
        
        String avdelningNamn = (String) cbFiltreraAvdelning.getSelectedItem();
        filtreraAvd(avdelningNamn);
        
    }//GEN-LAST:event_btnFiltreraAvdelningActionPerformed

    private void btnSokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSokActionPerformed
        
        if(anstalld.getAdmin(anstalldID)){
            searchAnstalld();
        }else{
            searchAnstalldAvd();
        }
        
    }//GEN-LAST:event_btnSokActionPerformed

    private void btnRedigeraAvdelningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedigeraAvdelningActionPerformed
        
        setSynlighet("addAvd", true);
        lblAddAvdelning.setText("Ändra i Avdelning");
        btnAdd.setText("Ändra");
        cbAddAvdelning.setVisible(true);
        
    }//GEN-LAST:event_btnRedigeraAvdelningActionPerformed

    private void btnAddAvdelningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAvdelningActionPerformed
        
        setSynlighet("addAvd", true);
        
    }//GEN-LAST:event_btnAddAvdelningActionPerformed

    private void btnAddAvbrytActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAvbrytActionPerformed
        
        String tom = "";
            tfAddAdress.setText(tom);
            tfAddBeskrivning.setText(tom);
            tfAddChef.setText(tom);
            tfAddEpost.setText(tom);
            tfAddNamn.setText(tom);
            tfAddStad.setText(tom);
            tfAddTelefon.setText(tom);
            cbAddAvdelning.setSelectedItem("Välj en Avdelning");
            
        setSynlighet("alla", false);
        
    }//GEN-LAST:event_btnAddAvbrytActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        
        String btnText = btnAdd.getText();
        if(btnText.equals("Lägg Till")){
            addAvdelningToDB();
            lblFelmeddelande.setText("Avdelningsuppgifter har tillagts");
            
        }else{
            setSynlighet("redigeraAvd", true);
            String valdAvdelning = (String) cbAddAvdelning.getSelectedItem();
            
            if(valdAvdelning.equals("Välj Avdelning")){
                lblFelmeddelande.setText("Välj en Avdelning");
                lblFelmeddelande.setVisible(true);
            }else{
                String avdID = avdelning.getAvdelningIdFromAvdelning(valdAvdelning);
                visaAvdelning(avdID);
            }
        }
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAddNamnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNamnActionPerformed
        setSynlighet("redigeraNamn", true);        
    }//GEN-LAST:event_btnAddNamnActionPerformed

    private void btnAddBeskrivningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddBeskrivningActionPerformed
        setSynlighet("redigeraBeskrivning", true);
    }//GEN-LAST:event_btnAddBeskrivningActionPerformed

    private void btnAddAdressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAdressActionPerformed
        setSynlighet("redigeraAdress", true);
    }//GEN-LAST:event_btnAddAdressActionPerformed

    private void btnAddEpostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEpostActionPerformed
        setSynlighet("redigeraEpost", true);
    }//GEN-LAST:event_btnAddEpostActionPerformed

    private void btnAddTelefonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTelefonActionPerformed
        setSynlighet("redigeraTelefon", true);
    }//GEN-LAST:event_btnAddTelefonActionPerformed

    private void btnAddChefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddChefActionPerformed
        setSynlighet("redigeraChef", true);
    }//GEN-LAST:event_btnAddChefActionPerformed

    private void btnAddStadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddStadActionPerformed
        setSynlighet("redigeraStad", true);
    }//GEN-LAST:event_btnAddStadActionPerformed

    private void btnRedigeraNamnAvbrytActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedigeraNamnAvbrytActionPerformed
        setSynlighet("redigeraNamn", false);    
    }//GEN-LAST:event_btnRedigeraNamnAvbrytActionPerformed

    private void btnRedigeraBeskrivningAvbrytActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedigeraBeskrivningAvbrytActionPerformed
        setSynlighet("redigeraBeskrivning", false);    
    }//GEN-LAST:event_btnRedigeraBeskrivningAvbrytActionPerformed

    private void btnRedigeraAdressAvbrytActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedigeraAdressAvbrytActionPerformed
        setSynlighet("redigeraAdress", false);    
    }//GEN-LAST:event_btnRedigeraAdressAvbrytActionPerformed

    private void btnRedigeraEpostAvbrytActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedigeraEpostAvbrytActionPerformed
        setSynlighet("redigeraEpost", false);    
    }//GEN-LAST:event_btnRedigeraEpostAvbrytActionPerformed

    private void btnRedigeraTelefonAvbrytActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedigeraTelefonAvbrytActionPerformed
        setSynlighet("redigeraTelefon", false);    
    }//GEN-LAST:event_btnRedigeraTelefonAvbrytActionPerformed

    private void btnRedigeraChefAvbrytActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedigeraChefAvbrytActionPerformed
        setSynlighet("redigeraChef", false);    
    }//GEN-LAST:event_btnRedigeraChefAvbrytActionPerformed

    private void btnRedigeraStadAvbrytActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedigeraStadAvbrytActionPerformed
        setSynlighet("redigeraStad", false);    
    }//GEN-LAST:event_btnRedigeraStadAvbrytActionPerformed

    private void btnRedigeraNamnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedigeraNamnActionPerformed
        
        String valdAvdelning = (String) cbAddAvdelning.getSelectedItem();
        String avdID = avdelning.getAvdelningIdFromAvdelning(valdAvdelning);
        String tfNamn = tfRedigeraNamn.getText();
        
        avdelning.changeCellAvdelning("namn", tfNamn, avdID);
        
    }//GEN-LAST:event_btnRedigeraNamnActionPerformed

    private void btnRedigeraBeskrivningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedigeraBeskrivningActionPerformed
        
        String valdAvdelning = (String) cbAddAvdelning.getSelectedItem();
        String avdID = avdelning.getAvdelningIdFromAvdelning(valdAvdelning);
        String tfBeskrivning = tfRedigeraBeskrivning.getText();
        
        avdelning.changeCellAvdelning("beskrivning", tfBeskrivning, avdID);
        
    }//GEN-LAST:event_btnRedigeraBeskrivningActionPerformed

    private void btnRedigeraAdressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedigeraAdressActionPerformed
        
        String valdAvdelning = (String) cbAddAvdelning.getSelectedItem();
        String avdID = avdelning.getAvdelningIdFromAvdelning(valdAvdelning);
        
        String tfAdressNr = tfRedigeraAdressNr.getText();
        String tfAdressGata = tfRedigeraAdressGata.getText();
        String tfAdressStad = tfRedigeraAdressStad.getText();
        String adress = tfAdressNr + " " + tfAdressGata + ", " + tfAdressStad;
        
        avdelning.changeCellAvdelning("adress", adress, avdID);
        
    }//GEN-LAST:event_btnRedigeraAdressActionPerformed

    private void btnRedigeraEpostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedigeraEpostActionPerformed
        
        String valdAvdelning = (String) cbAddAvdelning.getSelectedItem();
        String avdID = avdelning.getAvdelningIdFromAvdelning(valdAvdelning);
        String tfEpost = tfRedigeraEpost.getText();
        String epost = tfEpost + "@example.com";
        
        avdelning.changeCellAvdelning("epost", epost, avdID);
        
    }//GEN-LAST:event_btnRedigeraEpostActionPerformed

    private void btnRedigeraTelefonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedigeraTelefonActionPerformed
        
        String valdAvdelning = (String) cbAddAvdelning.getSelectedItem();
        String avdID = avdelning.getAvdelningIdFromAvdelning(valdAvdelning);
        String tfTelefon = tfRedigeraTelefon.getText();
        
        avdelning.changeCellAvdelning("Telefon", tfTelefon, avdID);
        
    }//GEN-LAST:event_btnRedigeraTelefonActionPerformed

    private void btnRedigeraChefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedigeraChefActionPerformed
        
        String valdAvdelning = (String) cbAddAvdelning.getSelectedItem();
        String avdID = avdelning.getAvdelningIdFromAvdelning(valdAvdelning);
        String tfChef = tfRedigeraChef.getText();
        
        avdelning.changeCellAvdelning("chef", tfChef, avdID);
        
    }//GEN-LAST:event_btnRedigeraChefActionPerformed

    private void btnRedigeraStadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedigeraStadActionPerformed
        
        String valdAvdelning = (String) cbAddAvdelning.getSelectedItem();
        String avdID = avdelning.getAvdelningIdFromAvdelning(valdAvdelning);
        String tfStad = tfRedigeraStad.getText();
        
        avdelning.changeCellAvdelning("stad", tfStad, avdID);
        
    }//GEN-LAST:event_btnRedigeraStadActionPerformed

    private void btnTillbakaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTillbakaActionPerformed
        Meny meny = new Meny(idb, anstalldID, "");
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
            java.util.logging.Logger.getLogger(Avdelning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Avdelning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Avdelning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Avdelning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new Avdelning().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddAdress;
    private javax.swing.JButton btnAddAvbryt;
    private javax.swing.JButton btnAddAvdelning;
    private javax.swing.JButton btnAddBeskrivning;
    private javax.swing.JButton btnAddChef;
    private javax.swing.JButton btnAddEpost;
    private javax.swing.JButton btnAddNamn;
    private javax.swing.JButton btnAddStad;
    private javax.swing.JButton btnAddTelefon;
    private javax.swing.JButton btnFiltreraAvdelning;
    private javax.swing.JButton btnRedigeraAdress;
    private javax.swing.JButton btnRedigeraAdressAvbryt;
    private javax.swing.JButton btnRedigeraAvdelning;
    private javax.swing.JButton btnRedigeraBeskrivning;
    private javax.swing.JButton btnRedigeraBeskrivningAvbryt;
    private javax.swing.JButton btnRedigeraChef;
    private javax.swing.JButton btnRedigeraChefAvbryt;
    private javax.swing.JButton btnRedigeraEpost;
    private javax.swing.JButton btnRedigeraEpostAvbryt;
    private javax.swing.JButton btnRedigeraNamn;
    private javax.swing.JButton btnRedigeraNamnAvbryt;
    private javax.swing.JButton btnRedigeraStad;
    private javax.swing.JButton btnRedigeraStadAvbryt;
    private javax.swing.JButton btnRedigeraTelefon;
    private javax.swing.JButton btnRedigeraTelefonAvbryt;
    private javax.swing.JButton btnSePersonal;
    private javax.swing.JButton btnSok;
    private javax.swing.JButton btnSokPersonalPaMinAvdelning;
    private javax.swing.JButton btnTillbaka;
    private javax.swing.JComboBox<String> cbAddAvdelning;
    private javax.swing.JComboBox<String> cbFiltreraAvdelning;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAddAdress;
    private javax.swing.JLabel lblAddAtEpost;
    private javax.swing.JLabel lblAddAvdelning;
    private javax.swing.JLabel lblAddBeskrivning;
    private javax.swing.JLabel lblAddChef;
    private javax.swing.JLabel lblAddEpost;
    private javax.swing.JLabel lblAddNamn;
    private javax.swing.JLabel lblAddStad;
    private javax.swing.JLabel lblAddTelefon;
    private javax.swing.JLabel lblAvdelning;
    private javax.swing.JLabel lblFelmeddelande;
    private javax.swing.JLabel lblFiltreraAvdelning;
    private javax.swing.JLabel lblPersonal;
    private javax.swing.JLabel lblRedigeraAdress;
    private javax.swing.JLabel lblRedigeraAdressGata;
    private javax.swing.JLabel lblRedigeraAdressNr;
    private javax.swing.JLabel lblRedigeraAdressStad;
    private javax.swing.JLabel lblRedigeraAtEpost;
    private javax.swing.JLabel lblRedigeraBeskrivning;
    private javax.swing.JLabel lblRedigeraChef;
    private javax.swing.JLabel lblRedigeraEpost;
    private javax.swing.JLabel lblRedigeraNamn;
    private javax.swing.JLabel lblRedigeraStad;
    private javax.swing.JLabel lblRedigeraTelefon;
    private javax.swing.JLabel lblSokAtEpost;
    private javax.swing.JLabel lblSokEfternamn;
    private javax.swing.JLabel lblSokEpost;
    private javax.swing.JLabel lblSokFornamn;
    private javax.swing.JLabel lblSokNamn;
    private javax.swing.JLabel lblSokPersonal;
    private javax.swing.JTextArea taPersonal;
    private javax.swing.JTextField tfAddAdress;
    private javax.swing.JTextField tfAddBeskrivning;
    private javax.swing.JTextField tfAddChef;
    private javax.swing.JTextField tfAddEpost;
    private javax.swing.JTextField tfAddNamn;
    private javax.swing.JTextField tfAddStad;
    private javax.swing.JTextField tfAddTelefon;
    private javax.swing.JTextField tfRedigeraAdressGata;
    private javax.swing.JTextField tfRedigeraAdressNr;
    private javax.swing.JTextField tfRedigeraAdressStad;
    private javax.swing.JTextField tfRedigeraBeskrivning;
    private javax.swing.JTextField tfRedigeraChef;
    private javax.swing.JTextField tfRedigeraEpost;
    private javax.swing.JTextField tfRedigeraNamn;
    private javax.swing.JTextField tfRedigeraStad;
    private javax.swing.JTextField tfRedigeraTelefon;
    private javax.swing.JTextField tfSokEfternamn;
    private javax.swing.JTextField tfSokEpost;
    private javax.swing.JTextField tfSokFornamn;
    // End of variables declaration//GEN-END:variables
}
