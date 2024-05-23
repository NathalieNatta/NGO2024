/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ngo2024;

import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.ArrayList;
import java.util.Random;

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
    
    public Anstallning(InfDB idb, String aid, String inget){
        this.idb = idb;
        anstalldID = aid;
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
    
    public String getAvdelning(String aid){
        
        String avd = "";
        
        try{ 
        String sqlFraga = "SELECT avdelning FROM anstalld WHERE aid = '" + aid + "'";
        avd = idb.fetchSingle(sqlFraga);
        }
        
        catch(InfException ex){
          System.out.println(ex.getMessage());
        }
        
        return avd;
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
    
    
}


