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

public class DlgArticMensajeErrorBaja extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	public DlgArticMensajeErrorBaja() {
		setTitle("Mensaje Error_Baja");
		setBounds(100, 100, 251, 165);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblMensajeErrorBaja = new JLabel("Se ha producido un error");
			lblMensajeErrorBaja.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblMensajeErrorBaja.setBounds(28, 26, 211, 39);
			contentPanel.add(lblMensajeErrorBaja);
		}
		{
			JButton btnAceptarMensajErrorBaja = new JButton("ACEPTAR");
			btnAceptarMensajErrorBaja.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnAceptarMensajErrorBaja.setBounds(80, 76, 89, 23);
			contentPanel.add(btnAceptarMensajErrorBaja);
			btnAceptarMensajErrorBaja.setActionCommand("OK");
			getRootPane().setDefaultButton(btnAceptarMensajErrorBaja);
		}
	}
}
