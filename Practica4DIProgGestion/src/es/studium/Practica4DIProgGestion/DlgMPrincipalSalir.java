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
 * Crea el diálogo Salir
 * @author Ismael
 */
public class DlgMPrincipalSalir extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Constructor sin parámetros 
	 */
	public DlgMPrincipalSalir() {
		setTitle("Salir");
		setBounds(100, 100, 271, 160);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblSalirMPrincipal = new JLabel("\u00BFQuieres salir del programa?");
			lblSalirMPrincipal.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblSalirMPrincipal.setBounds(25, 23, 220, 35);
			contentPanel.add(lblSalirMPrincipal);
		}
		{
			JButton btnSiSalir = new JButton("SI");
			btnSiSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			btnSiSalir.setFont(new Font("Tahoma", Font.PLAIN, 11));
			btnSiSalir.setBounds(56, 75, 60, 23);
			contentPanel.add(btnSiSalir);
			btnSiSalir.setActionCommand("OK");
			getRootPane().setDefaultButton(btnSiSalir);
		}
		{

			JButton btnNoSalir = new JButton("NO");
			btnNoSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnNoSalir.setBounds(155, 75, 60, 23);
			contentPanel.add(btnNoSalir);
			btnNoSalir.setActionCommand("Cancel");
		}
	}
}
