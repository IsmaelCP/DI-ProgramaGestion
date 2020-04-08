package es.studium.Practica4DIProgGestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Modelo 
{
	public Modelo() {}

	// BASE DE DATOS

	// Método para conectar con la BD
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

	// Método para desconectar la conexión de la BD
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


	// ARTÍCULOS

	// Método para CONSULTAR los datos de la tabla "articulos"
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

	// Método para ELIMINAR un Artículo
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

	// Método para "obtener los datos" del artículo elegido en el choice chcArticuloModif1
	public ResultSet datosArticulo(Connection con, int idArticulos)
	{
		String consultaArticulos = "SELECT descripcionArticulo, precioArticulo, cantidadArticulo FROM articulos WHERE idArticulos = "+idArticulos;
		Statement statement = null;
		// Variable para guardar los datos de la consulta
		ResultSet rs = null;

		// Realizar la consutla
		try
		{
			statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = statement.executeQuery(consultaArticulos);
			rs.next();	
		}
		catch(SQLException e) {
			System.out.println("Error en la sentencia SQL");
		}
		return rs;	
	}

	// Método para Modificar un Artículo (Update) - MODIFICACIONES
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

	// Método para obtener los datos para VistaArticulosConsultas
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


	// TICKETS

	// Método para Consultar la descripción del artículo elegido en choice de Alta_Tickets
	public ResultSet descripArt(Connection con, int idArticulos)
	{
		String consultaArticulos = "SELECT descripcionArticulo, precioArticulo FROM articulos WHERE idArticulos = "+idArticulos;
		Statement statement = null;
		// Variable para guardar los datos de la consulta
		ResultSet rs = null;

		// Realizar la consulta
		try
		{
			statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = statement.executeQuery(consultaArticulos);
			rs.next();	
		}
		catch(SQLException e) {
			System.out.println("Error en la sentencia SQL");
		}
		return rs;	
	}

	// Método para obtener el último registro de la tabla "tickets"
	public int idTicket(Connection con)
	{
		int id = 0;
		String obtenerNumTicket = "SELECT numTicket FROM tickets ORDER BY 1 DESC LIMIT 1";
		Statement statement = null;
		//Variable para guardar los datos de la consulta
		ResultSet rs = null;

		// Realizar la consulta
		try
		{
			statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = statement.executeQuery(obtenerNumTicket);
			rs.next();
			id = rs.getInt("numTicket");
		}
		catch(SQLException e) {
			System.out.println("Error en la sentencia SQL  al obtener el último registro de la tabla incluyen");
		}
		return id;	
	}
	
	// Método para obtener el último registro de la tabla "incluyen"
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

	// Método para Modificar un Tickets (Update) - MODIFICACIONES
	public int modificarTickets(Connection c, int numTicket, double importeTicket)
	{
		String sentenciaSQL = "UPDATE tickets SET numTicket = '"+numTicket+"', importeTicket = '"+importeTicket+"' WHERE numTicket="+numTicket;	
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

	// Método para ELIMINAR de la tabla "incluyen" el último registro
	public int eliminarRegistroIncluyen(Connection c, int numTicket)
	{
		String sentenciaSQL = "DELETE FROM incluyen WHERE idTicketFK2 = "+numTicket;
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

	// Método para ELIMINAR de la tabla "tickets" el último registro
	public int eliminarRegistroTicket(Connection c, int numTicket)
	{
		String sentenciaSQL = "DELETE FROM tickets WHERE numTicket = "+numTicket;
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