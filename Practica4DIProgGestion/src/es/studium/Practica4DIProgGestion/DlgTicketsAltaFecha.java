package es.studium.Practica4DIProgGestion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Crea la vista Mensaje Fecha Alta Tickets
 * @author Ismael
 */
public class DlgTicketsAltaFecha extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtFechaTck;

	/**
	 * Declara los objetos que controla
	 * VistaTicketsAlta
	 * DlgTicketsMensajeErrorFecha
	 * Modelo
	 */
	VistaTicketsAlta objVAltaTickets = new VistaTicketsAlta();
	DlgTicketsMensajeErrorFecha objDlgTicketsMensajeErrorFecha = new DlgTicketsMensajeErrorFecha();
	Modelo modelo = new Modelo();

	/**
	 * Constructor sin parámetros 
	 */
	public DlgTicketsAltaFecha() {
		setTitle("Mensaje Fecha Alta Tickets");
		setBounds(100, 100, 268, 184);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblIntrodFechaTck = new JLabel("Introduzca la fecha del Tickets");
			lblIntrodFechaTck.setBounds(19, 10, 214, 20);
			lblIntrodFechaTck.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(lblIntrodFechaTck);
		}
		{
			JLabel lblFechaTck = new JLabel("Fecha");
			lblFechaTck.setBounds(60, 41, 40, 20);
			lblFechaTck.setFont(new Font("Tahoma", Font.PLAIN, 16));
			contentPanel.add(lblFechaTck);
		}
		{
			txtFechaTck = new JTextField();
			txtFechaTck.setBounds(105, 41, 86, 20);
			txtFechaTck.setColumns(10);
			contentPanel.add(txtFechaTck);
			// Obtener la fecha actual
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			txtFechaTck.setText(dateFormat.format(date));		
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{

				JButton btnAceptarFecha = new JButton("ACEPTAR");
				btnAceptarFecha.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// String para guardar el dato del txt
						String fecha = txtFechaTck.getText();
						// Modificar el formato de la fecha
						String[] arrayfchTck = fecha.split("-");
						String fechaTckCambiada = arrayfchTck[2]+"-"+arrayfchTck[1]+"-"+arrayfchTck[0];
						// Conectar con la BD desde el Modelo
						Connection conn = modelo.conectar();
						// Sentencia para INSERTAR en la fecha en la tabla Tickets
						String sentenciaSQL = "INSERT INTO tickets(fechaTicket) VALUES ('"+fechaTckCambiada+"')";   
						Statement statement = null;
						try {
							statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
							statement.executeUpdate(sentenciaSQL);
							// Abre la Vista Tickets Alta
							objVAltaTickets.setVisible(true);
							// Cierra el Diálogo Fecha Tickets
							dispose();
						}catch(SQLException ex)
						{
							ex.printStackTrace();
							// En caso de Error muestra el Diálogo Mensaje Error_Fecha Tickets
							objDlgTicketsMensajeErrorFecha.setVisible(true);
						}
					}
				});
				btnAceptarFecha.setActionCommand("OK");
				buttonPane.add(btnAceptarFecha);
				getRootPane().setDefaultButton(btnAceptarFecha);
			}
			{
				JButton btnCancelarFecha = new JButton("CANCELAR");
				btnCancelarFecha.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelarFecha.setActionCommand("Cancel");
				buttonPane.add(btnCancelarFecha);
			}
		}
	}
}
