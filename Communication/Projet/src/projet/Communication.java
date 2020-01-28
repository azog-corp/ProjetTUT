package projet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class Communication {

	private DatagramSocket dSocket ;
	private InetAddress iPserveur;
	private int portServeur;

	/**
	 * Ajoute a la fin du fichier
	 * @param temperatures
	 */
	private void ajouterFichier(ArrayList<String> temperatures) {
		// TODO Auto-generated method stub

	}

	public void remiseAzero(Context myContext) {
		RechercheTemperature.supprimerTemp(myContext);
		ajouterFichier(comRasp(date)); // date 1900
	}

	public ArrayList<String> comRasp(String date) {
		try {
			ArrayList<String> temperatures = new ArrayList<String>();
			int nbTemp = 0;
			byte[] buffer = new byte[100]; // message sous forme de tableau d'octet
			buffer = date.getBytes();
			dSocket.send(new DatagramPacket(buffer, buffer.length, 
					iPserveur, portServeur));
			dSocket.setSoTimeout(4000); // Temps d'attente réception max en millisecondes 
			dSocket.receive(new DatagramPacket(buffer,100));
			nbTemp = Integer.parseInt(new String(buffer));
			for(int i =0; i < nbTemp; i++) {
				dSocket.receive(new DatagramPacket(buffer,100));
				temperatures.add(new String(buffer));
			}
			return temperatures;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public majDerniereConnexion(Context myContext) {
		//lire fichier derniere co dans date
		ajouterFichier(comRasp(date));
		//mettre a jour fichier connexion avec date actuelle
	}
	
	coteServ(){
		//recoit
		//recupere la date
		//Recupere les dates apres celle envoye
		//ajoute a une arrayList -> lectureFichier
		//envoi la taille de l'arrayList
		//envoi toutes les temp
	}
	
	public ArrayList<String> lectureFichier(String date){
		//CONVERSION = fct convertir date en string
		try {
			String ligne;
			date = conversion(date);
			ArrayList<String> temp = new ArrayList<String>();
			BufferedReader fic = new BufferedReader(new FileReader(new File("temperatures.txt")));
			while((ligne = fic.readLine()) != null) {
				if(date.compareTo(conversion(ligne))) {
					temp.add(ligne);
				}
			}
		}catch(IOException e) {
			System.out.println(e);
		}
	}
}