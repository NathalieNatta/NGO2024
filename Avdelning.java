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
public class Avdelning {
    private InfDB idb;
    private String inloggadAnvandare;
    
    public Avdelning(InfDB idb , String inloggadAnvandare) {
            this.idb = idb;
            this.inloggadAnvandare = inloggadAnvandare; 
    
}
    public ArrayList<HashMap<String, String>> getPersonalfromAvdelning(int avdelning){
    
        ArrayList<HashMap<String, String>> personalLista = new ArrayList<>();
        
        try{
        
        String sqlFraga = "SELECT * from anstalld where avdelning = " + avdelning;
        System.out.println(sqlFraga);
        ArrayList<HashMap<String, String>> anstallda = idb.fetchRows(sqlFraga);
        
        personalLista.addAll(anstallda);
        
        } catch (InfException ex) {
            System.out.println(ex.getMessage());
        }
       
         return personalLista;
    }

    
    
    
}