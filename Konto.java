/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ngo2024;

import oru.inf.InfDB;
import oru.inf.InfException;

/**
 *
 * @author JillWithJ, Natha, Israael
 */
public class Konto {
    
    private InfDB idb;
    private String anstalldID;
    
    public Konto(String anstalldID, InfDB idb){
        
        this.idb = idb;
        this.anstalldID = anstalldID;
    }
    
    
    
    public String getEpost(){
        
        String epost = "";
        
        try{
            String sqlFraga = "SELECT epost FROM anstalld WHERE aid = '" + anstalldID + "'";
            epost = idb.fetchSingle(sqlFraga);
        }
        
        catch(InfException ex){
            System.out.println(ex.getMessage());
        }
      
        return epost;
    }
    
    public String getLosenord(){
        
        String losenord = "";
        
        try{
            String sqlFraga = "SELECT losenord FROM anstalld WHERE aid = '" + anstalldID + "'";
            losenord = idb.fetchSingle(sqlFraga);
        }
        
        catch(InfException ex){
            System.out.println(ex.getMessage());
        }
      
        return losenord;
    }
    
    public String getAdress(){
        
        String adress = "";
        
        try{
            String sqlFraga = "SELECT adress FROM anstalld WHERE aid = '" + anstalldID + "'";
            adress = idb.fetchSingle(sqlFraga);
        }
        
        catch(InfException ex){
            System.out.println(ex.getMessage());
        }
      
        return adress;
    }
    
    public String getTelefonNummer(){
        
        String telefonNr = "";
        
        try{
            String sqlFraga = "SELECT telefon FROM anstalld WHERE aid = '" + anstalldID + "'";
            telefonNr = idb.fetchSingle(sqlFraga);
        }
        
        catch(InfException ex){
            System.out.println(ex.getMessage());
        }
      
        return telefonNr;
    }
    
    public void setEpost(String nyEpost){
                
        try{
           String sqlFraga = "UPDATE anstalld SET epost = '" + nyEpost + "@example.com" + "' WHERE aid = '" + anstalldID + "'";
           idb.update(sqlFraga);
        }
        
        catch(InfException ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void setLosenord(String nyttLosenord){
                
        try{
            String sqlFraga = "UPDATE anstalld SET losenord = '" + nyttLosenord + "' WHERE aid = '" + anstalldID + "'";
            idb.update(sqlFraga);
            
        }
        
        catch(InfException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void setAdress(String nyAdress){
         
            try{
                String sqlFraga = "UPDATE anstalld SET adress = '" +nyAdress + "' WHERE aid = '" + anstalldID + "'" ;
                idb.update(sqlFraga);
            }
        
            catch(InfException ex){
                System.out.println(ex.getMessage());
            }
    }
    
    public void setTelefonNummer(String nyttTelefonNummer){
                
        try{
            String sqlFraga = "UPDATE anstalld SET telefon = " + nyttTelefonNummer + " WHERE aid = '" + anstalldID + "'";
            idb.update(sqlFraga);
        }
        
        catch(InfException ex){
            System.out.println(ex.getMessage());
        }
    }
}
