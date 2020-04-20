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
 * Crea el diálogo Mensaje_Alta
 * @author Ismael
 */
public class DlgArticMensajeAlta extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Constructor sin parámetros 
	 */
	public DlgArticMensajeAlta() {
		setTitle("Mensaje_Alta");
		setBounds(100, 100, 269, 158);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblMensajeAltaArtic = new JLabel("Alta realizada correctamente");
			lblMensajeAltaArtic.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblMensajeAltaArtic.setBounds(25, 22, 208, 39);
			contentPanel.add(lblMensajeAltaArtic);
		}
		{
			JButton btnVolverAltaArtic = new JButton("VOLVER");
			btnVolverAltaArtic.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnVolverAltaArtic.setBounds(85, 72, 82, 23);
			contentPanel.add(btnVolverAltaArtic);
			btnVolverAltaArtic.setActionCommand("OK");
			getRootPane().setDefaultButton(btnVolverAltaArtic);
		}
	}
}
