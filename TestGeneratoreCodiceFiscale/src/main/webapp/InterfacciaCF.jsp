<%@page import="java.util.HashMap"%>
<%@page import="esercizio.GeneratoreListaComuni"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="esercizio.ServicesCrud"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CF generator</title>
</head>
<body>


Generatore di codice Fiscale



<form action="genCF">
Nome: <input type="text" name="nome"><br>
Cognome: <input type="text" name="cognome"><br>

Data di nascita:

<select name="giorno">
<option value="0" selected>Giorno</option>

<% for(int giorno = 1; giorno <=31; giorno++) { %>
	<option value="<%=giorno %>"> <%=giorno %>  </option>
<% } %>
</select>

<select name="mese">
<option value="0" selected>Mese:</option>
<option value="a"> Gennaio  </option>
<option value="b"> Febbraio  </option>
<option value="c"> Marzo  </option>
<option value="d"> Aprile  </option>
<option value="e"> Maggio  </option>
<option value="h"> Giugno  </option>
<option value="l"> Luglio  </option>
<option value="m"> Agosto  </option>
<option value="p"> Settembre  </option>
<option value="r"> Ottobre  </option>
<option value="s"> Novembre  </option>
<option value="t"> Dicembre  </option>
</select>

<select name="anno">
<option value="0" selected>Anno</option>

<% for(int anno = 1920; anno <=2017; anno++) { %>
	<option value="<%=anno %>"> <%=anno %>  </option>
<% } %>
</select><br>
 
Sesso:
<select name="sesso">
<option value="0" selected>Sesso:</option>
<option value="m"> Maschio  </option>
<option value="f"> Femmina  </option>
</select> <br>

Comune di nascita:
<select name="comune">
<option value="0" selected>(Seleziona il comune:)</option>

<%-- <% GeneratoreListaComuni gn = new GeneratoreListaComuni();   gn.salvaListaComuni(); %> --%>

<% HashMap<String, String> listaComuni = GeneratoreListaComuni.leggiListaComuni(); %>

<% for(String key : listaComuni.keySet()) { %>
	<option value="<%=listaComuni.get(key) %>"> <%=key %>  </option>
<% } %>

</select> <br>

<input type="submit" value="Invia"><br>
</form>

</body>
</html>