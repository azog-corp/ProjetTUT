package nvProjet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class Serveur {
	/** Liste desclients a traiter */
	static ArrayList<Client> listeClient = new ArrayList<Client>();
	static DatagramSocket socket; 
	static byte[] buffer;
	static String message;
	static DatagramPacket paquet;
	static int comptThread;
	static int port = 4523;



	public static ArrayList<Client> getListeClient() {
		return listeClient;
	}

	public static void setListeClient(ArrayList<Client> listeClient) {
		Serveur.listeClient = listeClient;
	}
	public static void setClient(int index,Client element) {
		Serveur.listeClient.set(index, element);
	}


	public static void main(String[] args) {
		System.out.println("Serveur lancé !");
		int compteur = 0;
		try {
			socket = new DatagramSocket(4523);
		} catch (SocketException e1) {

		} 
		traiterClient();
		while(true) {
			try {
				buffer = new byte[100];
				System.out.println("INIT BUFFER" + new String(buffer));
				paquet = new DatagramPacket(buffer,100);
				System.out.println("ATTENTE MESSAGE");
				socket.receive(paquet);
				message = new String(buffer);
				System.out.println("Message :"+message);
				switch(message.charAt(0)) {
				case 'i':
					inscription();
					break;
				case 'a':
					acquittement(message);
					break;
				case 'f':
					terminer();
					break;
				case 'r': // r = retry rappel au serveur
					verifierPretAEnvoyer();
					break;
				}
				
				verifierFini();
				compteur++;
				affichageEtat();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void affichageEtat() {
		for(int i =0; i <listeClient.size(); i++) {
			System.out.println("Client : numero :" + i + listeClient.get(i).getAdresseIp()
					+ " Etat Temp traiter :" +listeClient.get(i).isTempTraiter()
					+ " Etat Fini :" + listeClient.get(i).isFini());
		}
	}

	private static void terminer() {
		for(int i=0; i < listeClient.size(); i++) {
			if(listeClient.get(i).getAdresseIp().equals(paquet.getAddress())) {
				System.out.println("FIN Client :" + listeClient.get(i).getAdresseIp());
				listeClient.get(i).setFini(true);
			}
		}
	}

	private static void verifierPretAEnvoyer() throws IOException{
		System.out.println("Verification pret a envoyer");
		for(int i=0; i < listeClient.size(); i++) {
			if(listeClient.get(i).getAdresseIp().equals(paquet.getAddress()))) {
				if(listeClient.get(i).isTempTraiter()
					&& !listeClient.get(i).isFini()) {
					System.out.println("Pret a envoyer :" + listeClient.get(i).getAdresseIp());
					envoyer(listeClient.get(i),listeClient.get(i).getAdresseIp());
				}
			}
		}
	}

	private static void envoyer(Client client,InetAddress adresseIp)  throws IOException{
		String msg = client.paquet();
		//TODO preparer requete et decouper
		System.out.println("ENVOI MESSAGE : " + msg);
		socket.send(new DatagramPacket(msg.getBytes(), msg.getBytes().length,
				adresseIp, port));
		//si tous les ack ok

	}

	private static void verifierFini() {
		System.out.println("Verification fini");
		for(int i=0; i < listeClient.size(); i++) {
			if(listeClient.get(i).isFini()) {
				System.out.println("Client fini :" + listeClient.get(i).getAdresseIp());
				listeClient.remove(i);
			}
		}

	}

	private static void traiterClient() {
		System.out.println("Creation thread");
		comptThread+=1;
		new Service(""+comptThread);
	}


	public static void inscription() {
		System.out.println("Message recu :" + message);
		String[] decomp = message.split("\\|");
		System.out.println("Message apres decomposition :" + decomp[1]);
		listeClient.add(new Client(paquet.getAddress(),decomp[1]));
		System.out.println("AJOUT CLIENT TAILLE ARRAYLIST :" +listeClient.size() );
		ack(paquet.getAddress());
	}

	private static void ack(InetAddress adresseIp) {
		String msg = "ACK";
		try {
			socket.send(new DatagramPacket(msg.getBytes(), msg.getBytes().length,
					adresseIp, port));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void acquittement(String message) throws IOException{
		System.out.println("ACK");
		for(int i=0; i < listeClient.size(); i++) {
			if(listeClient.get(i).getAdresseIp().equals(paquet.getAddress())) {
				System.out.println("PAQUET BON");
				listeClient.get(i).paquetOk();
				if(listeClient.get(i).envoiFini()) {
					System.out.println("TOUS LES PAQUETS ACK");
					listeClient.get(i).setFini(true);	
				}
			}
		}
	}

}