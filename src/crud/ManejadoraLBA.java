package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManejadoraLBA {
	
	/* void listaEquiposLBA()
	 * 
	 * Descripción: Procedimiento que muestra por pantalla los equipos de nuestra liga
	 * Pre: Nada
	 * Entrada: Nada
	 * Salida: Nada
	 * Post: Se muestran en pantalla los datos de los equipos
	 */
	public void listaEquiposLBA() {
		final String USE = "Use [LBA2]";
		Connection connexionBaseDatos = null;
		// Carga el driver
	    try {

	      // Carga la clase del driver
	      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

	      // Define the data source for the driver
	      String sourceURL = "jdbc:sqlserver://localhost";
	      String usuario = "prueba";
	      String password = "123";
	      String miSelect = "SELECT nombre, fecha_fundacion, localidad FROM LBA_Equipos";

	      // Crear una connexión con el DriverManager
	      connexionBaseDatos = DriverManager.getConnection(sourceURL, usuario, password);
	      Statement sentencia = connexionBaseDatos.createStatement();
	      // Establezco la base de datos que necesito como la activa
	      sentencia.execute(USE);
	      // Ejecuto mi sentencia SQL
	      ResultSet nombresEquipos = sentencia.executeQuery(miSelect);
	      
	      // Mostrar los datos del ResultSet
	      System.out.println("Equipos 		-		 Fecha de Fundación 		- 		Ciudad");
	      System.out.println("----------------------------------------------------------------");
	      while (nombresEquipos.next())
	    	  System.out.println(nombresEquipos.getString("nombre") + " - " + nombresEquipos.getString("fecha_fundacion") + " - " +	nombresEquipos.getString("localidad"));	      
	    } 
	      catch (ClassNotFoundException cnfe) {
	      System.err.println(cnfe);
	    } 
	      catch (SQLException sqle) {
	      System.err.println(sqle);
	    } finally {
	    	//Cerrar conexión
	    	try {
				connexionBaseDatos.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	}	
}
