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

public class DlgArticMensajeErrorModif extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	public DlgArticMensajeErrorModif() {
		setTitle("Mensaje Error_Modificaciones");
		setBounds(100, 100, 244, 164);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblMensajeErrorModif = new JLabel("Se ha producido un error");
			lblMensajeErrorModif.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblMensajeErrorModif.setBounds(22, 23, 200, 44);
			contentPanel.add(lblMensajeErrorModif);
		}
		{
			JButton btnAceptarModif = new JButton("ACEPTAR");
			btnAceptarModif.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnAceptarModif.setBounds(68, 78, 92, 23);
			contentPanel.add(btnAceptarModif);
			btnAceptarModif.setActionCommand("OK");
			getRootPane().setDefaultButton(btnAceptarModif);
		}
	}
}
