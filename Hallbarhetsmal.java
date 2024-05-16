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
public class Hallbarhetsmal {
  private InfDB idb;
  private String inloggadAnvandare;  
  
  
  public Hallbarhetsmal(InfDB idb, String inloggadAnvandare) {
      this.idb = idb;
      this.inloggadAnvandare = inloggadAnvandare;
      
  }
  
  public ArrayList<HashMap<String, String>> getHallbarhetMal() {
    ArrayList<HashMap<String, String>> hallbarhetsLista = new ArrayList<>();  
    
    
    try{
        String sqlFraga = "SELECT * from hallbarhetsmal"; 
        System.out.println(sqlFraga);
       ArrayList<HashMap<String, String>> lista = idb.fetchRows(sqlFraga);
       
       hallbarhetsLista.addAll(lista);
    
    } catch (InfException ex) {
        System.out.println(ex.getMessage());
    }
    
    return hallbarhetsLista;
  }
  
  
  
}
