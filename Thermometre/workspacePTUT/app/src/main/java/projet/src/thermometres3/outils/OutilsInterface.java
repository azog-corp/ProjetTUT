package projet.src.thermometres3.outils;

import android.content.Context;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import projet.src.thermometres3.Erreur.ErreurIntervalle;

import static projet.src.thermometres3.outils.RechercheTemperature.conversion;
import static projet.src.thermometres3.outils.RechercheTemperature.getNouvelleTemp;

public class OutilsInterface {

    /**
     * Lit le fichier derniereCo.txt
     * @param myContext Contexte de l'application au moment de l'execution
     * @returnretourne la ligne en String de la derniere connexion
     */
    public static String getLastCo(Context myContext) {
        String derniereCo = myContext.getFilesDir()+"/derniereCo.txt";
        try (BufferedReader fic = new BufferedReader(new FileReader(new File(derniereCo)))) { // Lecture du fichier
            return fic.readLine(); // retourne la ligne date
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ""; //bouchon la date ne peut theoriquement pas etre fausse
    }

    /**
     * Creer le fichier derniere Connexion / ou le met a jour
     * @param myContext Contexte de l'application au moment de l'execution
     */
    public static void creerFichierLastCo(Context myContext) {
        String derniereCo = myContext.getFilesDir()+"/derniereCo.txt"; // defini le chemin du fichier
        try (BufferedWriter fic = new BufferedWriter(new FileWriter(new File(derniereCo)))) { // Lecture du fichier
            /* TODO REMETTRE APRES
            System.out.println(getDateActuelle()); // affichage debug
            fic.write(getDateActuelle()); // ecrit dans le fichier la date
            */
            System.out.println("CREATION LAST CO");
            fic.write("14/12/2019 18:00:00");
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*test -- test visuel
        System.out.println("TEST creerFichierLastCo");
        TestOutilsInterface.testLastCo(getApplicationContext());
         */
    }

    /**
     * Creer le fichier derniere Connexion / ou le met a jour
     * @param myContext Contexte de l'application au moment de l'execution
     *
    public static void majFichierLastCo(Context myContext) {
        String derniereCo = myContext.getFilesDir()+"/derniereCo.txt"; // defini le chemin du fichier
        try (BufferedWriter fic = new BufferedWriter(new FileWriter(new File(derniereCo)))) { // Lecture du fichier
            System.out.println(getDateActuelle()); // affichage debug
            fic.write(getDateActuelle()); // ecrit dans le fichier la date
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * Creer le fichier des temperatures
     * Lors du lancement de l'application pour la premiere fois
     * Lit le fichier des temperatures dans le dossier assets
     * Creer le fichier fichierTem.txt et y place les temperatures
     * Fonctionnement necessaire car il est impossible de modifier des fichiers present
     * dans le dossier assets. On creer donc les fichiers dans la memoire de l'appareil
     * android
     * @param myContext Contexte de l'application au moment de l'execution
     */
    public static void creerFichierTemperatures(Context myContext) {
        String ligne;
        try (BufferedWriter fichEcri = new BufferedWriter(new FileWriter(new File( // Ouvre / creer le fichier fichierTemp.txt
                myContext.getFilesDir() + "/fichierTemp.txt")))) {
                fichEcri.write(  ""); //Ecriture dans le fichier en memoire
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fonction qui permet de mettre a jour le fichier des temperatures
     * Pour cela ouvre le fichier contenant les nouvelles temperatures et met a jour
     * le fichier des temperatures
     * Cette fonction permet de simuler le fonctionnement finale de
     * l'application qui mettra ses donnees a jour grace a la BD
     * @param myContext Contexte de l'application au moment de l'execution
     *
    //TODO RESEAU
    public static void majFichierTemp(Context myContext) {
        String ligne;
        //BufferedWriter fichEcri = new BufferedWriter(new FileWriter("/fichierTemp.txt", true))) { //TODO maj en fin fichier
        System.out.println("MAJ fich temp");
        try (BufferedWriter fichEcri = new BufferedWriter(new FileWriter(new File(myContext.getFilesDir() + "/fichierTemp.txt")))){
            BufferedReader fichLir = new BufferedReader(new InputStreamReader(
                    myContext.getAssets().open(getNouvelleTemp()))); // ouverture fichier Nouvelles temperatures
            while ((ligne = fichLir.readLine()) != null) { // Lecture fichier nouvellesTemperatures
                fichEcri.write(ligne + "\n"); // ecrit dans le fichier temperature
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * Fonction qui permet d'obtenir la date a un instant
     * @return la date en String avec le format dd/MM/yyyy HH:mm:ss
     */
    public static String getDateActuelle() {
        Date date = new Date(); // Recupere la date Actuelle
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // defini le format de la date
        sdf.setTimeZone(TimeZone.getTimeZone("Europe/Paris")); // Defini la zone de la date pour que l'heure soit correcte
        return sdf.format(date);
    }
    /**
     * Fonction qui permet d'obtenir la date 2 jours avant
     * @return la date en String avec le format dd/MM/yyyy HH:mm:ss
     */
    public static String getDate2JoursPrec(){
        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // defini le format de la date
        Date date = new Date(System.currentTimeMillis() - (2 * DAY_IN_MS));
        sdf.setTimeZone(TimeZone.getTimeZone("Europe/Paris")); // Defini la zone de la date pour que l'heure soit correcte
        return sdf.format(date);
    }

}
