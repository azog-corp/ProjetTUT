package thermometre.outils;

public class OutilsTemperature {
	
	public static String convertir(float horaire) {

		StringBuilder conversion = new StringBuilder(5);                                              
		float heure,
		minute,		// minute apr�s conversion en heure et minute
		seconde;

		if (horaire >= 0 && horaire <= 1439) {
			heure = horaire / 60;
			minute = horaire % 60;
			seconde = horaire - heure - minute;
			if (heure < 10) {
				conversion.append("0");
			}	
			conversion.append(heure);
			conversion.append("h");
			if ( minute < 10) {
				conversion.append("0");
			}	
			conversion.append(minute);
			conversion.append("m");
			conversion.append(seconde);
		}
		return conversion.toString();
	}
	
	
	/**
	 * Convertit l'horaire argument en entier (resultat en minutes)
	 * Si l'horaire argument n'est pas valide, c'est l'entier -1 qui sera renvoye
	 * @param horaire  cha�ne contenant l'horaire a convertir
	 * @return un entier egal a la conversion en minutes de l'horaire argument
	 *                   ou bien a -1 si l'horaire argument n'est pas valide
	 */
	public static float convertir(String horaire) {
		float conversion = -1,
		heure,
		minute,
		seconde;

		if (estValide(horaire)) {
			heure = horaire.charAt(0) - '0';
			heure *= 10;
			heure +=(horaire.charAt(1) - '0');
			minute = horaire.charAt(3) - '0';
			minute *= 10;
			minute += (horaire.charAt(4) - '0');
			seconde = ((horaire.charAt(6) - '0') + (horaire.charAt(7) - '0'))/100;
			conversion = heure * 60 + minute + seconde;
		}
		return conversion;
	}
	
	/**
	 * Determine si une cha�ne de caract�res contient bien un horaire dans le format
	 * "hh:mm:ss"
	 * De plus, l'horaire doit etre un horaire de la journee (donc heure compris entre
	 * 0 et 23 et minute entre 0 et 59)
	 * @param aTester  cha�ne contenant l'horaire a tester
	 * @return un booleen egal a vrai si l'horaire teste est valide
	 */
	public static boolean estValide(String horaire) {
		boolean resultat = false; // vrai si l'horaire a tester est valide
		if (horaire.length() == 5) {
			if(horaire.charAt(2) == ':' && horaire.charAt(5) == ':') {
				switch (horaire.charAt(0)) {
					case '1':
					resultat = horaire.charAt(1) >= '0' && horaire.charAt(1) <= '9' 
					&& horaire.charAt(3) >= '0' && horaire.charAt(3) <= '5' 
					&& horaire.charAt(4) >= '0' && horaire.charAt(4) <= '9'
					&& horaire.charAt(6) >= '0' && horaire.charAt(6) <= '5'
					&& horaire.charAt(7) >= '0' && horaire.charAt(7) <= '9';
					break;
					case '2':
					resultat = horaire.charAt(1) >= '0' && horaire.charAt(1) <= '3' 
					&& horaire.charAt(3) >= '0' && horaire.charAt(3) <= '5' 
					&& horaire.charAt(4) >= '0' && horaire.charAt(4) <= '9'
					&& horaire.charAt(6) >= '0' && horaire.charAt(6) <= '5'
					&& horaire.charAt(7) >= '0' && horaire.charAt(7) <= '9';
					break;
					default:
					break;
				}
			}
		}
		return resultat;
	}
	
	

}
