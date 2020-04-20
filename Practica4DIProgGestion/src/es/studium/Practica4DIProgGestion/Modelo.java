package es.studium.Practica4DIProgGestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Realiza la conexión con la BD tiendecita y contiene los métodos que interactúan con la BD
 * @author Ismael
 */
public class Modelo {

	/**
	 * Conecta con la BD 
	 * @return devuelve el resultado de la conexión a la BD
	 */
	public Connection conectar()
	{
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/tiendecita?autoReconnect=true&useSSL=false";
		String login ="root";
		String password = "Studium2018;";
		Connection connection = null;

		// Cargar el Driver
		try {
			Class.forName(driver);
		}
		catch(ClassNotFoundException e) {
			System.out.println("Se ha producido un error al cargar el Driver");
		}

		// Establecer la conexión con la base de datos
		try {
			connection = DriverManager.getConnection(url, login, password);
		}
		catch(SQLException e) {
			System.out.println("Se produjo un error al conectar a la Base de Datos");
		}

		return connection;
	}

	/**
	 * Realiza la desconexión a la BD 
	 * @param c conexión con la BD
	 */
	public void desconectar(Connection c)
	{
		try
		{
			if(c!=null)
			{
				c.close();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Consulta los datos de la tabla -articulos-
	 * @param c conexión con la BD
	 * @return devuelve los registros de la tabla -articulos- mostrando los campos idArticulos, descripcionArticulo, precioArticulo, cantidadArticulo
	 */
	public ResultSet consultaArticulos(Connection c)
	{
		// consulta = todos los datos de la tabla "articulos"
		String consultaArticulos = "SELECT idArticulos, descripcionArticulo, precioArticulo, cantidadArticulo FROM articulos";
		Statement statement = null;
		// Variable para guardar los datos de la consulta
		ResultSet rs = null;

		// Realizar la consutla
		try
		{
			statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = statement.executeQuery(consultaArticulos);
		}
		catch(SQLException e) {
			System.out.println("Error en la sentencia SQL");
		}
		return rs;	
	}

	/**
	 * Elimina un artículo de la tabla -articulos-
	 * @param c conexión con la BD
	 * @param idArticulos se le pasa el idArticulos a eliminar
	 * @return devuelve 0 si realiza la baja en la BD con éxito o 1 si no se ha realizado 
	 */
	public int eliminarArticulo(Connection c, int idArticulos)
	{
		String sentenciaSQL = "DELETE FROM articulos WHERE idArticulos = "+idArticulos;
		Statement statement  = null;
		try
		{
			statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			statement.executeUpdate(sentenciaSQL);
			return 0;
		}
		catch(SQLException e) {
			System.out.println("Error en la sentencia SQL");
			return 1;
		}
	}

	/**
	 * Modifica un artículo de la tabla -articulos- 
	 * @param c conexión con la BD
	 * @param idArticulos int correspondiente al idArticulos del artículo elegido
	 * @param descripcionArticulo String correspondiente a la descripción 
	 * @param precioArticulo double correspondiente al precio
	 * @param cantidadArticulo int correspondiente a la cantidad
	 * @return devuelve 0 si realiza la modificación en la BD con éxito o 1 si no se ha realizado 
	 */
	public int modificarArticulo(Connection c, int idArticulos, String descripcionArticulo, double precioArticulo, int cantidadArticulo)
	{
		String sentenciaSQL = "UPDATE articulos SET idArticulos = '"+idArticulos+"', descripcionArticulo = '"+descripcionArticulo+"', precioArticulo = '"+precioArticulo+"', cantidadArticulo = '"+cantidadArticulo+"' WHERE idArticulos="+idArticulos;	
		Statement statement  = null;
		try
		{
			statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			statement.executeUpdate(sentenciaSQL);
			// con el int devuelve una respuesta ok o error
			return 0;
		}
		catch(SQLException e) {
			System.out.println("Error en la sentencia SQL");
			return 1;
		}
	}

	/**
	 * Obtiene los datos de los registros de la tabla -articulos-
	 * @return devuelve un String con los datos 
	 */
	public String montarVistaArticulosConsultas()
	{				
		ResultSet consulArticulos = null;
		Connection conConsulta = null;
		conConsulta = conectar();
		consulArticulos = consultaArticulos(conConsulta);
		String resultado = "";
		try
		{
			String id;
			String descripcion;
			String precio;
			String cantidad;
			while(consulArticulos.next())
			{			
				id = consulArticulos.getInt("idArticulos")+"";
				descripcion = consulArticulos.getString("descripcionArticulo");
				precio = consulArticulos.getString("precioArticulo");
				cantidad = consulArticulos.getString("cantidadArticulo");
				// Modificar formato de la fecha fechaSum
				resultado = resultado +"\n"+id+" - "+descripcion+" - "+precio+" - "+cantidad;
			}
			desconectar(conConsulta);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return resultado;
	}

	/**
	 * Obtiene el último registro de la tabla -tickets-
	 * @param con conexión con la BD
	 * @return devuelve un int con el idTicket
	 */
	public int idTicket(Connection con)
	{
		int id = 0;
		String obtenerNumTicket = "SELECT idTicket FROM tickets ORDER BY 1 DESC LIMIT 1";
		Statement statement = null;
		//Variable para guardar los datos de la consulta
		ResultSet rs = null;

		// Realizar la consulta
		try
		{
			statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = statement.executeQuery(obtenerNumTicket);
			rs.next();
			id = rs.getInt("idTicket");
		}
		catch(SQLException e) {
			System.out.println("Error en la sentencia SQL  al obtener el último registro de la tabla incluyen");
		}
		return id;	
	}

	/**
	 * Obtiene el último registro de la tabla -incluyen-
	 * @param con conexión con la BD
	 * @return devuelve un int con el idIncluyen
	 */
	public int idIcluyen(Connection con)
	{
		int id = 0;
		String obtenerNumTicket = "SELECT idIncluyen FROM incluyen ORDER BY 1 DESC LIMIT 1";
		Statement statement = null;
		//Variable para guardar los datos de la consulta
		ResultSet rs = null;

		// Realizar la consulta
		try
		{
			statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = statement.executeQuery(obtenerNumTicket);
			rs.next();
			id = rs.getInt("idIncluyen");
		}
		catch(SQLException e) {
			System.out.println("Error en la sentencia SQL al obtener el último registro de la tabla incluyen");
		}
		return id;	
	}

	/**
	 * Modifica un registro de la tabla -tickets-
	 * @param c conexión con la BD
	 * @param idTicket int correspondiente al idTicket del ticket elegido
	 * @param importeTicket double correspondiente al importeTicket elegido
	 * @return devuelve 0 si realiza la modificación en la BD con éxito o 1 si no se ha realizado 
	 */
	public int modificarTickets(Connection c, int idTicket, double importeTicket)
	{
		String sentenciaSQL = "UPDATE tickets SET idTicket = '"+idTicket+"', importeTicket = '"+importeTicket+"' WHERE idTicket="+idTicket;	
		Statement statement  = null;
		try
		{
			statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			statement.executeUpdate(sentenciaSQL);
			// con el int devuelve una respuesta ok o error
			return 0;
		}
		catch(SQLException e) {
			System.out.println("Error en la sentencia SQL");
			return 1;
		}
	}

	/**
	 * Elimina un registro de la tabla -incluyen-
	 * @param c conexión con la BD
	 * @param idTicket int correspondiente al idTicket del ticket elegido
	 * @return devuelve 0 si elimina el registro en la BD con éxito o 1 si no se ha realizado 
	 */
	public int eliminarRegistroIncluyen(Connection c, int idTicket)
	{
		String sentenciaSQL = "DELETE FROM incluyen WHERE idTicketFK2 = "+idTicket;
		Statement statement  = null;
		try
		{
			statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			statement.executeUpdate(sentenciaSQL);
			return 0;
		}
		catch(SQLException e) {
			System.out.println("Error en la sentencia SQL al eliminar registro de la tabla incluyen");
			return 1;
		}
	}

	/**
	 * Elimina un registro de la tabla -tickets-
	 * @param c conexión con la BD
	 * @param idTicket int correspondiente al idTicket del ticket elegido
	 * @return devuelve 0 si elimina el registro en la BD con éxito o 1 si no se ha realizado 
	 */
	public int eliminarRegistroTicket(Connection c, int idTicket)
	{
		String sentenciaSQL = "DELETE FROM tickets WHERE numTicket = "+idTicket;
		Statement statement  = null;
		try
		{
			statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			statement.executeUpdate(sentenciaSQL);
			return 0;
		}
		catch(SQLException e) {
			System.out.println("Error en la sentencia SQL al eliminar registro de la tabla incluyen");
			return 1;
		}
	}
}