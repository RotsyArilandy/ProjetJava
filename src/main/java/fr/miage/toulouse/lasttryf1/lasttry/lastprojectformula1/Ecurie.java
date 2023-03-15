package fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1;

public class Ecurie {

    private String ecurie;
    private String  pilote1;
    private String pilote2;

    public Ecurie(String ecurie, String pilote1, String pilote2) {
        this.ecurie = ecurie;
        this.pilote1 = pilote1;
        this.pilote2 = pilote2;
    }

    public String getEcurie() {
        return ecurie;
    }

    public String getPilote1() {
        return pilote1;
    }

    public String getPilote2() {
        return pilote2;
    }
}
