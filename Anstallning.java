/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ngo2024;

import oru.inf.InfDB;
import oru.inf.InfException;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.Random;
import java.sql.Date;

/**
 *
 * @author JillWithJ
 */
public class Anstallning {
    
    private String inloggadAnv;
    private String anstalldID;
    private InfDB idb;
    
    public Anstallning(InfDB idb, String inloggadAnv){
        this.inloggadAnv = inloggadAnv;
        this.idb = idb;
        anstalldID = getInloggadAID(inloggadAnv);
        
    }
    
    public String getInloggadAID(String inloggadAnv){
        
        
        String aid = "";
                
        try{ 
        String sqlFraga = "SELECT aid FROM anstalld WHERE epost = '" + inloggadAnv + "'";
        aid = idb.fetchSingle(sqlFraga);
        }
        
        catch(InfException ex){
          System.out.println(ex.getMessage());
        }
        
        return aid;
    }
    
    public boolean getAdmin(String aid){
        
        String adminString;
        int adminInt;
        boolean admin = false;
        
        try{
            String sqlFraga = "SELECT behorighetsniva FROM admin WHERE aid = '" + aid + "'";
            adminString = idb.fetchSingle(sqlFraga);
            if(adminString != null){
                adminInt = Integer.parseInt(adminString);
                admin = (adminInt == 1);
            }
            
        }
        
        catch(InfException ex){
            System.out.println(ex.getMessage());
        }
                
        System.out.print("Admin: " + admin);
        
        
        return admin;
    }
    
    public boolean getMentor(String aid){
        
        String mentorString;
        boolean mentor = false;
        
        
        try{
            String sqlFraga = "SELECT mentor FROM handlaggare WHERE aid = '" + aid + "'";
            mentorString = idb.fetchSingle(sqlFraga);
            if(mentorString != null){
                mentor = true;
            }
        }
        
        catch(InfException ex){
            System.out.println(ex.getMessage());
        }
      
        return mentor;
    }
    
    public String getMentorTill(String aid){
        
        String mentorTill = "";
        String mentorString;
        boolean arMentor = getMentor(aid);
        
        try{
            String sqlFraga = "SELECT mentor FROM handlaggare WHERE aid = '" + aid + "'";
            mentorString = idb.fetchSingle(sqlFraga);
            if(mentorString != null && arMentor){
                mentorTill = mentorString;
            }
        }
        
        catch(InfException ex){
            System.out.println(ex.getMessage());
        }
        
        return mentorTill;
        
    }
    
    public String getNamn(String aid){
        
        String namn;
        String fornamn = "";
        String efternamn = "";
        
        try{
            String sqlFraga1 = "SELECT fornamn FROM anstalld WHERE aid = '" + aid + "'";
            String sqlFraga2 = "SELECT efternamn FROM anstalld WHERE aid = '" + aid + "'";
            fornamn = idb.fetchSingle(sqlFraga1);
            efternamn = idb.fetchSingle(sqlFraga2);
        }
        
        catch(InfException ex){
            System.out.println(ex.getMessage());
        }
        
        namn = fornamn + " " + efternamn;
        
        return namn;
        
    }
    
    public String getAtkomst(String aid){
        
        boolean arAdmin = getAdmin(aid);
        String atkomst = "Handläggare";
        
        if(arAdmin){
            atkomst = "Administratör";
        }
        
        return atkomst;
        
    }
    
