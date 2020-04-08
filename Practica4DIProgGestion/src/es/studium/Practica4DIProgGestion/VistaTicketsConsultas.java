package es.studium.Practica4DIProgGestion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class VistaTicketsConsultas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public VistaTicketsConsultas() {
		setTitle("Consultas_Tickets");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 263);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtConsultasTickets = new JTextArea();
		txtConsultasTickets.setBounds(10, 24, 414, 145);
		contentPane.add(txtConsultasTickets);
		
		JButton btnAceptarConsultasTickets = new JButton("ACEPTAR");
		btnAceptarConsultasTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAceptarConsultasTickets.setBounds(176, 192, 89, 23);
		contentPane.add(btnAceptarConsultasTickets);
	}
}
