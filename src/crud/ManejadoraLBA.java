package crud;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
	      String miSelect = "SELECT * FROM LBA_Equipos";

	      // Crear una connexión con el DriverManager
	      connexionBaseDatos = DriverManager.getConnection(sourceURL, usuario, password);
	      Statement sentencia = connexionBaseDatos.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	      // Establezco la base de datos que necesito como la activa
	      sentencia.execute(USE);
	      // Ejecuto mi sentencia SQL
	      ResultSet nombresEquipos = sentencia.executeQuery(miSelect);
	      
	      // Mostrar los datos del ResultSet
	      System.out.println("Código - Equipos - Fecha de Fundación - Ciudad - Código de Cancha");
	      System.out.println("--------------------------------------------------------------------");
	      while (nombresEquipos.next()) {
	    	  System.out.println(nombresEquipos.getString("codigo") + " - " + nombresEquipos.getString("nombre") + " - " + nombresEquipos.getString("fecha_fundacion") + " - " + nombresEquipos.getString("localidad") + " - " + nombresEquipos.getString("cod_cancha")); 
	      }
	      //Actualizo una fila para que mole más
	      //nombresEquipos.absolute(1);
	      //nombresEquipos.updateString("nombre","Ayyyylmao");
	      //nombresEquipos.updateRow();
	      
	      //Actualizo para dejarla igual (PRUEBAS)
	      //nombresEquipos.absolute(1);
	      //nombresEquipos.updateString("nombre","Ay Payo");
	      //nombresEquipos.updateRow();
	      
	      //Veo los resultados de la actualización (yendome antes a la primera fila)
	      //nombresEquipos.first();
	      //while (nombresEquipos.next()) {
	    	//  System.out.println(nombresEquipos.getString("codigo") + " - " + nombresEquipos.getString("nombre") + " - " + nombresEquipos.getString("fecha_fundacion") + " - " + nombresEquipos.getString("localidad") + " - " + nombresEquipos.getString("cod_cancha")); 
	      //}
	      
	      /*
	      //Inserción de una fila nueva
	      //Muevo a la fila de inserción
	      nombresEquipos.moveToInsertRow();
	      //Inserto datos
	      nombresEquipos.updateString("codigo","EMM");
	      nombresEquipos.updateString("nombre","Equipo Mas Molon");
	      Calendar calendar = new GregorianCalendar(2010,10,10);
	      Date date = new Date(calendar.getTimeInMillis());
	      nombresEquipos.updateDate("fecha_fundacion",date);
	      nombresEquipos.updateString("localidad","Mordor");
	      nombresEquipos.updateInt("cod_cancha",1);
	      //Hago la inserción de esos datos
	      nombresEquipos.insertRow();
	      //Vuelvo a la fila anterior a la inserción
	      nombresEquipos.moveToCurrentRow();
	      
	      //Muestro resultados
	      nombresEquipos.first();
	      while (nombresEquipos.next()) {
	    	  System.out.println(nombresEquipos.getString("codigo") + " - " + nombresEquipos.getString("nombre") + " - " + nombresEquipos.getString("fecha_fundacion") + " - " + nombresEquipos.getString("localidad") + " - " + nombresEquipos.getString("cod_cancha")); 
	      }
	      */
	      
	      //Borro una fila
	      nombresEquipos.first();
	      while(nombresEquipos.next()) {
	    	  String idABorrar = nombresEquipos.getString("codigo");
	    	  if(idABorrar.equals("EMM")) {
	    		  nombresEquipos.deleteRow();
	    	  }
	      }	      
	      
	      //Muestro resultados
	      nombresEquipos.first();
	      while (nombresEquipos.next()) {
	    	  System.out.println(nombresEquipos.getString("codigo") + " - " + nombresEquipos.getString("nombre") + " - " + nombresEquipos.getString("fecha_fundacion") + " - " + nombresEquipos.getString("localidad") + " - " + nombresEquipos.getString("cod_cancha")); 
	      }
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
