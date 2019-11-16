package thermometre.outils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import thermometre.outils.Temperature;
import thermometre.outils.OutilsTemperature;

public class RechercheTemperature {
	
	/**
	 * Liste contenant toutes les instances de temp�rature
	 */
	private static ArrayList<Temperature> listeTemp = new ArrayList<Temperature>();
	
	/**
	 * Entree courante
	 */
	private static Scanner entree = new Scanner(System.in);
	
	/**
	 * Fonction qui lit un fichier texte contenant
	 * des temp�rature et cr�� une instance pour chacune
	 * d'entre elle
	 */
	private static void initTemp() {    
		
		String ligne;    // ligne lue dans le fichier

		try ( // d�claration et cr�ation de l'objet fichier
			BufferedReader fichier = new BufferedReader(new FileReader("fichierTemp.txt"))) {

			while (((ligne = fichier.readLine()) != null)) {
				listeTemp.add(new Temperature(ligne));
		} 

			// fermeture du fichier automatique avec try-with-ressource          
	} catch (IOException ex) {      
		System.out.println("Bonjour");
			// probl�me d'acc�s au fichier
	}
		return;
	}
	
	/**
	 * Affiche la derni�re instance de la liste de temp�rature
	 */
	private static void tempActuelle() {
		System.out.println(listeTemp.get(listeTemp.size()-1).toString());
	}
	
	/**
	 * Affiche l'instance de temp�rature � une date donn�e
	 * @param date la date en question
	 */
	private static void tempAnterieure(String date) {
		
		for (int x = 0 ; x < listeTemp.size() ; x++) {
			
			if (listeTemp.get(x).getDate() == date) {
				System.out.println(listeTemp.get(x).toString());
			}
		}
	}
	
	/**
	 * Affiche la diff�rence de temp�rature entre deux intervalle
	 * @param date1
	 * @param date2
	 */
	private static void diffTemp(String date1, String date2) {
		
		int intervalle1 = 0,
		intervalle2 = 0;
		
		for (int x = 0 ; x < listeTemp.size() ; x++) {
			if (date1 == listeTemp.get(x).getDate()) {
				intervalle1 = x;
				break;
			} else if (date2 == listeTemp.get(x).getDate()) {
				intervalle1 = x;
				break;
			}
		}
		
		for (int x = 0 ; x < listeTemp.size() ; x++) {
			if (date1 == listeTemp.get(x).getDate()) {
				intervalle2 = x;
				break;
			} else if (date2 == listeTemp.get(x).getDate()) {
				intervalle2 = x;
				break;
			}
		}
		
		System.out.println(listeTemp.get(intervalle1).getTemp() - listeTemp.get(intervalle2).getTemp());
		
	}
	
	


	public static void main(String[] args) {
		initTemp();
		int choix = -1;
		do {
			entree.hasNextLine();
			System.out.println("1 : Temp�rature actuelle\n2 : Temp�rature ant�rieure\n3 : diff�rence de temp�rature\n0 : Quitter");
			choix = entree.hasNextInt() ? entree.nextInt() : -1;
			if (choix == 1) {
				tempActuelle();
			} else if (choix == 2) {
				tempAnterieure("01/11/2019 10:11:12 14,5");
			} else if (choix == 3) {
				diffTemp("01/11/2019 10:11:12 14,5", "04/11/2019 20:11:12 1,5");
			} else {
				System.out.println("Entr�e incorecte");
			}
		} while (choix != 0);
	}

}
