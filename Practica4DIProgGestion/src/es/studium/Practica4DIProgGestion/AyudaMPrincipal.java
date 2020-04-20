package es.studium.Practica4DIProgGestion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Crea el diálogo Ayuda
 * @author Ismael
 */
public class AyudaMPrincipal extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Constructor sin parámetros 
	 */
	public AyudaMPrincipal() {
		setTitle("Ayuda");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSalirAyudaMPrinc = new JButton("SALIR");
				btnSalirAyudaMPrinc.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnSalirAyudaMPrinc.setActionCommand("OK");
				buttonPane.add(btnSalirAyudaMPrinc);
				getRootPane().setDefaultButton(btnSalirAyudaMPrinc);
			}
		}
	}
}
