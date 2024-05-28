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
public class Stad {
    
    private String anstalldID;
    private InfDB idb;

    public Stad(InfDB idb, String aid){
        
        this.anstalldID = aid;
        this.idb = idb;
        
    }
    
    public String getStad(String sid){
        
        String stad = "";
        
        try{ 
            String sqlFraga = "SELECT namn from stad where sid = '" + sid + "'";
            stad = idb.fetchSingle(sqlFraga);
  

        } catch (InfException ex) {
            System.out.println(ex.getMessage());
        }
        return stad;
    }
    
    public String getStadID(String namn){
        
        String stad = "";
        
        try{ 
            String sqlFraga = "SELECT sid from stad where namn = '" + namn + "'";
            stad = idb.fetchSingle(sqlFraga);
  

        } catch (InfException ex) {
            System.out.println(ex.getMessage());
        }
        return stad;
    }
    
}
