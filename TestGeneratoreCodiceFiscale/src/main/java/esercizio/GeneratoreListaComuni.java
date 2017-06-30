package esercizio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GeneratoreListaComuni {
	
	
	private HashMap<String, String> listaComuniEcodici;
	
	
	
	public GeneratoreListaComuni() {
		this.listaComuniEcodici = new HashMap<String, String>(); 
	}


	public HashMap<String, String> getListaComuni() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			
			//Ip usato al polo didattico 192.168.237.174:3306
			//192.168.1.6
			//localhost
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.1.6/CodiceFiscale", "root", "tirocinio123");

			Statement createStatement = connection.createStatement();
			
			ResultSet resultSet = createStatement.executeQuery("SELECT Comune, CodFisco FROM comuni");

			while(resultSet.next()) {
				
				//Key nome dei comuni, Value  Sigla del codice fiscale dei comuni
				this.listaComuniEcodici.put(resultSet.getString("Comune"), resultSet.getString("CodFisco"));
			}
			
		}
		
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		
		return this.listaComuniEcodici;
	}
	
	
	public void salvaListaComuni() {
		
		getListaComuni();
		
		try {
			File file = new File("listaComuni.txt");
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.listaComuniEcodici);

			oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public static HashMap<String, String> leggiListaComuni() {
		
		HashMap<String, String> listaComuniEcodici = new HashMap<String, String>();
		
		try {
			FileInputStream fis = new FileInputStream("listaComuni.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);

			listaComuniEcodici = (HashMap<String, String>) ois.readObject();


			ois.close();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return listaComuniEcodici;
	}
	
	public void  stampaListaCominiEcodici() {
		
		for( String key :  this.listaComuniEcodici.keySet()) {
			System.out.println(key + " | " + this.listaComuniEcodici.get(key));
		}
	}

}
