/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ngo2024;

import oru.inf.InfDB;
import oru.inf.InfException;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Date;

/**
 *
 * @author natha, JillWithJ, Israa
 */
public class Projekt {
    
    private InfDB idb;
    private String anstalldID;
    //private String inloggadAnvandare;
    private String avdelning;
    private Anstallning anstalld;
    
    
    public Projekt(InfDB idb, String aid){
        this.idb = idb;
        anstalldID = aid;
        //int anstalldIdInt = Integer.parseInt(anstalldID);
        //this.inloggadAnvandare = inloggadAnvandare;
        this.avdelning = getMinAvdelning(anstalldID);
        //anstalld = new Anstallning(idb, anstalldID, "");
    }   
    public String getMinAvdelning(String aid)
        {
        String minAvdelning = "";
        try{ 
            String sqlFraga = "SELECT avdelning from anstalld where aid = '" + aid + "'";
            minAvdelning = idb.fetchSingle(sqlFraga);
  

        } catch (InfException ex) {
            System.out.println(ex.getMessage());
        }
        return minAvdelning;
    
    }
        
    public String getProjektID(String projektnamn)
        {
        String projektID = "";
        
        try{ 
            String sqlFraga = "SELECT pid from projekt where projektnamn = '" + projektnamn + "'";
            projektID = idb.fetchSingle(sqlFraga);
  

        } catch (InfException ex) {
            System.out.println(ex.getMessage());
        }
        return projektID;
    
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
    
    public ArrayList<HashMap<String,String>> getAllaProjekt()
        {
        ArrayList<HashMap<String, String>> projektlista = new ArrayList<>();
        
        try{ 
            String sqlFraga = "SELECT * FROM projekt";
            ArrayList<HashMap<String,String >> resultat = idb.fetchRows(sqlFraga);
  
            projektlista.addAll(resultat);
            
        } catch (InfException ex) {
            System.out.println(ex.getMessage());
        }
        return projektlista;
    
    } 
    
    public ArrayList<HashMap<String,String>> getAllprojectsFromAvdelning(String avdelning) {
        ArrayList<HashMap<String, String>> projektlista = new ArrayList<>();
               
        try{
            String sqlFraga = "SELECT p.* from projekt p JOIN ans_proj ap ON p.pid = ap.pid JOIN anstalld a ON ap.aid = a.aid WHERE a.avdelning = '" + avdelning + "'";
            System.out.println(sqlFraga);
            ArrayList<HashMap<String,String >> resultat =  idb.fetchRows(sqlFraga);
    
            projektlista.addAll(resultat);
        
        } catch (InfException ex){
           System.out.println(ex.getMessage());
         
        }
        return projektlista;
    }
    
    public ArrayList<HashMap<String, String>> getMinaProjekt(String aid) {
        ArrayList<HashMap<String, String>> projektlista = new ArrayList<>();
             
        
        try{
        String sqlFraga =  "Select * from projekt p join ans_proj ap on p.pid =ap.pid join anstalld a on ap.aid = a.aid where a.aid =   '" + aid + "'";  
        System.out.println(sqlFraga);
      HashMap<String, String> rad = idb.fetchRow(sqlFraga);
      
      
      projektlista.add(rad);
       
      
        }
        catch(InfException ex){
            System.out.println(ex.getMessage());
        
        }
        return projektlista;
    }  
    
    public ArrayList<HashMap<String,String>> getProjectsOnAvdelningByStatus(String avdelning,String status){
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
    
    public ArrayList<HashMap<String, String>> listAllPartnersInProjects(String aid) {
          ArrayList<HashMap<String, String>> partnerlista = new ArrayList<>();
          
      try{
    String sqlFraga = "Select partner.* from partner join ans_proj ap on partner.pid = ap.pid join anstalld a on ap.pid = a.aid where a.aid = '" + aid + "'";
    System.out.println(sqlFraga);
    ArrayList<HashMap<String, String>> partners = idb.fetchRows(sqlFraga);
    
    partnerlista.addAll(partners);


}  catch(InfException ex) {
     System.out.println(ex.getMessage());
} 
      return partnerlista;
}
    
    public ArrayList<HashMap<String, String>> getProjectsByDate(String avdelning){
            ArrayList<HashMap<String, String>> projektdatum = new ArrayList<>();
            
            
            try{
    String sqlFraga = "SELECT p.* from projekt p join ans_proj ap on  p.pid = ap.pid  join anstalld a on ap.aid = a.aid where avdelning =  '" + avdelning + "' and p.status != 'avslutat' order by p.startdatum ";
           System.out.println(sqlFraga);
           ArrayList<HashMap<String, String>> sqlsvar = idb.fetchRows(sqlFraga);
           
           projektdatum.addAll(sqlsvar);
            
            }catch(InfException ex) {
                System.out.println(ex.getMessage());
            }
            return projektdatum;
}
    public void changeProjektNamn(int projektchef, int pid, String nyttProjektNamn) {
        try {
            String sqlFraga = "SELECT projektnamn WHERE projektchef = '" + projektchef + "' AND pid = '" + pid + "'";
            HashMap<String, String> projektInfo = idb.fetchRow(sqlFraga);
            
            if(projektInfo !=null){
                String namnUppdatering = "UPDATE projekt SET projektnamn = '" + nyttProjektNamn + "where pid = '"+ pid + "'";
            idb.update(namnUppdatering);
            System.out.println("Projektnamn ändrat till" + nyttProjektNamn);
            } else {
                System.out.println("Projektchefen är inte chef över detta projekt");
            }
        
        
        
        }catch (InfException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public void changeProjektBeskrivning(int projektchef, int pid, String beskrivning) {
        try{
        
            String sqlFraga = "SELECT beskrivning WHERE projektchef = '" + projektchef + "AND pid = '" + pid + "'";
            HashMap<String, String> projektInfo = idb.fetchRow(sqlFraga);
            
            if(projektInfo != null) {
                String beskrivningUppdatering = "UPDATE projekt SET beskrivning = '" + beskrivning + "where pid = '" + pid + "'";
                idb.update(beskrivningUppdatering);
                System.out.println("Beskrivning för projekt har ändrats" + beskrivning);
            }else {
            System.out.println("Projektchefen är inte chef över detta projekt");
            }
            
        
        } catch (InfException ex){
            System.out.println(ex.getMessage());
        }
    }
    public void changeProjektStartdatum(int projektchef, int pid, Date startdatum){
        try{
            String sqlFraga = "SELECT startdatum WHERE projektchef = '" + projektchef + "AND pid = '" + pid + "'";
            HashMap<String, String> projektInfo = idb.fetchRow(sqlFraga);
            
            if(projektInfo !=null) {
                String startdatumUppdatering = "UPDATE projekt SET startdatum = '" + startdatum + "where pid = '" + pid + "'";
                idb.update(startdatumUppdatering);
                System.out.println("Startdatum för projektet har uppdaterats" + startdatum);
            }else {
                System.out.println("Projektchefen är inte chef över detta projekt");
            }
            
        }catch (InfException ex) {
            System.out.println(ex.getMessage());
        }
        
       
    }
    public void changeProjektSlutDatum(int projektchef, int pid, Date slutdatum){
        try {
            String sqlFraga = "SELECT startdatum WHERE projektchef = '" + projektchef + "AND pid = '" + pid + "'";
            HashMap<String, String> projektInfo = idb.fetchRow(sqlFraga);
            
            if(projektInfo !=null){
                String slutdatumUppdatering = "UPDATE projekt SET slutdatum = '" + slutdatum + "where pid = '" + pid +"'";
                idb.update(slutdatumUppdatering);
                System.out.println("Projektchefen är inte chef  över detta projekt");
            }
        }catch(InfException ex){
           System.out.println(ex.getMessage());
        }
    }
    public void changeProjektKostnad(int projektchef, int pid, double kostnad){
        try {
            String sqlFraga = "SELECT kostnad WHERE projektchef = '" + projektchef + "AND pid = '" + pid + "'";
            HashMap<String, String> projektInfo = idb.fetchRow(sqlFraga);
            
            if(projektInfo !=null){
            String kostnadUppdatering = "UPDATE projekt SET kostnad = '" + kostnad +"where pid = '" + pid + "'";
            idb.update(kostnadUppdatering);
            System.out.println("Projektchefen är inte chef över detta projekt");
        }
    }catch(InfException ex) {
        System.out.println(ex.getMessage());
    }
    }
    public void changeProjektStatus(int projektchef, int pid, String status){
      try {
            String sqlFraga = "SELECT status WHERE projektchef = '" + projektchef + "AND pid = '" + pid + "'";
            HashMap<String, String> projektInfo = idb.fetchRow(sqlFraga);
            
    if(projektInfo != null){
            String statusUppdatering = "UPDATE projekt SET status = '" + status +"where pid = '" + pid + "'";
            idb.update(statusUppdatering);
            System.out.println("Projektchefen är inte chef över detta projekt");
        }
    
    }catch(InfException ex){
        System.out.println(ex.getMessage());
        
    }
}
    public void changeProjektPrioritet(int projektchef, int pid, String prioritet){
        try {
            String sqlFraga = "SELECT prioritet WHERE projektchef = '" + projektchef + "AND pid = '" + pid + "'";
            HashMap<String, String> projektInfo = idb.fetchRow(sqlFraga);
            
           if(projektInfo != null){
            String prioritetUppdatering = "UPDATE projekt SET prioritet = '" + prioritet +"where pid = '" + pid + "'";
            idb.update(prioritetUppdatering);
            System.out.println("Projektchefen är inte chef över detta projekt"); 
    }
}catch (InfException ex){
    System.out.println(ex.getMessage());
}
    }
    public void changeProjektLand(int projektchef, int pid, int land){
        try {
            String sqlFraga = "SELECT land WHERE projektchef = '" + land + "AND pid = '" + pid + "'";
            HashMap<String, String> projektInfo = idb.fetchRow(sqlFraga);
            
            if(projektInfo != null){
            String landUppdatering = "UPDATE projekt SET land = '" + land +"where pid = '" + pid + "'";
            idb.update(landUppdatering);
            System.out.println("Projektchefen är inte chef över detta projekt");
    }
    }catch (InfException ex){
        System.out.println(ex.getMessage());
    }
}
    public void addProjektPartner(int projektchef, int pid, String namn,String kontaktperson, String kontaktepost, String telefon, String adress, String branch,int stad){
        try{
            String sqlFraga = "SELECT projektchef FROM projekt WHERE pid = ' " + pid + "'";
            HashMap<String, String> projektInfo = idb.fetchRow(sqlFraga);
            
            if(projektInfo !=null && Integer.parseInt(projektInfo.get("projektchef"))== projektchef){
            String kontrolleraPartnerFraga = "SELECT pid FROM partner WHERE namn = '" + namn + "' AND kontaktperson = '" + kontaktperson + "'";
            HashMap<String, String> partnerInfo = idb.fetchRow(kontrolleraPartnerFraga);
            int partnerID;
            
            if(partnerInfo != null){
                partnerID = Integer.parseInt(partnerInfo.get("pid"));
            }else {
                String addPartnerFraga = "INSERT INTO Partner(namn, kontaktperson, kontaktepost,telefon, adress, branch,stad) VALUES ('" + namn + "','" + kontaktperson + "','"+kontaktepost+"','"+ telefon + "','"+ adress + "','"+ branch + "','"+ stad + ")";
                idb.insert(addPartnerFraga);
                partnerInfo = idb.fetchRow(kontrolleraPartnerFraga);
                partnerID = Integer.parseInt(partnerInfo.get("pid"));
            }
            String addProjektPartnerFraga = "INSERT INTO projekt_partner(pid, partner_pid) VALUES ("+ pid + "," + partnerID + ")";
            idb.insert(addProjektPartnerFraga);
            System.out.println("Partnern har lagts till i projektet");
        } else {
                System.out.println("Projektchefen är inte chef över detta projekt eller projektet existerar inte");
                }
        }catch (InfException ex){
            System.out.println(ex.getMessage());
        }catch (NumberFormatException ex){
            System.out.println("Ett fel inträffade vid nummerformatkonvertering: " + ex.getMessage());
        }
    }
    public void removeProjektPartner(int projektchef, int pid, int partnerID) {
            try {
        String sqlFraga = "SELECT projektchef FROM projekt WHERE pid = '" + pid;
        HashMap<String, String> projektInfo = idb.fetchRow(sqlFraga);
        
        if(projektInfo != null && Integer.parseInt(projektInfo.get("projektchef"))== projektchef){
                String taBortPartnerFromProjektFraga = "DELETE from projekt_partner WHERE pid = '" + pid + "AND partner_pid = '" + partnerID + "'";
                idb.delete(taBortPartnerFromProjektFraga);
                System.out.println("Partnern har tagits bort från projektet");
            
        } else {
                System.out.println("Projektchefen är inte chef över detta projekt eller existerar inte");
                }
    }catch(InfException ex){
    System.out.println(ex.getMessage());
}catch(NumberFormatException ex){
    System.out.println("Ett fel inträffade vid nummerformatkonvertering: " + ex.getMessage());
}
}
    public void addHandlaggareTillProjekt(int projektchef, int pid, int aid){
        try{
            String sqlFraga = "SELECT projektchef FROM projekt WHERE pid '" + pid + "'";
            HashMap<String, String> projektInfo = idb.fetchRow(sqlFraga);
            
            if(projektInfo !=null && Integer.parseInt(projektInfo.get("projektchef")) == projektchef) {
                String addHandlaggareFraga = "INSERT INTO ans_proj (pid, aid) VALUES ('" + pid + "','" + aid + "')";
                idb.insert(addHandlaggareFraga);
                System.out.println("Handläggaren har lagts till i projektet");
            }else {
                System.out.println("Projektchefen är inte chef över detta projekt eller projektet finns inte");
            }
        }catch (InfException ex){
            System.out.println(ex.getMessage());
        }catch (NumberFormatException ex){
            System.out.println("Ett fel inträffade vid nummerformatkonvertering" + ex.getMessage());
        }
    }
    public void removeHandlaggareFromProjekt(int projektchef, int pid, int aid){
        try{
            String sqlFraga = "SELECT projektchef FROM projekt WHERE pid = '" + pid + "'";
            HashMap<String, String> projektInfo = idb.fetchRow(sqlFraga);
            
            if(projektInfo != null && Integer.parseInt(projektInfo.get("projektchef")) == projektchef){
                String removeHandlaggareFraga = "DELETE FROM ans_proj WHERE pid = '" + pid + "AND aid = '"+ aid + "'";
                idb.delete(removeHandlaggareFraga);
                System.out.println("Handläggaren har tagits bort från projektet");
            }else {
                System.out.println("Användaren är inte projektledare för detta projektet eller projektet finns inte");
            }
        }catch (InfException ex) {
            System.out.println(ex.getMessage());
        }catch (NumberFormatException ex){
            System.out.println("Ett fel inträffade vid nummerformatering" + ex.getMessage());
        }
    }
    public double getProjektKostnadStatistik(String projektchef){
        double totalKostnad = 0.0;
        
        try{
            String sqlFraga = "SELECT kostnad FROM projekt WHERE projektchef = '" + projektchef + "'";
            ArrayList<HashMap<String, String>> kostnadResultat = idb.fetchRows(sqlFraga);
            
            for(HashMap<String, String> projekt : kostnadResultat){
                String kostnadString = projekt.get("kostnad");
                if(kostnadString != null && !kostnadString.isEmpty()){
                    totalKostnad += Double.parseDouble(kostnadString);
                }
            }
        }catch (InfException ex){
            System.out.println(ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("Ett fel inträffade vid nummerformatering " + ex.getMessage());
        }
        return totalKostnad;
    }
    
     public void addProjekt(String pid, String projektnamn, String beskrivning, Date startdatum, Date slutdatum, Double kostnad, String status, String prioritet, String projektchef, String land){
        if(anstalld.getAdmin(anstalldID)){
            try {
                String sqlFraga = "INSERT INTO projekt(pid, projektnamn, beskrivning, startdatum ,slutdatum, kostnad, status, prioritet, projektchef, land)VALUES ('"+ pid + "','"+ projektnamn + "','" + beskrivning + "','" + startdatum + "','"+ slutdatum + "','" + kostnad + "','" + status + "','" + prioritet + "','" + projektchef + "','" + land + "','";
                idb.insert(sqlFraga);
                System.out.println("Projektet är tillagt!");
            }catch (InfException ex) {
                System.out.println(ex.getMessage());
            }
        }else {
            System.out.println("Endast administratörer kan lägga till projekt");
        }
    }
    public void changeProjekt(String pid, String projektnamn, String beskrivning, Date startdatum, Date slutdatum, Double kostnad, String status, String prioritet, String projektchef, String land){
        if(anstalld.getAdmin(anstalldID)){
            try {
                String sqlFraga = "UPDATE projekt SET projektnamn = '"+ projektnamn + "', beskrivning = '" + beskrivning + "',startdatum = '"+ startdatum + "',slutdatum = '" + slutdatum + "', kostnad = " + kostnad + ", status = '" + status + "', prioritet = '" + prioritet + "', projektchef = " + projektchef + ", land = " + land + "WHERE pid = '" + pid + "'";
                idb.update(sqlFraga);
                System.out.println("Projektet har ändrats");
            } catch (InfException ex){
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("Endast administratörer kan ändra uppgifter om ett projekt");
        }
    }
    public void removeProjekt(String pid){
        if(anstalld.getAdmin(anstalldID)){
            try {
                String sqlFraga = "DELETE from projekt WHERE pid = '" + pid + "'";
                idb.delete(sqlFraga);
                System.out.println("Projektet har tagits bort!");
            } catch (InfException ex){
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("Endast administratörer kan ta bort projekt");
        }
    }
    
    public void addPartner(int pid, String namn, String kontaktperson, String kontaktepost, String telefon, String adress, String branch, int stad){
        if(anstalld.getAdmin(anstalldID)){
            try{
                String sqlFraga = "INSERT INTO partner(pid, namn, kontaktperson, kontaktepost, telefon, adress, branch, stad) VALUES('" + pid + "','" + namn + "','" + kontaktperson + "','" + kontaktepost + "','" + telefon + "','" + adress + "','" + branch + "','" + stad + "','";
                idb.insert(sqlFraga);
                System.out.println("Partneruppgifter har lagts till");
            } catch (InfException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("Endast administratörer kan lägga till partners");
        }
    }
    
    public void changePartner(String pid, String namn, String kontaktperson, String kontaktepost, String telefon, String adress, String branch, String stad) {
        if(anstalld.getAdmin(anstalldID)){
            try{
                String sqlFraga = "UPDATE partner SET namn = '" + namn + "', kontaktperson = '" + kontaktperson + "', kontaktepost = '" + kontaktepost + "', telefon = '" + telefon + "', adress = '" + adress + "', branch = '" + branch + "', stad = '" + stad + "WHERE pid = '" + pid + "'";
                idb.update(sqlFraga);
                System.out.println("Partneruppgifter har ändrats");
            } catch (InfException ex){
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("Endast administratörer kan ändra partners");
        }
    }
    
        public void removePartner(int pid){
            if(anstalld.getAdmin(anstalldID)){
                try{
                    String sqlFraga = "DELETE from partner WHERE pid = " + pid;
                    idb.delete(sqlFraga);
                    System.out.println("Partnern har tagits bort");
                } catch (InfException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                System.out.println("Endast administratörer kan ta bort partners");
            }
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


