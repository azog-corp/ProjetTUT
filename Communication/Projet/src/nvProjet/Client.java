package nvProjet;

import java.net.InetAddress;
import java.util.ArrayList;

public class Client {
	InetAddress adresseIp;
	String message;
	int nbAck;
	boolean tempTraiter;
	boolean fini;
	ArrayList<String> paquets = new ArrayList<String>();
	
	public Client(InetAddress adresseIp,String message) {
		this.message = message;
		this.adresseIp = adresseIp;
	}
	
	public boolean envoiFini() {
		return paquets.size() == 0;
	}
	public boolean isVide() {
		return paquets.size() == 0;
	}
	
	public void paquetOk() {
		paquets.remove(0);
	}

	public int getNbAck() {
		return nbAck;
	}

	public void setNbAck(int nbAck) {
		this.nbAck = nbAck;
	}

	public boolean isTempTraiter() {
		return tempTraiter;
	}

	public void setTempTraiter(boolean tempTraiter) {
		this.tempTraiter = tempTraiter;
	}

	public ArrayList<String> getPaquets() {
		return paquets;
	}
	
	public String paquet() {
		return paquets.get(0);
	}

	public void setPaquets(ArrayList<String> paquets) {
		this.paquets = paquets;
	}

	public InetAddress getAdresseIp() {
		return adresseIp;
	}

	public void setAdresseIp(InetAddress adresseIp) {
		this.adresseIp = adresseIp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isFini() {
		return fini;
	}

	public void setFini(boolean fini) {
		this.fini = fini;
	}
	
	
}