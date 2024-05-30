/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ngo2024;

import java.util.ArrayList;
import oru.inf.InfDB;
import oru.inf.InfException;
/**
 *
 * @author JillWithJ, Natha, Israael
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
    
    public boolean arProjektChef(String aid){
        
        boolean arChef = false;
        
        ArrayList<String> chefer = new ArrayList<String>();
        try{ 
            String sqlFraga = "SELECT projektchef from projekt";
            chefer = idb.fetchColumn(sqlFraga);
  

        } catch (InfException ex) {
            System.out.println(ex.getMessage());
        }
        for( String chef : chefer ){
            if(chef.equals(aid)){
                arChef = true;
            }
        }
        return arChef;
    }
    
    
    
}
