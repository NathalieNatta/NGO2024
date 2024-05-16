/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ngo2024;

import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author natha
 */
public class Projekt {
    private InfDB idb;
    private String inloggadAnvandare;
    private int avdelning;
    
    
    public Projekt(InfDB idb, String inloggadAnvandare, int aid){
        this.idb = idb;
        this.inloggadAnvandare = inloggadAnvandare;
        this.avdelning = getMinAvdelning(aid);
    }   
    public int getMinAvdelning(int aid)
        {
String minavdelning = "";
try{ 
String sqlFraga = "SELECT avdelning from anstalld where aid = '" + aid + "'";
    minavdelning = idb.fetchSingle(sqlFraga);
  

} catch (InfException ex) {
System.out.println(ex.getMessage());
}
return Integer.parseInt(minavdelning);
    
}
        
        
        
    
    public ArrayList<HashMap<String,String>> getAllprojectsFromAvdelning(int avdelning) {
       ArrayList<HashMap<String, String>> projektlista = new ArrayList<>();
               
       try{
       String sqlFraga = "SELECT p.* from projekt p JOIN ans_proj ap ON p.pid = ap.pid JOIN anstalld a ON ap.aid = a.aid WHERE a.avdelning = " + avdelning;
       System.out.println(sqlFraga);
    ArrayList<HashMap<String,String >> resultat =  idb.fetchRows(sqlFraga);
    

            
        
        projektlista.addAll(resultat);
    
    
    
            
           
       
       } catch (InfException ex){
           System.out.println(ex.getMessage());
           
       
           
       }
       return projektlista;
    }
    public ArrayList<HashMap<String, String>> getMinaProjekt(int aid) {
        ArrayList<HashMap<String, String>> projektlista = new ArrayList<>();
             
        
        try{
        String sqlFraga =  "Select * from projekt p join ans_proj ap on p.pid =ap.pid join anstalld a on ap.aid = a.aid where a.aid =   " + aid;  
        System.out.println(sqlFraga);
      HashMap<String, String> rad = idb.fetchRow(sqlFraga);
      
      
      projektlista.add(rad);
       
      
        }
        catch(InfException ex){
            System.out.println(ex.getMessage());
        
        }
        return projektlista;
    }  
    
    public ArrayList<HashMap<String,String>> getProjectsOnAvdelningByStatus(int avdelning,String status){
        ArrayList<HashMap<String, String>> statuslista = new ArrayList<>();
        
        
        try{
            
            String sqlFraga = "Select * from projekt p join ans_proj ap on p.pid = ap.pid join anstalld a on ap.aid = a.aid where avdelning = " + avdelning + "and p.status = " + status + "order by p.status";
             System.out.println(sqlFraga);
            ArrayList<HashMap<String, String>> rader = idb.fetchRows(sqlFraga);
             
            statuslista.addAll(rader);
            
        }catch(InfException ex) {
            System.out.println(ex.getMessage());
            
        }
        return statuslista;
    }
    
    public ArrayList<HashMap<String, String>> listAllPartnersInProjects(int aid) {
          ArrayList<HashMap<String, String>> partnerlista = new ArrayList<>();
          
      try{
    String sqlFraga = "Select partner.* from partner join ans_proj ap on partner.pid = ap.pid join anstalld a on ap.pid = a.aid where a.aid = " + aid;
    System.out.println(sqlFraga);
    ArrayList<HashMap<String, String>> partners = idb.fetchRows(sqlFraga);
    
    partnerlista.addAll(partners);


}  catch(InfException ex) {
     System.out.println(ex.getMessage());
} 
      return partnerlista;
}
    
    public ArrayList<HashMap<String, String>> getProjectsByDate(int avdelning){
            ArrayList<HashMap<String, String>> projektdatum = new ArrayList<>();
            
            
            try{
    String sqlFraga = "SELECT p.* from projekt p join ans_proj ap on  p.pid = ap.pid  join anstalld a on ap.aid = a.aid where avdelning =  " + avdelning + "and p.status != 'avslutat' order by p.startdatum ";
           System.out.println(sqlFraga);
           ArrayList<HashMap<String, String>> sqlsvar = idb.fetchRows(sqlFraga);
           
           projektdatum.addAll(sqlsvar);
            
            }catch(InfException ex) {
                System.out.println(ex.getMessage());
            }
            return projektdatum;
}
}
