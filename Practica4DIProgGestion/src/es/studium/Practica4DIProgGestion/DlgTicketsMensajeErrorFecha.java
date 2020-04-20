package es.studium.Practica4DIProgGestion;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Crea la vista Mensaje Error_Fecha Tickets
 * @author Ismael
 */
public class DlgTicketsMensajeErrorFecha extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Constructor sin parámetros 
	 */
	public DlgTicketsMensajeErrorFecha() {
		setTitle("Mensaje Error_Fecha Tickets");
		setBounds(100, 100, 341, 164);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblMensajeErrorFecha = new JLabel("No ha introducido correctamente la fecha");
			lblMensajeErrorFecha.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblMensajeErrorFecha.setBounds(22, 22, 300, 44);
			contentPanel.add(lblMensajeErrorFecha);
		}
		{
			JButton btnAceptarFecha = new JButton("ACEPTAR");
			btnAceptarFecha.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnAceptarFecha.setBounds(129, 78, 91, 23);
			contentPanel.add(btnAceptarFecha);
			btnAceptarFecha.setActionCommand("OK");
			getRootPane().setDefaultButton(btnAceptarFecha);
		}
	}
}
