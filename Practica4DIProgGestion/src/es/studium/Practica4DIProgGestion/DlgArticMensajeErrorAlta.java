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
 * Crea la vista Mensaje Error_Alta
 * @author Ismael
 */
public class DlgArticMensajeErrorAlta extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Constructor sin parámetros 
	 */
	public DlgArticMensajeErrorAlta() {
		setTitle("Mensaje Error_Alta");
		setBounds(100, 100, 244, 164);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblMensajeErrorAlta = new JLabel("Se ha producido un error");
			lblMensajeErrorAlta.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblMensajeErrorAlta.setBounds(22, 23, 200, 44);
			contentPanel.add(lblMensajeErrorAlta);
		}
		{
			JButton btnAceptarAlta = new JButton("ACEPTAR");
			btnAceptarAlta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnAceptarAlta.setBounds(67, 78, 91, 23);
			contentPanel.add(btnAceptarAlta);
			btnAceptarAlta.setActionCommand("OK");
			getRootPane().setDefaultButton(btnAceptarAlta);
		}
	}
}
