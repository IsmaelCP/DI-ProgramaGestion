package es.studium.Practica4DIProgGestion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class VistaArticulosConsultas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea txtArticConsultas;

	// Objetos que vamos a controlar
	Modelo modelo = new Modelo();

	public VistaArticulosConsultas() {
		setTitle("Consultas_Art\u00EDculos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 263);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtArticConsultas = new JTextArea("Num. - Descripci�n - Precio(�) - Cantidad",5,100);
		txtArticConsultas.setBounds(10, 24, 414, 145);
		contentPane.add(txtArticConsultas);
		
		// Obtener datos Art�culos_Consulta de la tabla "art�culos"
		txtArticConsultas.setText(modelo.montarVistaArticulosConsultas());
		
		// Bot�n Aceptar. Acci�n --> cierra la Vista Consultas_Art�culos y vuelve al Men� Principal
		JButton btnAceptarConsultas = new JButton("ACEPTAR");
		btnAceptarConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAceptarConsultas.setBounds(176, 192, 89, 23);
		contentPane.add(btnAceptarConsultas);
	}
	
	// Recibe los datos actualizados del m�todo montarVistaArticulosConsultas() del Modelo
	public void actualizar()
	{
		txtArticConsultas.setText(modelo.montarVistaArticulosConsultas());
	}
}
