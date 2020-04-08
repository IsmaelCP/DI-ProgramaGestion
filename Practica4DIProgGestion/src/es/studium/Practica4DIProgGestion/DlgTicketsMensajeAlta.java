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

public class DlgTicketsMensajeAlta extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	public DlgTicketsMensajeAlta() {
		setTitle("Mensaje_Alta");
		setBounds(100, 100, 269, 158);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblMensajeAltaTickets = new JLabel("Alta realizada correctamente");
			lblMensajeAltaTickets.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblMensajeAltaTickets.setBounds(25, 22, 208, 39);
			contentPanel.add(lblMensajeAltaTickets);
		}
		{
			JButton btnVolverAltaTickets = new JButton("VOLVER");
			btnVolverAltaTickets.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnVolverAltaTickets.setBounds(85, 72, 88, 23);
			contentPanel.add(btnVolverAltaTickets);
			btnVolverAltaTickets.setActionCommand("OK");
			getRootPane().setDefaultButton(btnVolverAltaTickets);
		}
	}
}
