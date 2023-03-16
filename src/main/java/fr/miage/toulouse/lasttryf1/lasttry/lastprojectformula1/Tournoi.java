package fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1;

import java.time.LocalDate;
import java.util.ArrayList;

public class Tournoi {
    private String nomTournoi;
    private ArrayList<Ecurie> ecuries;
    private ArrayList<GrandPrix> tabGrandPrix;
    private LocalDate DateDeb;
    private LocalDate DateFin;
    public Tournoi(String nom , LocalDate d1, LocalDate d2) {
        this.nomTournoi = nom;
        ArrayList<GrandPrix> tabGrandPrix = new ArrayList<>();
        ArrayList<Ecurie> ecuries = new ArrayList<>();
        this.DateDeb = d1;
        this.DateFin = d2;
    }

    public void AjouterEcurie(Ecurie e){

        ecuries.add(e);
    }

    public void SuppEcurie(Ecurie e){
        ecuries.remove(e);
    }





}
