package fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1;

import org.json.JSONObject;

import java.time.LocalDate;
import java.util.Date;

public class GrandPrix {

    private String name;
    private String pays;
    private LocalDate date;

    public GrandPrix(String name, String pays, LocalDate date){
        this.name = name;
        this.pays = pays;
        this.date = date;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPays(){
        return this.pays;
    }

    public void setPays( String p) {
        this.pays = p;
    }

    public LocalDate getDate(){
        return this.date;
    }

    public void setDate( LocalDate d) {
        this.date = d;
    }

    public JSONObject GetJSONObject(){
        if(this == null)
            return new JSONObject();

        try
        {
            JSONObject objet = new JSONObject();
            objet = objet.put("name", this.name);
            objet = objet.put("pays", this.pays);
            objet = objet.put("date", this.date);
            return objet;
        }
        catch (Exception e)
        {
            return new JSONObject();
        }
    }
}

