package es.studium.Practica4DIProgGestion;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Crea la vista Mensaje Error_Alta
 * @author Ismael
 */
public class DlgTicketsMensajeErrorAlta extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Constructor sin parámetros 
	 */
	public DlgTicketsMensajeErrorAlta() {
		setTitle("Mensaje Error_Alta");
		setBounds(100, 100, 244, 164);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblMensajeErrorAltaTickets = new JLabel("Se ha producido un error");
			lblMensajeErrorAltaTickets.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblMensajeErrorAltaTickets.setBounds(22, 23, 200, 44);
			contentPanel.add(lblMensajeErrorAltaTickets);
		}
		{
			JButton btnAceptarAltaTickets = new JButton("ACEPTAR");
			btnAceptarAltaTickets.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnAceptarAltaTickets.setBounds(67, 78, 93, 23);
			contentPanel.add(btnAceptarAltaTickets);
			btnAceptarAltaTickets.setActionCommand("OK");
			getRootPane().setDefaultButton(btnAceptarAltaTickets);
		}
	}
}
