/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ngo2024;

import oru.inf.InfDB;
import oru.inf.InfException;
import java.lang.Integer;

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
    

    public String getNamn(String aid){
        
        String namn;
        String fornamn = "";
        String efternamn = "";
        
        try{
            String sqlFraga1 = "SELECT fornamn FROM anstalld WHERE epost = '" + inloggadAnv + "'";
            String sqlFraga2 = "SELECT efternamn FROM anstalld WHERE epost = '" + inloggadAnv + "'";
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
    
}
