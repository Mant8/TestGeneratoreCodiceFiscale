package esercizio;

public class MainProva {

	public static void main(String[] args) {
		
		String nome = "Marco";
		String cognome = "Sardo";
		int giorno = 19;
		String mese = "T";
		String anno = "1988";
		String sesso = "f";
		String comune = null;
		
		Persona gc = new  Persona(nome, cognome, giorno, mese, anno, sesso, comune);
		
		String codice = gc.getCodiceFiscale();
		
		System.out.println(codice);
	}

}
