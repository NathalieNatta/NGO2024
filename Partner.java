/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ngo2024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import oru.inf.InfDB;
import oru.inf.InfException;
/**
 *
 * @author JillWithJ
 */
public class Partner {

    private InfDB idb;
    private String anstalldID;
    private Anstallning anstalld;
    private Projekt projekt;

    public Partner(InfDB idb, String aid) {

        this.idb = idb;
        anstalldID = aid;
        anstalld = new Anstallning(idb, anstalldID, "");
        projekt = new Projekt(idb, anstalldID);

    }

    public String getPartnerID(String partnernamn) {

        String partnerID = "";

        try {
            String sqlFraga = "SELECT pid from partner where namn = '" + partnernamn + "'";
            partnerID = idb.fetchSingle(sqlFraga);

        } catch (InfException ex) {
            System.out.println(ex.getMessage());
        }
        return partnerID;
    }


    public HashMap<String, String> getPartner(String partnerID) {
        
        HashMap<String, String> partner = new HashMap<>();
        
        try {
            String sqlFraga = "SELECT * FROM partner WHERE pid = '" + partnerID + "'";
            partner = idb.fetchRow(sqlFraga);
               
        } catch (InfException ex) {
            System.out.println(ex.getMessage());
        }
        return partner;
    }
    
    public ArrayList<HashMap<String, String>> getAllPartners() {
        
        ArrayList<HashMap<String, String>> partners = new ArrayList<>();
        
        try {
            String sqlFraga = "SELECT * FROM partner";
            partners = idb.fetchRows(sqlFraga);
               
        } catch (InfException ex) {
            System.out.println(ex.getMessage());
        }
        return partners;
    }
    
    public String setPartnerID(){
        
        ArrayList<String> pids = new ArrayList<>();
        Random random = new Random();
        
        String pid;
        
        try{
            String sqlFraga = "SELECT pid FROM partner";
            pids = idb.fetchColumn(sqlFraga);
        }
        
        catch(InfException ex){
            System.out.println(ex.getMessage());
        }
        
        do{
            int slumpInt = random.nextInt(99);
            String slumpString = Integer.toString(slumpInt);
            pid = slumpString;
        }while (pids.contains(pid));
        
        return pid;
    }

    public void addPartner(String pid, String namn, String kontaktperson, String kontaktepost, String telefon, String adress, String branch, String stad) {
        if (anstalld.getAdmin(anstalldID)) {
            try {
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
    
    public void addPartneriProjekt(String projektID, String partnerID) {
        
        try {
            String sqlFraga = "INSERT INTO projekt_partner(pid, partner_pid) VALUES('" + projektID + "','" + partnerID + "')";
            idb.insert(sqlFraga);
            System.out.println("Partneruppgifter har lagts till");
        } catch (InfException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void deletePartneriProjekt(String projektID, String partnerID) {
        
        try {
            String sqlFraga = "DELETE FROM projekt_partner WHERE pid = '" + projektID + "' AND partner_pid = '" + partnerID + "'";
            idb.insert(sqlFraga);
        } catch (InfException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public ArrayList<HashMap<String, String>> getAllPartnersiProjekt() {
        
        ArrayList<HashMap<String, String>> partners = new ArrayList<>();
        
        try {
            String sqlFraga = "SELECT * FROM projekt_partner";
            partners = idb.fetchRows(sqlFraga);
               
        } catch (InfException ex) {
            System.out.println(ex.getMessage());
        }
        return partners;
    }

    public void changePartner(String pid, String namn, String kontaktperson, String kontaktepost, String telefon, String adress, String branch, String stad) {
        if (anstalld.getAdmin(anstalldID)) {
            try {
                String sqlFraga = "UPDATE partner SET namn = '" + namn + "', kontaktperson = '" + kontaktperson + "', kontaktepost = '" + kontaktepost + "', telefon = '" + telefon + "', adress = '" + adress + "', branch = '" + branch + "', stad = '" + stad + "WHERE pid = '" + pid + "'";
                idb.update(sqlFraga);
                System.out.println("Partneruppgifter har ändrats");
            } catch (InfException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("Endast administratörer kan ändra partners");
        }
    }

    public void removePartner(String pid) {
        if (anstalld.getAdmin(anstalldID)) {
            try {
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

    /*public ArrayList<HashMap<String, String>> listAllPartnersInProjectsAvdelning(String aid) {
        ArrayList<HashMap<String, String>> partnerlista = new ArrayList<>();

        try {
            String sqlFraga = "Select partner.* from partner join ans_proj ap on partner.pid = ap.pid join anstalld a on ap.pid = a.aid where a.aid = '" + aid + "'";
            System.out.println(sqlFraga);
            ArrayList<HashMap<String, String>> partners = idb.fetchRows(sqlFraga);

            partnerlista.addAll(partners);

        } catch (InfException ex) {
            System.out.println(ex.getMessage());
        }
        return partnerlista;
    }*/
    
    
    public String getPartnerIdFromProjektId(String projektID){
        
        String partnerID = "";

        try {
            String sqlFraga = "SELECT partner_pid FROM projekt_partner where pid = '" + projektID + "'";
            partnerID = idb.fetchSingle(sqlFraga);

        } catch (InfException ex) {
            System.out.println(ex.getMessage());
        }
        return partnerID;
    }
   

}
