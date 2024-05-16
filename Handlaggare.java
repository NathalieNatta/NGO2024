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
public class Handlaggare {
  private InfDB idb;
    private String inloggadAnvandare;
    
    public Handlaggare(InfDB idb , String inloggadAnvandare) {
            this.idb = idb;
            this.inloggadAnvandare = inloggadAnvandare; 
      
}
    public ArrayList<HashMap<String, String>> getHandlaggarefromAvdelning(int avdelning, String fornamn, String efternamn, String epost){
        
        ArrayList<HashMap<String, String>> handlaggare = new ArrayList<>();
        
        try{
         String sqlFraga = "SELECT h.* from handlaggare h join anstalld a on h.aid = a.aid where avdelning = " + avdelning + " and fornamn = " + fornamn + "and efternamn = " + efternamn + "or epost = " + epost;
          System.out.println(sqlFraga);
          HashMap<String, String> resultat = idb.fetchRow(sqlFraga);
          
          handlaggare.add(resultat);
            
        }catch(InfException ex){
            System.out.println(ex.getMessage());
        }
        return handlaggare;
    }
    }
    

