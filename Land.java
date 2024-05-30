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
public class Land {
    
    private InfDB idb;
    private String anstalldID;
    private Anstallning anstalld;
    private Validering validering;
    
    public Land(InfDB idb, String aid){
        
        this.idb = idb;
        anstalldID = aid;
        anstalld = new Anstallning(idb, anstalldID, "");
        validering = new Validering(idb, anstalldID);
    }
    
    public String getLandID(String namn)
    {
        String landID = "";
        
        try{ 
            String sqlFraga = "SELECT lid from land where namn = '" + namn + "'";
            landID = idb.fetchSingle(sqlFraga);
  

        } catch (InfException ex) {
            System.out.println(ex.getMessage());
        }
        return landID;
    }
    
    public void addLand(String lid, String namn, String sprak, Double valuta, String tidszon, String politisk_struktur, String ekonomi){
            if(anstalld.getAdmin(anstalldID)){
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
        
        public void changeLand(String lid, String namn, String sprak, Double valuta, String tidszon, String politisk_struktur, String ekonomi){
            if(anstalld.getAdmin(anstalldID)){
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
