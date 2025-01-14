package projet;

import java.net.InetAddress;
import java.util.ArrayList;

public class Client {
	/** Adresse IP du client */
	InetAddress adresseIp;
	/** Message envoyer par le client (date) */
	String message;
	/** Boolean qui définit si la requete du client est traité 
	 true = Temp traiter donc les paquets sont préparés
	 false = Traitement non fini*/
	boolean tempTraiter;
	/** Liste des paquets a envoyés */
	ArrayList<String> paquets = new ArrayList<String>();
	
	boolean nbPaquetRecu;
	
	/**Constructeur permettant de créer un noueau client*/
	public Client(InetAddress adresseIp,String message) {
		this.message = message;
		this.adresseIp = adresseIp;
	}
	
	/** Vérification qui regard s'il reste des paquets a envoyer
	 * S'il n 'y en a plus alors return true
	 */
	public boolean envoiFini() {
		return paquets.size() == 0;
	}
	/**Supprime le premier paquet de l'ArrayList des paquets
	 * Le premier paquet correspond au paquet envoyé
	 */
	public void paquetOk() {
		paquets.remove(0);
	}
	/**Getter du boolean tempTraiter
	 * return l'état du boolean pour le client
	 */
	public boolean isTempTraiter() {
		return tempTraiter;
	}

	/** Setter pour l'attribut tempTraiter
	 */
	public void setTempTraiter(boolean tempTraiter) {
		this.tempTraiter = tempTraiter;
	}

	/** Getter attribut paquets 
	* return l'ArrayList paquets*/
	public ArrayList<String> getPaquets() {
		return paquets;
	}
	
	public int getNbPaquets() {
		return paquets.size();
	}
	
	/** Fonction qui retourne le premier paquet de l'ArrayList paquets*/
	public String paquet() {
		return paquets.get(0);
	}

	/** Setter attribut paquets */
	public void setPaquets(ArrayList<String> paquets) {
		this.paquets = paquets;
	}

	/** Getter attribut adresseIp*/
	public InetAddress getAdresseIp() {
		return adresseIp;
	}
	/** Setter attribut adresseIp */
	public void setAdresseIp(InetAddress adresseIp) {
		this.adresseIp = adresseIp;
	}
	/** Getter attribut message */
	public String getMessage() {
		return message;
	}

	/**Setter attribut message */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Getter attribut nbPaquetRecu
	 * @return
	 */
	public boolean getNbPaquetRecu() {
		return nbPaquetRecu;
	}

	/**
	 * Setter nbPaquetRecu
	 * @param nbPaquetRecu boolean etat a definir
	 */
	public void setNbPaquetRecu(boolean nbPaquetRecu) {
		this.nbPaquetRecu = nbPaquetRecu;
	}
	
	
	
	
}
