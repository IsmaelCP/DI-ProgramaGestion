package es.studium.Practica4DIProgGestion;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class DlgConsultaTickets extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtFchDesde;
	private JTextField txtFchHasta;

	public DlgConsultaTickets() {
		setTitle("Consulta Tickets");
		setBounds(100, 100, 290, 194);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblFchDesde = new JLabel("Fecha Desde:");
		lblFchDesde.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFchDesde.setBounds(25, 26, 116, 20);
		contentPanel.add(lblFchDesde);

		JLabel lblFchHasta = new JLabel("Fecha Hasta:");
		lblFchHasta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFchHasta.setBounds(25, 57, 116, 20);
		contentPanel.add(lblFchHasta);

		txtFchDesde = new JTextField();
		txtFchDesde.setBounds(127, 28, 94, 20);
		contentPanel.add(txtFchDesde);
		txtFchDesde.setColumns(10);

		txtFchHasta = new JTextField();
		txtFchHasta.setColumns(10);
		txtFchHasta.setBounds(127, 59, 94, 20);
		contentPanel.add(txtFchHasta);

		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Obtiene el informe de los Tickets
				try {
					// String para guardar el dato del txt
					String fecha1 = txtFchDesde.getText();
					String fecha2 = txtFchHasta.getText();

					// Modificar el formato de la fecha desde
					String[] arrayfch1 = fecha1.split("-");
					String fchD = arrayfch1[2]+"-"+arrayfch1[1]+"-"+arrayfch1[0];

					// Modificar el formato de la fecha hasta
					String[] arrayfch2 = fecha2.split("-");
					String fchH = arrayfch2[2]+"-"+arrayfch2[1]+"-"+arrayfch2[0];

					Map<String, Object> fechas = new HashMap<String, Object>();
					fechas.put("desde", fchD);
					fechas.put("hasta", fchH);

					// Compilar el informe generando un fichero jasper
					JasperCompileManager.compileReportToFile("tickets.jrxml");
					System.out.println("Fichero tickets.jasper generado");

					// Cargar el informe compilado
					JasperReport report = (JasperReport)JRLoader.loadObjectFromFile("tickets.jasper");

					// Conectar a la base de datos para obtener la información
					Class.forName("com.mysql.jdbc.Driver");
					String servidor = "jdbc:mysql://localhost:3306/tiendecita?useSSL=false";
					String usuarioDB = "root";
					String passwordDB = "Studium2018;";
					Connection conexion = DriverManager.getConnection(servidor, usuarioDB, passwordDB);

					// Completar el informe con los datos de la base de datos
					JasperPrint print = JasperFillManager.fillReport(report, fechas, conexion);

					// Mostrar el informe en JasperViewer
					JasperViewer.viewReport(print, false);

					// Exportar a pdf
					JasperExportManager.exportReportToPdfFile(print, "tickets.pdf");

					// Abrir el fichero PDF generado
					File path = new File ("tickets.pdf");
					Desktop.getDesktop().open(path);
				}
				catch(Exception e)
				{
					System.out.println("Error: " + e.toString());
				}
			}
		});
		btnAceptar.setBounds(97, 107, 89, 23);
		contentPanel.add(btnAceptar);
	}
}
