/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nvProjet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pierremarie.combalbe
 */
public class Service extends Thread{	

	static ArrayList<Client> clients;

	/**
	 * format prédéfinis pour les Dates
	 */
	private static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public Service(String name){
		super(name);
		System.out.println("statut du thread " + name + " = " +this.getState());
		this.start();
		System.out.println("statut du thread " + name + " = " +this.getState());

	}



	public void run(){
		System.out.println("RUN");
		while(true) {
			clients = Serveur.getListeClient();
			for(int i = 0; i < clients.size(); i++) {
				if(!clients.get(i).tempTraiter) {
					traitement(i,clients.get(i));
					Serveur.setClient(i,clients.get(i));
				}
			}
		}
	}

	public void traitement(int index,Client client) {
		try {
			System.out.println("TRAITEMENT");
			Date date = conversion(client.getMessage());
			ArrayList<String> tempValide = new ArrayList<String>();
			//tempValide = lectureFichier(date);
			tempValide.add("23/02/2020 18:00:00");
			tempValide.add("23/02/2020 18:00:10");
			//TODO preparer requete bouchon
			client.paquets.add(tempValide.get(0));
			client.paquets.add(tempValide.get(1));
			client.setTempTraiter(true); 
			clients.set(index, client);
			
		}catch(ParseException E) {

		}
	}

	public ArrayList<String> lectureFichier(Date date) throws ParseException{
		System.out.println("LECTURE FICHIER");
		try {
			String ligne;
			ArrayList<String> temp = new ArrayList<String>();
			BufferedReader fic = new BufferedReader(new FileReader(new File("temperatures.txt")));
			while((ligne = fic.readLine()) != null) {
				if(date.compareTo(conversion(ligne)) < 0) {
					temp.add(ligne);
				}
			}
			return temp;
		}catch(IOException e) {
			System.out.println(e);
		}
		return null;
	}

	/**
	 * Convertie un date en String Ã  une date en Date
	 * @param date
	 * @return la date formate avec le format dd/MM/yyyy hh:mm:ss
	 * @throws ErreurDate si le format de la date n'est pas valide
	 */
	public static Date conversion(String date) throws ParseException {
		Date dateFormate;
		dateFormate  = format.parse(date);
		return dateFormate;
	}

}

