package fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1;

import org.json.JSONObject;

import java.security.Timestamp;

public class Pilote {
    private String nomPilote;
    public Timestamp temps;
   // private String nationalite;
    //private Date ddn; //La date de naissance du pilote

    public Pilote(String nom){
        this.nomPilote = nom;
    }

    //public Pilote(String nom, String nat, Date d){
        //this.nomPilote = nom;
        //this.nationalite= nat;
        //this.ddn = d;
   // }

    public String getNomPilote() {
        return nomPilote;
    }

    /*public String getNationalite() {
        return nationalite;
    }

    public Date getDdn() {
        return ddn;
    }*/

    public JSONObject GetJSONObject(){
        if(this == null)
            return new JSONObject();

        try
        {
            JSONObject objet = new JSONObject();
            objet = objet.put("nomPilote", this.nomPilote);
            objet = objet.put("temps", this.temps);
            return objet;
        }
        catch (Exception e)
        {
            return new JSONObject();
        }
    }
}