    public void deleteAnstalld(String epost){
        
        String aid = getInloggadAID(epost);
        
        try{
            String sqlFraga = "DELETE FROM anstalld WHERE aid = '" + aid + "'";
            idb.delete(sqlFraga);
        }
    
        catch(InfException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void addAnstalld(String aid, String fornamn, String efternamn, String adress, String epost, String telefon, String anstallningsdatum, String losenord, String avdelning){
        
        try{
            String sqlFraga = "INSERT INTO anstalld (aid, fornamn, efternamn, adress, epost, telefon, anstallningdatum, losenord, avdelning) VALUES (" + aid + ", " + fornamn + ", " + efternamn + ", " + adress + ", " + epost + ", " + telefon + ", " + anstallningsdatum + ", " + losenord + ", " + avdelning + ")";
            idb.insert(sqlFraga);
            }
        
        catch(InfException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public String genereraEpost(String fornamn, String efternamn){
        
        String epost = fornamn + "." + efternamn + "@example.com";
        
        ArrayList<String> eposter = new ArrayList<>();
        Random random = new Random();
        
        try{
            String sqlFraga = "SELECT epost FROM anstalld";
            eposter = idb.fetchColumn(sqlFraga);
        }
        
        catch(InfException ex){
            System.out.println(ex.getMessage());
        }
        
        while (eposter.contains(epost)){
            int slumpInt = random.nextInt(99);
            String slumpString = Integer.toString(slumpInt);
            epost = slumpString + "@example.com";   
        }

        epost = epost.replace("@example.com", "");
        
        return epost;
    }
    
    public String genereraLosenord(){
        
        String losenord = "password";

        Random random = new Random();
        int slumpInt = random.nextInt(999);
        String slumpString = Integer.toString(slumpInt);
        losenord = losenord + slumpString;   
                
        return losenord;
    }
    
    public String setAID(){
        
        ArrayList<String> aids = new ArrayList<>();
        Random random = new Random();
        
        String aid;
        
        try{
            String sqlFraga = "SELECT aid FROM anstalld";
            aids = idb.fetchColumn(sqlFraga);
        }
        
        catch(InfException ex){
            System.out.println(ex.getMessage());
        }
        
        do{
            int slumpInt = random.nextInt(99);
            String slumpString = Integer.toString(slumpInt);
            aid = slumpString;
        }while (aids.contains(aid));
        
        return aid;
    }
    
    public void addProjekt(int pid,String projektnamn, String beskrivning, Date startdatum, Date slutdatum, Double kostnad, String status, String prioritet, int projektchef, int land){
        if(getAdmin(anstalldID)){
            try {
                String sqlFraga = "INSERT INTO projekt(pid, projektnamn, beskrivning, startdatum ,slutdatum, kostnad, status, prioritet, projektchef, land)VALUES ('"+ pid + "','"+ projektnamn + "','" + beskrivning + "','" + startdatum + "','"+ slutdatum + "','" + kostnad + "','" + status + "','" + prioritet + "','" + projektchef + "','" + land + "','";
                idb.insert(sqlFraga);
                System.out.println("Projektet är tillagt!");
            }catch (InfException ex) {
                System.out.println(ex.getMessage());
            }
        }else {
            System.out.println("Endast administratörer kan lägga till projekt");
        }
    }
    public void changeProjekt(int pid, String projektnamn, String beskrivning, Date startdatum, Date slutdatum, Double kostnad, String status, String prioritet,int projektchef, int land){
        if(getAdmin(anstalldID)){
            try {
                String sqlFraga = "UPDATE projekt SET projektnamn = '"+ projektnamn + "', beskrivning = '" + beskrivning + "',startdatum = '"+ startdatum + "',slutdatum = '" + slutdatum + "', kostnad = " + kostnad + ", status = '" + status + "', prioritet = '" + prioritet + "', projektchef = " + projektchef + ", land = " + land + "WHERE pid = " + pid;
                idb.update(sqlFraga);
                System.out.println("Projektet har ändrats");
            } catch (InfException ex){
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("Endast administratörer kan ändra uppgifter om ett projekt");
        }
    }
    public void removeProjekt(int pid){
        if(getAdmin(anstalldID)){
            try {
                String sqlFraga = "DELETE from projekt WHERE pid = " + pid;
                idb.delete(sqlFraga);
                System.out.println("Projektet har tagits bort!");
            } catch (InfException ex){
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("Endast administratörer kan ta bort projekt");
        }
    }
    public void addAvdelning(int avdid, String namn, String beskrivning, String adress, String epost, String telefon, int stad, int chef){
        if(getAdmin(anstalldID)){
            try{
                String sqlFraga = "INSERT INTO avdelning(avdid, namn, beskrivning, adress, epost, telefon, stad, chef) VALUES('" + avdid + "','"+ namn + "','" + beskrivning + "','" + adress + "','" + epost + "','" + telefon + "','" + stad + "','" + chef + "','";
                idb.insert(sqlFraga);
                System.out.println("Avdelningsuppgifter har tillagts");
            } catch (InfException ex){
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("Endast administratörer kan lägga till avdelningsuppgifter");
        }
    }
    public void changeAvdelning(int avdid, String namn, String beskrivning, String adress, String epost, String telefon, int stad, int chef){
        if(getAdmin(anstalldID)){
            try{
                String sqlFraga = "UPDATE avdelning SET namn = '" + namn + "',  beskrivning = '" + beskrivning + "', adress = '" + adress + "', epost = '" + epost + "', telefon = '" + telefon + "', stad = '" + stad + "', chef = '" + chef + "WHERE avdid = " + avdid;
                idb.update(sqlFraga);
                System.out.println("Avdelningsuppgifter har ändrats");
            } catch (InfException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("Endast administratörer kan ändra avdelningsuppgifter");
        }
    }
    public void addPartner(int pid, String namn, String kontaktperson, String kontaktepost, String telefon, String adress, String branch, int stad){
        if(getAdmin(anstalldID)){
            try{
                String sqlFraga = "INSERT INTO partner(pid, namn, kontaktperson, kontaktepost, telefon, adress, branch, stad) VALUES('" + pid + "','" + namn + "','" + kontaktperson + "','" + kontaktepost + "','" + telefon + "','" + adress + "','" + branch + "','" + stad + "','";
                idb.insert(sqlFraga);
                System.out.println("Partneruppgifter har lagts till");
            } catch (InfException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("Endast administratörer kan lägga till partners");
        }
    }
    public void changePartner(int pid, String namn, String kontaktperson, String kontaktepost, String telefon, String adress, String branch, int stad) {
        if(getAdmin(anstalldID)){
            try{
                String sqlFraga = "UPDATE partner SET namn = '" + namn + "', kontaktperson = '" + kontaktperson + "', kontaktepost = '" + kontaktepost + "', telefon = '" + telefon + "', adress = '" + adress + "', branch = '" + branch + "', stad = '" + stad + "WHERE pid = " + pid;
                idb.update(sqlFraga);
                System.out.println("Partneruppgifter har ändrats");
            } catch (InfException ex){
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("Endast administratörer kan ändra partners");
        }
    }
        public void removePartner(int pid){
            if(getAdmin(anstalldID)){
                try{
                    String sqlFraga = "DELETE from partner WHERE pid = " + pid;
                    idb.delete(sqlFraga);
                    System.out.println("Partnern har tagits bort");
                } catch (InfException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                System.out.println("Endast administratörer kan ta bort partners");
            }
        }
        public void addLand(int lid, String namn, String sprak, Double valuta, String tidszon, String politisk_struktur, String ekonomi){
            if(getAdmin(anstalldID)){
                try {
                    String sqlFraga = "INSERT INTO land(lid, namn, sprak, valuta, tidszon, politisk_struktur, ekonomi) VALUES ('" + lid + "','" + namn + "','" + sprak + "','" + valuta + "','" + tidszon + "','" + politisk_struktur + "','" + ekonomi + "','";
                    idb.insert(sqlFraga);
                    System.out.println("Landet har lagts till");
                } catch (InfException ex){
                    System.out.println(ex.getMessage());
                }
            } else {
                System.out.println("Endast administratörer kan lägga till ett land");
            }
        }
        public void changeLand(int lid, String namn, String sprak, Double valuta, String tidszon, String politisk_struktur, String ekonomi){
            if(getAdmin(anstalldID)){
                try{
                    String sqlFraga = "UPDATE land SET namn = '" + namn + "', språk = '" + sprak + "', valuta = '" + valuta + "', tidszon = '" + tidszon + "', politisk struktur = '" + politisk_struktur + "', ekonomi = '" + ekonomi + "WHERE lid = " + lid;
                    idb.update(sqlFraga);
                    System.out.println("Landets uppgifter har ändrats");
                } catch (InfException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                System.out.println("Endast administratörer kan ändra länder");
            }
        }
    }



