package es.studium.Practica4DIProgGestion;

import java.awt.Desktop;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class InformeArticulos {

	public InformeArticulos() {}
	
	// M�todo para obtener el informe de art�culos
	public void infoArtic() {
		try {
			// Compilar el informe generando un fichero jasper
			JasperCompileManager.compileReportToFile("articulos.jrxml");
			System.out.println("Fichero articulo.jasper generado");
			
			// Objeto para guardar par�metros necesarios para el informe
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			
			// Cargar el informe compilado
			JasperReport report = (JasperReport)JRLoader.loadObjectFromFile("articulos.jasper");
			
			// Conectar a la base de datos para obtener la informaci�n
			Class.forName("com.mysql.jdbc.Driver");
			String servidor = "jdbc:mysql://localhost:3306/proggestion?useSSL=false";
			String usuarioDB = "root";
			String passwordDB = "Studium2018;";
			Connection conexion = DriverManager.getConnection(servidor, usuarioDB, passwordDB);
			
			// Completar el informe con los datos de la base de datos
			JasperPrint print = JasperFillManager.fillReport(report, parametros, conexion);
			
			// Mostrar el informe en JasperViewer
			JasperViewer.viewReport(print, false);
			
			// Exportar a pdf
			JasperExportManager.exportReportToPdfFile(print, "articulos.pdf");
			
			// Abrir el fichero PDF generado
			File path = new File ("articulos.pdf");
			Desktop.getDesktop().open(path);
		}
		catch(Exception e)
		{
			System.out.println("Error: " + e.toString());
		}
	}


}	

