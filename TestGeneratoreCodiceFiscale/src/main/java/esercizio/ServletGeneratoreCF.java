package esercizio;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
		String giornoStringa = (String) request.getParameter("giorno");
		int giorno= Integer.parseInt(giornoStringa);
		String mese = (String)  request.getParameter("mese");
		String anno = (String) request.getParameter("anno");
		String sesso = (String) request.getParameter("sesso");
		String comune = (String) request.getParameter("comune");
		
		
		Persona utente = new Persona(nome, cognome, giorno, mese, anno, sesso, comune);
		
		if(!cercaPersonaNelDatabase(request, response, crud, utente))
			salvaCodiceNelDatabase(request, response, crud, utente);
		
		crud.closeLogicaJPA();
			
//		try {
//			int parseInt = Integer.parseInt(attribute);
//		} catch (NumberFormatException e) {
//			RequestDispatcher rd = request.getRequestDispatcher("/RiconosciNumero.jsp");
//			String messaggio= "Il valore inserito NON è un numero!!";
//			request.setAttribute("eccezione", messaggio);
//			rd.forward(request, response);
//		}
		
	}
	
	public void salvaCodiceNelDatabase(HttpServletRequest request, HttpServletResponse response, ServicesCrud crud, Persona utente) throws ServletException, IOException {
		
		
		crud.jpaCreate(utente);
		
		String codice = utente.getCodiceFiscale();
		crud.jpaUpdate(utente);
		
		
		if(codice != null){
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			out.print("<html><body>");
			out.print("<h1>Il codice fiscale è: "+ codice + "</h1>");
			out.print("</body></html>");
		}
	}
	
	public boolean cercaPersonaNelDatabase(HttpServletRequest request, HttpServletResponse response, ServicesCrud crud, Persona utente) throws ServletException, IOException {
		
		List<Persona> utenti = crud.jpaRead("SELECT s FROM Persona s").getResultList();
		
		for(Persona cercata : utenti){
			if(utente.equivale(cercata)) {
				
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();

				out.print("<html><body>");
				out.print("<h1>Utente già esistente nel Database, il suo Cf è: " + cercata.getCodiceFiscaleCalcolato() + "</h1>");
				out.print("</body></html>");
				
				return true;
			}
		}
		return false;
	}
	
	public boolean verificaRegex(String nome, String cognome, int giorno, String anno) {
		
		if (nome != null && Pattern.matches(REG_EXPR_NOME, nome) &&
				cognome != null && Pattern.matches(REG_EXPR_NOME, cognome) &&
				giorno >0 && giorno <= 31 &&
				anno != null && Pattern.matches(REG_EXPR_DATA, anno))
			return true;
		
		return false;
	}

}