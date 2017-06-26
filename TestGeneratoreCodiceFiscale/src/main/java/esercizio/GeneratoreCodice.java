package esercizio;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GeneratoreCodice {
	
	private String nome;
	private String cognome;
	private int giorno;
	private String mese;
	private String anno;
	private String sesso;
	private String comune;
	
	
	private String codiceFinale;
	
	
	
	
	public GeneratoreCodice(String nome, String cognome, int giorno, String mese, String anno, String sesso, String comune) {
		this.nome = nome;
		this.cognome = cognome;
		this.giorno = giorno;
		this.mese = mese;
		this.anno = anno;
		this.sesso = sesso;
		this.comune = comune;
	}

	public boolean isVocale(char carattere) {
		if(carattere == 'a' || carattere == 'e' || carattere == 'i'||
				carattere == 'o' || carattere == 'u')
			return true;
		return false;
	}
	
	public String separatoreConsonantieVocali(String parola) {
		char[] consonanti = new char[20];
		char[] vocali = new char[20];
		int j = 0;
		int z = 0;
		
		for (int i = 0; i < parola.length(); i++) {
			if(!isVocale(parola.charAt(i)))
				consonanti[j++] = parola.charAt(i);
			else vocali[z++] = parola.charAt(i);
		}
		String ritorno = String.valueOf(consonanti).concat( String.valueOf(vocali) );
		System.out.println(ritorno);
		return ritorno;
	}
		
	public String riempiConX(String parola) {
		for (int i = 0; i < 2 - parola.length(); i++) {
			parola.concat("x");
		}
		return parola;
	}
	
	public String  codiceCognome() {
		String codiceCognome;
		String codice = separatoreConsonantieVocali(this.cognome);
		if(codice.length() >= 3)
			codiceCognome = codice.substring(0, 3);
		else codiceCognome = riempiConX(codice);
		
		
		System.out.println(codiceCognome);
		return codiceCognome;
	}
	
	public String codiceNome() {
		String codiceNome;
		String codice = separatoreConsonantieVocali(this.nome);
		if(codice.length() >= 3)
			codiceNome = codice.substring(0, 3);
		else codiceNome = riempiConX(codice);
		
		System.out.println(codiceNome);
		return codiceNome;
	}
	
	public String codiceData() {
		String anno = this.anno.substring(2, this.anno.length());
		if( this.sesso.equals("f")) this.giorno += 40;
		
		String ritorno = anno + this.mese + this.giorno;
		System.out.println(ritorno);
		return ritorno;
	}
	
	public String codiceComune() {
		String comune = null; 
		
		DBconnector connector = new DBconnector();

		connector.connectToDB();

		ResultSet resultSet = connector.exeQuery("SELECT CodFisco FROM comuni WHERE Comune = '" + this.comune + "'");

		try {
			while(resultSet.next()) {
				comune = resultSet.getString("CodFisco");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return comune;
	}
	
	public String getCodiceFiscale() {
		
		codiceFinale = codiceCognome() + codiceNome() + codiceData() + codiceComune();
		
		return codiceFinale;
	}

}
