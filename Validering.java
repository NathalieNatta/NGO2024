/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ngo2024;

import oru.inf.InfDB;
import oru.inf.InfException;
/**
 *
 * @author JillWithJ
 */
public class Validering {
    
    private InfDB idb;
    private String anstalldID;
    private Anstallning anstalld;
    
    public Validering(InfDB idb, String aid){
        
        this.idb = idb;
        anstalldID = aid;
        
        anstalld = new Anstallning(idb, anstalldID, "");
        
        
    }
    
    public String getEpost(String aid){
        
        String epost = "";
        
        try{ 
        String sqlFraga = "SELECT epost FROM anstalld WHERE aid = '" + aid + "'";
        epost = idb.fetchSingle(sqlFraga);
        }
        
        catch(InfException ex){
          System.out.println(ex.getMessage());
        }
        
        return epost;
    }
    
}
