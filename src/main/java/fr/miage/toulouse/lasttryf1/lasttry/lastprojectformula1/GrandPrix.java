package fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1;

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
}
