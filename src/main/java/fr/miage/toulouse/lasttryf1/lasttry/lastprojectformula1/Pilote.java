package fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1;

import org.json.JSONObject;


public class Pilote  implements Comparable<Pilote>{
    private String nomPilote;
    public long temps;
   // private String nationalite;
    //private Date ddn; //La date de naissance du pilote

    /**
     * constructeur de pilote
     * @param nom
     */
    public Pilote(String nom){
        this.nomPilote = nom;
    }

    //public Pilote(String nom, String nat, Date d){
        //this.nomPilote = nom;
        //this.nationalite= nat;
        //this.ddn = d;
   // }

    public long getTemps( ) {
        return this.temps ;
    }

    public void setTemps(long temps) {
        this.temps = temps;
    }

    /**
     * recupère le nom de pilote
     * @return
     */
    public String getNomPilote() {
        return nomPilote;
    }

    /*public String getNationalite() {
        return nationalite;
    }

    public Date getDdn() {
        return ddn;
    }*/

    /**
     * recupère les pilotes et le temps dans le dossier de sauvgarde json
     * @return
     */
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

    /**
     * fait le tri descendant de tout les pilotes
     * @param p2 the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Pilote p2) {
        //tri desc
        if (this.temps < p2.temps)
            return -1;
        if (p2.temps == this.temps)
            return 0;
        return 1;
    }
}
