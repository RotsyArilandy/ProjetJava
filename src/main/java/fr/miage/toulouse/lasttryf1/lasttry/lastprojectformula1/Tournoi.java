package fr.miage.toulouse.lasttryf1.lasttry.lastprojectformula1;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Tournoi {
    public String nomTournoi;
    public String codeTournoi;
    public ArrayList<Ecurie> ecuries;
    public ArrayList<GrandPrix> tabGrandPrix;
    public LocalDate DateDeb;
    public LocalDate DateFin;

    public ArrayList<Pilote> resulat;
    /**
     *  constructeur de tournoi
     */

    public Tournoi(String nom , String code, LocalDate d1, LocalDate d2) {
        this.nomTournoi = nom;
        this.codeTournoi = code;
        this.tabGrandPrix = new ArrayList<>();
        this.ecuries = new ArrayList<>();
        this.DateDeb = d1;
        this.DateFin = d2;
    }

    /**
     *  pour ajouter une écurie
      */
    public void AjouterEcurie(Ecurie e){

        ecuries.add(e);
    }

    /**
     * pour supprimer une écurie du tableau
     */
    public void SuppEcurie(Ecurie e){
        ecuries.remove(e);
    }

    /**
     *  Retourne un objet JSON représentant une instance de Tournoi.
     *
     */
    public JSONObject GetJSONObject(){
        if(this == null)
            return new JSONObject();

        try
        {
            JSONObject objet = new JSONObject();
            objet = objet.put("nomTournoi", this.nomTournoi);
            objet = objet.put("codeTournoi", this.codeTournoi);
            objet = objet.put("dateDeb", this.DateDeb);
            objet = objet.put("dateFin", this.DateFin);
            objet = objet.put("ecuries", this.ecuries);
            objet = objet.put("tabGrandPrix", this.tabGrandPrix);

            return objet;
        }
        catch (Exception e)
        {
            return new JSONObject();
        }
    }
    /**
     * Écrit une liste d'instances de Tournoi dans un fichier JSON.
     *
     * @param tournois une liste d'instances de Tournoi à écrire dans le fichier.
     */
    public static void WriteData(ArrayList<Tournoi> tournois){
        // on écrase le contenu du fichier json
        String [] result = new String[tournois.size()];
        for (int i = 0; i < result.length; i++) {
            Tournoi e = tournois.get(i);
            JSONObject obj = e.GetJSONObject();
            result[i] = obj.toString();
        }
        try {
            String finalString = "[";
            finalString += String.join(",", result);
            finalString+="]";
            File file = new File("data/tournoi.json");
            file.delete();
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            fw.write(finalString);
            fw.close();
        } catch (Exception e) {
            System.out.println("erreur write data" + e.getMessage());
        }
    }
    /**
     * Met à jour l'objet Tournoi correspondant au code de tournoi de l'objet Tournoi en paramètre dans une nouvelle liste d'objets Tournoi.
     *
     * @param tournoi l'objet Tournoi à mettre à jour
     * @return une nouvelle liste d'objets Tournoi avec l'objet Tournoi mis à jour
     */
    public static ArrayList<Tournoi> SetTournoiInList(Tournoi tournoi) // retourne aussi dans une nouvelle liste
    {
        ArrayList<Tournoi> tournois = GetTournoisFromList();
        for (int i = 0; i < tournois.size(); i++) {
            if(tournois.get(i).codeTournoi.equals(tournoi.codeTournoi))
            {
                tournois.set(i,tournoi);
                break; // pas la peine de continuer la boucle
            }
        }
        return tournois;
    }

    /**
     * Récupère l'objet Tournoi correspondant au code de tournoi spécifié.
     *
     * @param codeTournoi le code de tournoi à rechercher
     * @return l'objet Tournoi correspondant au code de tournoi spécifié, ou null si aucun objet Tournoi ne correspond.
     */
    public static Tournoi GetTournoiByCode(String codeTournoi){
        ArrayList<Tournoi> tournois = GetTournoisFromList();
        Tournoi resultat = null;

        for (Tournoi tour: tournois ) {
            if(tour.codeTournoi.equals(codeTournoi)) // si les codes matchent c'est le bon
            {
                resultat= tour;
                break;
            }
        }
        return resultat;
    }

    public static ArrayList<Tournoi> GetTournoisFromList()
    {
        // récupérer tout ce qui est dans tournois.json
        BufferedReader reader = null;
        String json = "";
        ArrayList<Tournoi> tournois = new ArrayList<>();
        try
        {
            reader = new BufferedReader(new FileReader("data/tournoi.json"));
            String line;
            while ((line = reader.readLine()) != null)
                json += line;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            // [{"nomTournoi":"tournoi12","codeTournoi":"TOU001","dateDeb":"2023-03-22","tabGrandPrix":[],"ecuries":[],"dateFin":"2023-03-24"}]
            // new JSONArray("[{"nomTournoi":"tournoi12","codeTournoi":"TOU001","dateDeb":"2023-03-22","tabGrandPrix":[],"ecuries":[],"dateFin":"2023-03-24"}]")
            JSONArray data = new JSONArray(json);
            for(int i=0; i < data.length(); i++)
            {
                JSONObject object = data.getJSONObject(i);
                Tournoi tour = new Tournoi(
                        object.getString("nomTournoi"),
                        object.getString("codeTournoi"),
                        LocalDate.parse(object.getString("dateDeb"), formatter),
                        LocalDate.parse(object.getString("dateFin"), formatter)
                        // il manque la gestion des arrays Ecuries
                        // attention c'est un array. c'est pas des objets
                );
                JSONArray listGrandPrixSousFormeArray = object.getJSONArray("tabGrandPrix");
                for (int j = 0; j < listGrandPrixSousFormeArray.length(); j++) {
                    JSONObject gpObject = listGrandPrixSousFormeArray.getJSONObject(j);
                    GrandPrix gp = new GrandPrix(
                            gpObject.getString("name"),
                            gpObject.getString("pays"),
                           null // à modifier
                           // LocalDate.parse(object.getString("date"), formatter)
                            );
                    tour.tabGrandPrix.add(gp);
                }
                JSONArray listEcuriesSousFormeArray = object.getJSONArray("ecuries");
                for (int j = 0; j < listEcuriesSousFormeArray.length(); j++) {
                    JSONObject gpObject = listEcuriesSousFormeArray.getJSONObject(j);
                    Ecurie ecurie = new Ecurie(
                            gpObject.getString("ecurie"), new Pilote("Paul"),new Pilote("Jacques")
                           // (Pilote) gpObject.get("pilote1"),
                           // (Pilote)gpObject.get("pilote2")
                    );
                    tour.ecuries.add(ecurie);
                }
                tournois.add(tour);
            }
        } catch (Exception e) {
            // alert "erreur sur la lecture du fichier
            System.out.println("erreur sur la lecture du fichier" + e.getMessage());
        }
        finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return tournois;
    }


}
