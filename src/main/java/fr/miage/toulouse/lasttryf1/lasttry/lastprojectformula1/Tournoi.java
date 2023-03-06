package fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1;

import java.util.ArrayList;

public class Tournoi {
    private String nomTournoi;
    private ArrayList<Ecurie> ecuries;
    private ArrayList<GrandPrix> tabGrandPrix;
    public Tournoi(String nom) {
        this.nomTournoi = nom;
        ArrayList<GrandPrix> tabGrandPrix = new ArrayList<>();
        ArrayList<Ecurie> ecuries = new ArrayList<>();
    }

    public void AjouterEcurie(Ecurie e){

        ecuries.add(e);
    }

    public void SuppEcurie(Ecurie e){
        ecuries.remove(e);
    }





}
