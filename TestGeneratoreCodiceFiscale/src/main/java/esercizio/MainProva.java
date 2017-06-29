package esercizio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class MainProva {
	
	private static final String REG_EXPR_DATA = "([0-9]+)";
	private static final String REG_EXPR_NOME = "([a-zA-Z]+)";

	public static void main(String[] args) {
		
		String nome = "Marco Antonio";
		String cognome = "Sardo";
		int giorno = 19;
		String anno = "1988";
//		
//		GeneratoreListaComuni gn = new GeneratoreListaComuni();
//		
//		gn.salvaListaComuni();
		
		
		if(!verificaRegex(nome, cognome, giorno, anno))
			System.out.println("Dati errati");
		else System.out.println("ok");
		
		
		String codNom = codiceNome(nome);
		
		System.out.println(codNom);
		
	}
	
	
	
	public static boolean verificaRegex(String nome, String cognome, int giorno, String anno) {
		
		if (nome != null && Pattern.matches(REG_EXPR_NOME, nome) &&
				cognome != null && Pattern.matches(REG_EXPR_NOME, cognome) /* &&
				giorno >0 && giorno <= 31 &&
				anno != null && Pattern.matches(REG_EXPR_DATA, anno)*/)
			return true;
		
		return false;
	}
	
	
	public static String codiceNome(String name) {
		String codiceNome;
		
		
		String codice = separatoreConsonantieVocali(name);
		
		if(seNomeHaAlmenoQuattroConsonanti(name.replaceAll(" ", "")))
			codice = codice.substring(0, 1) + codice.substring(2);
		
		if(codice.length() >= 3)
			codiceNome = codice.substring(0, 3);
		else codiceNome = riempiConX(codice);
		
		System.out.println(codiceNome);
		return codiceNome;
	}
	
	
	public static String separatoreConsonantieVocali(String parola) {
		
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
		
	public static String riempiConX(String parola) {
		for (int i = 0; i < 2 - parola.length(); i++) {
			parola.concat("x");
		}
		return parola;
	}
	
	public static boolean isVocale(char carattere) {
		if(carattere == 'a' || carattere == 'e' || carattere == 'i'||
				carattere == 'o' || carattere == 'u')
			return true;
		return false;
	}
	
	public static boolean seNomeHaAlmenoQuattroConsonanti(String parola) {
		int counter = 0;
		for (int i = 0; i < parola.length(); i++) {
			if(!isVocale(parola.charAt(i)))
				counter++;
			if(counter >=4)
				return true;
		}
		return false;
	}

}
	

