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
 * Crea el diálogo Mensaje_Baja
 * @author Ismael
 */
public class DlgArticMensajeBaja extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Constructor sin parámetros 
	 */
	public DlgArticMensajeBaja() {
		setTitle("Mensaje_Baja");
		setBounds(100, 100, 258, 163);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblMensajeBaja = new JLabel("Baja realizada correctamente");
			lblMensajeBaja.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblMensajeBaja.setBounds(22, 27, 239, 40);
			contentPanel.add(lblMensajeBaja);
		}
		{
			JButton btnVolverMensBaja = new JButton("VOLVER");
			btnVolverMensBaja.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnVolverMensBaja.setBounds(90, 78, 86, 23);
			contentPanel.add(btnVolverMensBaja);
			btnVolverMensBaja.setActionCommand("OK");
			getRootPane().setDefaultButton(btnVolverMensBaja);
		}
	}
}
