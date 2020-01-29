/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

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
	byte[] buffer;

	DatagramPacket paquet;

	DatagramSocket socket;

	Thread t;
	
	/**
	 * format pr�d�finis pour les Dates
	 */
	private static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public Service(String name,DatagramSocket socket,DatagramPacket paquet, byte[] buffer){
		super(name);
		this.socket = socket;
		this.paquet = paquet;
		this.buffer = buffer;
		System.out.println("statut du thread " + name + " = " +this.getState());
		this.start();
		System.out.println("statut du thread " + name + " = " +this.getState());

	}



	public void run(){
		System.out.println("RUN");
		String msg, ar; // Message reçu et accusé réception
		InetAddress ipClient;
		int portClient;
		try {
			msg = new String(buffer);
			msg = msg.trim();
			// Lire un paquet
			ipClient = paquet.getAddress();
			portClient = paquet.getPort();
			System.out.println("Recu : " + msg +" Thread : " + this.getName() 
			+"IP : " + ipClient + " PORT : " + portClient);
			Date date = conversion(msg);
			
			ArrayList<String> tempValide;
			tempValide = lectureFichier(date);
			ar = ""+tempValide.size();
			socket.send(new DatagramPacket(ar.getBytes(), ar.getBytes().length,
					ipClient, portClient));
			for(int i = 0; i < tempValide.size(); i++) {
				ar = tempValide.get(i);
				socket.send(new DatagramPacket(ar.getBytes(), ar.getBytes().length,
						ipClient, portClient));
			}

		} catch (IOException e) {
			System.out.println("nsm" + e);
		} catch(ParseException e) {
			//stub impossible
		}
	}

	public ArrayList<String> lectureFichier(Date date) throws ParseException{
		//CONVERSION = fct convertir date en string
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
	 * Convertie un date en String à une date en Date
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

