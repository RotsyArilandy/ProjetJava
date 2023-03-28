package fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1;

public class Ecurie {

    private String ecurie;
    private Pilote  pilote1;
    private Pilote pilote2;

    public Ecurie(String ecurie, Pilote pilote1, Pilote pilote2) {
        this.ecurie = ecurie;
        this.pilote1 = pilote1;
        this.pilote2 = pilote2;
    }

    public String getEcurie() {
        return ecurie;
    }

    public Pilote getPilote1(){
        return pilote1;
    }

    public Pilote getPilote2(){
        return pilote2;
    }
}
