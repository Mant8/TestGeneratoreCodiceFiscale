package esercizio;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class genCF
 */
public class ServletGeneratoreCF extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String REG_EXPR_DATA = "([0-9]+)";
	private static final String REG_EXPR_NOME = "([a-zA-Z]+)";


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ServicesCrud crud = new ServicesCrud("jpa-cf");
		
		String nome = (String) request.getParameter("nome");
		String cognome = (String) request.getParameter("cognome");
		String giorno1 = (String) request.getParameter("giorno");
		int giorno;
		try{
			giorno = Integer.parseInt(giorno1);
		} catch(NumberFormatException e) {
			System.out.println("Non hai inserito un giorno valido");
			giorno = 0;
		}
		String mese = (String)  request.getParameter("mese");
		String anno = (String) request.getParameter("anno");
		String sesso = (String) request.getParameter("sesso");
		String comune = (String) request.getParameter("comune");
		
		
		Persona utente = new Persona(nome, cognome, giorno, mese, anno, sesso, comune);
		crud.jpaCreate(utente);
		
		String codice = utente.getCodiceFiscale();
		crud.jpaUpdate(utente);
		
		if(!verificaRegex(nome, cognome, giorno, anno)){
			
			RequestDispatcher requestDispatcherObj = request.getRequestDispatcher("/Risposta.jsp");
			
			request.setAttribute("flag", false);
			requestDispatcherObj.forward(request, response);
			
			
		}
		
		else if(codice != null){
			
			RequestDispatcher requestDispatcherObj = request.getRequestDispatcher("/Risposta.jsp");
			
			request.setAttribute("flag", true);
			request.setAttribute("codice", codice);
			requestDispatcherObj.forward(request, response);
			
		}
		
		
			
//		try {
//			int parseInt = Integer.parseInt(attribute);
//		} catch (NumberFormatException e) {
//			RequestDispatcher rd = request.getRequestDispatcher("/RiconosciNumero.jsp");
//			String messaggio= "Il valore inserito NON è un numero!!";
//			request.setAttribute("eccezione", messaggio);
//			rd.forward(request, response);
//		}
		
	}
	
	protected boolean verificaRegex(String nome, String cognome, int giorno, String anno) {
		
		if (nome != null && Pattern.matches(REG_EXPR_NOME, nome) &&
				cognome != null && Pattern.matches(REG_EXPR_NOME, cognome) &&
				giorno >0 && giorno <= 31 &&
				anno != null && Pattern.matches(REG_EXPR_DATA, anno))
			return true;
		
		return false;
	}

}