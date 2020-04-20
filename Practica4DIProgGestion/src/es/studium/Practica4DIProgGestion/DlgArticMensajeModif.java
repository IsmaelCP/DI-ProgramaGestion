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
 * Crea la vista Mensaje_Modificaciones
 * @author Ismael
 */
public class DlgArticMensajeModif extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Constructor sin parámetros 
	 */
	public DlgArticMensajeModif() {
		setTitle("Mensaje_Modificaciones");
		setBounds(100, 100, 357, 152);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblMensajeModif = new JLabel("Modificaci\u00F3n realizada correctamente");
			lblMensajeModif.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblMensajeModif.setBounds(25, 22, 274, 39);
			contentPanel.add(lblMensajeModif);
		}
		{
			JButton btnVolverModif = new JButton("VOLVER");
			btnVolverModif.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnVolverModif.setBounds(135, 72, 88, 23);
			contentPanel.add(btnVolverModif);
			btnVolverModif.setActionCommand("OK");
			getRootPane().setDefaultButton(btnVolverModif);
		}
	}
}
