package esercizio;

import java.util.regex.Pattern;

public class MainProva {
	
	private static final String REG_EXPR_DATA = "([0-9]+)";
	private static final String REG_EXPR_NOME = "([a-zA-Z]+)";

	public static void main(String[] args) {
		
		String nome = "Marco";
		String cognome = "Sardo";
		int giorno = 19;
		String anno = "1988";
		
		
		if(!verificaRegex(nome, cognome, giorno, anno))
			System.out.println("Dati errati");
		else System.out.println("ok");
	}
	
	
	
	public static boolean verificaRegex(String nome, String cognome, int giorno, String anno) {
		
		if (nome != null && Pattern.matches(REG_EXPR_NOME, nome) &&
				cognome != null && Pattern.matches(REG_EXPR_NOME, cognome) /* &&
				giorno >0 && giorno <= 31 &&
				anno != null && Pattern.matches(REG_EXPR_DATA, anno)*/)
			return true;
		
		return false;
	}

}
	

