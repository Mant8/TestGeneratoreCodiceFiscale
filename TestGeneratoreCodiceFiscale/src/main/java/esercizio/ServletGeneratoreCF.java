package esercizio;

import java.io.IOException;
import java.io.PrintWriter;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ServicesCrud crud = new ServicesCrud("jpa-cf");
		
		String nome = (String) request.getParameter("nome");
		String cognome = (String) request.getParameter("cognome");
		String giorno1 = (String) request.getParameter("giorno");
		int giorno = Integer.parseInt(giorno1);
		String mese = (String)  request.getParameter("mese");
		String anno = (String) request.getParameter("anno");
		String sesso = (String) request.getParameter("sesso");
		String comune = (String) request.getParameter("comune");
		
		
		Persona utente = new Persona(nome, cognome, giorno, mese, anno, sesso, comune);
		crud.jpaCreate(utente);
		
		String codice = utente.getCodiceFiscale();
		crud.jpaUpdate(utente);
		
		if(codice != null){
			
			RequestDispatcher requestDispatcherObj = request.getRequestDispatcher("/Risposta.jsp");
			
			request.setAttribute("codice", codice);
			requestDispatcherObj.forward(request, response);
			
		}
		
		
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		
//		
//		out.print("<html><body>");
		
		
//		try {
//			int parseInt = Integer.parseInt(attribute);
//		} catch (NumberFormatException e) {
//			RequestDispatcher rd = request.getRequestDispatcher("/RiconosciNumero.jsp");
//			String messaggio= "Il valore inserito NON è un numero!!";
//			request.setAttribute("eccezione", messaggio);
//			rd.forward(request, response);
//		}
		
		
		
//		out.print("Il Codice Fiscale è: " + codice);
//		out.print("</body></html>");
//		
	}

}