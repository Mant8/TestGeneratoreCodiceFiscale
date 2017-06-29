package esercizio;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Persona", schema = "CodiceFiscale")
public class Persona {
	
	@Id
	@GeneratedValue
	private Integer codicePersona;
	
	private String nome;
	private String cognome;
	private int giorno;
	private String mese;
	private String anno;
	private String sesso;
	private String comune;
	
	
	private String codiceFiscale;
	
	public Persona() {}
	
	
	public Persona(String nome, String cognome, int giorno, String mese, String anno, String sesso, String comune) {
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
		
		parola = parola.toLowerCase().replace(" ", "");
		
		String consonanti = " ";
		String vocali = " ";
		
		for (int i = 0; i < parola.length(); i++) {
			if(!isVocale(parola.charAt(i)))
				consonanti += String.valueOf(parola.charAt(i));
			else vocali += String.valueOf(parola.charAt(i));
		}
		
		
		String ritorno = consonanti+vocali;
		
		System.out.println(ritorno);
		return ritorno.replace(" ", "");
	}
		
	public String riempiConX(String parola) {
		for (int i = 0; i < 2 - parola.length(); i++) {
			parola.concat("x");
		}
		return parola;
	}
	
	public boolean seNomeHaAlmenoQuattroConsonanti(String parola) {
		int counter = 0;
		for (int i = 0; i < parola.length(); i++) {
			if(!isVocale(parola.charAt(i)))
				counter++;
			if(counter >=4)
				return true;
		}
		return false;
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
		
		if(seNomeHaAlmenoQuattroConsonanti(this.nome.replaceAll(" ", "")))
			codice = codice.substring(0, 1) + codice.substring(2);
		
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
		return this.comune;
	}
	
	public String getCodiceFiscale() {
		
		this.codiceFiscale = codiceCognome() + codiceNome() + codiceData() + codiceComune();
		
		this.codiceFiscale = this.codiceFiscale.toUpperCase();
		
		
		return this.codiceFiscale;
	}
	
	//-------------- equals
	
	
	public boolean equivale(Persona confrontata) {
		if( confrontata.getNome().equalsIgnoreCase(this.nome)
				&& confrontata.getCognome().equalsIgnoreCase(this.cognome)
				//&& confrontata.getGiorno() == this.giorno
				&& confrontata.getMese().equalsIgnoreCase(this.mese)
				&& confrontata.getAnno().equalsIgnoreCase(this.anno)
				&& confrontata.getSesso().equalsIgnoreCase(this.sesso)
				&& confrontata.getComune().equalsIgnoreCase(this.comune))
			return true;
		
		return false;
	}

	//---------------Getters and Setters
	
	

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public int getGiorno() {
		return giorno;
	}


	public void setGiorno(int giorno) {
		this.giorno = giorno;
	}


	public String getMese() {
		return mese;
	}


	public void setMese(String mese) {
		this.mese = mese;
	}


	public String getAnno() {
		return anno;
	}


	public void setAnno(String anno) {
		this.anno = anno;
	}


	public String getSesso() {
		return sesso;
	}


	public void setSesso(String sesso) {
		this.sesso = sesso;
	}


	public String getComune() {
		return comune;
	}


	public void setComune(String comune) {
		this.comune = comune;
	}


	public String getCodiceFiscaleCalcolato() {
		return this.codiceFiscale;
	}

	
	//--------------toString

	@Override
	public String toString() {
		return "Persona [codicePersona=" + codicePersona + ", nome=" + nome + ", cognome=" + cognome + ", giorno="
				+ giorno + ", mese=" + mese + ", anno=" + anno + ", sesso=" + sesso + ", comune=" + comune
				+ ", codiceFiscale=" + codiceFiscale + "]";
	}

}
