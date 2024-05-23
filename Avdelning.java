/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ngo2024;


import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author natha
 */
public class Avdelning {
    private InfDB idb;
    private String anstalldID;
    private Anstallning anstallning;
    private String avdelning;
    
    public Avdelning(InfDB idb , String aid) {
            this.idb = idb;
            anstalldID = aid;
            anstallning = new Anstallning(idb, anstalldID);
            avdelning = anstallning.getAvdelning(anstalldID);
    
}
    
    
    
    public ArrayList<HashMap<String, String>> getAvdelningar(){
    
        ArrayList<HashMap<String, String>> avdelningLista = new ArrayList<>();
        
        try{
        
        String sqlFraga = "SELECT * from avdelning";
        System.out.println(sqlFraga);
        ArrayList<HashMap<String, String>> avdelningar = idb.fetchRows(sqlFraga);
        
        avdelningLista.addAll(avdelningar);
        
        } catch (InfException ex) {
            System.out.println(ex.getMessage());
        }
       
         return avdelningLista;
    }
    
    public String getAvdelningIdFromAvdelning(String namn){
        
        String avdID = "";
        try{
            String sqlFraga = "SELECT avdid FROM avdelning WHERE namn = '" + namn + "'";
            avdID = idb.fetchSingle(sqlFraga);
        }
        catch (InfException ex) {
            System.out.println(ex.getMessage());
        }
        return avdID;
    }
    
    public ArrayList<HashMap<String, String>> getPersonalFromAvdelning(String avdelning){
    
        ArrayList<HashMap<String, String>> personalLista = new ArrayList<>();
        
        try{
        
        String sqlFraga = "SELECT * from anstalld where avdelning = '" + avdelning + "'";
        System.out.println(sqlFraga);
        ArrayList<HashMap<String, String>> anstallda = idb.fetchRows(sqlFraga);
        
        personalLista.addAll(anstallda);
        
        } catch (InfException ex) {
            System.out.println(ex.getMessage());
        }
       
         return personalLista;
    }
    
    public ArrayList<HashMap<String, String>> getPersonal(){
    
        ArrayList<HashMap<String, String>> personalLista = new ArrayList<>();
        
        try{
        
        String sqlFraga = "SELECT * from anstalld";
        System.out.println(sqlFraga);
        ArrayList<HashMap<String, String>> anstallda = idb.fetchRows(sqlFraga);
        
        personalLista.addAll(anstallda);
        
        } catch (InfException ex) {
            System.out.println(ex.getMessage());
        }
       
         return personalLista;
    }

    public void addAvdelning(String avdid, String namn, String beskrivning, String adress, String epost, String telefon, String stad, String chef){
        
        if(anstallning.getAdmin(anstalldID)){
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
    
    public void changeAvdelning(String avdid, String namn, String beskrivning, String adress, String epost, String telefon, String stad, String chef){
        
        if(anstallning.getAdmin(anstalldID)){
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
    
    public void changeCellAvdelning(String nyckel, String varde, String avdid){
        
        if(anstallning.getAdmin(anstalldID)){
            try{
                String sqlFraga = "UPDATE avdelning SET " + nyckel + " = '" + varde + "'"+ "WHERE avdid = '" + avdid + "'";
                idb.update(sqlFraga);
                System.out.println("Avdelningsuppgifter har ändrats");
            } catch (InfException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("Endast administratörer kan ändra avdelningsuppgifter");
        }
    }
    
    public HashMap<String, String> getHelAvdelning(String avdid){
    
        HashMap<String, String> avdelningMap = new HashMap<>();
        
        try{
        
        String sqlFraga = "SELECT * from avdelning WHERE avdid = '" + avdid + "'";
        avdelningMap = idb.fetchRow(sqlFraga);
                
        } catch (InfException ex) {
            System.out.println(ex.getMessage());
        }
       
         return avdelningMap;
    }
    
    public String setAvdID(){
        
        ArrayList<String> avdids = new ArrayList<>();
        Random random = new Random();
        
        String avdid;
        
        try{
            String sqlFraga = "SELECT avdid FROM avdelning";
            avdids = idb.fetchColumn(sqlFraga);
        }
        
        catch(InfException ex){
            System.out.println(ex.getMessage());
        }
        
        do{
            int slumpInt = random.nextInt(99);
            String slumpString = Integer.toString(slumpInt);
            avdid = slumpString;
        }while (avdids.contains(avdid));
        
        return avdid;
    }
    
}